package main;
import main.subclass.DoSome;
import main.subclass.DoSomeDefault;
import scala.util.control.Breaks.TryBlock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.servlet.jsp.tagext.TryCatchFinally;
/**
 * Created by shawn.wang on 2016/8/11.
 */
public class Test {
    public static final String a = "-----test messge ----";
    public static ExecutorService executorService  =  java.util.concurrent.Executors.newCachedThreadPool();

    public  static void  runTask(final String className) {
    	try {
        executorService.submit(new Runnable() {
            public void run() {
                try {
                    //在这里不管是反射出抽象类 还是接口都可以作为调用父类使用
//                    DoSome   doSome =   (DoSome)Class.forName(className).newInstance() ;
//                    doSome.loaDdata(a);
//                    System.out.println(Thread.currentThread().getName());
//                    Thread.currentThread().sleep(1000);
//                    doSome.saveData();
                    //通过接口调用的话 就没有Util 类中的方法

                    DoSomeDefault doSomeDefault =  (DoSomeDefault)Class.forName(className).newInstance() ;
                    doSomeDefault.loaDdata(a);
                    doSomeDefault.saveData();
                    //调用Util 类中的方法
                    doSomeDefault.getConnent();
                    //
                    doSomeDefault.test();

                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
        });
        
		} finally {
//			executorService.shutdown(); 
			
		}
    	
        
    }
    public static void main(String[] args) throws  Exception{
//        String ss = Test.class.getResource("/").getPath();
//        System.out.printf(ss);
        //将接口的子类实现全类路径写入配置文件，读取配置文件反射实例化每一个实现类，调用其方法
    	
//         LineIterator it = FileUtils.lineIterator(new File("/root/ngrok/Test/config/class.txt"),"UTF-8");
         LineIterator it = FileUtils.lineIterator(new File("E:\\source2\\storm-job\\src\\main\\resources\\class.txt"),"UTF-8");

        List<String>  subClass = new ArrayList<String>();
        try {
            while (it.hasNext()) {
                final String line = it.nextLine();
//                System.out.println(line);
                    subClass.add(line);
            }
        } finally {
            it.close();
        }
        //commit to ThreadPool run
        for (String ss:subClass
             ) {
//            System.out.printf(ss);
            runTask(ss);
        }
        //
    }
}

