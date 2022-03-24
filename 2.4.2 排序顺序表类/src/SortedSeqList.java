//声明SortedSeqList<T>排序顺序表类，泛型适用
public class SortedSeqList<T extends Comparable<? super T>> extends SeqList<T>{

    //无继承构造方法
    public SortedSeqList() {
        super();
    }

    public SortedSeqList(int length) {
        super(length);
    }

    public SortedSeqList(T[] values) {
        super(values.length);
        for (int i = 0; i < values.length; i++)
            if (values[i] != null)
                this.element[this.n++] = values[i];
        //this.insert(values[i]);
    }
    //拷贝构造，深拷贝
    public SortedSeqList(SortedSeqList<? extends T> slist)
    {
        super(slist);                                           //调用Seqlist(Seqlist<T>list)
        //list引用子类实例，参数类型赋值相容
    }
    //有顺序表list构造排序顺序表this,深拷贝
    //T 为参数类型Seqlist<T>,是SortedSeqList<T>类声明的T，可比较大小
    public SortedSeqList(SeqList<? extends T> list)
    {
        super(list.element.length);                             //调用SeqList(length),以长度创建空顺序表
        //super();
        for(int i= 0; i< list.n; i++)                           //直接插入排序算法，每次插入1个元素
            this.insert(list.get(i));                           //调用子类覆盖的insert(T)方法
    }

    //不需要也不支持父类的insert(i,x)和set(i, x)
    public void set(int i, T x) {
        throw new java.lang.UnsupportedOperationException("不需要 set(int i,T x)");
    }

    public int insert(int i, T x) {
        throw new java.lang.UnsupportedOperationException("不需要 insert(int i,T X)");
    }
    //插入方法：
    //插入x, x！=null，根据x对象大小顺序查找确定插入位置（升序）。插入在 等值元素之后，返回x序号
    //调用T的compareTo方法比较对象大小，覆盖父类的insert(x),参数列表和返回值相同
    public int insert(T x)
    {
        int i= 0;
        if(this.isEmpty() || x.compareTo(this.get(this.n -1)) >0)
            i = this.n;
        else
            while(i < this.n && x.compareTo(this.get(i)) >=0)
                i++;
        super.insert(i, x);
        return i;
    }
    //查找：由key的compareTo方法比较对象大小和相等，覆盖
    //顺序查找首个与key相等的元素，返回元素序号(0<=i<n).查找不成功，返回-1
    public int search(T key)
    {
        int start= 0;
        for(int i= start; i< this.n && key.compareTo(this.get(i)) >= 0; i++)
            if(key.compareTo(this.get(i)) == 0)                     //对象相等，运行时多态
                return i;
            return -1;                                              //空表或未找到
    }
    //删除：顺序查找并删除首个与key相等元素，返回被删除元素；若查找不成功，返回null
    public T remove(T key)
    {               //查找后，再调用remove(i)。查找不成功，返回-1，不删除，其中this.search(key)执行子类的查找方法。
        return this.remove(this.search(key));
    }

    public static void main(String[] args) {

        Integer[] values= {70, 20, 80, 30, 60};
        SeqList<Integer> list1= new SeqList<Integer>(values);
        SortedSeqList<Integer> slist1= new SortedSeqList<Integer>(values);
        list1.insert(50);
        slist1.insert(50);
        list1.insert(0, 10);
        System.out.println("list1= "+ list1.toString() +"\nslist1= "+ slist1.toString());
        slist1.insert(0, 10);

        //有排序顺序表构造顺序表
        SeqList<Integer> list2= new SeqList<Integer>(values);
        list2.insert(50);
        SeqList<Integer> list3= new SeqList<Integer>(slist1);
        SortedSeqList<Integer> slist2= new SortedSeqList<Integer>(list2);

    }

}