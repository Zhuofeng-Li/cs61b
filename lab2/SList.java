public class SList{
    private class IntList{
        public int item;
        public IntList next;
        public IntList(int i , IntList n){
            item = i;
            next = n;
        }
    }
    private IntList m;
    public int size;
    public  SList(){
        m = new IntList(63,null);
        size = 0;
    }
    public void addFirst(int x){
        m.next = new IntList(x,m.next);
        size++;
    }
    public void addLast(int x){
        IntList p = m;//这里必须传递
        while(p.next != null){
            p = p.next;
        }
        p.next = new IntList(x,null);
        size++;
    }
    public  int getvalue(){
        return m.item;
    }

    public static void main(String[] args) {
        SList n = new SList();
        //n.addFirst(5);
        n.addLast(25);
        System.out.println(n.size);
    }
}

