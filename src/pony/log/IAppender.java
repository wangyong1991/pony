package pony.log;

import org.xml.sax.ErrorHandler;

import pony.log.appender.OutputStreamManager;

/**
 * Appends {@link LogMessage}s. An Appender can contain a {@link ILayout} if applicable as well
 * as an {@link ErrorHandler}. Typical Appender implementations coordinate with an
 * implementation of {@link org.apache.logging.log4j.core.appender.AbstractManager} to handle external resources
 * such as streams, connections, and other shared state. As Appenders are plugins, concrete implementations need to
 * be annotated with {@link org.apache.logging.log4j.core.config.plugins.Plugin} and need to provide a static
 * factory method annotated with {@link org.apache.logging.log4j.core.config.plugins.PluginFactory}.
 *
 * <p>Most core plugins are written using a related Manager class that handle the actual task of serializing a
 * {@link LogMessage} to some output location. For instance, many Appenders can take
 * advantage of the {@link org.apache.logging.log4j.core.appender.OutputStreamManager} class.</p>
 *
 * <p>It is recommended that Appenders don't do any heavy lifting since there can be many instances of the class
 * being used at any given time. When resources require locking (e.g., through {@link java.nio.channels.FileLock}),
 * it is important to isolate synchronized code to prevent concurrency issues.</p>
 */
public interface IAppender {

    /**
     * Logs a LogMessage using whatever logic this Appender wishes to use. It is typically recommended to use a
     * bridge pattern not only for the benefits from decoupling an Appender from its implementation, but it is also
     * handy for sharing resources which may require some form of locking.
     *
     * @param event The LogMessage.
     */
    void append(final LogMessage _message);


    /**
     * Get the name of this Appender.
     *
     * @return name, may be null.
     */
    String getName();

    /**
     * Returns the Layout used by this Appender if applicable.
     *
     * @return the Layout for this Appender or {@code null} if none is configured.
     */
    ILayout getLayout();

    OutputStreamManager getManager();
}
