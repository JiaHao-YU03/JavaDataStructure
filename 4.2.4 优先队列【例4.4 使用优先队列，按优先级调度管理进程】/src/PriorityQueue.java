//声明PriorityQueue优先队列， 为最终类，实现队列接口，使用排序单链表存储，元素可比较大小
public final class PriorityQueue<T extends Comparable<? super T>> implements Queue<T> {

    private SortedSinglyList<T> list;                       //排序单链表，存储队列元素，按升/降序

    public PriorityQueue(boolean asc)                       //构造空队列，asc指定升序（true）或降序（false）
    {
        this.list= new SortedSinglyList<T>(asc);
    }
    public PriorityQueue()                                  //构造空队列，默认升序
    {
        this(true);
    }
    public PriorityQueue(T[] objs, boolean asc)             //构造优先队列，asc指定升/降序
    {
        this(asc);
        for(int i= 0; i< objs.length; i++)
            this.add(objs[i]);                              //元素入队
    }

    //判断队列是否，若为空，返回true
    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }
    //元素x入队，空对象不能入队
    public boolean add(T x)
    {
        return this.list.insert(x) != null;                         //排序单链表按值插入，比较元素大小
    }

    //返回队头元素，没有删除，若队列为空，返回null
    public T peek()
    {
        return this.list.get(0);
    }
    //出队，返回队头元素，若队列为空，返回null
    public T poll()
    {
        return this.list.remove(0);                     //返回队头元素，删除队头结点
    }
    public String toString()
    {
        return this.list.toString();
    }
}
