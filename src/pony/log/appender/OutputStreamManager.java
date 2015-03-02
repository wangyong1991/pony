package pony.log.appender;

import java.io.IOException;
import java.io.OutputStream;

import pony.exception.AppenderLoggingException;
import pony.log.ILayout;

public abstract class OutputStreamManager {
	private final String name;
	
	private volatile OutputStream os;
	
	private final ILayout layout;
	
	public OutputStreamManager(
			final OutputStream _os,
			final String _name,
			final ILayout _layout){
		this.os = _os;
		this.name = _name;
		this.layout = _layout;
		if (_layout != null) {
            final byte[] header = _layout.getHeader();
            if (header != null) {
                try {
                    this.os.write(header, 0, header.length);
                } catch (final IOException ioe) {
                	// TODO:
//                    LOGGER.error("Unable to write header", ioe);
                }
            }
        }
	}
	
	protected void write(final byte[] bytes)  {
        write(bytes, 0, bytes.length);
    }
	
	 protected synchronized void write(final byte[] bytes, final int offset, final int length)  {
	        //System.out.println("write " + count);
	        try {
	            os.write(bytes, offset, length);
	        } catch (final IOException ex) {
	            final String msg = "Error writing to stream " + getName();
	            throw new AppenderLoggingException(msg, ex);
	        }
	    }
	
	/**
     * Writes the footer.
     */
    protected void writeFooter() {
        if (layout == null) {
            return;
        }
        final byte[] footer = layout.getFooter();
        if (footer != null) {
            write(footer);
        }
    }
    
    protected OutputStream getOutputStream() {
        return os;
    }
    
    protected void setOutputStream(final OutputStream os) {
        final byte[] header = layout.getHeader();
        if (header != null) {
            try {
                os.write(header, 0, header.length);
                this.os = os; // only update field if os.write() succeeded
            } catch (final IOException ioe) {
            	// TODO
//                LOGGER.error("Unable to write header", ioe);
            }
        } else {
            this.os = os;
        }
    }
	
	public String getName(){
		return name;
	}
	
	protected synchronized void close() {
        final OutputStream stream = os; // access volatile field only once per method
        if (stream == System.out || stream == System.err) {
            return;
        }
        try {
            stream.close();
        } catch (final IOException ex) {
        	// TODO
//            LOGGER.error("Unable to close stream " + getName() + ". " + ex);
        }
    }
	
	/**
     * Flushes any buffers.
     */
    public synchronized void flush() {
        try {
            os.flush();
        } catch (final IOException ex) {
            final String msg = "Error flushing stream " + getName();
            throw new AppenderLoggingException(msg, ex);
        }
    }
}
