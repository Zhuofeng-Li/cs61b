package deque;
//包的作用:deque.ArrayDeque可以使用ArrayDeque来代替
import java.util.Iterator;

public class LinkedListDeque<B> implements Deque<B>{//注意这里泛型的使用,继承也需要指定泛型
    public class IntNode{
        B value;
        private IntNode next;
        private IntNode before;
        private IntNode(B x,IntNode n,IntNode b){
            value = x;
            next = n;
            before = b;
        }

    }
    IntNode s_first;
    IntNode s_last;
    private int size;
    public LinkedListDeque(){
        s_first = new IntNode(null,null,null);//这里写null即可
        s_last = new IntNode(null,null,null);
        s_first.next = s_last;
        s_last.before = s_first;
        size = 0;
    }
    public void addFirst(B item){
        IntNode m = new IntNode(item,s_first.next,s_first);
        s_first.next.before = m;
        s_first.next = m;
        size++;
    }
    public void addLast(B item) {//注意这里相当重要:不能按照单链表处理:其需要处理四根指针,有两根在new时已经处理好了;
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
    public B removeFirst(){
        IntNode temp = s_first.next;
        if(temp.next == null){//注意这里有两个哨兵
            return null;
        }
        B temp_remove = s_first.next.value;//注意这里移动是给前一个
        s_first.next.next.before = s_first;
        s_first.next = s_first.next.next;
         //注意这里的改变顺序:1.撤掉前后节点指针的顺序(相当重要) 2.撤掉当前节点指针的顺序
        temp.before = null;//注意这里多引入的变量:如果提前切断连续first到不了后面的
        temp.next = null; //一定要同步改动
        size--;
        return temp_remove;
    }
    public B removeLast(){ //这里的操作一样需要移除四根指针
        IntNode temp = s_last.before;
        if(temp.before == null){//这里与前面道理相同
            return null;
        }
        B temp_remove = temp.value;
        s_last.before.before.next = s_last;
        s_last.before = s_last.before.before;
        temp.next = null;
        temp.before = null;
        size--;
        return temp_remove;
    }
    public B get(int index){
        IntNode temp = s_first.next;
        while (index>0){
            temp = temp.next;
            index--;
        }
        return temp.value;
    }
    //注意这里还有一个递归get方法
    public Iterator<B> iterator(){
        return new LinkedListDeque_Iter();
    }
    private class LinkedListDeque_Iter implements Iterator<B>{
        int now;
        IntNode temp = s_first;
        public LinkedListDeque_Iter(){
            IntNode temp = s_first;
            now = 0;
        }
        @Override
        public boolean hasNext() {
           if (now < size){
               return true;
           }
           return false;
        }
        @Override
        public B next() {
            temp = temp.next;
            now++;
            return temp.value;
        }
    }
    public boolean equals(Object o){
        IntNode temp = s_first.next;
        LinkedListDeque<?> temp1 = (LinkedListDeque<?>)o;
        LinkedListDeque<?>.IntNode temp2 = temp1.s_first.next;
        if(o instanceof Deque&&((Deque<?>) o).size()==this.size){
            for(int i = 0;i<size;i++){
            if(temp2.value != temp.value){
                return false;
            }
            temp2 = temp2.next;
            temp = temp.next;
        }
            return true;
    }
        return false;
    }
}
