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
 * 阻塞式nio（接收服务端的反馈）
 */
public class TestBlockingNIO2 {

    @Test
    public void client() throws IOException {
        SocketChannel sc=SocketChannel.open(new InetSocketAddress("127.0.0.1",9236));
        FileChannel inChannel=FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        ByteBuffer buf=ByteBuffer.allocate(1024);
        while (inChannel.read(buf)!=-1){
            buf.flip();
            sc.write(buf);
            buf.clear();
        }
        sc.shutdownOutput();
        //接收反馈
        int len=0;
        while ((len=sc.read(buf))!=-1){
            buf.flip();
            System.out.println(new String(buf.array(),0,len));
            buf.clear();
        }
        inChannel.close();
        sc.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel ssc=ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9236));
        SocketChannel sc=ssc.accept();
        ByteBuffer buf=ByteBuffer.allocate(1024);
        FileChannel outChannel=FileChannel.open(Paths.get("3.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        while (sc.read(buf)!=-1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }
        //发送反馈
        buf.put("服务端接收数据完毕".getBytes());
        buf.flip();
        sc.write(buf);

        outChannel.close();
        sc.close();
        ssc.close();
    }

}
