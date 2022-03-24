//声明Comparator<T>比较器,T指定类型

public interface Comparator<T> {

    public abstract boolean equals(Object obj);             //比较两个比较器对象是否相等
    public abstract int compare(T obj1, T obj2);            //比较T 类对象obj1和obj2 的相等和大小

}
