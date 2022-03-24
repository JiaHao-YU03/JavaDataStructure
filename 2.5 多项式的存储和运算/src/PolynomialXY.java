//多项式类
import java.util.LinkedList;
import java.util.List;

public class PolynomialXY {

    private PolyDoubleList<TermXY> listxy;


    public PolynomialXY()
    {
        this(true);
    }
    public PolynomialXY(boolean asc)
    {
        super();
        this.listxy= new PolyDoubleList<>(asc);
    }
    public PolynomialXY(TermXY[] terms, Boolean asc)
    {
        this.listxy= new PolyDoubleList<>(terms, asc);
    }
    //字符串构造多项式，分解成项
    public PolynomialXY(String str)
    {
        this();
        if(str == null)
            return;

        DoubleNode<TermXY> front= this.listxy.head;
        int begin= 0, end= 0;
        while(begin < str.length() && end < str.length())
        {
            //从end位置开始找符号
            //不是从0位开始找，跳过第一位符号位
            int i= str.indexOf('+', end+1);
            if(i == -1)
                i= str.length();
            int j= str.indexOf('-', end+1);
            if(j == -1)
                j= str.length();
            //end记录较小的位置的下一个符号
            if(i < j)
                end= i;
            else
                end= j;

            front.next= new DoubleNode<TermXY>(new TermXY(str.substring(begin, end)), front, this.listxy.head);
            front= front.next;
            this.listxy.head.prev= front;
                begin= end;
        }
    }

    public PolynomialXY(PolynomialXY poly)
    {
        this(poly.listxy.asc);
        DoubleNode<TermXY> rear= this.listxy.head;
        for(DoubleNode<TermXY> p= poly.listxy.head.next; p != null; p= p.next)
        {
            rear.next= new DoubleNode<TermXY>(new TermXY(p.data), rear,this.listxy.head);
            rear= rear.next;
        }
    }
    public String toString()
    {
        String s= " ";
        for(DoubleNode<TermXY> p= this.listxy.head.next; p != this.listxy.head ; p= p.next)
        {
//            int i= p.data.toString().indexOf("0");
//            if(i != -1)
            s += p.data.toString();
//            else
        }
        return s;
    }
    //比较两个多项式相等object
    public boolean equals(Object obj)
    {
        if(this == obj)
            return  true;
        if(obj instanceof Polynomial)
            return this.listxy.equals(((PolynomialXY) obj).listxy);
        return false;
    }
    //比较两个多项式相等polynomial
//    public boolean equals(Polynomial poly)
//    {
//        if(this == poly)
//            return true;
//        return this.list.equals(poly.list);
//    }
//    public boolean equals(PolynomialXY poly)
//    {
//        if(this == poly)
//            return true;
//        Node<TermXY> p= this.listxy.head.next;
//        Node<TermXY> q= poly.listxy.head.next;
//        while(p != null && q != null && p.data.equals(q.data))
//        {
//            p= p.next;
//            q= q.next;
//        }
//        return q == null && p == null;
//    }
    public PolynomialXY multi(PolynomialXY poly)
    {
        PolynomialXY po1= new PolynomialXY();
        DoubleNode<TermXY> db= po1.listxy.head.next;
        DoubleNode<TermXY> tb= this.listxy.head.next;

        int tsize= this.listxy.size(),  psize= poly.listxy.size();
        int t1= 0, p1= 0;

        while(t1 < tsize) {
            p1 = 0;
            DoubleNode<TermXY> pb= poly.listxy.head.next;
//            DoubleNode<TermXY> ta= this.listxy.get(t1);
            while (p1 < psize) {


//                DoubleNode<TermXY> pa= poly.listxy.get(p1);
//                int xishu= ta.data.coef * pa.data.coef;
//                int xshu= ta.data.xexp + pa.data.xexp;
//                int yshu= ta.data.yexp + pa.data.yexp;
                Object obj1 = tb.data;
                Object obj2 = pb.data;
                TermXY term1 = new TermXY(obj1.toString());
                TermXY term2 = new TermXY(obj2.toString());
                term1.multi(term2);

                DoubleNode<TermXY> rep= getrep(po1, term1.xexp, term1.yexp);
                //指数有重复的，合并同类项
                if(rep != null)
                {
                    int repnum= rep.data.coef + term1.coef;
                    //相加为0，删除
                    if(repnum == 0) {
                        po1.remove(rep);
                        db = db.prev;
                    }
                    else {
//                        db.next = new DoubleNode<TermXY>(term1, db.next.prev, po1.listxy.head);
//                        db = db.next;
                        po1.updata(db, rep, repnum);
                        db = db.next;
                    }
                }
                else
                {
                    db.next = new DoubleNode<TermXY>(term1, db, po1.listxy.head);
                    db = db.next;
                }
                po1.listxy.head.prev= db;
                pb= pb.next;
                p1++;
            }
            tb= tb.next;
            t1++;
        }
        return po1;
    }

    private void updata(DoubleNode<TermXY> db, DoubleNode<TermXY> rep, int repnum) {

        TermXY term= new TermXY(repnum, rep.data.xexp, rep.data.yexp);
        db.next = new DoubleNode<TermXY>(term, db, this.listxy.head);
        this.remove(rep);
    }

    private void remove(DoubleNode<TermXY> rep) {

        if(rep.next == null)
            rep.prev.next= null;
        else
            rep.prev.next= rep.next;
        rep.next.prev= rep.prev;
    }

    private DoubleNode<TermXY> getrep(PolynomialXY po1, int xexp, int yexp) {

        if(po1 == null)
            return null;
        for(DoubleNode<TermXY> db= po1.listxy.head.next; db != po1.listxy.head; db= db.next)
            if(db.data.xexp == xexp && db.data.yexp == yexp)
                return db;
        return null;
    }

    public static void main(String[] args) {

        PolynomialXY apoly= new PolynomialXY("1-2x^3y+x");
//        PolynomialXY apoly= new PolynomialXY("x^2-2xy+2y^2");
        System.out.println("A= "+ apoly.toString());
//        PolynomialXY bpoly = new PolynomialXY("x+y");//默认升序
        PolynomialXY bpoly= new PolynomialXY("2-2x+xy^2-9x^3y+2x^6y^2");
        System.out.println("\nB="+bpoly.toString());

        PolynomialXY cpoly = apoly.multi(bpoly);
        System.out.println("C=A*B，C="+cpoly.toString());

//        System.out.println((apoly.equals(bpoly)) ? "相同" : "不相同");
    }
}

