package hashtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author K1ta
 */
public class HashTable<K, V> implements Map<K, V> {

    private class Elem{
        Entry<K,V> curEntry = null, nextEntry = null;
    }

    private Elem[] table;
    private int size;
    private float loadFactor;
    private int capacity;
    private int treshold;

    public HashTable() {
        new HashTable(11, 0.75f);
    }

    public HashTable(int initialCapacity) {
        new HashTable(initialCapacity, 0.75f);
    }

    public HashTable(int initialCapacity, float loadFactor) {
        table = (Elem[]) new Object[initialCapacity];
        capacity = initialCapacity;
        size = 0;
        this.loadFactor = loadFactor;
        treshold = (int) (capacity * loadFactor);
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
        /*int index = hash(key.hashCode());
        ArrayList<Entry<K, V>> list = table.get(index);
        if (list == null) {
            return false;
        }
        for (Entry<K, V> e : list) {
            if (e.getKey().equals(key) && e.hashCode() == key.hashCode()) {
                return true;
            }
        }
        return false;*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V get(Object key) {
        /*int index = hash(key.hashCode());
        ArrayList<Entry<K, V>> list = table.get(index);
        for (Entry<K, V> e : list) {
            if (e.getKey().equals(key) && e.hashCode() == key.hashCode()) {
                return e.getValue();
            }
        }
        return null;*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
