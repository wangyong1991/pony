package pony;

import java.nio.ByteBuffer;

public interface IFilter {
	public ByteBuffer doFilter(final ByteBuffer input);
	
	public void destroy();
}
