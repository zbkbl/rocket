package nio;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuyang
 * @description
 * @date 2020/06/29 15:31
 **/
public class ProtoStuffUtil {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap();


    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema)cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            if (schema != null) {
                cachedSchema.put(clazz, schema);
            }
        }

        return schema;
    }

    static <T> byte[] serialize(T obj) {
        if(obj == null ){
            return null;
        }
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        byte[] var4;
        try {
            Schema<T> schema = getSchema(clazz);
            var4 = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception var8) {
            throw new IllegalStateException(var8.getMessage(), var8);
        } finally {
            buffer.clear();
        }

        return var4;
    }

    static <T> T deserialize(byte[] data, Class<T> clazz) {
        if(data == null ){
            return null;
        }
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception var4) {
            throw new IllegalStateException(var4.getMessage(), var4);
        }
    }
}
