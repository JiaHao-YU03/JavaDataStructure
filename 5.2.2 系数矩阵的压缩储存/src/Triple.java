//声明稀疏矩阵非零元素三元组类，实现可比较接口
//稀疏矩阵三元组顺序表 缺失 随机存储特性和数据移动量大
//稀疏矩阵三元组链式表 遍历 时间复杂度大
public class Triple implements Comparable<Triple> {

    int row, column, value;                             //行号、列号、元素值、默认访问权限

    //构造方法，参数依次指定行号、列号为负、则抛出无效参数异常
    public Triple(int row, int column, int value) {
        if (row >= 0 && column >= 0) {
            this.row = row;
            this.column = column;
            this.value = value;
        } else
            throw new IllegalArgumentException("行、列号不能为负数：row= " + row + ",column=" + column);
    }

    //字符串构造方法，形式为“(,)”，没有空格
    public Triple(String triple) {
        int i = triple.indexOf(','), j = triple.indexOf(',', i + 1);              //查找两个','字符的序号
        //未处理数值格式异常
        this.row = Integer.parseInt(triple.substring(1, i));
        this.column = Integer.parseInt(triple.substring(i + 1, j));
        this.value = Integer.parseInt(triple.substring(j + 1, triple.length() - 1));

        if (this.row < 0 || this.column < 0)
            throw new IllegalArgumentException("行、列号错误：row=" + row + ", column=" + column);
    }

    //拷贝构造方法，复制一个三元组
    public Triple(Triple triple) {
        this(triple.row, triple.column, triple.value);
    }

    //返回三元组描述字符串
    public String toString() {
        return "(" + row + "," + column + "," + value + ")";
    }

    //返回矩阵对称位置元素的三元组
    public Triple toSymmetry() {
        return new Triple(this.column, this.row, this.value);
    }

    //比较this与obj三元组是否相等，比较其位置和元素值
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof Triple)
        {
            Triple triple= (Triple)obj;
            return this.row== triple.row && this.column == triple.column && this.value == triple.value;
        }
        return false;
    }
    //根据行列位置(行主序) 比较三元组对象大小，与元素值无关，约定三元组排序次序
    public int compareTo(Triple triple)
    {
        if(this.row == triple.row && this.column == triple.column)                  //相等条件，与equal()方法含义不同
            return 0;
        return (this.row < triple.row || this.row == triple.row && this.column < triple.column)? -1 : 1;
    }
}
