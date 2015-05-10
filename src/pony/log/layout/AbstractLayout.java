package pony.log.layout;

import java.nio.charset.Charset;

import pony.log.ILayout;
import pony.log.LogEvent;

public abstract class AbstractLayout implements ILayout {
	
	protected final byte[] header;
	protected final byte[] footer;
	protected final Charset charset;
	
	protected static final Charset DEFAULT_CHARSET = Charset.forName("GBK");
	

	public AbstractLayout(final byte[] _header, final byte[] _footer, final Charset _charset) {
		super();
		this.header = _header;
		this.footer = _footer;
		this.charset = _charset;
	}

	public AbstractLayout(final Charset _charset) {
		this(null, null, _charset);
	}

	public AbstractLayout() {
		this(null, null, DEFAULT_CHARSET);
	}

	@Override
	public byte[] getHeader() {
		return this.header;
	}

	@Override
	public byte[] getFooter() {
		return this.footer;
	}

	@Override
	public Charset getCharset() {
		return this.charset;
	}

	 /**
     * @return The default content type for Strings.
     */
    @Override
    public String getContentType() {
        return "text/plain";
    }

    /**
     * Formats the Log Event as a byte array.
     *
     * @param _event
     *        The Log Event.
     * @return The formatted event as a byte array.
     */
    @Override
    public byte[] toByteArray(final LogEvent _event) {
        return format(_event).getBytes(charset);
    }
}
