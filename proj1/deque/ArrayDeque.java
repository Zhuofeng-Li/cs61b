package deque;

import java.util.Deque;

public class ArrayDeque<B> {
    B[] items;
    int size;
    int before;
    int next;
    public ArrayDeque(){
        items = (B[]) new Object[8];//如何创建一个泛型数组?
        size = 0;
        before = 4;
        next = 5;
    }
    //add方法的关键在于在数组中添加元素的位置
    //这里的设计思想是:当next == before时一定只有一个元素,直接重整数组这样才会方便使用resize,如果没有next == before的情况那么满的时候重整即可(复制的方式不同)
    //这里只有两种情况:1.在循环处处理 2.在两端处处理
    public void addFirst(B item){
        //对于size == items.length可以忽略了永远不可能
        items[before] = item;
        size++;
        //还需要添加一下before与last的规定:
        if(before == 0 && size != items.length){//这里改变数组之后必须改
            before = items.length - 1;
            if(next == before){
                resize_line(2*size); //这里相当于从before在最前面,next在最后面
            }
        } else{
            before--;//先--容易混淆
        }

        if(before == next){
            resize4(2*size);//这里需要复制两次:找出最方便的算数组序号的方法
        }
    }
    private void resize_line(int i) {
        B[] temp = (B[]) new Object[i];
        System.arraycopy(items,0,temp,5,size);
        items = temp;
        before = 4;
        next = size + 5;
    }

    public void addLast(B item){
        items[next] = item;
        size++;
        if(next == items.length - 1&&size != items.length){
            next = 0; //注意这里重整数组的方式
            if(before == next){
                resize2(2*size);
            }
        }
        else{
            next++;
        }
        if(before == next){
            resize4(2*size);
        }
    }
    private void resize2(int i) {//注意这里与resize_line的区别
        B[] temp = (B[]) new Object[i];
        System.arraycopy(items,before + 1,temp,5,size);
        items = temp;
        before = 4;
        next = size + 5;
    }
//    private void resize(int i) {
//        B[] temp = (B[]) new Object[i];
//        System.arraycopy(items,before + 1,temp,5,size - before);//注意:before指向的是null所以必须用before + 1,复制是必须用size而不是length
//        System.arraycopy(items,0,temp,size - before + 5,before);//计算数的个数:确定第一数的位置,确定最后一个数的位置相减+1计算
//        //这里拷贝同样有问题:next在before前后
//        //直接copy太容易弄不清楚了,直接定义遍历last->before即可
//        items = temp;
//        before = 4;
//        next = size + 5;
//    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        String temp_string = new String();
       if(before < next){
           for(int i = before + 1;i < next;i++){
               if(items[i] == null){
                   continue;
               }
               temp_string += items[i];
               System.out.println(items[i]);
           }
       }
       else{
           int temp = before;
           for(int i = before + 1;i < items.length;i++){//注意这里同样不能用size,因为remove了size,如果要表示最后使用length最保险
               if(items[i] == null){
                   continue;
               }
               temp_string += items[i];
               System.out.println(items[i]);
           }
           for(int i = 0;i < next;i++){
               if(items[i] == null){
                   continue;
               }
               temp_string += items[i];
               System.out.println(items[i]);
           }
       }
    }
    //是否需要按顺序打印?:需要
    //注意这里虽然是循环但是内定了顺序
    /*
    移除first:1.需要将移除的指向null;
    2.需要检查size与length的大小关系,如果在(重新建立一个resize函数)
    3.在没有填满的地方first++可能导致first跳到首段
     */
    public B removeFirst(){
        if (size == 0) {//注意这里不能用length因为有的框没有被丢弃
            return null;}//注意这里的before是指向null的
        if(before == items.length - 1){
            before = 0;
            size--;
        }
        else{
            size--;
            before++;//注意这里++与移动的顺序

        }
        B temp = items[before];
        items[before] = null;
        if(items.length >= 16){
            if (size < items.length * 0.25){
                if(before > next){
                    resize4(4*size);
                }
                else{
                    resize2(4*size);
                }
            }
        }
        return temp;}
    public B removeLast(){
        if (size == 0) {
            return null;
        }

        if(next == 0){
            next = items.length - 1;
        } else{
            next--;
        }
        size--;
        B temp = items[next];
        items[next] = null;
        if(items.length >= 16){
            if (size < items.length * 0.25){
                if(before > next){
                    resize4(4*size);
                }
                else{
                    resize2(4*size);
                }
            }
        }
        return temp;
    }
    private void resize4(int i) {
        B[] temp = (B[]) new Object[i];
        System.arraycopy(items,before + 1,temp,5,items.length - before - 1);//注意这里不再是size了而是length代表到底
        System.arraycopy(items,0,temp,items.length - before + 4,next);//注意数组的个数到底是怎么算的:例如len = 1;post = 5;那么下一个为6
        items = temp;
        before = 4;
        next = size + 5;

    }
    public B get(int index){//注意这里index的定义
        if(index > size - 1){
            return null;
        }
        if(before <= next){
            return items[before + index + 1];
        }
        else{
            int m = items.length - 1 - before;
            if(m >= index + 1){//数与个数的关系
                return items[before + index + 1];}
            else{
                return items[index - m];
                }
            }
        }
    public boolean equal(Object o){
        if(! (o instanceof Deque)){
            return false;
        }
        else{
            o = (ArrayDeque) o;//是否可以这么转化
            return  this.printDeque_string().equals(o);
        }
    }
    public String printDeque_string(){
        String temp_string = new String();
        if(before < next){
            for(int i = before + 1;i < next;i++){
                if(items[i] == null){
                    continue;
                }
                temp_string += items[i];
            }
            return temp_string;
        }
        else{
            int temp = before;
            for(int i = before + 1;i < items.length;i++){//注意这里同样不能用size,因为remove了size,如果要表示最后使用length最保险
                if(items[i] == null){
                    continue;
                }
                temp_string += items[i];
            }
            for(int i = 0;i < next;i++){
                if(items[i] == null){
                    continue;
                }
                temp_string += items[i];
            }
            return temp_string;
        }
    }
    }
