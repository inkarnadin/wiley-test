package ru.alber.wiley;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LeastRecentlyUsedCache<T> {
	
	private int cacheSize;
	
	private ConcurrentLinkedDeque<T> elementAgeStore;	
	private ConcurrentHashMap<T, Integer> elementStore;
	
	public LeastRecentlyUsedCache(int cacheSize) {
		if (cacheSize <= 0)
			throw new IllegalArgumentException();
		
		this.cacheSize = cacheSize;
		
		elementStore = new ConcurrentHashMap<T, Integer>(cacheSize);
		elementAgeStore = new ConcurrentLinkedDeque<T>();
	}
	
	public void cache(T object) {	
		if (Objects.isNull(object))
			throw new IllegalArgumentException();
		
		if (!elementStore.containsKey(object) && elementAgeStore.size() == cacheSize)
			elementStore.remove(elementAgeStore.removeLast());			
		else
			elementAgeStore.remove(object);
		
		elementAgeStore.push(object);
		elementStore.putIfAbsent(object, 0);
	}	
	
	public Set<T> viewAll() {
		return elementStore.keySet();
	}

}
