package deque;
//包的作用:deque.ArrayDeque可以使用ArrayDeque来代替
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{//注意这里泛型的使用,继承也需要指定泛型
    private class IntNode{
        T value;
        private IntNode next;
        private IntNode before;
        private IntNode(T x,IntNode n,IntNode b){
            value = x;
            next = n;
            before = b;
        }

    }
    private IntNode s_first;
    private IntNode s_last;
    private int size;
    public LinkedListDeque(){
        s_first = new IntNode(null,null,null);//这里写null即可
        s_last = new IntNode(null,null,null);
        s_first.next = s_last;
        s_last.before = s_first;
        size = 0;
    }
    public void addFirst(T item){
        IntNode m = new IntNode(item,s_first.next,s_first);
        s_first.next.before = m;
        s_first.next = m;
        size++;
    }
    public void addLast(T item) {//注意这里相当重要:不能按照单链表处理:其需要处理四根指针,有两根在new时已经处理好了;
  //注意考虑两个哨兵,这两段可以合并了
            IntNode m = new IntNode(item, s_last, s_last.before);//注意构造函数中参数的顺序
            s_last.before.next = m;
            s_last.before = m;
        size++;
    }
    public int size(){
        return size;
    }
    public void printDeque(){//注意哨兵不变量
        IntNode p = s_first.next;
        while(p.next!=null){
            System.out.print(p.value);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println("");
    }
    public T removeFirst(){
        IntNode temp = s_first.next;
        if(temp.next == null){//注意这里有两个哨兵
            return null;
        }
        T temp_remove = s_first.next.value;//注意这里移动是给前一个
        s_first.next.next.before = s_first;
        s_first.next = s_first.next.next;
         //注意这里的改变顺序:1.撤掉前后节点指针的顺序(相当重要) 2.撤掉当前节点指针的顺序
        temp.before = null;//注意这里多引入的变量:如果提前切断连续first到不了后面的
        temp.next = null; //一定要同步改动
        size--;
        return temp_remove;
    }
    public T removeLast(){ //这里的操作一样需要移除四根指针
        IntNode temp = s_last.before;
        if(temp.before == null){//这里与前面道理相同
            return null;
        }
        T temp_remove = temp.value;
        s_last.before.before.next = s_last;
        s_last.before = s_last.before.before;
        temp.next = null;
        temp.before = null;
        size--;
        return temp_remove;
    }
    public T get(int index){
        IntNode temp = s_first.next;
        if(index < 0){
            return null;
        }
        while (index>0 && temp != s_last){
            temp = temp.next;
            index--;
        }
        if(temp == s_last){
            return null;
        }
        return temp.value;
    }
    public T getRecursive(int index){//这里需要辅助函数
        IntNode temp = s_first.next;
        if(index < 0){
            return null;
        }else if(index == 0){
            return temp.value;
        }
        return null;
    }
    //注意这里还有一个递归get方法
    public Iterator<T> iterator(){
        return new LinkedListDeque_Iter();
    }
    private class LinkedListDeque_Iter implements Iterator<T> {
        int now;
        IntNode temp = s_first;

        public LinkedListDeque_Iter() {
            IntNode temp = s_first;
            now = 0;
        }

        @Override
        public boolean hasNext() {
            if (now < size) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                temp = temp.next;
                now++;
                return temp.value;
            }
            return null;
        }
    }
    public boolean equals(Object o){//注意这里只要是Deque就需要判断,必须统一接口
        IntNode temp = s_first.next;
        if(!(o instanceof Deque)){
            return false;
        } else if (o instanceof ArrayDeque) {
            //注意这里的类型转化,为什么直接转化不行
            return ((ArrayDeque) o).printDeque_string().equals(this.printDeque_string());
        }
        else if(((LinkedListDeque<?>) o).size()==this.size){
            return ((LinkedListDeque) o).printDeque_string().equals(printDeque_string());
    }
        return false;
    }

    public String printDeque_string() {
        IntNode p = s_first.next;
        String temp = new String();
        while(p.next!=null){
            temp += p.value;//这里会自动转
            p = p.next;
        }
        return temp;
    }
}

