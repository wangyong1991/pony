package pony.log;

import java.util.Map;

public interface ILayout {

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

    byte[] toByteArray(final LogEvent _message);

    // TODO: it would be nice to provide ByteBuffers alongside the byte[]s

    /**
     * Formats the event as an Object that can be serialized.
     *
     * @param event The Logging Event.
     * @return The formatted event.
     */
    String toSerializable(final LogEvent _message);

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
