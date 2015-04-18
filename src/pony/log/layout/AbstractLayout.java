package pony.log.layout;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import pony.log.ILayout;
import pony.log.LogEvent;
import pony.util.Charsets;

/**
 * 所有样式的抽象基类
 * @author &#x738B;&#x52C7;
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractLayout implements ILayout {

    /**
     * The header to include when the stream is opened. May be null.
     */
    protected final byte[] header;

    /**
     * The footer to add when the stream is closed. May be null.
     */
    protected final byte[] footer;
    
    /**
     * The charset for the formatted message.
     */
    // TODO: Charset is not serializable. Implement read/writeObject() ?
    private final Charset charset;

    /**
     * Constructs a layout with an optional header and footer.
     * 
     * @param header
     *        The header to include when the stream is opened. May be null.
     * @param footer
     *        The footer to add when the stream is closed. May be null.
     */
    public AbstractLayout(final byte[] header, final byte[] footer, final Charset charset) {
        super();
        this.header = header;
        this.footer = footer;
        this.charset = charset == null ? Charsets.UTF_8 : charset;
    }
    
    public AbstractLayout(Charset charset) {
		this(null, null, charset);
	}

	protected Charset getCharset() {
        return charset;
    }

    @Override
    public Map<String, String> getContentFormat() {
        return new HashMap<String, String>();
    }

    /**
     * Returns the footer, if one is available.
     * 
     * @return A byte array containing the footer.
     */
    @Override
    public byte[] getFooter() {
        return footer;
    }

    /**
     * Returns the header, if one is available.
     * 
     * @return A byte array containing the header.
     */
    @Override
    public byte[] getHeader() {
        return header;
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
     * @param _message
     *        The Log Event.
     * @return The formatted event as a byte array.
     */
    @Override
    public byte[] toByteArray(final LogEvent _message) {
        return toSerializable(_message).getBytes(charset);
    }
}
