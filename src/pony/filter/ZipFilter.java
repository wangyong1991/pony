package pony.filter;

import java.nio.ByteBuffer;
import java.util.zip.Deflater;

import pony.IFilter;

public class ZipFilter implements IFilter {

	private Deflater zip = new Deflater();

	@Override
	public ByteBuffer doFilter(final ByteBuffer input) {
		final byte[] data = new byte[input.remaining()];
		input.get(data);
		zip.setInput(data);
		zip.finish();
		final ByteBuffer output = ByteBuffer.allocate(1024);
		final byte[] tempData = new byte[1024];
		while (true) {
			final int compressedDataLength = zip.deflate(tempData);
			if (compressedDataLength <= 0)
				break;
			output.put(tempData, 0, compressedDataLength);
		}
		zip.reset();
		output.flip();
		return output;
	}

	@Override
	public void destroy() {
		zip = null;
	}
}
