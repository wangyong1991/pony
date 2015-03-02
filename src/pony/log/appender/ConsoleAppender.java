package pony.log.appender;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import pony.log.ILayout;
import pony.log.LogConfig;

public class ConsoleAppender {
	
	/**
     * Enumeration of console destinations.
     */
    public enum Target {
        /** Standard output. */
        SYSTEM_OUT,
        /** Standard error output. */
        SYSTEM_ERR
    }
    
    private static OutputStreamManager getManager(
    		final boolean _follow,
    		final Target _target,
    		final ILayout _layout){
    	final OutputStream os = getOutputStream(_follow, _target);
    	// TODO
    	return null;
    }
    
    private static OutputStream getOutputStream(
    		final boolean _follow,
    		final Target _target){
    	final String charsetName = LogConfig.getCharset().name();
    	OutputStream os = null;
    	try{
    		os = _target == Target.SYSTEM_OUT ?
    				_follow ? new PrintStream(new SystemOutStream(), true, charsetName) : System.out :
    				_follow ? new PrintStream(new SystemErrStream(), true, charsetName) : System.err;
    	}catch (final UnsupportedEncodingException ex) { // should never happen
            throw new IllegalStateException("Unsupported default encoding " + charsetName, ex);
        }
    	return os;
    }
	
	/**
     * An implementation of OutputStream that redirects to the current System.err.
     */
    private static class SystemErrStream extends OutputStream {
        public SystemErrStream() {
        }

        @Override
        public void close() {
            // do not close sys err!
        }

        @Override
        public void flush() {
            System.err.flush();
        }

        @Override
        public void write(final byte[] b) throws IOException {
            System.err.write(b);
        }

        @Override
        public void write(final byte[] b, final int off, final int len)
            throws IOException {
            System.err.write(b, off, len);
        }

        @Override
        public void write(final int b) {
            System.err.write(b);
        }
    }
	
	private static class SystemOutStream extends OutputStream {
		
		public SystemOutStream(){
			
		}

		@Override
		public void write(final byte[] b) throws IOException {
			System.out.write(b);
		}

		@Override
		public void write(final byte[] b, final int off, final int len) throws IOException {
			System.out.write(b, off, len);
		}

		@Override
		public void flush() throws IOException {
			System.out.flush();
		}

		@Override
		public void close() throws IOException {
			// do not close system output stream !
		}

		@Override
		public void write(final int b) throws IOException {
			System.out.write(b);
		}
		
	}

}
