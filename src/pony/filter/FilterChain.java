package pony.filter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pony.IFilter;

public class FilterChain implements IFilter {
	private List<IFilter> chain ;
	
	public FilterChain(){
		chain = new ArrayList<IFilter>();
	}
	
	public FilterChain(final IFilter ... filters){
		this();
		for(int idx=0; idx < filters.length; idx++){
			chain.add(filters[idx]);
		}
	}
	
	public boolean addFilter(final IFilter filter){
		return chain.add(filter);
	}
	
	public boolean removeFilter(final IFilter filter){
		return chain.remove(filter);
	}
	
	public void addFilter(final int index, final IFilter filter){
		chain.add(index, filter);
	}
	
	public IFilter removeFilter(final int index){
		return chain.remove(index);
	}

	@Override
	public ByteBuffer doFilter(final ByteBuffer input) {
		ByteBuffer output = input;
		final Iterator<IFilter> iterator = chain.iterator();
		while(iterator.hasNext()){
			final IFilter filter = iterator.next();
			output = filter.doFilter(output);
		}
		return output;
	}

	@Override
	public void destroy() {
		final Iterator<IFilter> iterator = chain.iterator();
		while(iterator.hasNext()){
			final IFilter filter = iterator.next();
			filter.destroy();
		}
	}
}
