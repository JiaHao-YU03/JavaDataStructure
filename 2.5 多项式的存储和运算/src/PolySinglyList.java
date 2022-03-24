//多项式排序单链表，继承排序单链表类

public class PolySinglyList<T extends Comparable<? super T> & Addible<? super T>> extends SortedSinglyList<T>{

    public PolySinglyList()
    {
        super(true);
    }
    public PolySinglyList(boolean asc)
    {
        super(asc);
    }
    //由项数组指定多项式各项值
    public PolySinglyList(T[] terms, boolean asc)
    {
        super(terms, asc);
    }

    //C(X)=A(X)+B(X),返回this(A)和list(B)相加的多项式(C),不改变this和list
    //算法不需要深拷贝和addAll(list),将A和B相加的元素插入结点到C多项式排序
    public PolySinglyList<T> union(PolySinglyList<T> list)
    {
        PolySinglyList<T> p= new PolySinglyList<>();
        Node<T> front= this.head.next;
        Node<T> rear= list.head.next;
        Node<T> p1= p.head;
        if(this.asc != list.asc)
            return null;

        while(front != null && rear != null)
        {
            if(front.data.compareTo(rear.data) == 0)
            {
                //两个系数相加，
//                int i= front.data;
                //引用值,泛型行不通
//                T obj= front.data;
                //改换用object类类型转换，是得A提取元素进行修改时，不会干扰到A
//                Object obj1= front.data;
//                Object obj2= rear.data;
                TermX term1= new TermX(((Object)front.data).toString());
                TermX term2= new TermX(((Object)rear.data).toString());
                term1.add(term2);
//                System.out.println(obj);
//                int i= (Integer)obj;
                //判断系数是否为0
                if(term1.removeable() != true)
                {
                    p1.next= new Node<T>((T)term1, null);
                    p1= p1.next;
                }
                front= front.next;
                rear= rear.next;
            }
            else if(front.data.compareTo(rear.data) > 0)
            {
                p1.next= new Node<T>(rear.data, null);
                p1=p1.next;
                rear= rear.next;
            }
            else
            {
                p1.next= new Node<T>(front.data, null);
                p1= p1.next;
                front= front.next;
            }
        }
        while(front != null)
        {
            p1.next= new Node<T>(front.data, null);
            p1= p1.next;
            front= front.next;
        }
        while(rear != null)
        {
            p1.next= new Node<T>(rear.data, null);
            p1= p1.next;
            rear= rear.next;
        }
        return p;
    }

    public static void main(String[] args) {

        TermX a[]={new TermX(2, 6), new TermX(-9, 3), new TermX(1, 2),
                new TermX(2, 0), new TermX(-1, 1)};
        PolySinglyList<TermX> apoly= new PolySinglyList<>(a,true);
        System.out.println("A= "+ apoly.toString());

        TermX b[]={new TermX(9, 8), new TermX(5, 7), new TermX(-3, 5),
                new TermX(10, 4), new TermX(-1, 2), new TermX(1, 1),
                new TermX(-1, 0)};
        PolySinglyList<TermX> bpoly= new PolySinglyList<>(b,true);
        System.out.println("\nB= "+ bpoly.toString());

        PolySinglyList<TermX> cpoly = apoly.union(bpoly);
        System.out.println("可能会改变的A= "+ apoly.toString());
        System.out.println("C=A+B，C= "+ cpoly.toString());
    }
}
