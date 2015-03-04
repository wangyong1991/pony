package pony.cache;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import pony.cache.policy.EvictionPolicy;
import pony.exception.CacheException;

public class Store<K, V> implements IStore<K, V> {
	
	private final ConcurrentHashMap<K, V> internalMap ;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();  
    private final Lock readLock = lock.readLock(); 
    
	public Store(){
		internalMap = new ConcurrentHashMap<K,V>();
	}

	@Override
	public boolean put(final K key, final V value) throws CacheException {
		internalMap.put(key, value);
		return true;
	}

	@Override
	public void putAll(Collection<V> value) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean putWithWriter(V value, CacheWriterManager writerManager)
			throws CacheException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V getQuiet(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<V> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAll(Collection<K> keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V removeWithWriter(K key, CacheWriterManager writerManager)
			throws CacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAll() throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V putIfAbsent(V value) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V replace(V value) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInMemorySize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getInMemorySizeInBytes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void expireValues() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean bufferFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EvictionPolicy getInMemoryEvictionPolicy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInMemoryEvictionPolicy(EvictionPolicy policy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<K, V> getAllQuiet(Collection<K> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<K, V> getAll(Collection<K> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recalculateSize(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

}
