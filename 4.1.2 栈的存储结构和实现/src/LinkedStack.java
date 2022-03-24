//声明链式栈类，为最终类，实现栈接口，T泛型适用数据类型
//栈顶在 单链表头
public final class LinkedStack<T> implements Stack<T>
{
    private SinglyList<T> list;                         //使用单链表存储栈元素

    public LinkedStack()                                //构造空栈
    {
        this.list = new SinglyList<T>();                //构造空单链表
    }
    //判断栈是否空，若为空，返回true
    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }
    //元素x入栈，空对象不能入栈（insert(int i,T x)方法判断空对象）
    public void push(T x)
    {
        this.list.insert(0, x);
    }
    //返回栈顶元素（未出栈）;若栈为空，则返回null(get(i)方法判断单链表头是否为空)
    public T peek()
    {
        return this.list.get(0);
    }
    //出栈，返回栈顶元素；若栈为空，则返回null(remove(i)方法判断单链表头是否为空)
    //以单链表 头删除为操作进行出栈
    public T pop()
    {
        return this.list.remove(0);
    }
    //返回栈所有元素的描述字符串，单链表中toString方法形式为“(,)”
    public String toString()
    {
//        return this.list.toString();
        return this.getClass().getName()+" "+this.list.toString();
    }

//    //栈 深拷贝构造，执行单链表的深拷贝构造方法
//    public LinkedStack(LinkedStack<T> stack)
//    {
//        this.list = new SinglyList<T>(stack.list);
//    }
//    //栈 深拷贝，执行单链表的深拷贝构造方法
//    public void copy(LinkedStack<T> stack)
//    {
//        this.list = new SinglyList<T>(stack.list);
//    }
//    //清空栈
//    public void clear()
//    {
//        this.list.clear();
//    }
}
