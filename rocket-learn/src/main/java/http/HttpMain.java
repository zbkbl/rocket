package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-10-23 11:44
 **/
public class HttpMain {

    private static final Logger LOG = LoggerFactory.getLogger(HttpMain.class);

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer("127.0.0.1", 10080);
        httpServer.addServlet("healthCheck", "/pixiu/*", HealthCheckServlet.class);
        try {
            httpServer.start();
        } catch (Exception e) {
            LOG.error("Fail to start phoenix engine worker.", e);

            System.exit(2);
        }
        try{
            Thread.currentThread().join();
        }catch (InterruptedException e){

        }
    }
}
