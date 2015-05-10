package pony.log.layout;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import pony.log.ILayout;
import pony.log.LogEvent;
import pony.log.LogLevel;
import pony.util.Constants;
import pony.util.Transform;

public class HtmlLayout extends AbstractLayout {
	 private static final int BUF_SIZE = 256;

    private static final String TRACE_PREFIX = "<br />&nbsp;&nbsp;&nbsp;&nbsp;";

    private static final String REGEXP = Constants.LINE_SEPARATOR.equals("\n") ? "\n" : Constants.LINE_SEPARATOR + "|\n";

    private static final String DEFAULT_TITLE = "Pony Log Messages";

    private static final String DEFAULT_CONTENT_TYPE = "text/html";

    public static final String DEFAULT_FONT_FAMILY = "arial,sans-serif";

//	    private final long jvmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
    
    public static final ILayout DEFAULT_HTML_LAYOUT = new HtmlLayout();

    private final String title;

    private final String contentType;
    
    /**Possible font sizes */
    public static enum FontSize {
        SMALLER("smaller"), XXSMALL("xx-small"), XSMALL("x-small"), SMALL("small"), MEDIUM("medium"), LARGE("large"),
        XLARGE("x-large"), XXLARGE("xx-large"),  LARGER("larger");

        private final String size;

        private FontSize(final String size) {
            this.size = size;
        }

        public String getFontSize() {
            return size;
        }

        public static FontSize getFontSize(final String size) {
            for (final FontSize fontSize : values()) {
                if (fontSize.size.equals(size)) {
                    return fontSize;
                }
            }
            return SMALL;
        }

        public FontSize larger() {
            return this.ordinal() < XXLARGE.ordinal() ? FontSize.values()[this.ordinal() + 1] : this;
        }
    }

    private final String font;
    private final String fontSize;
    private final String headerSize;
    
    public HtmlLayout(){
    	super(Charset.forName("GBK"));
    	this.title = DEFAULT_TITLE;
    	this.contentType = DEFAULT_CONTENT_TYPE;
    	this.font = DEFAULT_FONT_FAMILY;
    	this.headerSize = FontSize.XLARGE.getFontSize();
    	this.fontSize = FontSize.MEDIUM.getFontSize();
    }

    public HtmlLayout(
    		final String title, 
    		final String contentType, 
    		final Charset charset,
            final String font, 
            final String fontSize, 
            final String headerSize) {
        super(charset);
        this.title = title;
        this.contentType = addCharsetToContentType(contentType);
        this.font = font;
        this.fontSize = fontSize;
        this.headerSize = headerSize;
    }
    
    private String addCharsetToContentType(final String contentType) {
        if (contentType == null) {
            return DEFAULT_CONTENT_TYPE + "; charset=" + getCharset();
        }
        return contentType.contains("charset") ? contentType : contentType + "; charset=" + getCharset();
    }


	@Override
	public String format(final LogEvent _event) {
		final StringBuilder sbuf = new StringBuilder(BUF_SIZE);

        sbuf.append(Constants.LINE_SEPARATOR).append("<tr>").append(Constants.LINE_SEPARATOR);

        sbuf.append("<td title=\"Level\">");
        if (_event.getLevel().equals(LogLevel.DEBUG)) {
            sbuf.append("<font color=\"#339933\">");
            sbuf.append(Transform.escapeHtmlTags(String.valueOf(_event.getLevel())));
            sbuf.append("</font>");
        } else if (_event.getLevel().getValue() >= LogLevel.WARN.getValue()) {
            sbuf.append("<font color=\"#993300\"><strong>");
            sbuf.append(Transform.escapeHtmlTags(String.valueOf(_event.getLevel())));
            sbuf.append("</strong></font>");
        } else {
            sbuf.append(Transform.escapeHtmlTags(String.valueOf(_event.getLevel())));
        }
        sbuf.append("</td>").append(Constants.LINE_SEPARATOR);

        String escapedLogger = Transform.escapeHtmlTags(_event.getLoggerName());
        if (escapedLogger.isEmpty()) {
            escapedLogger = "root";
        }
        sbuf.append("<td title=\"").append(escapedLogger).append(" logger\">");
        sbuf.append(escapedLogger);
        sbuf.append("</td>").append(Constants.LINE_SEPARATOR);

        sbuf.append("<td title=\"Message\">");
        sbuf.append(Transform.escapeHtmlTags(_event.getMessage()).replaceAll(REGEXP, "<br />"));
        sbuf.append("</td>").append(Constants.LINE_SEPARATOR);
        sbuf.append("</tr>").append(Constants.LINE_SEPARATOR);

        final Throwable throwable = _event.getThrown();
        if (throwable != null) {
            sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : ").append(fontSize);
            sbuf.append(";\" colspan=\"6\">");
            formatThrowable(throwable, sbuf);
            sbuf.append("</td></tr>").append(Constants.LINE_SEPARATOR);
        }

        return sbuf.toString();
	}

