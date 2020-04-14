package http;

public interface HttpConstants {


    /** 线程资源稀少时的最大空闲时间，单位:毫秒 */
    public final static String LOW_RESOURCE_MAXIDLE_TIME = "low.resource.max.idle.time";
    public final static int DEFAULT_LOW_RESOURCE_MAXIDLE_TIME = 3000;

    public final static String ACCEPT_QUEUE_SIZE = "accept.queue.size";
    public final static int DEFAULT_ACCEPT_QUEUE_SIZE = 128;

    public final static String RESOLVE_NAMES_ENABLE = "resolve.names.enable";
    public final static boolean DEFAULT_RESOLVE_NAMES_ENABLE = false;

    public final static String USE_DIRECT_BUFFERS_ENABLE = "use.direct.buffers.enable";
    public final static boolean DEFAULT_USE_DIRECT_BUFFERS_ENABLE = false;

    public final static String REQUEST_BUFFER_SIZE = "request.buffer.size";
    public final static int DEFAULT_REQUEST_BUFFER_SIZE = 4096;

    public final static String RESPONSE_BUFFER_SIZE = "response.buffer.size";
    public final static int DEFAULT_RESPONSE_BUFFER_SIZE = 4096;

    /** 连接最大空闲时间，单位:毫秒 */
    public final static String MAX_IDLE_TIME = "max.idle.time";
    public final static int DEFAULT_MAX_IDLE_TIME = 4000;

    /** http server最大线程数 */
    public static final String HTTP_SERVER_MAX_THREADS = "http.server.max.threads";

    /** 默认http服务器最大线程数 */
    public static final int DEFAULT_HTTP_SERVER_MAX_THREADS = 10;

    /** post参数最大长度 */
    public static final String MAX_FORM_CONTENT_SIZE = "max.form.content.size";

    /** 默认post参数最大长度2M */
    public static final int DEFAULT_MAX_FORM_CONTENT_SIZE = 2000000;

    public static final String HTTP_RESOURCE_BASE = "http.resource.base";
}
