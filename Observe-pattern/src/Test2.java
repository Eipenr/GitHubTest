import java.util.ArrayList;

/**
 * 观察者模式
 * 1、首先要有一个主题对象接口
 * 2、要有一个观察者接口
 * 3、还要有被观察的对象
 * 4、要有观察的对象
 */
public class Test2 {
    public static void main(String[] args) {
        ZhangSan zhangSan=new ZhangSan();
        zhangSan.jieqian(new ZhaoSi());
        zhangSan.jieqian(new WangWu());
        zhangSan.notifyFangDai();
    }
}

interface DaiKuanFang{
    void jieqian(FangDaiFang fangdaifang);
    void notifyFangDai();
}

class ZhangSan implements DaiKuanFang{

    ArrayList<FangDaiFang> arrayList=new ArrayList<>();
    private Integer money=0;

    @Override
    public void jieqian(FangDaiFang fangdaifang) {
        arrayList.add(fangdaifang);
    }

    @Override
    public void notifyFangDai() {
        arrayList.forEach(fangdaifang -> fangdaifang.takeMoney());
    }
}

interface FangDaiFang{
    void takeMoney();
}

class ZhaoSi implements FangDaiFang{

    @Override
    public void takeMoney() {
        System.err.println("zhaosi要你还钱");
    }
}

class WangWu implements FangDaiFang{
    @Override
    public void takeMoney() {
        System.err.println("wangwu要你还钱");

    }
}