import com.sun.xml.internal.ws.api.ha.StickyFeature;
import sun.awt.image.ImageWatched;

//声明LinkedMatrix稀疏矩阵行的单链表储存的矩阵类
//行指针顺序表的元素是 排序单链表，排序单链表的元素是 三元组
public class LinkedMatrix {

    private int rows, columns;                          //矩阵行数、列数
    SeqList<SortedSinglyList<Triple>> rowlist;          //行指针矩阵，元素是排序单链表

    //构造m×n矩阵，元素为0，若m <0 或n <0,则参数错，抛出无效参数异常
    public LinkedMatrix(int m, int n) {
        if (m >= 0 && n >= 0) {
            this.rows = m;
            this.columns = n;
            this.rowlist = new SeqList<SortedSinglyList<Triple>>(m);             //构造行顺序表，初值为null
            //按照顺序表尾插入的方法,构造每一行的排序单链表，自动扩容；排序单链表默认升序
            for (int i = 0; i < m; i++)
                this.rowlist.insert(new SortedSinglyList<Triple>());
        } else
            throw new IllegalArgumentException("矩阵行列数不能<0,m= " + m + ",n= " + n);
    }

    //构造n×n矩阵，元素为0
    public LinkedMatrix(int n) {
        this(n, n);
    }

    //构造0×0矩阵，储存为最小值
    public LinkedMatrix() {
        this(0, 0);
    }

    //构造m×n矩阵，由tris三元组数组提供元素值
    public LinkedMatrix(int m, int n, Triple[] tris) {
        this(m, n);
        //按行主序插入三元组，按三元组内的行，列，元素值来设置
        for (int i = 0; i < tris.length; i++)
            this.set(tris[i]);
    }

    //构造m×n矩阵，tris字符串 指定 三元组形式的元素序列，“,”分割，没有空格
    public LinkedMatrix(int m, int n, String tris) {
        this(m, n);
        int start = 0, end = 0;
        //判断：start小于tris长度，end从start开始的三元组的当前左括号的“)”停止，index方法
        while (start < tris.length() && (end = tris.indexOf(')', start)) != -1) {
            this.set(new Triple(tris.substring(start, end + 1)));
            start = end + 2;
        }
    }

    //返回矩阵行数
    public int getRows() {
        return this.rows;
    }

    //返回矩阵列数
    public int getColumns() {
        return this.columns;
    }

    //返回第i行第j列元素。若i，j序号越界，则抛出序号越界异常
    //用(i, j, 0)比较寻找的三元组，成功，返回元素值，不成功，返回0
    public int get(int i, int j) {
        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns) {
            //在第i行排序单链表中顺序寻找
            Node<Triple> find = this.rowlist.get(i).search(new Triple(i, j, 0));
            return (find != null) ? find.data.value : 0;
        }
        throw new IndexOutOfBoundsException("i= " + i + ",j= " + j);
    }

    //设置第i行第j列元素。若i，j序号越界，则抛出序号越界异常
    //查找、插入、删除算法均比较三元组大小
    public void set(int i, int j, int x) {
        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns) {
            SortedSinglyList<Triple> link = this.rowlist.get(i);                     //先获取第i行排序单链表
            if (x == 0)
                link.remove(new Triple(i, j, 0));                             //查找成功，删除
            else {
                //查找再插入或替换元素操作，遍历link排序单链表两次
                Triple tri = new Triple(i, j, x);
                Node<Triple> find = link.search(tri);                               //顺序查找tri，若元素>tri，不成功
                if (find != null)
                    find.data.value = x;                                            //成功，修改find引用对象的成员变量值
                else
                    link.add(tri);                                  //不成功，排序单链表link按(i, j)次序插入三元组tri
                //link.insert(tri);
            }
        } else
            throw new IndexOutOfBoundsException("i= " + i + ",j= " + j);
    }

    public void set(Triple tri) {
        this.set(this.rows, this.columns, tri.value);
    }

    //返回稀疏矩阵三元组 行的单链表描述串
    public String toString() {
        String str = "";
        for (int i = 0; i < this.rowlist.size(); i++)
            str += i + "->" + this.rowlist.get(i).toString() + "\n";
        return str;
    }
    //输出矩阵
    public void printMatrix()
    {
        System.out.println("矩阵"+ this.getClass().getName() +"("+ rows +"×"+ columns +"): ");
        for(int i= 0; i< this.rows; i++)
        {
            Node<Triple> p= this.rowlist.get(i).head.next;              //遍历第i行的排序单链表
            for(int j= 0; j< this.columns; j++)
            {
                //已经有了i == p.data.row
                if(p != null && j == p.data.column)
                {
                    System.out.print(String.format("%4d",p.data.value));
                    p= p.next;
                }
                else
                    System.out.print(String.format("%4d", 0));
            }
            System.out.println();
        }
    }
    //设置矩阵为m行n列。若m指定行数较大，则将行指针顺序表扩容，使用原各行单链表
    public void setRowsColumns(int m,int n)
    {
        if(m >= 0 && n >= 0)
        {
            if(m > this.rows)                                                   //m参数指定行数较大
                for(int i= this.rows; i< m; i++)
                    this.rowlist.insert(new SortedSinglyList<Triple>());        //顺序表尾插入，自动扩容
            //对矩阵行列参数 赋值更新
            this.rows= m;
            this.columns= n;
        }
        else
            throw new IllegalArgumentException("矩阵行列数不能<0, m="+ m +",n= "+ n);
    }
//    //[思考5-2]
//    public Triple minValue()
//    {}

    public static void main(String[] args) {

        Triple[] elema = {new Triple(0, 2, 11), new Triple(0, 4, 17),
                new Triple(1, 1, 20), new Triple(3, 0, 19),
                new Triple(3, 2, 26), new Triple(3, 2, 26),
                new Triple(3, 5, 28), new Triple(4, 2, 50)};
        LinkedMatrix mata = new LinkedMatrix(5, 6, elema);
        System.out.print("A 矩阵三元组行的单链表：\n" + mata.toString());
        mata.printMatrix();
    }
}
