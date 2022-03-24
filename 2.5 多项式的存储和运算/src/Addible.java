//可相加接口

public interface Addible<T> {

    public void add(T obj);                     //+=,约定元素相加规则
    public boolean removeable();                //约定删除元素的条件
}
