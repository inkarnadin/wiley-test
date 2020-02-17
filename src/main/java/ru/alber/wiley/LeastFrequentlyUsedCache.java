package ru.alber.wiley;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

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
		
		Entry<T, Integer> minEntry = null;
		if (!elementStore.isEmpty()) {
			minEntry = Collections.min(elementStore.entrySet(), 
				    new Comparator<Entry<T, Integer>>() {				       
				        public int compare(Entry<T, Integer> e1, Entry<T, Integer> e2) {
				            return e1.getValue().compareTo(e2.getValue());
				        }
				    });					
		}
		
		if (elementStore.size() == cacheSize && !object.equals(minEntry.getKey()) && !elementStore.containsKey(object)) {
			elementStore.remove(minEntry.getKey());			
		}		
		elementStore.putIfAbsent(object, 1);

		elementStore.forEach((k, v) -> { 
			System.out.print(k + " = " + v + ", "); 
		});
		System.out.println();
	}
}
