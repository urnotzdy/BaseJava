package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 非阻塞式nio
 */
public class TestNonBlockingNIO {

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            System.out.println(sc.next());
        }
    }

    @Test
    public void client() throws IOException {
        //获取通道
        SocketChannel sc=SocketChannel.open(new InetSocketAddress("127.0.0.1",9627));
        //切换非阻塞模式
        sc.configureBlocking(false);
        //分配指定大小的缓冲区
        ByteBuffer buf= ByteBuffer.allocate(1024);
        //发送数据给服务端
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            buf.put((new Date().toString()+scanner.next()).getBytes());
            buf.flip();
            sc.write(buf);
            buf.clear();
        }
        //关闭通道
        sc.close();
    }

    @Test
    public void server() throws IOException {
        //接收通道
        ServerSocketChannel ssChannel=ServerSocketChannel.open();
        //切换到非阻塞模式
        ssChannel.configureBlocking(false);
        //绑定接口
        ssChannel.bind(new InetSocketAddress(9627));
        //创建Selector
        Selector selector= Selector.open();
        //将通道注册到selector选择器上
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //查看选择器上是不是有“准备就绪”的通道事件
        while (selector.select()>0){
            //获取注册到选择器上的所有事件
            Iterator<SelectionKey> it=selector.selectedKeys().iterator();
            while (it.hasNext()){
                //获取准备就绪的单个事件
                SelectionKey key=it.next();
                //判断监听事件的类型
                if(key.isAcceptable()){
                    //若“接受就绪”，获取客户端的连接
                    SocketChannel sc=ssChannel.accept();
                    //设置非阻塞模式
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    //获取当前选择器上"读就绪"状态的通道
                    SocketChannel sc= (SocketChannel) key.channel();
                    //读取数据
                    ByteBuffer buf=ByteBuffer.allocate(1024);
                    int len=0;
                    while ((len=sc.read(buf))!=-1){
                        buf.flip();
                        System.out.println(new String(buf.array(),0,len));
                        buf.clear();
                    }
                }
                //取消选择键SelectionKey
                it.remove();
            }
        }
    }

}
