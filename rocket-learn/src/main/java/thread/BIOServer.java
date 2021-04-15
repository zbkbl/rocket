package thread;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuyang
 * @description tset
 * @date 2021/03/15 00:30
 **/
public class BIOServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(6666);
        final Socket socket = serverSocket.accept();
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(()->{
            handler(socket);
        });
    }

    private static void handler(Socket socket){
        try{
            while (true){
                final byte[] bytes = new byte[1024];
                InputStream inputStream = socket.getInputStream();
                int read = inputStream.read(bytes);
                if(read == -1){
                    return;
                }
                String s = new String(bytes,0,read);
                System.out.print(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                socket.close();
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }
}
