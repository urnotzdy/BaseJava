package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

import java.io.IOException;
import java.net.Socket;

//创建自己的jedis客户端
public class SelfJedis {

    private Socket socket;

    public SelfJedis(Socket socket) {
        this.socket = socket;
    }

    public SelfJedis() {
        try {
            socket = new Socket("127.0.0.1",8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String set(String key,String value){
        //遵循resp协议
        StringBuffer buffer=new StringBuffer();
        buffer.append("*3").append("/r/n");//数组3个
        buffer.append("$").append(Protocol.Command.SET.name().length()).append("/r/n");
        buffer.append(Protocol.Command.SET).append("/r/n");//发送命令
        buffer.append("$").append(key.length()).append("/r/n");
        buffer.append(key).append("/r/n");
        buffer.append("$").append(value.length()).append("/r/n");
        buffer.append(value).append("/r/n");
        byte[] b=new byte[1024];
        try {
            socket.getOutputStream().write(buffer.toString().getBytes());//往外写数据
            socket.getInputStream().read(b);//读取响应
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(b);
    }

    public static void main(String[] args) {
        SelfJedis selfJedis=new SelfJedis();
        System.out.println(selfJedis.set("name","123"));

    }

}
