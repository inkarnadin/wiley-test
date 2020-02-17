package ru.alber.wiley;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LeastFrequentlyUsedCache<T> {
	
	private int cacheSize;

	private ConcurrentHashMap<T, Integer> elementStore;
	
	public LeastFrequentlyUsedCache(int cacheSize) {
		if (cacheSize <= 0)
			throw new IllegalArgumentException();
		
		this.cacheSize = cacheSize;
		
		elementStore = new ConcurrentHashMap<T, Integer>(cacheSize);
	}
	
	public void cache(T object) {
		if (Objects.isNull(object))
			throw new IllegalArgumentException();

		elementStore.computeIfPresent(object, (k, v) -> v + 1);
		
		if (elementStore.size() == cacheSize) {
			elementStore.entrySet().parallelStream().min(Map.Entry.comparingByValue()).ifPresent(x -> {
				if (!object.equals(x.getKey()) && !elementStore.containsKey(object)) 
					elementStore.remove(x.getKey());
			});
		}

		elementStore.putIfAbsent(object, 1);
	}
	
	public Set<T> viewAll() {
		return elementStore.keySet();
	}
}
