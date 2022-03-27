package com.example.myhashmap.map.jdk7Version;

/**
 * 存储在链表中的数据，记录key、value、hashcode
 */
public class MapNode<K, V> {
    public K key;
    public V value;
    public int hashCode;

    public MapNode() {
    }

    public MapNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}
