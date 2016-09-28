package main.subclass;

/**
 * Created by shawn.wang on 2016/8/11.
 *
 */
public class B extends DoSomeDefault {
    public void loaDdata(String message) {
        System.err.println("just load data  !" + this.getClass().getName() + " msg: "+message);
    }

    public int saveData() {
        System.err.println("just save data B !");
        return 0;
    }
}
