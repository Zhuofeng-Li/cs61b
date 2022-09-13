package bstmap;
//如何在java中使用递归?
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> { //继承这里的接口以实现比较
    private BSTNode first; //类似链表将内部的指针实现隐藏:注意命名
    private int size;
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode temp = first;
        return containsKey_helper(first, key);
    }

    private boolean containsKey_helper(BSTNode first, K key) {
        if (first == null) {
            return false;
        } else if (first.k.equals(key)) {
            return true;
        } else {
            if (key.compareTo(first.k) < 0){
                return containsKey_helper(first.left, key);
            } else {
                return containsKey_helper(first.right, key);
            }
        }
    }

    @Override
    public V get(K key) {
        BSTNode temp = first;
        return get_helper(first, key);
    }

    private V get_helper(BSTNode first, K key) {
        if (first == null) {
            return null;
        } else if (first.k.equals(key)) {
            return first.v;
        } else {
            if (key.compareTo(first.k) < 0){
                return get_helper(first.left, key);
            } else {
                return get_helper(first.right, key);
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        first = put_helper(first, key, value);//这里必须使用first来指向直接修改其指针,而不是使用临时变量
    }

    private BSTNode put_helper(BSTNode first, K key, V value) {
        if (first == null) {
            size++;
            return new BSTNode(key, value, null, null);
        } else {
            if (key.compareTo(first.k) < 0) {
                first.left = put_helper(first.left, key, value);
            } else if (key.compareTo(first.k) > 0) {
                first.right = put_helper(first.right, key, value);
            } else {
                first.v = value;
            }
            return first;
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    //如何实现一个递归调用:单独实现一个private方法,方便递归
    public void printInOrder() {
        BSTNode temp = first;
        printInOrder(temp);//注意这里可以是同名的
    }
    private void printInOrder(BSTNode m) {
        if (m == null) {
            return;
        }
        printInOrder(m.left);
        System.out.println(m.k);
        printInOrder(m.right);
    }
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();//注意这里抛出异常的方法
    }

    private class BSTNode {//注意,的空格
        K k;
        V v;
        BSTNode left;
        BSTNode right;
        public BSTNode(K k, V v, BSTNode left, BSTNode right) {//这里是需要构造函数的
            this.k = k;
            this.v = v;
            this.left = left;
            this.right = right;
        }
    }
    public BSTMap() {
        first = null;
    }  //如何隐藏细节:直接将其指向null在方法中来实现new

}
