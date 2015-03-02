package pony.filter;

import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import pony.IFilter;

public class UnzipFilter implements IFilter {
	private Inflater unzip = new Inflater();
	
	@Override
	public ByteBuffer doFilter(final ByteBuffer input) {
		final byte[] data = new byte[input.remaining()];
		input.get(data);
		
		unzip.setInput(data);
		unzip.finished();
		ByteBuffer output = ByteBuffer.allocate(1024);
		final byte[] tempData = new byte[1024];
		while (true) {
			try {
				final int unCompressedSize = unzip.inflate(tempData);
				if (unCompressedSize <= 0)
					break;
				output.put(tempData, 0, unCompressedSize);
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
		unzip.reset();
		output.flip();
		return output;
	}

	@Override
	public void destroy() {
		unzip = null;
	}

}
