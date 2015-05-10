package pony.log.appender;

import java.io.IOException;
import java.io.OutputStream;

import pony.exception.AppenderLoggingException;
import pony.log.ILayout;

public class OutputStreamManager {
	private volatile OutputStream os;
	private final ILayout layout;
	private final String name;

	public OutputStreamManager(
			final String _name,
			final OutputStream _os,
			final ILayout _layout) {
		super();
		this.name = _name;
		this.os = _os;
		this.layout = _layout;
		if (_layout != null) {
            final byte[] l_header = _layout.getHeader();
            if (l_header != null) {
                try {
                    this.os.write(l_header, 0, l_header.length);
                } catch (final IOException ioe) {
//                    LOGGER.error("Unable to write header", ioe);
                }
            }
        }
	}

	public final String getName() {
		return this.name;
	}

	public final ILayout getLayout() {
		return this.layout;
	}

	public final OutputStream getOutputStream() {
		return this.os;
	}

	public final void setOutputStream(OutputStream _os) {
		final byte[] header = layout.getHeader();
        if (header != null) {
            try {
            	_os.write(header, 0, header.length);
                this.os = _os; // only update field if os.write() succeeded
            } catch (final IOException ioe) {
//                LOGGER.error("Unable to write header", ioe);
            }
        } else {
            this.os = _os;
        }
	}
	
	/**
     * Default hook to write footer during close.
     */
    public void release() {
        writeFooter();
        close();
    }
	
	private void writeFooter(){
		if (this.layout == null) {
            return;
        }
        final byte[] footer = this.layout.getFooter();
        if (footer != null) {
            write(footer);
        }
	}
	
	void write(byte[] _bytes) {
		write(_bytes, 0, _bytes.length);
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
	
	protected void close(){
		final OutputStream stream = os; // access volatile field only once per method
        if (stream == System.out || stream == System.err) {
            return;
        }
        try {
            stream.close();
        } catch (final IOException ex) {
            LOGGER.error("Unable to close stream " + getName() + ". " + ex);
        }
	}
	
	public void flush(){
		try {
            os.flush();
        } catch (final IOException ex) {
            final String msg = "Error flushing stream " + getName();
            throw new AppenderLoggingException(msg, ex);
        }
	}
}
