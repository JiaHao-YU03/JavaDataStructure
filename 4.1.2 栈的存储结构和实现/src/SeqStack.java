//声明顺序栈类，为最终类，实现栈接口，T泛型适用数据类型
//栈顶在 顺序表尾部
public final class SeqStack<T> implements Stack<T>
{
    private SeqList<T> list;                                //使用顺序表存储栈元素

    public SeqStack(int length)                             //构造容量为length的空栈
    {
        this.list = new SeqList<T>(length);                  //执行顺序表构造方法
    }
    public SeqStack()                                       //构造默认为64的空栈
    {
        this(64);
    }
    //判断栈是否空，若为空，返回true
    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }
    //元素x入栈，空对象不能入栈
    public void push(T x)
    {
        //顺序表尾插入元素x，或元素长度等于数组，自动扩充容量
        this.list.insert(x);
    }
    //返回栈顶元素（未出栈），若栈为空，则返回null（get(i)方法返回null）
    public T peek()
    {
        return this.list.get(list.size()-1);
//        return this.isEmpty() ? null : this.list.get(list.size()-1);
    }
    //出栈，返回栈顶元素；若栈为空，则返回null（remove(i)方法返回null）
    //若栈不空，顺序表尾删除，返回删除元素
    public T pop()
    {
        return this.list.remove(list.size()-1);
    }

    //返回栈所有元素的描述字符串，顺序表中toString方法形式为“(,)”形式为“(,)”
    public String toString() {
        return //this.getClass().getName()+
                this.list.toString();//输出顺序表
    }
    //反序输出顺序栈
    public String toPreviousString()
    {
        return //this.getClass().getName()+" "+
                this.list.toPreviousString();//反序输出顺序表
    }

//    //栈 深拷贝构造方法,执行顺序表的深拷贝构造方法
//    public SeqStack(SeqStack<T> stack)
//    {
//        this.list = new SeqList<T>(stack.list);
//    }
//    //栈 深拷贝,执行顺序表的深拷贝构造方法
//    public void copy(SeqStack<T> stack)
//    {
//        this.list = new SeqList<T>(stack.list);
//    }
//    //清空栈
//    public void clear()
//    {
//        this.list.clear();
//    }
//    //返回元素个数
//    public int size()
//    {
//        return this.list.size();
//    }
}
