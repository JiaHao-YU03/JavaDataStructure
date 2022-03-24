//声明GenNode广义表结点类,T泛型表示数据元素的数据类型
public class GenNode<T> {

    public T data;                                  //泛型数据域
    public GenList<T> child;                        //地址域，指向子表
    public GenNode<T> next;                         //地址域，指向后继结点
    //构造方法
    public GenNode(T data, GenList<T> child, GenNode<T> next)
    {
        this.data= data;
        this.child= child;
        this.next= next;
    }
    public GenNode()
    {
        this(null, null, null);
    }
    public String toString()
    {
        return this.data.toString();
    }
}
