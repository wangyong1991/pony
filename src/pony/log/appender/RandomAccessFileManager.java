package pony.log.appender;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import pony.exception.AppenderLoggingException;
import pony.log.ILayout;

public class RandomAccessFileManager extends OutputStreamManager {
	
	static final int DEFAULT_BUFFER_SIZE = 256*1024;
	
	private final boolean immediateFlush;
	
	private final RandomAccessFile randomAccessFile;
	
	private final ByteBuffer buffer;
	

	public RandomAccessFileManager(
			final RandomAccessFile _file,
			final String _fileName,
			final OutputStream _os, 
			final boolean _immediateFlush,
			final int _bufferSize,
			final ILayout _layout) {
		super(_os, _fileName, _layout);
		this.immediateFlush = _immediateFlush;
		this.randomAccessFile = _file;
		this.buffer = ByteBuffer.allocate(_bufferSize);
	}

	@Override
	protected synchronized void write(final byte[] _bytes, int _offset, int _length){
		super.write(_bytes, _offset, _length);
		int chunk = 0;
		do{
			if(_length > buffer.remaining()){
				flush();
			}
			chunk = Math.min(_length, buffer.remaining());
			buffer.put(_bytes, _offset, chunk);
			_offset += chunk;
			_length -= chunk;
		}while(_length > 0);
		
		if(immediateFlush){
			flush();
		}
	}
	
	@Override
    public synchronized void flush() {
        buffer.flip();
        try {
            randomAccessFile.write(buffer.array(), 0, buffer.limit());
        } catch (final IOException ex) {
            final String msg = "Error writing to RandomAccessFile " + getName();
            throw new AppenderLoggingException(msg, ex);
        }
        buffer.clear();
    }
	
	@Override
    public synchronized void close() {
        flush();
        try {
            randomAccessFile.close();
        } catch (final IOException ex) {
        	// TODO
//            LOGGER.error("Unable to close RandomAccessFile " + getName() + ". "
//                    + ex);
        }
    }
	
	/**
     * Returns the name of the File being managed.
     *
     * @return The name of the File being managed.
     */
    public String getFileName() {
        return getName();
    }
    
    /**
     * Returns the buffer capacity.
     * @return the buffer size
     */
    public int getBufferSize() {
        return buffer.capacity();
    }
}
