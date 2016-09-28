package main.subclass;

/**
 * Created by shawn.wang on 2016/8/11.
 */
public class A  extends DoSomeDefault {
    public void loaDdata(String message) {
        System.err.println("just load data  !dd" + this.getClass().getName() + " msg: "+message);
    }

    public int saveData() {
        System.err.println("just save data A ! dded");
        return 0;
    }

    @Override
    public void test() {
        System.err.println("just my  test() ");
//        super.test();
    }
}
