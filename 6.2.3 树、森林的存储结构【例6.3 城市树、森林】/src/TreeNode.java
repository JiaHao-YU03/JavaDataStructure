//树节点类，父母孩子兄弟链表结构
public class TreeNode<T> {

    public T data;                                  //数据域
    public TreeNode<T> parent, child, sibling;      //父母、孩子、兄弟结点
}
