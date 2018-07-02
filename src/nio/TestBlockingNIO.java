package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用NIO完成网络通信的三个核心
 * 1.通道（Channel）：负责连接
 *   java.nio.channels.Channel 接口：
 *      |---SelectableChannel
 *         \--- SocketChannel(TCP)
 *         |--- ServerSocketChannel
 *         |--- DatagramChannel（UDP）
 *
 *         |-- Pipe.SinkChannel
 *         |-- Pipe.SourceChannel
 * 2.缓冲区（Buffer）：负责数据的存取
 * 3.选择器（Selector）：是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 *
 */
public class TestBlockingNIO {

    public static void main(String[] args) throws IOException {
        TestBlockingNIO nio=new TestBlockingNIO();
        nio.server();
        nio.client();
    }

    //客户端
    @Test
    public void client() throws IOException {
        //socket通道
        SocketChannel sc=SocketChannel.open(new InetSocketAddress("127.0.0.1",9236));
        //文件读取通道
        FileChannel fc=FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        //缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while (fc.read(byteBuffer)!=-1){
            byteBuffer.flip();
            sc.write(byteBuffer);
            byteBuffer.clear();
        }
        fc.close();
        sc.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        //接收通道
        ServerSocketChannel ssc=ServerSocketChannel.open();
        //绑定端口号
        ssc.bind(new InetSocketAddress(9236));
        //接收内容
        SocketChannel sc=ssc.accept();
        FileChannel outChannel=FileChannel.open(Paths.get("2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while (sc.read(byteBuffer)!=-1){
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        ssc.close();
        sc.close();
        outChannel.close();
    }

}
