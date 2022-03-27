package com.example.myhashmap.map.jdk7Version;

import com.example.myhashmap.linkedlist.LinkedList;
import com.example.myhashmap.linkedlist.Node;
import com.example.myhashmap.map.MyMap;

import java.util.HashSet;
import java.util.Set;

/**
 * 对jdk7版本的HashMap模仿，基于数组+链表实现
 */
public class MyHashMapJDK7<K, V> implements MyMap<K, V> {
    /**
     * 存放链表的数组
     */
    private LinkedList<MapNode<K, V>>[] array;

    /**
     * 存放所有key的set
     */
    private Set<K> kSet;

    /**
     * 记录所有节点的个数
     */
    private int nodeCount = 0;

    /**
     * 取余数，默认16
     */
    private int mod = 16;

    public MyHashMapJDK7() {
        this.array = new LinkedList[mod];
        this.kSet = new HashSet<>();
    }

    public MyHashMapJDK7(int mod) {
        this.mod = mod;
        this.array = new LinkedList[mod];
        this.kSet = new HashSet<>();
    }

    /**
     * 获取hashCode
     *
     * @param k
     * @return
     */
    private int getHashCode(K k) {
        //允许null为key
        return k == null ? 0 : k.hashCode();
    }

    /**
     * 获取hashCode的取模值，即数组下标
     *
     * @param k
     * @return
     */
    private int getIndex(K k) {
        return Math.abs(this.getHashCode(k) % mod);
    }

    /**
     * 查询指定key值的node节点
     *
     * @param k
     * @param node
     */
    private Node<MapNode<K, V>> getMapNode(K k, Node<MapNode<K, V>> node) {
        MapNode<K, V> e = node.e;
        if (e.key == null) {
            //当key为null时走 ==
            if (e.key == k) {
                return node;
            }
            if (node.next != null) {
                return this.getMapNode(k, node.next);
            }
        } else {
            //当key不为null时走equals
            if (e.key.equals(k)) {
                return node;
            }
            if (node.next != null) {
                return this.getMapNode(k, node.next);
            }
        }
        return null;
    }

    /**
     * 新增数据
     *
     * @param k
     * @param v
     */
    @Override
    public V put(K k, V v) throws Exception {
        //获取下标
        int index = this.getIndex(k);
        LinkedList<MapNode<K, V>> linkedList = this.array[index];
        if (linkedList == null) {
            //当前下标不存在链表
            linkedList = new LinkedList<>();
            linkedList.addFirst(new MapNode<>(k, v, this.getHashCode(k)));
            this.array[index] = linkedList;
            this.kSet.add(k);
            this.nodeCount++;
        } else {
            //当前下表存在链表
            Node<MapNode<K, V>> firstNode = linkedList.getFirstNode();
            Node<MapNode<K, V>> mapNode = this.getMapNode(k, firstNode);
            if (mapNode == null) {
                //链表中不包含这个key
                linkedList.addFirst(new MapNode<>(k, v, this.getHashCode(k)));
                this.kSet.add(k);
                this.nodeCount++;
            } else {
                //链表中包含这个key
                mapNode.e.value = v;
            }
        }
        return v;
    }

    /**
     * 根据key获取value
     *
     * @param k
     * @return
     */
    @Override
    public V get(K k) {
        //获取下标
        int index = this.getIndex(k);
        LinkedList<MapNode<K, V>> linkedList = array[index];
        if (linkedList == null) {
            return null;
        }
        Node<MapNode<K, V>> mapNode = this.getMapNode(k, linkedList.getFirstNode());
        return mapNode == null ? null : mapNode.e.value;
    }

    /**
     * 根据key删除值,并且返回删除的内容
     *
     * @param k
     * @return
     */
    @Override
    public V remove(K k) {
        //获取下标
        int index = this.getIndex(k);
        LinkedList<MapNode<K, V>> linkedList = array[index];
        if (linkedList == null) {
            return null;
        }
        Node<MapNode<K, V>> mapNode = this.getMapNode(k, linkedList.getFirstNode());
        if (mapNode == null) {
            return null;
        }
        V value = mapNode.e.value;
        linkedList.removeElement(mapNode.e);
        this.kSet.remove(k);
        this.nodeCount--;
        return value;
    }

    /**
     * 获取大小,这里的大小指的是数组的长度,即横向大小.数组中包含的链表\红黑树是竖向大小,即深度.
     *
     * @return
     */
    @Override
    public int getSize() {
        return array == null ? 0 : array.length;
    }

    /**
     * 获取所有的key
     *
     * @return
     */
    @Override
    public Set<K> keySet() {
        return this.kSet;
    }

    /**
     * 判断当前map是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    public LinkedList<MapNode<K, V>>[] getArray() {
        return array;
    }
}
