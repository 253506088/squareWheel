package com.example.myhashmap.linkedlist;

/**
 * 链表的node节点
 *
 * @param <E>
 */
public class Node<E> {
    public E e;
    public Node<E> next;

    public Node(E e) {
        this.e = e;
    }

    public Node(E e, Node<E> next) {
        this.e = e;
        this.next = next;
    }

    public Node() {
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
