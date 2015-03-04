package pony.cache;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pony.util.TimeUtils;

public class Element<K,V> implements Cloneable {
	
	private final static Logger logger = LoggerFactory.getLogger(Element.class.getName());
	
	private static final AtomicLongFieldUpdater<Element> HIT_COUNT_UPDATER = AtomicLongFieldUpdater.newUpdater(Element.class, "hitCount");
	
	private final K key;
	
	private final V value;
	
	 /**
     * The number of times the element was hit.
     */
    private volatile long hitCount;

    /**
     * The amount of time for the element to live, in seconds. 0 indicates unlimited.
     */
    private volatile int timeToLive = Integer.MAX_VALUE;
    
    /**
     * The creation time.
     */
    private transient long creationTime;

    /**
     * The last access time.
     */
    private transient long lastAccessTime;

    /**
     * If there is an Element in the Cache and it is replaced with a new Element for the same key,
     * then both the version number and lastUpdateTime should be updated to reflect that. The creation time
     * will be the creation time of the new Element, not the original one, so that TTL concepts still work.
     */
    private volatile long lastUpdateTime;
    
    public Element(final K _key, final V _value){
    	this.key = _key;
    	this.value = _value;
    	HIT_COUNT_UPDATER.set(this, 0);
        this.creationTime = TimeUtils.currentTimeMillis();
    }
    
    public Element(final K _key, final V _value, final int _timeToLive){
    	this(_key, _value);
    	this.timeToLive = _timeToLive;
    }
    
    /**
     * Gets the creationTime of the Element
     *
     * @return The creationTime value
     */
    public final long getCreationTime() {
		return creationTime;
	}

	/**
     * @return the time to live, in seconds
     */
    public int getTimeToLive() {
        if (Integer.MIN_VALUE == timeToLive) {
            return 0;
        } else {
            return timeToLive;
        }
    }

	public final void setTimeToLive(int timeToLive) {
		if (timeToLive < 0) {
            throw new IllegalArgumentException("timeToLive can't be negative");
        }
        this.timeToLive = timeToLive;
	}

	public final long getLastAccessTime() {
		return lastAccessTime;
	}

	public final void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	/**
     * Gets the last access time of this element.
     * <p/>
     * Access means the element was written into a cache or read from it.
     * When first instantiated an {@link Element} has a lastAccessTime of 0, unless passed into the constructor.
     *
     * @see #Element(Object, Object, long, long, long, long, boolean, int, int, long)
     * @see #Element(Object, Object, long, long, long, long, long)
     * @see #resetAccessStatistics()
     * @see #updateAccessStatistics()
     */
	public final long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public final void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public final K getKey() {
		return key;
	}

	public final V getValue() {
		return value;
	}

	 /**
     * Gets the hit count on this element.
     */
	public final long getHitCount() {
		return hitCount;
	}
	
	 /**
     * Calculates the latest of creation and update time
     * @return if never updated, creation time is returned, otherwise updated time
     */
    public final long getLatestOfCreationAndUpdateTime() {
        if (0 == lastUpdateTime) {
            return creationTime;
        } else {
            return lastUpdateTime;
        }
    }
    
    /**
     * Resets the hit count to 0 and the last access time to now. Used when an Element is put into a cache.
     */
    public final void resetAccessStatistics() {
        lastAccessTime = TimeUtils.currentTimeMillis();
        HIT_COUNT_UPDATER.set(this, 0);
    }

    /**
     * Sets the last access time to now and increase the hit count.
     */
    public final void updateAccessStatistics() {
        lastAccessTime = TimeUtils.currentTimeMillis();
        HIT_COUNT_UPDATER.incrementAndGet(this);
    }

    /**
     * Sets the last access time to now without updating the hit count.
     */
    public final void updateUpdateStatistics() {
        lastUpdateTime = TimeUtils.currentTimeMillis();
    }

    /**
     * @return true if the element is eternal
     */
    public boolean isEternal() {
        return Integer.MAX_VALUE == timeToLive;
    }
    
    /**
     * An element is expired if the expiration time as given by {@link #getExpirationTime()} is in the past.
     *
     * @return true if the Element is expired, otherwise false. If no lifespan has been set for the Element it is
     *         considered not able to expire.
     * @see #getExpirationTime()
     */
    public boolean isExpired() {
        if (isEternal()) {
            return false;
        }

        long now = TimeUtils.currentTimeMillis();
        long expirationTime = getExpirationTime();

        return now > expirationTime;
    }
    
    /**
     * Returns the expiration time based on time to live. If this element also has a time to idle setting, the expiry
     * time will vary depending on whether the element is accessed.
     *
     * @return the time to expiration
     */
    public long getExpirationTime() {
        if (isEternal()) {
            return Long.MAX_VALUE;
        }

        long expirationTime = 0;
        long ttlExpiry = creationTime + TimeUtils.secondsToMillis(getTimeToLive());

        long mostRecentTime = Math.max(creationTime, lastAccessTime);
        
        return expirationTime;
    }

    /**
     * Returns a {@link String} representation of the {@link Element}.
     */
    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[ key = ").append(key)
                .append(", value=").append(value)
                .append(", hitCount=").append(hitCount)
                .append(", CreationTime = ").append(this.getCreationTime())
                .append(", LastAccessTime = ").append(this.getLastAccessTime())
                .append(" ]");

        return sb.toString();
    }

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Element)) {
            return false;
        }

        Element element = (Element) object;
        if (key == null) {
            return false;
        }

        return key.equals(element.getKey());
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
}
