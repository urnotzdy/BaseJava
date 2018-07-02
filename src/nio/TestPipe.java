package nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/27 15:27.
 */
public class TestPipe {

    @Test
    public void testPipe() throws IOException {
        //创建管道
        Pipe pipe=Pipe.open();
        //将缓冲区的数据写入sink通道
        Pipe.SinkChannel sinkChannel=pipe.sink();
        ByteBuffer buf=ByteBuffer.allocate(1024);
        buf.put("管道测试".getBytes());
        buf.flip();
        sinkChannel.write(buf);
        buf.clear();
        //创建source读通道
        Pipe.SourceChannel sourceChannel=pipe.source();
        sourceChannel.read(buf);
        sinkChannel.close();
        sourceChannel.close();
    }

}
