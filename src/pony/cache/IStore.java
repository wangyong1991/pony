package pony.cache;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import pony.cache.policy.EvictionPolicy;
import pony.exception.CacheException;

public interface IStore<K,V> {
	/**
     * Puts an item into the store.
     * @return true if this is a new put for the key or element is null. Returns false if it was an update.
     */
    boolean put(K key, V value) throws CacheException;

    /**
     * Puts a collection of elements into the store.
     * @param elements Collection of elements to be put in the store
     */
    void putAll(Collection<V> value) throws CacheException;

    /**
     * Puts an item into the store and the cache writer manager in an atomic operation
     * @return true if this is a new put for the key or element is null. Returns false if it was an update.
     */
    boolean putWithWriter(V value, CacheWriterManager writerManager) throws CacheException;

    /**
     * Gets an item from the cache.
     */
    V get(K key);

    /**
     * Gets an {@link Element} from the Store, without updating statistics
     *
     * @return The element
     */
    V getQuiet(K key);

    /**
     * Gets an Array of the keys for all elements in the disk store.
     *
     * @return An List of {@link java.io.Serializable} keys
     */
    List<V> getKeys();

    /**
     * Removes an item from the cache.
     *
     * @since signature changed in 1.2 from boolean to Element to support notifications
     */
    V remove(K key);

    /**
     * Removes a collection of elements from the cache.
     */
    void removeAll(Collection<K> keys);

    /**
     * Removes an item from the store and the cache writer manager in an atomic operation.
     */
    V removeWithWriter(K key, CacheWriterManager writerManager) throws CacheException;

    /**
     * Remove all of the elements from the store.
     * <p/>
     * If there are registered <code>CacheEventListener</code>s they are notified of the expiry or removal
     * of the <code>Element</code> as each is removed.
     */
    void removeAll() throws CacheException;

    /**
     * Put an element in the store if no element is currently mapped to the elements key.
     *
     * @param element element to be added
     * @return the element previously cached for this key, or null if none.
     *
     * @throws NullPointerException if the element is null, or has a null key
     */
    V putIfAbsent(V value) throws NullPointerException;

    /**
     * Replace the cached element only if an Element is currently cached for this key
     * @param element Element to be cached
     * @return the Element previously cached for this key, or null if no Element was cached
     * @throws NullPointerException if the Element is null or has a null key
     */
    V replace(V value) throws NullPointerException;

    /**
     * Prepares for shutdown.
     */
    void dispose();

    /**
     * Returns the current local store size
     * @return the count of the Elements in the Store on the local machine
     */
    int getSize();

    /**
     * Returns the current local in-memory store size
     * @return the count of the Elements in the Store and in-memory on the local machine
     */
    int getInMemorySize();
    
    /**
     * Gets the size of the in-memory portion of the store, in bytes.
     * <p/>
     * This method may be expensive to run, depending on implementation. Implementers may choose to return
     * an approximate size.
     *
     * @return the approximate in-memory size of the store in bytes
     */
    long getInMemorySizeInBytes();

    /**
     * Returns the cache status.
     */
    Status getStatus();


    /**
     * A check to see if a key is in the Store.
     *
     * @param key The Element key
     * @return true if found. No check is made to see if the Element is expired.
     *  1.2
     */
    boolean containsKey(K key);

    /**
     * Expire all elements.
     */
    public void expireValues();

    /**
     * Flush elements to persistent store.
     * @throws IOException if any IO error occurs
     */
    void flush() throws IOException;

    /**
     * Some store types, such as the disk stores can fill their write buffers if puts
     * come in too fast. The thread will wait for a short time before checking again.
     * @return true if the store write buffer is backed up.
     */
    boolean bufferFull();

    /**
     * @return the current eviction policy. This may not be the configured policy, if it has been
     *         dynamically set.
     * @see #setInMemoryEvictionPolicy(Policy)
     */
    EvictionPolicy getInMemoryEvictionPolicy();

    /**
     * Sets the eviction policy strategy. The Store will use a policy at startup. The store may allow changing
     * the eviction policy strategy dynamically. Otherwise implementations will throw an exception if this method
     * is called.
     *
     * @param policy the new policy
     */
    void setInMemoryEvictionPolicy(EvictionPolicy policy);
    
    /**
     * Retries the elements associated with a set of keys without updating the statistics
     * Keys which are not present in the cache will have null values associated
     * with them in the returned map
     *
     * @param keys a collection of keys to look for
     * @return a map of keys and their corresponding values
     */
    Map<K, V> getAllQuiet(Collection<K> keys);

    /**
     * Retries the elements associated with a set of keys and update the statistics
     * Keys which are not present in the cache will have null values associated
     * with them in the returned map
     *
     * @param keys a collection of keys to look for
     * @return a map of keys and their corresponding values
     */
    Map<K, V> getAll(Collection<K> keys);

    /**
     * Recalculate size of the element mapped to the key
     * @param key the key
     */
    void recalculateSize(K key);
}
