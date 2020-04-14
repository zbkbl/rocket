package http;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;

import javax.servlet.http.HttpServlet;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-10-23 11:19
 **/
public class HttpServer implements HttpConstants {
    private Server webServer = null;
    private WebAppContext webAppContext = null;

    private final static String HOME_DIR = System.getProperty("user.dir");

    public HttpServer(String host, int port) {
        webServer = new Server();
        Connector listener = createChannelConnector();
        listener.setHost(host);
        listener.setPort(port);

        webServer.addConnector(listener);
        webServer.setThreadPool(new QueuedThreadPool(DEFAULT_HTTP_SERVER_MAX_THREADS));

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        webServer.setHandler(contexts);

        webAppContext = new WebAppContext();
        webAppContext.setDisplayName("WebAppsContext");
        webAppContext.setContextPath("/");
        webAppContext.setMaxFormContentSize(DEFAULT_MAX_FORM_CONTENT_SIZE);
        webAppContext.setWar(HOME_DIR + "/webapps");
        webServer.addHandler(webAppContext);
    }

    public void start() throws Exception {
        webServer.start();
    }

    public void close() throws Exception {
        webServer.stop();
    }

    public void addServlet(String name, String pathSpec, Class<? extends HttpServlet> clazz, String parameterName,
                           String parameterValue) {
        ServletHolder servletHolder = new ServletHolder(clazz);
        if (name != null) {
            servletHolder.setName(name);
        }
        if (null != parameterName && null != parameterValue) {
            servletHolder.setInitParameter(parameterName, parameterValue);
        }
        this.webAppContext.addServlet(servletHolder, pathSpec);
    }

    public void addServlet(String name, String pathSpec, Class<? extends HttpServlet> clazz, Map<String, String> params) {
        ServletHolder holder = new ServletHolder(clazz);
        if (name != null) {
            holder.setName(name);
        }

        if (null != params) {
            Set<Map.Entry<String, String>> set = params.entrySet();
            for (Map.Entry<String, String> entry : set) {
                holder.setInitParameter(entry.getKey(), entry.getValue());
            }
        }
        this.webAppContext.addServlet(holder, pathSpec);
    }

    public void addServlet(String name, String pathSpec, Class<? extends HttpServlet> clazz) {
        this.addServlet(name, pathSpec, clazz, null,null);
    }

    public void setAttribute(String name, Object value) {
        this.webAppContext.setAttribute(name, value);
    }

    public void addFilter(Class<?> filterClass, String pathSpec) {
        this.webAppContext.addFilter(filterClass, pathSpec, 1);
    }


    private Connector createChannelConnector() {
        SelectChannelConnector scc = new SelectChannelConnector();
        scc.setLowResourceMaxIdleTime(DEFAULT_LOW_RESOURCE_MAXIDLE_TIME);
        scc.setAcceptQueueSize(DEFAULT_ACCEPT_QUEUE_SIZE);
        scc.setResolveNames(DEFAULT_RESOLVE_NAMES_ENABLE);
        scc.setUseDirectBuffers(DEFAULT_USE_DIRECT_BUFFERS_ENABLE);
        scc.setResponseBufferSize(DEFAULT_RESPONSE_BUFFER_SIZE);
        scc.setRequestBufferSize(DEFAULT_REQUEST_BUFFER_SIZE);
        scc.setMaxIdleTime(DEFAULT_MAX_IDLE_TIME);

        return scc;
    }
}
