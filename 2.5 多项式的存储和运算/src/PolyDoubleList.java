//多项式双链表类

public class PolyDoubleList<T extends Comparable<? super T> & Addible<? super T>> extends SortedCirDoublyList<T>{

    public PolyDoubleList()
    {
        super(true);
    }
    public PolyDoubleList(boolean asc)
    {
        super(asc);
    }
    public PolyDoubleList(T[] terms,boolean asc)
    {
        super(terms, asc);
    }


}
