//树/森林类
public class Tree<T> {

    public TreeNode<T> root;
    public Tree()
    {
        this.root= null;
    }
    //先根遍历
    public void preorder()
    {
        for(TreeNode<T> p= this.root; p!= null; p= p.sibling)
            preorder(p);
        System.out.println();
    }
    public void preorder(TreeNode<T> p)
    {
        if(p != null)
        {
            System.out.println();
            //q遍历p.child的兄弟链
            for(TreeNode<T> q= p.child; q!= null ; q= q.sibling)
                preorder(q);
        }
    }
    //树/森林的横向凹入法
    public String toString()
    {
        return "树的横向凹入法表示： \n"+ toString(root, "");
    }
    //先根遍历以p为根的子树，tab指定缩进量，返回子树的横向凹入表示字符串，递归算法
    public String toString(TreeNode<T> p, String tab)
    {
        if(p == null)
            return "";
        return tab + p.data.toString() +"\n"+ toString(p.child, tab+"\t") + toString(p.sibling, tab);
    }
}
