package com.company;

import java.util.*;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair> values = new ArrayList<>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return values.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return size() == 0;
        // END
    }

    @Override
    public boolean containsKey(Object key) {
        // BEGIN (write your solution here)
        for(K k: keySet())
            if (key != null ? key.equals(k) : k == null)
                return true;
        return false;
        // END
    }

    @Override
    public boolean containsValue(Object value) {
        // BEGIN (write your solution here)
        for(V v: values())
            if (value != null ? value.equals(v) : v == null)
                return true;
        return false;
        // END
    }

    @Override
    public V get(Object key) {
        // BEGIN (write your solution here)
        for(Entry<K, V> entry: entrySet())
            if (key != null ? key.equals(entry.getKey()) : entry.getKey() == null)
                return entry.getValue();
        return null;
        // END
    }

    @Override
    public V put(K key, V value) {
        // BEGIN (write your solution here)
        for(Entry<K, V> entry: entrySet()) {
            if (key != null ? key.equals(entry.getKey()) : entry.getKey() == null){
                V temp = entry.getValue();
                entry.setValue(value);
                return temp;
            }
        }
        Pair pair = new Pair(key, value);
        values.add(pair);
        return null;
        // END
    }

    @Override
    public V remove(Object key) {
        // BEGIN (write your solution here)
        for(Entry<K, V> entry: entrySet())
            if (key != null ? key.equals(entry.getKey()) : entry.getKey() == null){
                V temp = entry.getValue();
                values.remove(entry);
                return temp;
            }
        return null;
        // END
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>)(Set)m.entrySet())
            put(e.getKey(), e.getValue());
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        values = new ArrayList<Pair>();
        // END
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (Pair p : values) keys.add(p.getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {
        // BEGIN (write your solution here)
        Collection<V> collection = new ArrayList<>();
        for(Entry<K, V> entry: entrySet()){
            V value = entry.getValue();
            collection.add(value);
        }
        return collection;
        // END
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return (Set<Entry<K, V>>)(Set)new HashSet<>(values);
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null)
                return false;
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }
    }
}
