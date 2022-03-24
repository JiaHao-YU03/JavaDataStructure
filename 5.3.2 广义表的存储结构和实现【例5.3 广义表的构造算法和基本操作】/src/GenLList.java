//声明GenLList广义表抽象数据类型
public interface GenLList<T> {

    boolean isEmpty();                      //判断是否为空表，若为空，则返回true
    int size();                             //返回广义表长度
    int depth();                            //返回广义表深度
    GenNode<T> get(int i);                  //返回第i个结点，0<=i<表长度
    GenNode<T> insert(int i, T x);          //插入原子x作为第i个结点，返回插入节点
    GenNode<T> insert(int i, GenList<T> genlist);        //插入Genlist作为第i个结点，返回插入结点
    GenNode<T> remove(int i);               //删除第i个结点，0<=i<表长度，返回被删除结点
    void clear();                           //删除全部结点，头结点和表名还在
    GenNode<T> search();                    //查找并返回首个与key相等的原子结点
    GenNode<T> remove(T key);               //查找并删除首个与key相等的原子结点，返回被删除结点
}
