//声明SeqQueue顺序循环队列类，为最终类，实现队列接口，T表示数据元素的数据类型
public final class  SeqQueue<T> implements Queue<T>{

    private Object element[];                               //存储队列数据元素的数组
    private int front, rear;                                //front，rear分别为 队头 队尾 下标
    private static final int MIN_CAPACITY= 16;              //常量，指定element数组容量的最小值
    //构造空队列，length指定数组容量
    public SeqQueue(int length)
    {
        if(length < MIN_CAPACITY)
            length= MIN_CAPACITY;                           //设置队列数组容量最小值
        this.element= new Object[length];
        this.front= this.rear= 0;                           //设置空队列
    }
    //构造空队列，默认容量
    public SeqQueue()
    {
        this(MIN_CAPACITY);
    }

    //判断队列是否为空，若空返回true
    public boolean isEmpty()
    {
        return this.front == this.rear;
    }
    //元素x入队，空对象不能入队
    public boolean add(T x)
    {
        if(x == null)
            return false;
        if(this.front == (this.rear +1) % this.element.length)                      //队列满，扩充数组
        {
            Object[] temp= this.element;
            this.element= new Object[temp.length *2];                               //重新申请一个容量更大的数组
            int j= 0;
            //按照队列元素次序复制数组元素
            for(int i= this.front; i!= this.rear; i= (i +1) % temp.length)
                this.element[j++]= temp[i];
            this.front= 0;
            this.rear= j;
        }
        //正常放入元素x
        this.element[this.rear]= x;
        this.rear= (this.rear +1) % this.element.length;
        return true;
    }
    //返回队头元素，不删除。队列空，返回null
    public T peek()
    {
        return this.isEmpty() ? null : (T)this.element[this.front];
    }
    //出队，返回队头元素，不删除，队列空，返回null
    public T poll()
    {
        if(this.isEmpty())
            return null;
        T temp= (T)this.element[this.front];                                    //队列头出栈，front++
        this.front= (this.front +1) % this.element.length;
        return temp;
    }
    //返回所有元素的描述字符串
    public String toString()
    {
//        StringBuffer strb= new StringBuffer(this.getClass().getName() +"(");
        StringBuffer strb= new StringBuffer("(");
        for(int i= this.front; i!= this.rear; i= (i +1) % this.element.length)
            strb.append(this.element[i].toString() +",");
        if(this.isEmpty())
            strb.append(")");
        else
            strb.setCharAt(strb.length() -1, ')');
        return new String(strb);
    }
}
