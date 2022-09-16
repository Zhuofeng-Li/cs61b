package hashmap;

import java.util.*;
//设计思想:每个bukect中放置的是node(里面有key,value),hashcode的作用在与确定其在bukects中的位置
//问题:1.接口的remove等参数怎么设置 2.如果resize数组的时候,如何调整现有的Node:不用处理remove方法
//3.如何处理那些需要copy的元素:类似数组链表,直接遍历整个然后重新放置
/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /** Constructors */
    public MyHashMap() {
        initialSize = 16;//注意这里需要单独再赋值
        buckets = new Collection[16];
        for(int i = 0; i < 16; i++) {
            buckets[i] = createBucket();
        }
        maxLoad = 0.75;
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        buckets = new Collection[initialSize];
        for(int i = 0; i < initialSize; i++) {
            buckets[i] = createBucket();
        }
        this.maxLoad = 0.75;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        buckets = new Collection[initialSize];
        for(int i = 0; i < initialSize; i++) {
            buckets[i] = createBucket();
        }
        this.maxLoad = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    @Override
    public void clear() { //注意这里的clear只能把其所有的bukect中的元素换为null
        for (int i = 0; i < initialSize; i++) {
            buckets[i].clear();
        }
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        V temp = get(key);
        if (temp == null) {
            return false;
        }
        return true;
    }
    //一个可以有多个value怎么处理
    @Override
    public V get(K key) {
        int m = key.hashCode() % initialSize; //注意处理负的hash值:使用math.floormod
        if (m < 0) {
            m = Math.floorMod(m, initialSize);
        }
         //注意这里必须判断空:如果将bucket指向null的话,size无法调用
        if (buckets[m] == null) {
            return null;
        }
        for (Node i : buckets[m]){
            if (i.key.equals(key)) {//注意比较对象的值使用equals
                return i.value;
            }
        }
        return null;//注意这里有三种情况
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int m = key.hashCode() % initialSize; //使用Math.floorMod来完成负值的转换
        if (m < 0) {
            m = Math.floorMod(m, initialSize);
        }
        //注意判断空
        if (containsKey(key)) {
            for (Node i : buckets[m]) {
                if (i.key == key) {
                    i.value = value;
                    return;
                }
            }
        } else {
            size++;
            buckets[m].add(new Node(key, value));
            if (size / initialSize >= maxLoad) {
                resize(2 * initialSize);//注意创建bukect之后还需要创建对应的bukect
            }

        }
    }
    private void resize(int i) {//注意扩容之后所有的其实都需要copy一次
        Collection<Node>[] temp = new Collection[i];
        for (int k = 0; k < i; k++) {
            temp[k] = createBucket();
        }
        for (int m = 0; m < buckets.length; m++)
            for (Node j : buckets[m]) {
                if (j != null) {
                    int temp_code = j.key.hashCode() % i;
                    if (temp_code < 0) {
                        temp_code = Math.floorMod(m, initialSize);
                    }
                    temp[temp_code].add(j);
                }
            }
        initialSize = i;//注意更新不变量
        buckets = temp;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> temp = new HashSet<>();
        for (Collection<Node> i : buckets)
            for (Node m : i) {
                temp.add(m.key);
            }
        return temp;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new Map_iter();
    }
    //
    private class Map_iter implements Iterator{
        private Collection<Node>[] iter = buckets;
        int count = 0;//维护一个总数目不变量跟踪
        int count_i = 0;
        @Override
        public boolean hasNext() {
            if (count == size) {
                return false;
            }
            return true;
        }
        //这里只能把有相同hash值的一起返回
        @Override
        public Object next() {
            int i = 0;
            HashSet<K> m = new HashSet<>();
            for (Node k:buckets[count_i]) {
                m.add(k.key);
                count++;
            }
            count_i++;
            return m;
        }
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int initialSize;
    private int size = 0;
    private double maxLoad;

// You should probably define some more!


    //在mymap的方法中可以直接使用add,remove,iterator方法
    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();

    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
