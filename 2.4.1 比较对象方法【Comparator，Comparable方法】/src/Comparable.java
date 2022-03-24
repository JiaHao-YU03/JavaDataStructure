//声明可比较接口，T 通常是实现该接口的当前类
public interface Comparable<T> {

    public abstract int compareTo(T obj);
}
