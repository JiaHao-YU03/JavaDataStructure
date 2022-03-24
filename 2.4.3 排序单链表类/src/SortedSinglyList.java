import org.omg.CORBA.PUBLIC_MEMBER;

//声明排序单链表类，继承单链表；增加成员变量asc指定排序次序、升序和降序
//T或T 的某个祖先类”？“实现ComparaTo<?>接口，提供compareTo()方法比较对象大小和相等
public class SortedSinglyList<T extends Comparable<? super T>> extends SinglyList<T> {

    protected boolean asc;                                  //排序次序，取值为true(升序)和false(降序)
    public SortedSinglyList(boolean asc)
    {
        super();
        this.asc = asc;
    }
    public SortedSinglyList()
    {
        this(true);                                     //默认升序
    }
    //构造，由asc 指定排序次序，values数组元素填充
    public SortedSinglyList(T[] values, boolean asc)
    {
        this(asc);
        for(int i= 0; i< values.length; i++)
            this.insert(values[i]);
    }
    public  SortedSinglyList(T[] values)
    {
        this(values, true);
    }
    //拷贝构造，调用父类方法，排序次序相同
    public SortedSinglyList(SortedSinglyList<T> slist)
    {
        super(slist);                                       //调用SinglyList(SinglyList<T> list),参数复制相容
        this.asc = slist.asc;
    }
    //深拷贝：由list单链表构造，按值插入list所有元素
    public SortedSinglyList(SinglyList<T> list, boolean asc)
    {
        this(asc);
        for(Node<T> p= list.head.next; p!= null; p= p.next)
            this.insert(p.data);
    }
    public SortedSinglyList(SinglyList<T> list)
    {
        this(list, true);
    }

    //不支持父类方法，覆盖并抛出异常
    public void set(int i, T x)
    {
        throw new java.lang.UnsupportedOperationException("不需要 set(i, x)");
    }
    public Node<T> insert(int i, T x)
    {
        throw new java.lang.UnsupportedOperationException("不需要 insert(i, x)");
    }

    //插入方法： 输入x, x!=null, 根据x对象大小 顺序查找 确定插入位置，插入在等值元素之后，返回插入节点
    //由 T类的compareTo方法比较对象大小，覆盖父类的insert(x)方法，参数列表和返回值相同
    public Node<T> insert(T x)
    {
        if(x == null)
            return null;
        //循环寻找插入位置，
        Node<T> front= this.head, p= front.next;                    //front指向p的前驱结点
        while (p != null && (this.asc? x.compareTo(p.data) >= 0 : x.compareTo(p.data) <= 0))
        {
            front = p;
            p = p.next;
        }
        front.next= new Node<T>(x, p);                              //在front之后，p前插入值为x的结点
        return front.next;                                          //返回插入的x结点
    }
    //比较相等：先比较asc是否相等，再调用父类equals()方法
    public boolean equals(Object obj)
    {
        if(((SortedSinglyList<T>)obj).asc != this.asc)
            return false;
        return super.equals(obj);
    }
    //不支持直接首尾合并连接
    public void concat(SinglyList<T> list)
    {
        throw new UnsupportedOperationException("concat(SinglyList<T> list");
    }
    //集合并，对加入数据元素排序插入，this+=list
    public void addAll(SinglyList<T> list)
    {
        for(Node<T> p= list.head.next; p!= null; p= p.next)
        {
            this.insert(p.data);
        }
    }
    //返回并集：this+list,返回合并的排序单链表
    public SortedSinglyList<T> union(SortedSinglyList<T> list)
    {
        SortedSinglyList<T> result= new SortedSinglyList<T>(this);
        result.addAll(list);
        return result;
    }
    //三元组
    public void add(T tri)
    {
       this.insert(tri);
    }

    public static void main(String[] args) {
        Integer[] str1= {10, 30, 50, 80, 70};
        Integer[] str2= {20, 40, 60, 50, 40};
        SortedSinglyList<Integer> list= new SortedSinglyList<Integer>(str1);
        SortedSinglyList<Integer> list1= new SortedSinglyList<Integer>(str1);
        SortedSinglyList<Integer> list2= new SortedSinglyList<Integer>(str2);
        System.out.println("list1= "+ list1.toString());
        System.out.println("list2= "+ list2.toString());
        System.out.println(list1.equals(list2));
        System.out.println(list1.equals(list));

        SortedSinglyList<Integer> list3= new SortedSinglyList<Integer>(str1, false);
        SortedSinglyList<Integer> list4= new SortedSinglyList<Integer>(list2);
        System.out.println("list3= "+ list3.toString());
        System.out.println("list4= "+ list4.toString());
    }
}
