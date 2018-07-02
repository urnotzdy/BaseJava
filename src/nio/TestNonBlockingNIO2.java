package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/27 11:36.
 */
public class TestNonBlockingNIO2 {

    @Test
    public void client() throws IOException {
        DatagramChannel dChannel=DatagramChannel.open();
        dChannel.configureBlocking(false);
        ByteBuffer buf=ByteBuffer.allocate(1024);
        buf.put("datagramChannel test".getBytes());
        buf.flip();
        dChannel.send(buf,new InetSocketAddress("127.0.0.1",9623));
        buf.clear();
        dChannel.close();
    }
    @Test
    public void server() throws IOException {
        DatagramChannel dChannel=DatagramChannel.open();
        dChannel.configureBlocking(false);
        dChannel.bind(new InetSocketAddress(9623));
        Selector selector=Selector.open();
        dChannel.register(selector, SelectionKey.OP_READ);
        while (selector.select()>0){
            Iterator<SelectionKey> it=selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key=it.next();
                if(key.isReadable()){
                    ByteBuffer buf=ByteBuffer.allocate(1024);
                    dChannel.receive(buf);
                    buf.flip();
                    System.out.println(new String(buf.array(),0,buf.limit()));
                    buf.clear();
                }
                it.remove();
            }
        }
    }

}
