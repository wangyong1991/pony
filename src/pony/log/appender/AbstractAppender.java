package pony.log.appender;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import pony.log.IAppender;
import pony.log.ILayout;
import pony.log.LogMessage;

public abstract class AbstractAppender implements IAppender {

	protected final ILayout layout;

	protected final String name;

	protected final boolean immediateFlush;

	protected final OutputStreamManager manager;

	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
	private final Lock readLock = rwLock.readLock();

	public AbstractAppender(final String _name, final ILayout _layout,
			final OutputStreamManager _manager, final boolean _immediateFlush) {
		this.layout = _layout;
		this.name = _name;
		this.manager = _manager;
		this.immediateFlush = _immediateFlush;
	}

	@Override
	public void append(final LogMessage _message) {
		readLock.lock();
		try {
			final byte[] bytes = getLayout().toByteArray(_message);
			if (bytes.length > 0) {
				manager.write(bytes);
				if (this.immediateFlush) {
					manager.flush();
				}
			}
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ILayout getLayout() {
		return layout;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public OutputStreamManager getManager() {
		return manager;
	}

}
