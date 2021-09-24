import java.util.ArrayList;

/**
 * 观察者模式
 * 1、首先有一个观察者接口
 */

public class test1 {
    public static void main(String[] args) {
        zhangsan zhangsan=new zhangsan();
        zhangsan.jieqian(new Wangwu());
        zhangsan.jieqian(new Zhaosi());
        zhangsan.notifyfangdai();
    }
}



interface Daikuanfang{
    void jieqian(Fangdaifang fangdaifang);//向fangfaifang借钱
    void notifyfangdai();//通知他们我有钱了
}

class zhangsan implements Daikuanfang{

    private Integer state =0;//1表示有钱
    private ArrayList<Fangdaifang> arrayList=new ArrayList<>();

    @Override
    public void jieqian(Fangdaifang fangdaifang) {
        arrayList.add(fangdaifang);
    }

    @Override
    public void notifyfangdai() {
        arrayList.forEach(fangdaifang -> fangdaifang.takeMoney());
    }
}


interface Fangdaifang{
    void takeMoney();
}

class Wangwu implements Fangdaifang{
    @Override
    public void takeMoney() {
        System.err.println("wangwu要钱");
    }
}

class Zhaosi implements Fangdaifang{
    @Override
    public void takeMoney() {
        System.err.println("zhaosi要钱");
    }
}
