//声明双链表结点类，泛型适用。比node<T>类  增加  prev前驱地址域
public class DoubleNode<T> {

    public T data;                              //数据域
    public DoubleNode<T> prev, next;            //前驱地址域，后继地址域

    public DoubleNode(T data, DoubleNode<T> prev, DoubleNode<T> next)
    {
        this.data= data;
        this.prev= prev;
        this.next= next;
    }
    public DoubleNode()                         //构造空双链表结点
    {
        this(null, null, null);
    }

    public String toString()
    {
        return this.data.toString();            //数据调用Object类toString方法
    }


}