	@Override
	public void formatThrowable(final Throwable _thrown, final StringBuilder sbuf) {
		final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        try {
        	_thrown.printStackTrace(pw);
        } catch (final RuntimeException ex) {
            // Ignore the exception.
        }
        pw.flush();
        final LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
        final ArrayList<String> lines = new ArrayList<String>();
        try {
          String line = reader.readLine();
          while (line != null) {
            lines.add(line);
            line = reader.readLine();
          }
        } catch (final IOException ex) {
            if (ex instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            lines.add(ex.toString());
        }
        boolean first = true;
        for (final String line : lines) {
            if (!first) {
                sbuf.append(TRACE_PREFIX);
            } else {
                first = false;
            }
            sbuf.append(Transform.escapeHtmlTags(line));
            sbuf.append(Constants.LINE_SEPARATOR);
        }
	}
	
	@Override
	public byte[] getHeader() {
		final StringBuilder sbuf = new StringBuilder();
        sbuf.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" ");
        sbuf.append("\"http://www.w3.org/TR/html4/loose.dtd\">");
        sbuf.append(Constants.LINE_SEPARATOR);
        sbuf.append("<html>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<head>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<meta charset=\"").append(getCharset()).append("\"/>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<title>").append(title).append("</title>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<style type=\"text/css\">").append(Constants.LINE_SEPARATOR);
        sbuf.append("<!--").append(Constants.LINE_SEPARATOR);
        sbuf.append("body, table {font-family:").append(font).append("; font-size: ");
        sbuf.append(headerSize).append(";}").append(Constants.LINE_SEPARATOR);
        sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}").append(Constants.LINE_SEPARATOR);
        sbuf.append("-->").append(Constants.LINE_SEPARATOR);
        sbuf.append("</style>").append(Constants.LINE_SEPARATOR);
        sbuf.append("</head>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">").append(Constants.LINE_SEPARATOR);
        sbuf.append("<hr size=\"1\" noshade>").append(Constants.LINE_SEPARATOR);
        sbuf.append("Log session start time " + new java.util.Date() + "<br>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<br>").append(Constants.LINE_SEPARATOR);
        sbuf.append(
            "<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">");
        sbuf.append(Constants.LINE_SEPARATOR);
        sbuf.append("<tr>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<th>Time</th>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<th>Thread</th>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<th>Level</th>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<th>Logger</th>").append(Constants.LINE_SEPARATOR);
       
        sbuf.append("<th>Message</th>").append(Constants.LINE_SEPARATOR);
        sbuf.append("</tr>").append(Constants.LINE_SEPARATOR);
        return sbuf.toString().getBytes(getCharset());
	}
	
	@Override
	public byte[] getFooter() {
		final StringBuilder sbuf = new StringBuilder();
        sbuf.append("</table>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<br>").append(Constants.LINE_SEPARATOR);
        sbuf.append("</body></html>");
        return sbuf.toString().getBytes(getCharset());
	}

}
