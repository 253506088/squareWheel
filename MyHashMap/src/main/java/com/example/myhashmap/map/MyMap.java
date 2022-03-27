package com.example.myhashmap.map;

import java.util.Set;

/**
 * 一个map的基础接口
 */
public interface MyMap<K, V> {
    /**
     * 新增数据
     *
     * @param k
     * @param v
     */
    V put(K k, V v) throws Exception;

    /**
     * 根据key获取value
     *
     * @param k
     * @return
     */
    V get(K k);

    /**
     * 根据key删除值,并且返回删除的内容
     *
     * @param k
     * @return
     */
    V remove(K k);

    /**
     * 获取大小,这里的大小指的是数组的长度,即横向大小.数组中包含的链表\红黑树是竖向大小,即深度.
     *
     * @return
     */
    int getSize();

    /**
     * 获取所有的key
     *
     * @return
     */
    Set<K> keySet();

    /**
     * 判断当前map是否为空
     *
     * @return
     */
    boolean isEmpty();
}
