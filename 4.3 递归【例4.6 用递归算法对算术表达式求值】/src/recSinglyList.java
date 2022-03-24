import org.omg.CORBA.PUBLIC_MEMBER;

import javax.print.DocFlavor;
import java.sql.SQLOutput;

//用递归，完成对单链表的操作
public class recSinglyList<T> {

    public Node<T> head;
    //构造字符串
    public recSinglyList()
    {
        this.head= new Node<T>();
    }
    public recSinglyList(T[] values)
    {
        this();
        this.head.next= create(values, 0);
    }
    public Node<T> create(T[] values, int i)
    {
        Node<T> p= null;
        if(i < values.length)
        {
            p= new Node<T>(values[i], null);
            p.next= create(values, i +1);
        }
        return p;
    }
    //返回单链表所有的描述字符串，形式为"(,)"
    public String toString()
    {
        return this.getClass().getName() +"("+ this.toString(this.head.next) +")";
    }
    public String  toString(Node<T> p)
    {
        if(p == null)                               //递归结束条件：空链表返回空串
            return "";
        String str= p.data.toString() + (p.next!=null ?",":"");                 //当前节点数据元素输出
        return str + toString(p.next);
    }


    public static void main(String[] args) {

        String[] str= {"A", "B", "C", "D", "E", "F"};
        System.out.println(new recSinglyList<String>(str).toString());
    }
}
