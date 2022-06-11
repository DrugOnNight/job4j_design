package ru.job4j.map;


import org.junit.Assert;
import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public void whenPutAndGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Oleg", 1);
        map.put("Maria", 2);
        Assert.assertEquals(map.get("Oleg"), Integer.valueOf(1));
    }

    @Test
    public void whenPutDuplicateKeyThenNotReplace() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("Oleg", 1));
        Assert.assertTrue(map.put("Maria", 2));
        Assert.assertFalse(map.put("Oleg", 3));
        Assert.assertThat(map.get("Oleg"), is(1));
    }

    @Test
    public void whenGetEmptyValueThenNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Oleg", 1);
        map.put("Maria", 2);
        Assert.assertNull(map.get("Ivan"));
    }

    @Test
    public void whenGetMariaThen2() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("Oleg", 1));
        Assert.assertTrue(map.put("Maria", 2));
        Assert.assertFalse(map.put("Maria", 3));
        Assert.assertEquals(map.get("Maria"), Integer.valueOf(2));
    }

    @Test
    public void whenRemoveInEmptyMap() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Assert.assertFalse(map.remove("Oleg"));
        Assert.assertNull(map.get("Oleg"));
    }

    @Test
    public void whenRemove() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Oleg", 1);
        map.put("Maria", 2);
        Assert.assertTrue(map.remove("Oleg"));
        Assert.assertNull(map.get("Oleg"));
    }

    @Test
    public void whenIteratorThenHasNextFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Oleg", 1);
        Iterator<String> it = map.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(it.next(), "Oleg");
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void whenIteratorEmpty() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Iterator<String> it = map.iterator();
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void iteratorHasNextManyTimes() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Oleg", 1);
        map.put("Masha", 2);
        Iterator<String> it = map.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(it.next(), "Oleg");
    }

}