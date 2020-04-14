package decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-01-23 11:28
 **/
public class LowerCaseInputStream extends FilterInputStream {

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c == -1 ? c : Character.toLowerCase((char) c));
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char)b[i]);
        }
        return result;
    }

    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }
}
