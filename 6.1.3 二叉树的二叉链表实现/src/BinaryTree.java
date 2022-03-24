//声明BinaryTree二叉树类，采用二叉链表储存，T泛型指定结点的元素类型
public class BinaryTree<T> {

    public BinaryNode<T> root;                          //根结点，二叉链表结点结构

    //构造空二叉树
    public BinaryTree() {
        this.root = null;
    }

    //构造二叉树，prelist数组指定二叉树标明 空子树的先根遍历序列
    public BinaryTree(T[] prelist) {
        this.root = create(prelist);
    }

    //以从i开始的标明空子树的先根序列，创建一颗以prelist[i]为根 的子树，返回子树的根结点
    //递归算法先创建根结点，再创建左子树,右子树
    //将i设为私有成员变量，对create方法为全局变量，用以返回递归的时候i的数值还在
    private int i = 0;

    private BinaryNode<T> create(T[] prelist) {
        BinaryNode<T> p = null;
        if (i < prelist.length) {
            T elem = prelist[i++];
            if (elem != null) {
                p = new BinaryNode<T>(elem);             //创建叶子结点
                p.left = create(prelist);                //创建p的左子树，递归
                p.right = create(prelist);               //创建p的右子树，递归
            }
        }
        return p;
    }

    //判断是否 空二叉树
    public boolean isEmpty() {
        return this.root == null;
    }

    //插入x元素作为根结点，x!=null，原来的根结点 当做 x的左孩子
    public void insert(T x) {
        if (x != null)
            this.root = new BinaryNode<T>(x, this.root, null);
    }

    //插入x元素作为p结点的左/右孩子，x!= null且p!= null，left指定左/右孩子，默认取值为true(左)
    //p原左/右孩子，返回插入的x元素结点，若x==null或p==null，则不插入，返回null
    public BinaryNode<T> insert(BinaryNode<T> p, boolean left, T x) {
        if (x == null || p == null) {
            return null;
        }
        if (left)                                            //插入x为p结点的左/右孩子，返回插入结点
            return p.left = new BinaryNode<T>(x, p.left, null);
        return p.right = new BinaryNode<T>(x, null, p.right);
    }

    //删除p结点的左/右孩子，left指定左/右子树，默认取值为true(左)
    public void remove(BinaryNode<T> p, boolean left) {
        if (p != null) {
            if (left)
                p.left = null;
            else
                p.right = null;
        }
    }

    //删除二叉树的所有结点，删除根结点
    public void clear() {
        this.root = null;
    }

    //先根次序遍历二叉树
    public void preorder() {
        preorder(this.root);
        System.out.println();
    }

    //先根次序遍历以p结点为根的子树，递归
    public void preorder(BinaryNode<T> p) {
        if (p != null)                                       //二叉树不空
        {
            System.out.println(p.data.toString() + " ");     //先访问当前结点
            preorder(p.left);                               //再访问p的左子树，递归，参数为左孩子
            preorder(p.right);                              //访问p的右子树，递归，参数为右孩子
        }
    }

    //中根次序遍历二叉树
    public void inorder() {
        preorder(this.root);
        System.out.println();
    }

    //中根次序遍历以p结点为根的子树，递归
    public void inorder(BinaryNode<T> p) {
        if (p != null)                                       //二叉树不空
        {
            preorder(p.left);                               //先访问p的左子树，递归，参数为左孩子
            System.out.println(p.data.toString() + " ");     //再访问当前结点
            preorder(p.right);                              //访问p的右子树，递归，参数为右孩子
        }
    }

    //后根次序遍历二叉树
    public void postorder() {
        preorder(this.root);
        System.out.println();
    }

    //后根次序遍历以p结点为根的子树，递归
    public void postorder(BinaryNode<T> p) {
        if (p != null)                                       //二叉树不空
        {
            preorder(p.left);                               //先访问p的左子树，递归，参数为左孩子
            preorder(p.right);                              //再访问p的右子树，递归，参数为右孩子
            System.out.println(p.data.toString() + " ");     //访问当前结点
        }
    }

    //二叉树的广义表表示
    //返回二叉树的广义表表示字符串
    public String toGenListString() {
        return "二叉树的广义表表示： " + toGenListString(this.root);
    }

    //返回以p结点为根的一颗子树的广义表表示字符串，先根遍历，递归
    public String toGenListString(BinaryNode<T> p) {
        if (p == null)
            return "^";                                      //返回空子树标记
        if (p.left == null && p.right == null)               //p是叶子结点
            return p.data.toString();
        return p.data.toString() + "(" + toGenListString(p.left) + "," + toGenListString(p.right) + ")";
    }

    //先根遍历二叉树的非递归算法，用栈
    public void preorderTraverse() {
        Stack<BinaryNode<T>> stack = new LinkedStack<BinaryNode<T>>();           //创建空栈
        BinaryNode<T> p = this.root;
        while (p != null || !stack.isEmpty())                                    //p非空 或 栈非空时
        {
            if (p != null) {
                System.out.print(p.data + " ");                                  //访问结点
                stack.push(p);                                                  //p 站点入栈
                p = p.left;                                                      //进入左子树
            } else {
                System.out.print("^");
                p = stack.pop();                                                 //p指向的结点出栈后，此时p指向当前栈顶元素
                p = p.right;                                                     //进入右子树
            }
        }
        System.out.println("^");
    }

