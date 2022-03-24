//声明Stack栈接口，具有栈抽象数据类型，T表示数据元素的数据类型
public interface Stack<T> {

    public abstract boolean isEmpty();                      //判断栈是否是空的
    public abstract void push(T x);                      //元素x 入栈
    public abstract T peek();                            //返回栈顶元素，未对元素做栈操作
    public abstract T pop();                             //出栈，并返回栈顶元素

}
