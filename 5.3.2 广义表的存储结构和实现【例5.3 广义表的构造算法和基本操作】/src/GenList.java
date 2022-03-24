//声明GenList广义表类，带表名，使用头结点的data域存储表名
//可插入共享子表，不能构造共享子表。不是递归表
public class GenList<T> implements GenLList<T> {

    public GenNode<T> head;                                             //头指针，指向头结点
    //构造空广义表，表名为null，创建头结点
    public GenList()
    {
        this.head= new GenNode<T>(null, null, null);
    }
    //构造空广义表，data指定表名
    public GenList(T data)
    {
        this.head= new GenNode<T>(data, null, null);
    }
    //构造广义表，data指定表名，atoms[]指定 原子初值，构造的是线性表，算法同单链表。
    public GenList(T data, T[] atoms)
    {
        this(data);                                                         //创建空广义表
        GenNode<T> rear= this.head;
        for (int i = 0; i < atoms.length; i++)
        {
            rear.next= new GenNode<T>(atoms[i], null, null);     //尾插入
            rear= rear.next;
        }
    }
    //判断是否空表，若空，返回true。看头结点的child 和 next是不是空的
    public boolean isEmpty()
    {
        return this.head.child == null && this.head.next == null;
    }
    //返回第i个元素结点，0≤i<表长度。若i越界，返回null。
    public GenNode<T> get(int i)
    {
        GenNode<T> p= this.head.next;
        for (int j= 0; p!= null && j< i; j++)                               //遍历，寻找第i个结点（p指向）
            p= p.next;
        //返回第i个结点，且对i元素判断是否有问题，有问题返回null
        return (i >= 0 && p != null) ? p : null;
    }
    public String getName() {
        return (String)this.head.data;
    }
    //返回this广义表的描述字符串
    public String toString()
    {
        return this.toString(this);
    }
    //返回genlist广义表的描述字符串，遍历算法，间接递归方法
    public String toString(GenList<T> genlist)
    {
        String str = (genlist.head.data == null ? "" : genlist.head.data.toString()) +"(";      //是否无/有名表
        //遍历genlist广义表，不能是递归表
        for (GenNode<T> p= genlist.head.next; p!= null; p= p.next)
            str+= toString(p) + (p.next != null ? "," : "");                                   //调用访问p结点
        return str +")";                                                                       //空表返回()
    }
    //返回p结点的广义表字符串，间接递归方法
    public String toString(GenNode<T> p)
    {
        if (p == null)
            return null;
        //若有子表，递归调用p.child子表的toString方法
        return (p.child == null) ? p.data.toString() : toString(p.child);
    }
    //返回广义表长度
    public int size()
    {
        int i= 0;
        for (GenNode<T> p= this.head.next; p!= null; p= p.next)
            i++;
        return i;
    }
    //返回广义表深度，递归方法
    public int depth()
    {
        int max= 1;
        for (GenNode<T> p= this.head.next; p!= null; p= p.next)
            if (p.child != null)
            {
                int d = p.child.depth();                            //递归调用，返回子表深度max
                if (max <= d)                                       //记住最大子表深度
                    max = d + 1;                                    //当前广义表深度为子表深度加1
            }
        return max;
    }
    //插入原子x作为第i个元素，x!=null，返回插入的原子结点。
    //对i容错，若i<0，头插入；若i>长度，尾插入。
    public GenNode<T> insert(int i, T x)
    {
        //没有插入结点。返回一种执行结果，不是错误，不抛出异常
        if (x == null)
            return null;
        GenNode<T> front= this.head;                                //front指向头结点
        //寻找第 i-1 个或 最后一个结点（front指向）
        for (int j= 0; front.next!= null && j< i; j++)
            front= front.next;
        //在front之后插入值为x结点
        front.next= new GenNode<T>(x, null, front.next);
        return front.next;                                          //返回插入的原子结点
    }
    //尾插入原子x结点,容错以整数最大值
    public GenNode<T> insert(T x)
    {
        return insert(Integer.MAX_VALUE, x);
    }
    //插入 子表glist 作为第i个元素，genlist !=null，返回插入的子表结点。
    //在插入的子表结点中，datad 存储genlist子表表名，child 引用genlist子表对象，
    //若genlist是this中的子表，则genlist成为共享子表。genlist!=this，不使this成为递归表。
    public GenNode<T> insert(int i, GenList<T> genlist)
    {
        if (genlist == null || this == genlist)                                 //不插入结点
            return null;
        GenNode<T> front= this.head;                                            //front指向头结点
        for (int j= 0; front.next!= null && j< i; j++)                          //寻找第 i-1 个或 最后一个结点（front指向）
            front= front.next;
        //下句在front之后插入子表结点，有表名，child指向（引用）genlist子表，可共享
        front.next= new GenNode<T>(genlist.head.data, genlist, front.next);
        return front.next;                                                      //返回插入的子表结点
    }
    //尾插入genlist子表,容错以整数最大值
    public GenNode<T> insert(GenList<T> genlist)
    {
        return insert(Integer.MAX_VALUE, genlist);
    }
    //删除并返回第i个元素结点，0≤i<长度；若i无效，不删除，返回null。
    //只是返回结点，不返回元素，因为其中可能包含子表
    public GenNode<T> remove(int i)
    {
        GenNode<T> front= this.head, p= null;                           //front指向头结点
        //遍历寻找第i-1结点（front指向）
        for(int j= 0; front.next!= null && j< i; j++)
            front= front.next;
        //若i在范围内，且front的后继结点(即第i个)存在，则删除之
        if(i >= 0 && front.next != null)
        {
            p= front.next;
            front.next= front.next.next;                                //删除front的后继结点，包括头删除、中间/尾删除
        }
        return p;
    }
    //删除所有元素，没有删除头结点和表名
    public void clear()
    {
        this.head.child=null;
        this.head.next=null;
    }
    //查找原子元素算法，递归遍历算法
    public GenNode<T> search()
    {
        return search(null);
    }
    //查找并返回首个与key相等元素结点
    public GenNode<T> search(T key)
    {
        return null;
    }
    //查找、删除并返回首个与key相等元素结点
    public GenNode<T> remove(T key)
    {
        return null;
    }
}