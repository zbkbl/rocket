package prototype;

import java.io.*;

/**
 * @description: 原型模式
 * @author: Liuyang
 * @date: 2018-11-07 11:49
 **/
public class Prototype implements Cloneable,Serializable {
    private static final long seriaVersionUID = 1L;
    private String str;


    public Object clone() throws CloneNotSupportedException {
        Prototype proto = (Prototype) super.clone();
        return proto;
    }


    public Object deepClone() throws IOException,ClassNotFoundException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
