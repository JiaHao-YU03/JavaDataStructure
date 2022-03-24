//声明循环双链表类，泛型适用。
public class CirDoubleList<T> {

    public DoubleNode<T> head;

    public DoubleNode<T> getHead() {
        return head;
    }

    //返回单链表长度
    public int size()
    {
        int i= 0;
        for(DoubleNode<T> p= this.head.next; p!= this.head; p= p.next)     //p遍历单链表
            i++;
        return i;
    }
    //返回第i个元素，0<=i<单链表长度。若i序号越界，返回null
    public DoubleNode get(int i)
    {
        DoubleNode<T> p= this.head.next;
        for(int j= 0; p!= this.head && j< i;j++)                     //遍历寻找第i个结点（p指向）
            p = p.next;
        return (i >= 0 && p != this.head)? p : null;            //p所指向的第i个结点不为空，返回其元素值
    }
    public CirDoubleList()
    {
        this.head = new DoubleNode<T>();
        this.head.prev = this.head;
        this.head.next = this.head;
    }


    public CirDoubleList(T[] values)
    {
        this();
        DoubleNode<T> rear=this.head;
        for(int i=0;  i<values.length;  i++)
        {
            rear.next = new DoubleNode<T>(values[i], rear, this.head);
            rear = rear.next;
        }
        this.head.prev = rear;
    }
    public boolean isEmpty()                        //判断循环双链表是否为空
    {
        return this.head.next == this.head;
    }
    public String toString()
    {
        String str= "(";
//        String str= this.getClass().getName() +"(";
        for(DoubleNode<T> p= this.head.next; p!= this.head; p= p.next)
            str += p.data.toString() + ((p.next!= this.head)? ",":"");
        return str +")";
    }
    public String toPreviouString()                 //返回所有元素的描述字符串，元素次序从后向前
    {
        String str= "(";
//        String str= this.getClass().getName() +"(";
        for(DoubleNode<T> p= this.head.prev; p!= this.head; p= p.prev)      //以前驱为方向，从后向前输出元素值，到头结点为止
            str += p.data.toString() + ((p.prev!= this.head)? ",":"");
        return str +")";
    }
    //删除:删除第i个元素，返回被删除元素；0<=i<链表长度。若i越界，不删除，返回null
    public T remove(int i)
    {
        DoubleNode<T> p= this.head.next;
        for(int j= 0; p!= this.head && j< i; j++)               //遍历寻找第i个结点（p指向）
            p= p.next;
        if(p != head && i >= 0)                                 //删除p结点
        {
            p.prev.next= p.next;                                //改换前驱、后继，断开连接
            p.next.prev= p.prev;
            return p.data;                                      //返回p结点元素
        }
        return null;                                            //当i<0或i>list.length
    }
    //利用查找的基础，进行删除、替换等操作（未写，好写）
    //[思考题]查找并删除
    public DoubleNode<T> search(T key)
    {return null;}
    public T remove(T key)
    {
        DoubleNode<T> find= search(key);                //找出含key元素的结点（find指向）
        if(find != null)
        {                                               //find自己删除自己。
            find.prev.next= find.next;
            find.next.prev= find.prev;
            return find.data;
        }
        return null;
    }

    //插入
    public DoubleNode<T> insert(int i, T x)
    {
        if(x == null)                                                       //参数元素为空无意义
            return null;
        DoubleNode<T> front= this.head;
        for(int j= 0; front.next!= this.head && j< i; j++)                  //遍历寻找第i-1个或最后一个结点（front指向）
            front= front.next;
        //以下在front之后插入值为x结点，包括头插入（i<=0），中间/尾插入（i>0）
        DoubleNode<T> q= new DoubleNode<T>(x, front, front.next);           //创造结点q,在front之后
        front.next.prev= q;                                                 //更改插入q
        front.next = q;
        return q;
    }
    //直接尾插入,利用循环双链表的特性，直接在头结点之前插入，返回插入节点
    public DoubleNode<T> insert(T x)
    {
        if(x == null)
            return null;
        DoubleNode<T> q= new DoubleNode<T>(x, head.prev, head);
        head.prev.next= q;
        head.prev= q;
        return q;
    }
    //[实验2-3]双链表合并连接(测验做错，还未写)
    public void concat(CirDoubleList<T> list)
    {
        DoubleNode<T> rear= this.head;
        while(rear.next != this.head)
            rear= rear.next;
        rear.next= list.head.next;
        list.head.next.prev= rear;
        while(rear.next != list.head)
            rear= rear.next;
        rear.next= this.head;
        this.head.prev= rear;

        list.head.next= this.head;
        list.head.prev= this.head;
    }
    //两表操作[例2-2】
    //合并连接，this+=list，在this之后合并连接list中所有节点，设置list为空
    public void addAll(CirDoubleList<T> list)
    {
        DoubleNode<T> rear= head.prev;
        rear.next= list.head.next;
        list.head.next.prev= rear;
        rear= list.head.prev;

        rear.next= this.head;
        this.head.prev= rear;
        list.head.prev= list.head;
        list.head.next= list.head;
    }

    public static void main(String[] args) {

        String[] str1= {"A","B","C","D","E"};
        String[] str2= {"x","y","z"};
        CirDoubleList<String> list1= new CirDoubleList<String>(str1);
        CirDoubleList<String> list2= new CirDoubleList<String>(str2);
        System.out.println("str1= "+ list1.toString());
        System.out.println("str2= "+ list2.toString());
        list1.concat(list2);
        System.out.println("连接后，str1= "+ list1.toString());
    }
}
