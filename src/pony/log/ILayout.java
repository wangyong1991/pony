package pony.log;

import java.io.Serializable;
import java.util.Map;

/**
 * Lays out a {@linkplain LogEvent} in different formats.
 *
 * The formats are:
 * <ul>
 * <li>
 * {@code byte[]}</li>
 * <li>
 * an implementer of {@linkplain Serializable}, like {@code byte[]}</li>
 * <li>
 * {@linkplain String}</li>
 * <li>
 * {@linkplain LogEvent}</li>
 * </ul>
 *
 * @param <T>
 *            The {@link Serializable} type returned by {@link #toSerializable(LogEvent)}
 *
 * TODO There is still a need for a character-based layout for character based event sinks (databases, etc). Would
 * introduce an EventEncoder, EventRenderer or something similar for the logging event to byte encoding. (RG) A layout
 * can be configured with a Charset and then Strings can be converted to byte arrays. OTOH, it isn't possible to write
 * byte arrays as character streams.
 */
public interface ILayout {

    /**
     * Main plugin element type for Layout plugins.
     *
     * @since 2.1
     */
    String ELEMENT_TYPE = "layout";

    /**
     * Returns the format for the layout format.
     * @return The footer.
     */
    byte[] getFooter();

    /**
     * Returns the header for the layout format.
     * @return The header.
     */
    byte[] getHeader();

    /**
     * Formats the event suitable for display.
     *
     * @param event The Logging Event.
     * @return The formatted event.
     * TODO Likely better to write to a OutputStream instead of return a byte[]. (RG) That limits how the
     * Appender can use the Layout. For example, it might concatenate information in front or behind the
     * data and then write it all to the OutputStream in one call.
     */
    byte[] toByteArray(final LogMessage _message);

    // TODO: it would be nice to provide ByteBuffers alongside the byte[]s

    /**
     * Formats the event as an Object that can be serialized.
     *
     * @param event The Logging Event.
     * @return The formatted event.
     */
    String toSerializable(final LogMessage _message);

    /**
     * Returns the content type output by this layout. The base class returns "text/plain".
     *
     * @return the content type.
     */
    String getContentType();

    /**
     * Returns a description of the content format.
     *
     * @return a Map of key/value pairs describing the Layout-specific content format, or an empty Map if no content
     * format descriptors are specified.
     */
    Map<String, String> getContentFormat();
}
