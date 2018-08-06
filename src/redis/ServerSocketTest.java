package redis;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            Socket socket=serverSocket.accept();
            InputStream inputStream=socket.getInputStream();
            byte[] b=new byte[1024];
            inputStream.read(b);
            System.out.println(new String(b));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
