//实现TripleComparator比较器类
public class TripleComparator implements java.util.Comparator<Triple>{

    public int compare(Triple triple1, Triple triple2)
    {
        return (int)(triple1.value - triple2.value);
    }
}
