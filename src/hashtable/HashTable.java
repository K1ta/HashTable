package hashtable;

import com.sun.prism.impl.Disposer;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author K1ta
 */
public class HashTable<K, V> implements Map<K, V> {

    private class Record<K, V> implements Entry<K, V> {

        private V value;
        private K key;
        public Record<K, V> next;

        public Record(V value, K key) {
            this.value = value;
            this.key = key;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

    }

    private Record<K, V>[] table;
    private int size;
    private float loadFactor;
    private int capacity;
    private int threshold;

    public HashTable() {
        this(11, 0.75f);
    }

    public HashTable(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public HashTable(int initialCapacity, float loadFactor) {
        table = (Record<K, V>[]) new Object[initialCapacity];
        capacity = initialCapacity;
        size = 0;
        this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
    }

    private int hash(int hash) {
        return hash % size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = hash(key.hashCode());
        for (Record<K, V> r = table[index]; r != null; r = r.next) {
            if (r.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < size; i++) {
            for (Record<K, V> r = table[i]; r != null; r = r.next) {
                if (r.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = hash(key.hashCode());
        for (Record<K, V> r = table[index]; r != null; r = r.next) {
            if (r.getKey().equals(key)) {
                return r.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key.hashCode());
        if (table[index] == null) {
            table[index] = new Record<>(value, key);
            return null;
        }
        Record<K, V> oldRecord = table[index];
        while (oldRecord.next != null) {
            if (oldRecord.key.equals(key)) {
                V oldValue = oldRecord.value;
                oldRecord.value = value;
                size++;
                return oldValue;
            }
            oldRecord = oldRecord.next;
        }
        oldRecord.next = new Record<>(value, key);
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = hash(key.hashCode());
        if (table[index] == null) {
            return null;
        }
        Record<K, V> record = table[index];
        if (record.getKey().equals(key)) {
            V value = record.getValue();
            table[index] = record.next;
            size--;
            return value;
        }
        Record<K, V> prevRecord = record;
        while (record.next != null) {
            record = record.next;
            if (record.key.equals(key)) {
                V value = record.value;
                prevRecord.next = record.next;
                size--;
                return value;
            }
            prevRecord = record;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            for (Record<K, V> r = table[i]; r != null; r = r.next) {
                set.add(r);
            }
        }
        return set;
    }

    private void rehash(){
        int oldCapacity = table.length;
        int newCapacity = oldCapacity*2;

        if(newCapacity - Integer.MAX_VALUE > 0){
            if(oldCapacity == Integer.MAX_VALUE)
                return;
            newCapacity = Integer.MAX_VALUE;
        }

        Record<K, V>[] oldMap = table;
        Record<K, V>[] newMap = new Record[newCapacity];
        threshold = (int)Math.min(newCapacity * loadFactor, Integer.MAX_VALUE);

        table = newMap;
        for(int i = oldCapacity - 1; i > 0; i--){
            for (Record<K,V> old = oldMap[i]; old != null ; ) {
                Record<K,V> cur = old;
                old = old.next;

                int index = cur.getKey().hashCode() % newCapacity;
                cur.next = newMap[index];
                newMap[index] = cur;
            }
        }

    }

}
