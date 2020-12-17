package com.zbkbl;

import java.util.Map;

/**
 * @author liuyang
 * @description LRU
 * @date 2020/12/17 19:55
 **/
public class LRUCache<K, V> {

    private int capacity;

    private int count;

    private Map<K, Node<K, V>> nodeMap;

    private Node<K, V> head;
    private Node<K, V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        Node<K, V> headNode = new Node<>(null, null);
        Node<K, V> tailNode = new Node<>(null, null);
        headNode.next = tailNode;
        tailNode.pre = headNode;
        this.head = headNode;
        this.tail = tailNode;
    }

    public void put(K key, V value) {
        Node<K, V> node = nodeMap.get(key);
        if (node == null) {
            if (count >= capacity) {
                removeNode();
            }
            node = new Node<>(key, value);

            addNode(node);
        }else {
            moveToHead(node);
        }
    }

    public Node<K, V> get(K key){
        Node<K, V> node = nodeMap.get(key);
        if(node != null){
            moveToHead(node);
        }
        return node;
    }


    public void removeNode() {
        Node<K, V> node = tail.pre;

        removeFromList(node);
        nodeMap.remove(node.key);
        count--;
    }

    public void removeFromList(Node<K, V> node){
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;

        node.pre = null;
        node.next = null;
    }

    public void addNode(Node<K, V> node){
        addToHead(node);
        nodeMap.put(node.key, node);
        count++;
    }

    public void addToHead(Node<K, V> node){
        Node next = head.next;
        next.pre = node;
        node.next = next;
        node.pre = head;
        head.next = node;
    }

    public void moveToHead(Node<K, V> node){
        removeFromList(node);
        addToHead(node);
    }

    static class Node<K, V> {
        K key;
        V value;
        Node pre;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
