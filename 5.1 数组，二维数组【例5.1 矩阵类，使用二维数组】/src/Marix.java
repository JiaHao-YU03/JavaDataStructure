//【例5.1】 矩阵类，使用二维数组
public class Marix {

    private int rows, columns;                                  //代表矩阵行数，列数
    private int[][] element;                                    //二维数组，存储矩阵数组
    private  static final int MIN_CAPACITY= 6;                  //常量，保证element数组容量的最小值
    //构造m×n矩阵，元素为0，若m<0 或 n<0,则参数错，抛出无效参数异常
    public Marix(int m, int n)
    {
        if(m >= 0 && n >= 0)
        {
            this.rows= m;
            this.columns= n;
            //若0<= m,n <= MIN_CAPACITY,容错
            if(m < MIN_CAPACITY)
                m= MIN_CAPACITY;
            if(n < MIN_CAPACITY)
                n= MIN_CAPACITY;
            this.element= new int[m][n];                        //数组初始化元素为0
        }
        else
            throw new IllegalArgumentException("矩阵行列数不能<0, m="+ m +",n="+ n);
    }
    //构造n×n矩阵
    public Marix(int n)
    {
        this(n, n);
    }
    //构造0×0矩阵
    public Marix()
    {
        this(0, 0);
    }
    //构造m×n矩阵,由values提供元素
    public Marix(int m, int n, int[][] values)
    {
        this(m, n);
        for(int i= 0; i< values.length && i< m; i++)
            for(int j= 0; j< values[i].length && j< n; j++)
                this.element[i][j]= values[i][j];
    }
    public int getRows()
    {
        return this.rows;
    }
    public int getColumns()
    {
        return this.columns;
    }
    //注意：i,j序号越界，抛出异常
    public int get(int i, int j)
    {
        if(i >= 0 && i < this.rows && j >= 0 && j < this.columns)
            return this.element[i][j];
        throw new IndexOutOfBoundsException("i="+ i +"j="+ j);
    }
    //注意：i,j序号越界，抛出异常
    public void set(int i, int j, int x)
    {
        if(i >= 0 && i < this.rows && j >= 0 && j < this.columns)
            this.element[i][j]= x;
        else
            throw new IndexOutOfBoundsException("i="+ i +"j="+ j);
    }
    //行主序遍历，返回矩阵元素描述字符串
    public String toString()
    {
        String str= " 矩阵"+ this.getClass().getName() +"("+ this.rows +"×"+ this.columns +"):\n";
        for(int i= 0; i< this.rows; i++)
        {
            for(int j= 0; j< this.columns; j++)
                str += String.format("%6d", this.element[i][j]);
            str += "\n";
        }
        return str;
    }
    //设置矩阵为m行n列，若参数指定行列数较大，则将矩阵扩容，复制原有矩阵元素
    public void setRowsColumns(int m, int n)
    {
        if(m > 0 && n > 0)
        {
            //判断m，n是否超出范围,超出就扩容
            if(m > this.element.length || n > this.element[0].length)
            {
                int[][] source = this.element;
                this.element = new int[m * 2][n * 2];
                for(int i= 0; i< this.rows; i++)
                    for(int j= 0; j< this.columns; j++)
                        this.element[i][j]= source[i][j];
            }
            this.rows= m;
            this.columns= n;
        }
        else
            throw new IllegalArgumentException("矩阵行列数不能<0,m="+ m +",n="+ n);
    }

    public static void main(String[] args) {

        int[][] value= {{1, 2, 3},{4, 5, 6, 7, 8},{9}};                         //动态数组，每行长度不一
        Marix mata= new Marix(3, 4, value);
        mata.set(2, 3, 10);
        System.out.println("A "+ mata.toString());
    }
}
