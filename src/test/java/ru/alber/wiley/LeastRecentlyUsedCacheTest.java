package ru.alber.wiley;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LeastRecentlyUsedCacheTest {
	
	@Test
	public void testLeastRecentlyUsedRemove() {
		LeastRecentlyUsedCache<JustObject> lru = new LeastRecentlyUsedCache<>(3);
		
		lru.cache(new JustObject(3));
		lru.cache(new JustObject(2));
		lru.cache(new JustObject(3));
		lru.cache(new JustObject(1));
		lru.cache(new JustObject(6));
		
		assertFalse(lru.viewAll().contains(new JustObject(2)), "Least recently object not has been removed");			
	}
	
	@Test
	public void testMostRecentlyUsedSave() {
		LeastRecentlyUsedCache<JustObject> lru = new LeastRecentlyUsedCache<>(3);
		
		lru.cache(new JustObject(3));
		lru.cache(new JustObject(2));
		lru.cache(new JustObject(3));
		lru.cache(new JustObject(1));
		lru.cache(new JustObject(6));
		lru.cache(new JustObject(2));
		
		assertTrue(lru.viewAll().contains(new JustObject(1)), "Recently used object (1) has been removed");	
		assertTrue(lru.viewAll().contains(new JustObject(6)), "Recently used object (6) has been removed");			
	}

}
