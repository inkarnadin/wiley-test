package ru.alber.wiley;

public class Main {
	
    public static void main( String[] args ) {       
//        LeastRecentlyUsedCache<Test> lru = new LeastRecentlyUsedCache<Test>(4);
//        lru.cache(new Test(1));
//        lru.cache(new Test(2));
//        lru.cache(new Test(3));
//        lru.cache(new Test(2));
//        lru.cache(new Test(8));
//        lru.cache(new Test(7));
//        lru.cache(new Test(3));
    	
    	LeastFrequentlyUsedCache<Test> t = new LeastFrequentlyUsedCache<>(3);
      t.cache(new Test(8));
      t.cache(new Test(7));
      t.cache(new Test(3));
      t.cache(new Test(7));
      t.cache(new Test(5));
      t.cache(new Test(3));
      t.cache(new Test(3));
      t.cache(new Test(7));
      t.cache(new Test(0));
    }
    
}
