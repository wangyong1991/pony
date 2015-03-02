package pony.log.appender;

import pony.log.ILayout;

public class RandomAccessFileAppender extends AbstractAppender {

	public RandomAccessFileAppender(
			final String _fileName, 
			final ILayout _layout,
			final OutputStreamManager _manager, 
			final boolean _immediateFlush) {
		super(_fileName, _layout, _manager, _immediateFlush);
	}

}
