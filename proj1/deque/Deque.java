package deque;//接口中是否可以有实例变量
public interface Deque<B> {
        public void addFirst(B item);
        public void addLast(B item);
        default public boolean isEmpty(){
                if (size() == 0){
                        return true;
                }
                return false;
        }
        public int size();
        public void printDeque();
        public B removeFirst();
        public B removeLast();
        public B get(int index);

}
