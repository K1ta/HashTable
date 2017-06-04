
import hashtable.HashTable;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by cilci_000 on 04.06.2017.
 */
public class UnitTest {

    @Test
    public void TestGet() {
        HashTable<Integer, String> table = new HashTable<>();
        table.put(10, "Cat");
        String value = table.get(10);
        if (value == null || !value.equals("Cat")) {
            fail("Error");
        }
        value = table.get(20);
        if (value != null) {
            fail("Error");
        }
        table.put(20, "Dog");
        value = table.get(20);
        if (value == null || !value.equals("Dog")) {
            fail("Error");
        }
        value = table.put(20, "Alligator");
        if (value == null || !value.equals("Dog")) {
            fail("Error");
        }
    }

    @Test
    public void TestPut() {
        HashTable<Integer, String> table = new HashTable<>();
        String value = table.put(10, "Alex");
        if (value != null) {
            fail("Error");
        }
        value = table.put(10, "Alexey");
        if (!value.equals("Alex")) {
            fail("Error");
        }
        value = table.put(20, "Vasya");
        if (value != null) {
            fail("Error");
        }
    }

    @Test
    public void testContainsValue() {
        HashTable<Integer, String> table = new HashTable<>();
        table.put(10, "Cat");
        if (!table.containsValue("Cat")) {
            fail("Error");
        }
        if(table.containsValue("Dog")){
            fail("Error");
        }
    }

    @Test
    public void testContainsKey(){
        HashTable<Integer, String> table = new HashTable<>();
        table.put(10, "Cat");
        if(!table.containsKey(10)){
            fail("Error");
        }
        if(table.containsKey(11)){
            fail("Error");
        }
    }

    @Test
    public void testRemove() {
        HashTable<Integer, String> table = new HashTable<>();
        table.put(10, "Cat");
        table.put(1, "Dog");
        table.put(100, "Fox");
        table.put(2110, "Fish");

        table.remove(1);
        if(table.containsKey("Dog"))
            fail("Error");

        table.remove(2110);
        if(table.containsKey("Fish"))
            fail("Error");

        table.remove(100);
        if(table.containsKey("Fox"))
            fail("Error");
    }

    @Test
    public void testClear() {
        HashTable<Integer, String> table = new HashTable<>();
        table.put(10, "Cat");
        table.put(1, "Dog");
        table.put(100, "Fox");
        table.put(2110, "Fish");

        table.clear();
        if(table.containsValue("Cat") || table.containsValue("Dog") || table.containsValue("Fox") || table.containsValue("Fish"))
            fail("Error");
    }

    @Test
    public void testKeySet() {
        HashTable<Integer, String> table = new HashTable<>();
        table.put(10, "Cat");
        table.put(1, "Dog");
        table.put(100, "Fox");
        table.put(2110, "Fish");

        Set<Integer> setAcc = table.keySet();
        Set<Integer> setExp = new TreeSet<>(Arrays.asList(10,1,100,2110));

        assertArrayEquals(setExp.toArray(),setAcc.toArray());
    }
}
