import com.sun.org.apache.xml.internal.utils.NSInfo;

import java.nio.file.NotDirectoryException;

//声明单链表类SinglyList<T>
public class SinglyList<T> extends Object{

    public Node<T> head;                                       //头指针，指向头结点
    //(1)构造方法
    public SinglyList()                                        //构造空单链表
    {
        this.head = new Node<T>();                             //创建头结点，data和next值为null
    }
    public SinglyList(T[] values)                              //构造时，尾插入values数组元素，忽略中间空对象
    {
        this();                                                //创建空链表，只有头结点
        Node<T> rear= this.head;                               //rear尾指针指向单链表的最后一个结点
        for(int i= 0; i< values.length; i++)                   //values.length==0,为空链表
        {
            if(values[i] != null)
            {
                rear.next = new Node<T>(values[i], null);       //尾插入，创造结点链入rear之后
                rear = rear.next;                                    //rear指向下一个全新的结点
            }
        }
    }

    //拷贝构造。
    /*浅拷贝
    public SinglyList(SinglyList<T> list)
    {
        this.head= list.head;
    }*/
    //深拷贝
    public SinglyList(SinglyList<T> list)
    {
        this();                                                 //创建空链表
        Node<T> rear= this.head;                                //rear指向新的空链表
        for(Node<T> p= list.head.next; p!= null; p= p.next)     // p指向参数列表
        {
            rear.next= new Node<T>(p.data, null);           //rear指向结点引用p的数据元素
            rear= rear.next;
        }
    }
    //[思考题]拷贝构造，复制对象(未成功)
   /* public SinglyList(SinglyList<String> list)
    {
        this();                                                 //创建空链表
        Node<String>[] object= new Node[list.size()];
        Node<String> p= list.head.next;
        for(int i= 0; p!= null && i< list.size(); p= p.next, i++)     // p指向参数列表
        {
                object[i]= new Node<String>(p.data, null);
        }
        int j= 0;
        for(Node<String> front= new Node<String>(); j< list.size(); j++)
        {
            front.next= object[j];                      //front.next= new Node<T>((T)object(i),null)
            front= front.next;
        }
    }*/
    //构造，集合并。
//    public SinglyList<T> union(SinglyList<T> list)
//    {
//        SinglyList<T> result= new SinglyList<T>(this);
//        result.addAll(list);
//        return  result;
//    }


/*   [思考题]头插入，构造反序单链表：单链表次序与数组元素次序相反
    public SinglyList<T>(T[] values)
    {
        this();
        for(int i= 0; i< values.length; i++)
            this.head.next= new Node<T>(values[i], this.head.next);

    }*/
    //(2)判空、存取元素、求长度、返回描述字符串等方法
    public boolean isEmpty()
    {
        return this.head.next == null;
    }
    //返回第i个元素，0<=i<单链表长度。若i序号越界，返回null
    public T get(int i)
    {
        Node<T> p= this.head.next;
        for(int j= 0; p!= null && j< i;j++)                     //遍历寻找第i个结点（p指向）
            p = p.next;
        return (i >= 0 && p != null)? p.data : null;            //p所指向的第i个结点不为空，返回其元素值
    }
    //设置第i个元素为x,0<=i<单链表的长度 且x!=null
    //若x==null,抛出空对象异常；若i序号越界，抛出序号越界异常
    public void set(int i, T x)
    {
        if(x == null)
            throw new NullPointerException("x == null");
        else
        {
            Node<T> p= this.head.next;
            for(int j= 0; p!= null && j< i; j++)                //遍历寻找第i个结点
                p = p.next;
            if(i >= 0 && p != null)
                p.data = x;                                     //data设置为x,p指向
            else throw new IndexOutOfBoundsException(i +"");
        }
    }
    //返回单链表长度
    public int size()
    {
        int i= 0;
        for(Node<T> p= this.head.next; p!= null; p= p.next)     //p遍历单链表
            i++;
        return i;
    }
    //返回所有元素的描述字符串，形式为"(,)"。覆盖object类的toString()方法
    public String toString()
    {
//        String str= "(";
        String str= this.getClass().getName() +"(";                    //返回类名
        for(Node<T> p= this.head.next; p!= null; p= p.next)             //遍历单链表
            str += p.data.toString() + (p.next != null?",":"");
        //p指向的下一个结点不为空(不是最后结点)，加“：“
        return str +")";                                               //空表则返回（）
    }

    //（3）插入：插入x为第i个元素，x!=null,返回插入结点。对i容错，若i<0,则头插入；若i>长度，则尾插入。
    public Node<T> insert(int i, T x)
    {
        if(x == null)
            return null;
        Node<T> front= this.head;                               //front指向头结点
        for(int j= 0; front.next!= null && j< i; j++)           //遍历寻找第i-1个或最后一个结点（front指向）
            front = front.next;
        front.next = new Node<T>(x, front.next);        //front后插入值为x结点，包括头插入，中间/尾插入
        return front.next;
    }
    //尾插入x,
    public Node<T> insert(T x)
    {
        //调用insert（i， x），用整数最大值保证容错，可插入最后
        return insert(Integer.MAX_VALUE, x);
    }

    //(4)删除
    //删除第i个元素，返回被删除元素；若i越界，返回null
    public T remove (int i)
    {
        Node<T> front= this.head;
        for(int j= 0; front.next!= null && j< i; j++)
            front = front.next;                                 //指向第i-1个结点（front指向）
        if(i >= 0 && front.next != null)                        //若front的后继结点存在，则删除
        {
            T x= front.next.data;
            //删除front的后继，包括头删除、中间/尾删除
            front.next= front.next.next;
            return x;
        }
        return null;
    }
    public void clear()
    {
        this.head.next= null;
    }
    //(5)顺序查找和基于查找算法的操作:查找并返回首个与key相等的元素；若查找不成功，返回null(自写)
    public Node<T> search(T key)
    {
        for(Node<T> front= this.head; front.next!= null; front= front.next)
        {
            if(key.equals(front.data))
                return (Node<T>) front.data;
        }
        return null;
    }

