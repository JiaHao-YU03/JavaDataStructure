//声明LinkedQueue链式队列类，为最终类，实现队列接口，T表示数据元素的数据类型
public final class LinkedQueue<T> implements Queue<T>{

    private Node<T> front, rear;                                    //front和rear分别指向队头和队尾结点
    //构造空队列
    public LinkedQueue()
    {
        this.front= this.rear= null;
    }
    //判断队列是否为空。若为空，返回true
    public boolean isEmpty()
    {
        return this.front == null && this.rear == null;
    }
    //元素x入队，空对象不能入队
    public boolean add(T x)
    {
        if(x == null)
            return false;
        Node<T> q= new Node<T>(x, null);
        if(this.front == null)
            this.front= q;                                          //空队列插入
        else
            this.rear.next= q;                                      //队列尾插入
        this.rear= q;
        return true;
    }
    //返回队头元素，不删除。队列空，返回null
    public T peek()
    {
        return this.isEmpty() ? null : this.front.data;
    }
    //出队，不删除。队列空，返回null
    public T poll()
    {
        if(isEmpty())
            return null;
        T x= this.front.data;                                       //返回队头元素
        this.front= this.front.next;                                //删除头结点
        //指向同一元素，且该元素在队尾
        if(this.front == null)
            this.rear= null;
        return x;
    }
    public String toString()
    {
        //        StringBuffer strb= new StringBuffer(this.getClass().getName() +"(");
        StringBuffer strb= new StringBuffer("(");
        for(; this.front != null; this.front= this.front.next)
            strb.append(this.front.data +",");
        if(this.isEmpty())
            strb.append(")");
        else
            strb.setCharAt(strb.length() -1, ')');
        return new String(strb);
    }
}
