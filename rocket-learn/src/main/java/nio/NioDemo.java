package nio;

import io.protostuff.ProtostuffIOUtil;
import raw.Student;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-09-05 11:20
 **/
public class NioDemo {

    private static final String path = "/data/app/rps/remote-config/rps-boss";

    public static void main(String[] args) {
//        try {
//            copyFileUseNIO("/Users/liuyang/Desktop/WechatIMG55.jpg", "/Users/liuyang/Desktop/copyFile.jpg");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        PluginRegisterInfoPo p = new PluginRegisterInfoPo();
        p.setId(1);
        p.setPluginName("shortList");
        p.setType(1);
        p.setInterfaceName("shortListInterface");
        p.setImplementation("shortListImpl");
        p.setRcdType(1);

        PluginRegisterInfoPo p1 = new PluginRegisterInfoPo();
        p1.setId(2);
        p1.setPluginName("longList");
        p1.setType(2);
        p1.setInterfaceName("longListInterface");
        p1.setImplementation("longListImpl");
        p1.setRcdType(2);

        List<PluginRegisterInfoPo> list = new ArrayList<>();
        list.add(p);
        list.add(p1);
        writeList(list);
        readList();
    }


    public static void copyFileUseNIO(String src, String dst) throws IOException {
        FileInputStream fis = new FileInputStream(new File(src));

        FileOutputStream fos = new FileOutputStream(new File(dst));

        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            int eof = inChannel.read(buffer);
            if (eof == -1) {
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


    public static void writeObject(List<PluginRegisterInfoPo> plugins, String remoteRankConfig) {
        OutputStreamWriter osw = null;
        BufferedWriter br = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(remoteRankConfig), StandardCharsets.UTF_8);
            br = new BufferedWriter(osw);
            int count = 0;
            for (PluginRegisterInfoPo p : plugins) {
                StringBuilder line = new StringBuilder();
                line.append(p.getId()).append("|");
                line.append(p.getPluginName()).append("|");
                line.append(p.getType()).append("|");
                line.append(p.getInterfaceName()).append("|");
                line.append(p.getImplementation()).append("|");
                line.append(p.getRcdType());
                br.write(line.toString());
                br.write("\n");
                count += 1;
            }
            br.flush();
            System.out.println("total remoteRankPlugin is " + count);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (osw != null) {
                    osw.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<PluginRegisterInfoPo> readObject(String remoteRankConfig) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        List<PluginRegisterInfoPo> list = new ArrayList<>();
        try {
            isr = new InputStreamReader(new FileInputStream(remoteRankConfig), StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            String line = null;
            int count = 0;
            while ((line = br.readLine()) != null) {
                PluginRegisterInfoPo p = new PluginRegisterInfoPo();
                String[] arr = line.split("\\|");
                p.setId(Integer.parseInt(arr[0]));
                p.setPluginName(arr[1]);
                p.setType(Integer.parseInt(arr[2]));
                p.setInterfaceName(arr[3]);
                p.setImplementation(arr[4]);
                p.setRcdType(Integer.parseInt(arr[5]));
                list.add(p);
                count++;
            }
            System.out.println("read remoteRankPlugin total : " + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    private static void writeList(List<PluginRegisterInfoPo> plugins) {
        FileOutputStream fos = null;
        try {
            File filePath = new File(path);
            if(!filePath.exists() && !filePath.mkdirs()){
                return;
            }
            File file = new File(path + File.separator + "boss-config");
            fos = new FileOutputStream(file, false);
            for(PluginRegisterInfoPo p : plugins){
                byte[] bytes = ProtoStuffUtil.serialize(p);
                ByteBuffer lengthBuffer = ByteBuffer.allocate(4);
                fos.write(lengthBuffer.putInt(bytes.length).array());
                fos.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<PluginRegisterInfoPo> readList() {
        List<PluginRegisterInfoPo> list = new ArrayList<>();
        try {
            File file = new File(path + File.separator + "boss-config");
            if (file.exists()) {
                InputStream stream = new FileInputStream(file);
                byte[] lengthData = new byte[4];
                while (stream.read(lengthData) != -1) {
                    int length = transIntValue(lengthData);
                    byte[] data = new byte[length];
                    stream.read(data);
                    list.add(ProtoStuffUtil.deserialize(data, PluginRegisterInfoPo.class));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    private static int transIntValue(byte[] value) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(value);
        return buffer.getInt(0);
    }
}
