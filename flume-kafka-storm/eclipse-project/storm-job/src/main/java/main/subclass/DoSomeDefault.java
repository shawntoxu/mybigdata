package main.subclass;

/**
 * Created by shawn.wang on 2016/8/11.
 * 默认实现类，根据传递性原则， 继承了DoSomeDefault 的类的子类 也属于DoSome的实现类
 *
 * 这个默认的类可以通过extentds 集成其他类的功能,也可以直接在类中扩展其他功能
 *
 * 如果接口 DoSome 增加了一个方法test()，
 * 要求所有的子类都有相同的行为,那么此时只需要Util类或者DoSomeDefault 去实现就可以，不需要所有的子类实现
 * 要求大部分的子类有相同的行为，个别子类有自己的行为，那么可以在子类中覆盖其父类方法实现就可以
 **/
public abstract  class DoSomeDefault extends Util implements DoSome {

}
