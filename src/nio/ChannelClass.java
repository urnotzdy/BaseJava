package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 1.通道（Channel）：
 *  用于源节点与目标节点的连接。在java NIO中负责缓冲区中数据的传输。Channel本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 2.通道的主要实现类：
 *  java.nio.channels.Channel 接口
 *       |--FileChannel  本地通道
 *       |--SocketChannel  网络通道 TCP
 *       |--ServerSocketChannel
 *       |--DatagramChannel UDP
 * 3.获取通道
 *  1）java针对支持通道的类提供了getChannel（）方法
 *     本地IO：
 *          FileInputStream/FileOutputStream
 *          RandomAccessFile
 *      网络IO:
 *          Socket、ServerSocket、DatagramSocket
 *  2）在JDK 1.7中NIO.2 针对各个通道提供了静态方法open()
 *  3）在JDK 1.7中NIO.2 的Files工具类的newByteChannel()
 * 4.通道之间的传输
 *  1）transferFrom()
 *  2）transferTo()
 * 5.分散（scatter）与聚集（gather）
 *  1)分散读取（Scattering Readers):将通道中的数据分散熬多个缓冲区中
 *  2)聚集写入（Gathering Writes）:将多个缓冲区中的数据聚集到通道中
 */
public class ChannelClass {

    public static void main(String[] args){
        ChannelClass channelClass=new ChannelClass();
//        channelClass.function1();
        try {
//            channelClass.function2();
//            channelClass.function3();
//            channelClass.function4();
            channelClass.function5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用对支持通道的类提供的getChannel()方法
    public void function1(){
        //创建流
        FileInputStream inputStream=null;
        FileOutputStream outputStream=null;
        //创建通道
        FileChannel inChannel=null;
        FileChannel outChannel=null;
        try {
            inputStream=new FileInputStream("C:\\install Path\\1.jpg");
            outputStream=new FileOutputStream("C:\\install Path\\2.jpg");
            outChannel=outputStream.getChannel();
            inChannel=inputStream.getChannel();
            //分配指定大小的缓存区
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            //将通道中的数据存入缓存区
            while (inChannel.read(byteBuffer)!=-1){
                byteBuffer.flip();
                //将缓冲区的数据写入通道中
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outChannel!=null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //使用直接缓冲区完成对文件的复制（内存映射文件）
    public void function2() throws IOException {
        //创建通道
        FileChannel inChannel=FileChannel.open(Paths.get("C:\\install Path\\1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel=FileChannel.open(Paths.get("C:\\install Path\\3.jpg"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
        //创建缓冲区
        MappedByteBuffer inBuffer=inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
        MappedByteBuffer outBuffer=outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());
        //从缓冲区中读
        byte[] dst=new byte[inBuffer.limit()];
        inBuffer.get(dst);
        //王缓冲区中写
        outBuffer.put(dst);
        inChannel.close();
        outChannel.close();
    }

    //使用通道传输
    public void function3() throws IOException {
        FileChannel inChannel=FileChannel.open(Paths.get("C:\\install Path\\1.jpg"),StandardOpenOption.READ);
        FileChannel outChannel=FileChannel.open(Paths.get("C:\\install Path\\4.jpg"),StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
//        outChannel.transferFrom(inChannel,0,inChannel.size());
        inChannel.transferTo(0,inChannel.size(),outChannel);
    }

    //使用通道传输
    public void function4() throws Exception {
        RandomAccessFile inFile=new RandomAccessFile("C:\\install Path\\1.jpg","rw");
        RandomAccessFile outFile=new RandomAccessFile("C:\\install Path\\5.jpg","rw");
        FileChannel inChannel=inFile.getChannel();
        FileChannel outChannel=outFile.getChannel();
//        outChannel.transferFrom(inChannel,0,inChannel.size());
        inChannel.transferTo(0,inFile.length(),outChannel);
    }

    //使用通道传输
    public void function5() throws Exception {
        RandomAccessFile inFile=new RandomAccessFile("C:\\install Path\\1.txt","rw");
        FileChannel inChannel=inFile.getChannel();
        //分散读取
        ByteBuffer buffer1=ByteBuffer.allocate(100);
        ByteBuffer buffer2=ByteBuffer.allocate(1024);
        ByteBuffer[] byteBuffers={buffer1,buffer2};
        inChannel.read(byteBuffers);
        for(int i=0;i<byteBuffers.length;i++){
            byteBuffers[i].flip();
        }
        System.out.println(new String(byteBuffers[0].array(),0,buffer1.limit()));
        System.out.println("------");
        System.out.println(new String(byteBuffers[1].array(),0,buffer2.limit()));
        //聚集写入
        RandomAccessFile outFile=new RandomAccessFile("C:\\install Path\\2.txt","rw");
        FileChannel outChannel=outFile.getChannel();
        outChannel.write(byteBuffers);
    }
}
