public class ArrayList {
    public int[] items;
    public int size;
    public ArrayList(){

        items = new int[1];
        size = 0;
    }
    public void AddLast(int x){
        if(size == items.length){
            resize(2*size);
        }
        items[size] = x;
        size++;
    }
    public void resize(int m){
        int[] a = new int[m];
        System.arraycopy(items,0,a,0,size);
        items = a;
    }

    public static void main(String[] args) {
        ArrayList m = new ArrayList();
        m.AddLast(1);
        m.AddLast(6);
        System.out.println(m.items[1]);
    }
}
