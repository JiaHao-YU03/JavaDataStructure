//用java语言的接口描述抽象数据类型

public interface Set<T> {
    //数据： 集合中的数据元素，元素的数据类型为T
    //数据元素之间没有联系
    //操作：
    boolean isEmpty();          //判断集合是否为空
    int size();                 //返回元素个数
    String toString();          //返回所有集合的描述字符串
    boolean add(T x);           //增加元素x，没有指定元素插入位置；若增加，则返回true
    T search(T key);            //查找并返回与key相同的元素；若查找不成功，则返回null
    boolean contains(T key);    //判断是否包含与key相等的元素
    T remove(T key);            //删除所有元素
    //以下声明集合运算，参数是另一个集合。
    boolean equals(Object obj);         //比较this和obj 引用集合是否相同
    boolean containsAll(Set<T> set);    //判断set是否是this的子集，
    boolean addAll(Set<T> set);         //集合并，添加set的所有元素
    boolean removeAll(Set<T> set);      //集合差，删除this中那些也包含在set的元素
    boolean retainAll(Set<T> set);      //集合交，仅保留this中那些也包含在set的元素

}
