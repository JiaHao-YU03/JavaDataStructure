//声明BinaryNode二叉链表结点类，T泛型指定结点元素类型
public class BinaryNode<T> {

    public T data;                              //数据域，存储数据元素
    public BinaryNode<T> left, right;           //地址域，分别指向左、右孩子结点
    //构造结点
    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right)
    {
        this.data= data;
        this.left= left;
        this.right= right;
    }
    //构造元素为data的叶子结点
    public BinaryNode(T data)
    {
        this(data, null, null);
    }
    //返回结点数据域的描述字符串
    public String toString()
    {
        return this.data.toString();
    }
    //判断是否叶子结点
    public boolean isLeaf()
    {
        return (this.left == null && this.right == null) ? true : false;
    }
}