    //查找并删除所有与pattern匹配的子树

//    private BinaryNode<T> pa= null;



    public void removeAll(BinaryTree<T> pattern) {
        BinaryNode<T> front = null;
        BinaryNode<T> p = null;
        BinaryNode<T> q = null;
//        BinaryNode<T> choose= null;

//        pa= pattern.root;
        p = this.root;
        q = pattern.root;
        front = p;
        search(p, q, front);
//        while(true)
//        {
//            choose= search(p, q, front);
//            if(choose == null)
//                break;
//            else if(left == true) {
//                choose.left = null;
//                p= p.left;
//            }
//            else if(right == true) {
//                choose.right = null;
//                p= p.right;
//            }
//        }

    }

    public void search(BinaryNode<T> p, BinaryNode<T> q, BinaryNode<T> front) {
//        BinaryNode<T> ch= null;
        if (p != null) {
            if (equals(p, q)) {
                if (front.left == p) {
//                    left= true;
//                    right= false;
//                    return front;
                    front.left = null;
                } else if (front.right == p) {
//                    right = true;
//                    left= false;
//                    return front;
                    front.right = null;
                }
            } else {
                search(p.left, q, p);
                search(p.right, q, p);
            }
        }
//        if(front != null)
//            return front;
//        return null;
    }

    public boolean equals(BinaryNode<T> p, BinaryNode<T> q) {

        if(p == null && q == null)
            return true;
        if(p != null && q != null && p.data == q.data && equals(p.left, q.left) && equals(p.right, q.right))
            return true;
        return false;
//        if(bo == true)
//        {
//            if(p != null && q!= null && p.data == q.data) {
//                equals(p.left, q.left);
//                equals(p.right, q.right);
//                bo= true;
//                if(p.left != null && p.right != null &&
//                        p.left.data == p.left.data && p.right.data == p.right.data)
//                   front= p;
//            }
//            else
//                bo= false;
//        }
//        if(bo == false)
//        {
//            q= pa;
//            if(p!= null)
//            {
//                if(p.data == q.data)
//                {
//                    bo= true;
//                    equals(p, q);
//
//                }
//                equals(p.left, q);
//                if(p.left != null && p.left == front)
//                    p.left= null;
//                equals(p.right, q);
//                if(p.right != null && p.right == front)
//                    p.right= null;
//
//            }
//        }


//        if (p != null && q != null && p.data == q.data) {
//            if (q.left != null && p.left != null) {
//                if (equals(p.left, q.left) && left != true)
//                    left = true;
//                else
//                    return true;
//
//            }
//
//            if (p.right != null && q.right != null) {
//                if (equals(p.right, q.right) && right != true)
//                    right = true;
//                else
//                    return true;
//            }
//            if (p.left == null && p.right == null && q != null)
//                return true;
//            if (left == true && right == true)
//                return true;
//        }
//
//        return false;
    }
    //二叉树的横向凹入表示
    public String toString()
    {
        return "二叉树的横向凹入表示：\n"+ toString(this.root, "");
    }
    public String toString(BinaryNode<T> p, String tab)
    {
        if(p == null)
            return "";
        return tab + p.data.toString() +"\n"+ toString(p.left, tab +"\t") + toString(p.right, tab +"\t");
    }
    public static void main(String[] args) {

        String[] list1={"A","B","C",null,null,
                "D","C","E",null,null,"F","H",null,null,null,"B",null,"C",null,null,
                "C","E","D",null,null,"C", "E",null,null,"F","H",null,null,null,
                "D",null,"C","H",null,null,"F","E",null,null,null};
        BinaryTree<String> bitree= new BinaryTree<String>(list1);

        System.out.println("bitree的广义表表示： "+ bitree.toGenListString());
        System.out.println(bitree.toString());

//        String[] list2={"D","G",null,null,"F",null,null};
        String[] list2={"C","E",null,null,"F","H",null,null,null};
        BinaryTree<String> pattern=new BinaryTree<String>(list2);

        System.out.println("pattern的广义表表示： "+ pattern.toGenListString());
        bitree.removeAll(pattern);
        System.out.println("删除后bitree的广义表表示： "+ bitree.toGenListString());
        System.out.println(bitree.toString());
    }
    //【例6.1】二叉树的构造、遍历及插入
//        String[] prelist= {"A", "B", "D", null, "G", null, null, null, "C", "E", null, null, "F", null, "H",null,null};
//        BinaryTree<String> bitree= new BinaryTree<String>(prelist);
    //        System.out.println("bitree的先根遍历： "); bitree.preorder();

//        String[] str1= {"C", "E", null, null, "F", null, null, null};
//        BinaryTree<String> pattern= new BinaryTree<String>(str1);

//        String[] list2={"D","G",null,null,"F",null,null};
}
//        System.out.println("bitree的先根遍历： "); bitree.preorder();

//        String[] str1= {"C", "E", null, null, "F", null, null, null};
//        BinaryTree<String> pattern= new BinaryTree<String>(str1);