    //顺序查找并删除首个与key 相等的元素，返回被删除元素；若查找不成功，返回null
    public T remove(T key)
    {

        //for循环遍历单链表（front指向p的前驱结点）,顺序查找与key相等的元素结点
        for(Node<T> front = this.head, p= front.next; p!= null; front= p, p= p.next)
        {
            if(key.equals(p.data))
            {
                front.next= p.next;                     //删除此时p所指向的结点，包括头删除
                return p.data;                          //返回p所指的结点数据
            }
        }
        return null;
    }
    //替换:找到key，用x替换
    public void replaceAll(T key, T x)
    {}
    //单链表运用（未写）
    public double average(SinglyList<Integer> list)
    {return 0;}
    public double averageExceptMaxMin(SinglyList<Integer> list)
    {return 0;}
    //[思考题2-2] 单链表逆转
    public static <T> void reverse(SinglyList<T> list)
    {
        if(list.isEmpty() == true)
            throw new NullPointerException("list1单链表为空，无效。");
        Node<T> front= list.head;
        Node<T> p= front.next;
        Node<T> s= p.next;
        for(int i= 0; p!= null; i++)
        {
            if(i == 1)
            {
                front.next= null;
                p.next= front;
            }
            else if(i > 1)
                p.next= front;
            front= p;
            p= s;
            if(s != null)
                s= s.next;
        }
        list.head.next= front;
    }
    //比较相等，覆盖
    public boolean equals(Object obj)
    {
        if(obj == this)                                         //引用比较
            return true;
        if(!(obj instanceof SinglyList<?>))                     //数据类型比较
            return false;
        Node<T> p= this.head.next;
        Node<T> q= ((SinglyList<T>)obj).head.next;
        //利用两条指向依次向后相互比较，利用短路特性，判空放前
        while(p!= null && q!= null && p.data.equals(q.data))
        {
            p= p.next;
            q= q.next;
        }
        return p == null && q == null;
    }
    //集合并运算
//    public void addAll(SinglyList<T> list)
//    {
//        this.concat(new SinglyList<T>(list));
//    }
    public void concat(SinglyList<T> list)
    {
        Node<T> rear= this.head;                    //rear遍历this单链表，找到最后一个结点
        while(rear.next != null)
            rear = rear.next;
        rear.next= list.head.next;                  //在rear结点之后连接 list的首个元素结点
        list.head.next= null;                       //设置list为空，否则逻辑出错。修改了list引用的单链表
    }

    //替换this中所有与pattern匹配的子表换成list（深拷贝）
    public void replaceAll(SinglyList<T> pattern, SinglyList<T> list)
    {
        Node<T> front= this.head, move= front.next, last, q= pattern.head.next;

        while(move != null)
        {
                if (move.data == q.data)
                {
                    move = move.next;
                    q = q.next;
                }
                else
                {
                    front = front.next;
                    move = front.next;
                    q = pattern.head.next;
                }
                if (q == null)
                {
                    //对list串深拷贝，node遍历list，每次创建节点，使front指向该节点。
//                    for (Node<T> node = list.head.next; node != null && node != move; node = node.next)
//                    {
//                        front.next = new Node<T>(node.data, null);
//                        front = front.next;
//                        //node遍历完list串
//                        if (node.next == null)
//                            front.next = move;
//                    }
                    Node<T> lista= list.head.next;
                    Node<T> node= new Node<T>();
                    Node<T> nodefront= node;
                    for(; lista!= null; node= node.next)
                    {
                        node.next= new Node<T>(lista.data, null);
                        lista= lista.next;
                    }
                    node.next= move;
                    front.next= nodefront.next;
                    front= node;
                    front = front.next;
                    //q返回指向pattern首个数据元素
                    q = pattern.head.next;
//                    //move遍历完this,front指向this尾节点时
//                    if (move == null)
//                        break;
//                    //当一个元素匹配，move指向this尾节点时；或move此时与pattern串匹配，判断数据元素相等
//                    //相等，front和move不会依次向后指向，不相等，直接向后延
//                    if (move.data == q.data)
//                        continue;
                    if (move == null)
                        break;
                    else
                        move= move.next;
                }
        }
    }


    public static void main(String[] args) {

        String[] str= {"B","B","C","B","E"};
        String[] pattern={"B","C"}, list={"A","C"};

        SinglyList<String> list1= new SinglyList<String>(str);

        SinglyList<String> pattern1= new SinglyList<String>(pattern);
        SinglyList<String> lists= new SinglyList<String>(list);

        System.out.println("list1= "+ list1.toString());

        list1.replaceAll(pattern1, lists);
        System.out.println("替换后，list1="+ list1.toString());
    }
//        String[] pattern={"B","B"}, list={"A","C"};
//        String[] pattern={"B","B","C"}, list={"A","C"};
//        SinglyList<String> list2= new SinglyList<String>(list1);
    //        System.out.println("list2= "+ list2.toString());
//        System.out.println("删除A，list2= "+ list2.remove("A").toString());
    //        System.out.println("list2= "+ list2.toString());
//        System.out.println("删除A，list2= "+ list2.remove("A").toString());
    //        System.out.println("list2= "+ list2.toString());
//        System.out.println("删除A，list2= "+ list2.remove("A").toString());
    //        System.out.println("list2= "+ list2.toString());
//        reverse(list1);
//        System.out.println("逆转后，list1= "+ list1.toString());
}
