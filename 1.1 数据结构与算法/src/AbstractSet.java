public abstract class AbstractSet<T> implements Set<T> {

    public boolean contains(T key) {
        return search(key) != null;     //查找结果获得判断，search(key)由子类实现，多态运行
    }
}