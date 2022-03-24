public class SortedCirDoublyList<T extends Comparable<? super T>>  extends CirDoubleList<T>
{
    protected boolean asc;                       //排序次序，取值为true（升序）或false（降序）

    public SortedCirDoublyList(boolean asc)      //构造空排序循环双链表，asc指定升/降序
    {
        super();                                 //构造空循环双链表，默认调用父类构造方法CirDoublyList()
        this.asc = asc;
    }

    public SortedCirDoublyList()                 //构造空循环双链表，默认升序
    {
        this(true);
    }

    //构造方法，按值插入values数组元素，asc指定升/降序。直接插入排序算法
    public SortedCirDoublyList(T[] values, boolean asc)
    {
        this(asc);                               //构造空排序单链表
        for(int i=0; i<values.length; i++)       //直接插入排序算法 
            this.insert(values[i]);              //按值插入
    }
    public SortedCirDoublyList(T[] values)       //构造方法，按值插入values数组元素，默认升序
    {
        this(values, true);
    }

//    public SortedCirDoublyList(SortedCirDoublyList<T> list)//排序循环双链表的深拷贝构造方法
//    {
//        super(list);                             //调用父类同参数的构造方法，不可省略
//    }

    //不支持父类的set(int i, T x)和insert(int i, T x)方法，将其覆盖并抛出异常
    public void set(int i, T x)
    {
        throw new UnsupportedOperationException("set(int i, T x)");
    }
    public DoubleNode<T> insert(int i, T x)
    {
        throw new UnsupportedOperationException("insert(int i, T x)");
    }

    //插入x，x!=null，根据x对象大小顺序查找确定插入位置，插入在等值结点之后；返回插入结点。O(n)
    //由T类的compareTo()方法比较对象大小。覆盖父类insert(x)方法，参数列表和返回值相同。
    ////插入在等值结点之后。优先队列用
    public DoubleNode<T> insert(T x)
    {
        if(x==null)
            return null;
        //以下若x是最大值，则插入在头结点之前，即尾插入，调用父类被覆盖的insert(T)方法，O(1)
        if(this.isEmpty() || x.compareTo(this.head.prev.data)>0)
            return super.insert(x);
        //以下循环寻找插入位置，插入在等值结点之后        
        DoubleNode<T> p=this.head.next;
        while(p!=this.head && (this.asc ? x.compareTo(p.data)>=0 : x.compareTo(p.data)<=0))
            p = p.next;
        DoubleNode<T> q = new DoubleNode<T>(x, p.prev, p); //在p结点之前插入值为x结点
        p.prev.next = q;
        p.prev = q;
        return q;                                //返回插入结点
    }



    //归并两条排序循环双链表，将list中所有结点归并到this中，合并后设置list为空
    public void merge(SortedCirDoublyList<T> list)
    {
//        System.out.println("归并排序循环双链表merge");
        DoubleNode<T> p=this.head.next;                    //p遍历this循环双链表，不需要记得前驱结点
        DoubleNode<T> q=list.head.next;                    //q遍历list循环双链表
        while(p!=this.head && q!=list.head)                //遍历两条排序循环双链表
            if((p.data).compareTo(q.data)<0)               //若p结点值小，则p继续前进
                p = p.next;
            else                                           //否则，将q结点插入到p结点之前
            {
                q.prev = p.prev;
                p.prev.next = q;
                p.prev = q;
                q = q.next;
                q.prev.next = p;
            }
        if(q!=list.head)                                   //将list链表中剩余结点并入this尾
        {
            q.prev = this.head.prev;
            this.head.prev.next = q;
            list.head.prev.next=this.head;                 //使this与list的最后结点连接成为环形
            this.head.prev = list.head.prev;
        }
        list.head.prev = list.head;                        //合并后设置list为空
        list.head.next = list.head;
    }
    //返回this和list归并后的排序循环双链表，不改变this和list，一次归并算法
    public SortedCirDoublyList<T> mergeWith(SortedCirDoublyList<T> list)
    {
//        System.out.println("归并排序循环双链表mergeWith");
        DoubleNode<T> p=this.head.next, q=list.head.next;  //p、q分别遍历this、list
        SortedCirDoublyList<T> result = new SortedCirDoublyList<T>();
        DoubleNode<T> rear=result.head;                    //rear指向result链表尾，准备插入
        while(p!=this.head || q!=list.head)                //遍历两条排序循环双链表
            if(p!=this.head && (q!=list.head && (p.data).compareTo(q.data)<=0 || q==list.head))
            {                                              //复制this结点，若p结点值小，或q已结束
                rear.next = new DoubleNode<T>(p.data, rear, null);
                rear = rear.next;
                p = p.next;
            }
            else if(q!=list.head && (p!=this.head && (p.data).compareTo(q.data)>0 || p==this.head))
            {                                              //否则，复制list结点，若q结点值小，或p已结束
                rear.next = new DoubleNode<T>(q.data, rear, null);
                rear = rear.next;
                q = q.next;
            }
        result.head.prev = rear;                           //设置result链成环形
        rear.next = result.head;
        return result;
    }
}