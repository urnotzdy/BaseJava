package nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.Set;

/**
 * 一、缓冲区（buffer）：在java NIO中负责数据的存取。缓冲区就是数组。用于存储不同类型的数据
 *
 * 根据数据类型不同，提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * FloatBuffer
 * LongBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过allocate（）获取缓冲区
 *
 * 二、缓冲区存取数据的核心方法：
 * put()：存入数据到缓冲区中
 * get():获取缓冲区的数据
 *
 * 三、缓冲区的四个核心属性
 * capacity：容量，表示缓冲区的最大存储数据的容量。一旦声明不能更改。
 * limit：界限，表示缓冲区中可以操作数据的大小。（limit后数据不能进行读写）
 * position：位置，表示缓冲区中正在操作数据的位置。
 * mark:标记，表示记录当前的position的位置。可以通过reset（）恢复到mark的位置
 * position<limit<capacity
 *
 * 四、直接缓冲区和非直接缓冲区
 * 直接缓冲区：通过allocateDirect（）分配，将缓冲区建立在物理内存中。可以提高效率
 * 非直接缓冲区：通过allocate（）分配，将缓冲区建在jvm的内存中。
 *
 * 五、字符集：Charset
 * 编码：字符串-->字节数组
 * 解码：字节数组-->字符串
 *
 */
public class BufferClass {

    public static void main(String[] args) throws CharacterCodingException {
        BufferClass bufferClass=new BufferClass();
//        bufferClass.function1();
//        bufferClass.function2();
//        bufferClass.function3();
//        bufferClass.function4();
        bufferClass.function5();
    }

    @Test
    public void function1(){
        String str="abcde";
        //分配一个制定大小的缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        System.out.println("----------allocate-----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //利用put（）向缓冲区放数据
        byteBuffer.put(str.getBytes());
        System.out.println("----------put-----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //flip（）改为读数据模式
        byteBuffer.flip();
        System.out.println("----------flip-----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //get（）从缓冲区读数据
        byte[] bytes=new byte[str.length()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println("----------get-----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //利用rewind()的，可重复读
        byteBuffer.rewind();
        System.out.println("----------rewind-----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //清空数据
        byteBuffer.clear();
        System.out.println("----------clear-----------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println((char)byteBuffer.get());
    }

    public void function2(){
        System.out.println("----function2-----");
        String str="sbcde";
        //创建缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        //放数据
        byteBuffer.put(str.getBytes());
        //读数据
        byteBuffer.flip();
        byte[] bytes=new byte[str.length()];
        byteBuffer.get(bytes,0,2);
        System.out.println(new String(bytes,0,2));
        System.out.println(byteBuffer.position());
        //标记一下
        byteBuffer.mark();
        byteBuffer.get(bytes,2,2);
        System.out.println(new String(bytes,2,2));
        System.out.println(byteBuffer.position());
        //恢复到标记的位置
        byteBuffer.reset();
        System.out.println(byteBuffer.position());
    }

    public void function3(){
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);
        System.out.println(byteBuffer.isDirect());
    }

    //所有的编码类型
    public void function4(){
        Map<String,Charset> charsets=Charset.availableCharsets();
        Set<Map.Entry<String,Charset>> entrys=charsets.entrySet();
        for(Map.Entry<String,Charset> entry:entrys){
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }

    @Test
    public void function5() throws CharacterCodingException {
        Charset cs1=Charset.forName("GBK");
        //获取编码器
        CharsetEncoder encoder=cs1.newEncoder();
        //获取解码器
        CharsetDecoder decoder=cs1.newDecoder();
        //创建缓冲区
        CharBuffer charBuffer=CharBuffer.allocate(1024);
        charBuffer.put("编码测试");
        //编码
        charBuffer.flip();
        ByteBuffer byteBuffer=encoder.encode(charBuffer);
        for(int i=0;i<byteBuffer.limit();i++){
            System.out.println(byteBuffer.get());
        }
        //解码
        byteBuffer.flip();
        CharBuffer decoderBuffer=decoder.decode(byteBuffer);
        System.out.println(decoderBuffer.toString());
        System.out.println("-------------------------------------");
        byteBuffer.flip();
        Charset cs2=Charset.forName("GBK");
        CharBuffer decodeBuffer1=cs2.decode(byteBuffer);
        System.out.println(decodeBuffer1.toString());
    }
}
