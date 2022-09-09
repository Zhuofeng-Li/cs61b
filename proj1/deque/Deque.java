package deque;//接口中是否可以有实例变量
public interface Deque<T> {
        public void addFirst(T item);
        public void addLast(T item);
        default public boolean isEmpty(){
                if (size() == 0){
                        return true;
                }
                return false;
        }
        public int size();
        public void printDeque();
        public T removeFirst();
        public T removeLast();
        public T get(int index);

}
