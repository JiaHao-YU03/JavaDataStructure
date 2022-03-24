//声明循环单链表类，泛型适用
public class CirSinglyList<T> {

    public Node<T> head;
    public CirSinglyList()                              //构造空表，尾指头
    {
        this.head = new Node<T>();
        this.head.next = this.head;
    }
    public CirSinglyList(T[] values)
    {
        this();
        Node<T> rear= this.head;
        for(int i= 0; i< values.length ;i++)
        {
            if(values[i] != null)
            {
                rear.next = new Node<T>(values[i], null);
                rear= rear.next;
                rear.next= this.head;
            }
        }
    }
    //返回单链表长度
    public int size()
    {
        int i= 0;
        for(Node<T> p= this.head.next; p!= this.head; p= p.next)     //p遍历单链表
            i++;
        return i;
    }

    public boolean isEmpty()
    {
        return this.head.next == this.head;
    }
    public String toString()
    {
            String str= "(";
//            String str= this.getClass().getName() +"(";
            for(Node<T> p= this.head.next; p!= this.head; p= p.next)
                str += p.data.toString() + ((p.next!= this.head)? ",":"");
            return str +")";
    }
    public boolean containsAll(SinglyList<T> list)
    {
        //front指向目标串头结点，p指向front下一个节点（初始为第一位），q初始指向模式串第一个数据元素（头结点下一个）
        Node<T> front= this.head, p= this.head.next, q= list.head.next;
        if(p == null || q == null)
            return false;
        while(p != this.head && q!= null)                           //p直到循环指到头结点，或q在比较中遍历完整个模式串，跳出
        {
            if(p.data == q.data)                                    //p指向数据与q指向相同
            {
                p= p.next;
                q= q.next;
            }
            else
            {                                                       //不匹配时
                q= list.head.next;                                  //q指向回到原来模式串第一位，front和p向后移一位
                p= front.next;
                front= front.next;
            }
        }
        return q == null;
    }

    public static void main(String[] args) {
        String[] str1= {"A", "B", "C", "D", "E"};
        String[] str2= {"D", "E"};
        String[] str3= {"C" ,"D"};
        CirSinglyList<String> list1= new CirSinglyList<String>(str1);
        SinglyList<String> list2= new SinglyList<String>(str2);
//        SinglyList<String> list3= new SinglyList<String>(str3);
//        System.out.println("list1= "+ list1.toString());
//        System.out.println("list2= "+ list2.toString());
//        System.out.println("list3= "+ list3.toString());
        System.out.println(list1.containsAll(list2));
   //     System.out.println(list1.containsAll(list3));
    }
}
