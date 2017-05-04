package nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

//test file 
public class TestFile {
	
	
	public static void readIn(){
		
		
	}
	
	public static void writ() throws IOException{
		
//		  File file =new File("e:/source2/main/src/main/java/nio/nio-data2.txt");
//	      //if file doesnt exists, then create it
//	      if(!file.exists()){
//	    	  System.out.println("creaet a new file ");
//	       file.createNewFile();
//	      }

	      //true = append file
	      FileWriter fileWritter = new FileWriter("e:/source2/main/src/main/java/nio/nio-data2.txt",true);
	             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	             bufferWritter.write("ttttttt");
	             bufferWritter.newLine(); // new line 
	             bufferWritter.flush();
	             bufferWritter.close();
	             
	             fileWritter.close();
	             
	         
	}
	
	public static void Test() throws IOException{
		 FileReader fr = new FileReader("e:/source2/main/src/main/java/nio/nio-data.txt");
		   BufferedReader bf = new BufferedReader(fr);
		   int b;
		   while((b=bf.read())!=-1){
		    System.out.println(bf.readLine());
		   }
	}
	
	
	public static void NioTest() throws IOException {
		
		RandomAccessFile aFile = new RandomAccessFile("e:/source2/main/src/main/java/nio/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		//create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(48);
		 //从通道向buffer 写入数据
		int bytesRead = inChannel.read(buf);
		
		byte[]  a = new byte[2] ;
		while (bytesRead != -1) {  //返回-1 表示到了文件末尾
			System.out.println("Read " + bytesRead);
			//从写模式切换到读取模式
			buf.flip();
			 
			while(buf.hasRemaining()){
			//System.out.print((char) buf.get()); // read 1 byte at a time
			System.out.println(new String(new char[] {buf.getChar()}));
			}
			//清空缓存，让其可以继续写入数据 // compact()方法只会清除已经读过的数据
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}
	
	
	public static void main(String[] args) throws Exception {
	

//		Test();
		
	writ();
	}
}

//Java NIO 有以下Buffer类型
//ByteBuffer
//MappedByteBuffer
//CharBuffer
//DoubleBuffer
//FloatBuffer
//IntBuffer
//LongBuffer
//ShortBuffer
