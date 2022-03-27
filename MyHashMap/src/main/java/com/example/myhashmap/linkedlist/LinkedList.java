package com.example.myhashmap.linkedlist;

/**
 * 在008号文档编写的单向列表基础上，为提高在此基础上实现的队列的效率，牺牲了一些删除节点的速度，
 * 将从末尾添加节点的性能从O(n)提高到O(1)。
 */
public class LinkedList<E> {
    private Node<E> dummyHead;//虚拟头结点，值为空
    private Node<E> tail;//链表末尾的节点
    private Integer size;

    public LinkedList() {
        size = 0;
        tail = null;
        dummyHead = new Node(null, tail);
    }

    /**
     * 创建链表并添加一个元素
     *
     * @param e
     */
    public LinkedList(E e) {
        dummyHead = new Node(null, new Node(e));
        size = 1;
    }

    /**
     * 将指定值插入到本链表头，原本的链表头改为第二个元素
     *
     * @param e
     */
    public void addFirst(E e) throws Exception {
        this.inset(0, e);
    }

    /**
     * 在链表尾部添加一个元素
     *
     * @param e
     * @throws Exception
     */
    public void addLast(E e) throws Exception {
        if (tail == null) {
            this.addFirst(e);
        } else {
            tail.next = new Node(e);
            tail = tail.next;
            size++;
        }
    }

    /**
     * 将指定值插入到链表中的指定位置，（链表里没有索引，只是引用一下这个概念），从0开始数。
     *
     * @param index
     * @param e
     */
    public void inset(Integer index, E e) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("索引值错误");
        } else if (index.equals(size) && tail != null) {
            this.addLast(e);
        } else {
            Node<E> buffer = dummyHead;
            for (Integer i = 0; i < index; i++) {
                buffer = buffer.next;
            }
            buffer.next = new Node(e, buffer.next);
            // 如果tail为空代表本次添加的元素是第一个元素，即是头结点也是尾节点
            if (tail == null) {
                tail = buffer.next;
            }
            size++;
        }
    }

    public Node<E> getFirstNode() {
        Node<E> node = dummyHead.next;
        return node;
    }

    /**
     * 获取最后一个链表的元素
     *
     * @return
     * @throws Exception
     */
    public E getLast() throws Exception {
        return this.get(size - 1);
    }

    /**
     * 获取链表头一个元素
     *
     * @return
     * @throws Exception
     */
    public E getFirst() throws Exception {
        return this.get(0);
    }

    /**
     * 获取指定位置的元素,从0开始
     *
     * @param index
     * @return
     */
    public E get(Integer index) throws Exception {
        this.checkIndex(index);
        Node<E> node = dummyHead.next;
        for (Integer i = 0; i < index; i++) {
            node = node.next;
        }
        return node.e;
    }

    /**
     * 更新指定位置元素的值,从0开始
     *
     * @param index
     * @param e
     * @throws Exception
     */
    public void update(Integer index, E e) throws Exception {
        this.checkIndex(index);
        Node<E> node = dummyHead.next;
        for (Integer i = 0; i < index; i++) {
            node = node.next;
        }
        node.e = e;
    }

    /**
     * 查询链表中是否包含指定元素
     *
     * @param e
     * @return
     */
    public Boolean contains(E e) {
        Node node = dummyHead.next;
        while (node != null) {
            if (node.e.equals(e)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 删除指定元素的节点，如果不存在该节点则什么也不做
     *
     * @param e
     * @return
     */
    public void removeElement(E e) {
        Node node = dummyHead.next;
        Node previous = dummyHead;
        while (node != null) {
            if (node.e.equals(e)) {
                Node deleteNode = node;
                previous.next = deleteNode.next;
                deleteNode = null;
                size--;
                break;
            }
            node = node.next;
            previous = node;
        }
    }

    /**
     * 删除指定位置的元素，从0开始
     *
     * @param index
     * @throws Exception
     */
    public E delete(Integer index) throws Exception {
        this.checkIndex(index);
        //因为这里获取的虚拟空节点，所以下面i<index而不是i<index-1
        Node<E> buffer = dummyHead;
        Node<E> deleteNode = null;
        for (Integer i = 0; i < index; i++) {
            buffer = buffer.next;
        }
        // 获取到要删除的节点
        deleteNode = buffer.next;
        //覆盖掉要删除的节点
        buffer.next = deleteNode.next;
        if (index.equals(size)) {
            tail = buffer;
        }
        E e = deleteNode.e;
        // 这是为了让JVM回收垃圾
        deleteNode = null;
        size--;
        return e;
    }

    /**
     * 删除最后一个元素
     *
     * @return
     * @throws Exception
     */
    public E deleteLast() throws Exception {
        return this.delete(size - 1);
    }

    /**
     * 删除第一个元素
     *
     * @return
     * @throws Exception
     */
    public E deleteFirst() throws Exception {
        return this.delete(0);
    }

    /**
     * 获取链表的元素个数
     *
     * @return
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 链表是否为空
     *
     * @return
     */
    public Boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String string = "size:" + size + "\tLinkedList[";
        //若不用虚拟节点，那么这里不用.next，因为dummyHead是个空节点不展现给用户
        Node buffer = dummyHead.next;
        for (Integer i = 0; i < size; i++) {
            string += buffer.e;
            buffer = buffer.next;
            if (i != size - 1) {
                string += ", ";
            }
        }
        string += "]";
        // 最右边是头
        return string;
    }

    private void checkIndex(Integer index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("索引值错误");
        }
    }

}