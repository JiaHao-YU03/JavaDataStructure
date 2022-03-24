//声明Queue队列接口，描述队列抽象数据类型，T 表示数据元素的数据类型
public interface Queue<T> {

    public abstract boolean isEmpty();               //判断队列为空
    public abstract boolean add(T x);                //元素x入队，若添加成功，则返回TRUE，否则返回false
    public abstract T peek();                        //返回队头元素，不删除，若队列空， 返回null
    public abstract T poll();                        //出队， 返回队头元素。若队列空，返回null

}
