public class SList{
    private class IntList{
        public int item;
        public IntList next;
        public IntList before;
        public  IntList(int i , IntList n,IntList m){
            item = i;
            next = n;
            before = m;
        }
    }
    private IntList s_first;
    private IntList s_last;
    public int size;
    public  SList(){
        s_first = new IntList(63,null,null);
        s_last = new IntList(60,null,null);
        size = 0;
    }
    public void addFirst(int x){
        s_first.next = new IntList(x,s_first.next,s_first);
        size++;
    }
    public void addLast(int x){
        s_last.before = new IntList(x,s_last,s_last.before);
        size++;
    }
    public  int getvalue(){
        return s_last.before.item;
    }

    public static void main(String[] args) {
        SList m = new SList();
        m.addFirst(5);
        m.addLast(3);
        m.addFirst(6);
        System.out.println(m.getvalue());
    }
}

