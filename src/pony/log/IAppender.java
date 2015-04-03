package pony.log;

import pony.log.appender.OutputStreamManager;

public interface IAppender {

 
    void append(final LogEvent _message);


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
