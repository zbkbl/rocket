package http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-10-23 11:46
 **/
public class HealthCheckServlet extends HttpServlet {
    private static final Gson GSON = new Gson();
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("status","OK");
        result.put("msg","this service status is OK! ");
        try(PrintWriter printWriter = resp.getWriter()){
            printWriter.append(GSON.toJson(result));
            printWriter.println();
            LOGGER.info("get health check request!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
