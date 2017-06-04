
import hashtable.HashTable;
import static org.junit.Assert.fail;
import org.junit.Test;

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
}
