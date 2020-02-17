package ru.alber.wiley;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LeastFrequentlyUsedCacheTest {
	
	@Test
	public void testLeastFrequentlyRemove() {
		LeastFrequentlyUsedCache<JustObject> lfu = new LeastFrequentlyUsedCache<>(3);
		
		lfu.cache(new JustObject(0));
		lfu.cache(new JustObject(6));
		lfu.cache(new JustObject(23));		
		lfu.cache(new JustObject(0));
		lfu.cache(new JustObject(6));
		lfu.cache(new JustObject(9));		
				
		assertFalse(lfu.viewAll().contains(new JustObject(23)), "Least frequent object not has been removed");		
	}
	
	@Test
	public void testMostFrequentlySave() {
		LeastFrequentlyUsedCache<JustObject> lfu = new LeastFrequentlyUsedCache<>(3);
		
		lfu.cache(new JustObject(0));
		lfu.cache(new JustObject(0));
		lfu.cache(new JustObject(23));
		lfu.cache(new JustObject(0));
		lfu.cache(new JustObject(0));
		lfu.cache(new JustObject(2));
		lfu.cache(new JustObject(23));		
		lfu.cache(new JustObject(0));		
		lfu.cache(new JustObject(23));
		lfu.cache(new JustObject(9));	
		
		assertTrue(lfu.viewAll().contains(new JustObject(0)), "Frequently used object (0) has been removed");	
		assertTrue(lfu.viewAll().contains(new JustObject(23)), "Frequently used object (23) has been removed");	
	}
	
}
