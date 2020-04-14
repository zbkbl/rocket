package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-09-05 11:20
 **/
public class NioDemo {

    public static void main(String[] args) {
        try {
            copyFileUseNIO("/Users/liuyang/Desktop/WechatIMG55.jpg", "/Users/liuyang/Desktop/copyFile.jpg");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void copyFileUseNIO(String src, String dst) throws IOException {
        FileInputStream fis = new FileInputStream(new File(src));

        FileOutputStream fos = new FileOutputStream(new File(dst));

        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(true){
            int eof = inChannel.read(buffer);
            if(eof == -1){
                break;
            }
            // 切换到读模式
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        inChannel.close();
        outChannel.close();
        fis.close();
        fos.close();
    }
}
