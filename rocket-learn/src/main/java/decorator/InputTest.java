package decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 11:32
 **/
public class InputTest {

    public static void main(String[] args) {
        int c;
        try {
            InputStream in = new LowerCaseInputStream(
                    new BufferedInputStream(
                            new FileInputStream("/Users/liuyang/Desktop/test.txt")
                    ));

            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
