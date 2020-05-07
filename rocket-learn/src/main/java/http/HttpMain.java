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

//    public static void main(String[] args) {
//        HttpServer httpServer = new HttpServer("127.0.0.1", 10080);
//        httpServer.addServlet("healthCheck", "/pixiu/*", HealthCheckServlet.class);
//        try {
//            httpServer.start();
//        } catch (Exception e) {
//            LOG.error("Fail to start phoenix engine worker.", e);
//
//            System.exit(2);
//        }
//        try{
//            Thread.currentThread().join();
//        }catch (InterruptedException e){
//
//        }
//    }

    public static void main(String[] args) {
        String s = "35913104        50525916        1       3d4de2f9-a887-425a-a36e-acd8cfc2b89f    1583402781734   rank-second-filter      {\"cnt\":748,\"filterInfo\":[{\"filterCnt\":3,\"filterType\":\"innerUserCount\",\"filterInfo\":[\"42888662:95275096\",\"51942308:105058121\",\"29864890:38938964\"]},{\"filterCnt\":49,\"filterType\":\"positionMatchFilterCount\",\"filterInfo\":[\"50416530:41983084\",\"74613567:86427920\",\"75515461:109347053\",\"50416530:78746140\",\"49340599:39121063\",\"26309216:6589359\",\"78001637:91531613\",\"19897804:3925046\",\"428745:109379804\",\"49376028:78758849\",\"96763361:111117625\",\"21819763:85299469\",\"27014740:25405910\",\"54137736:105447287\",\"7251873:1528831\",\"50434330:109492078\",\"20273122:101629389\",\"43644623:30162751\",\"29069694:10295149\",\"85094356:103292299\",\"27169340:109244175\",\"71934095:89586496\",\"51592852:44264185\",\"7224711:1742505\",\"72051749:104594485\",\"49925558:53848961\",\"27169340:109245205\",\"45842574:33427814\",\"26377852:110782788\",\"95729329:109724542\",\"68341845:74893561\",\"41495447:84512363\",\"11980153:74809203\",\"30075565:14003234\",\"11850768:2009865\",\"11850768:107066249\",\"71424984:106964530\",\"82043867:105121887\",\"63202063:109248873\",\"59898797:58578898\",\"45812001:33385316\",\"59132636:82299685\",\"81038770:96505529\",\"80037857:110947644\",\"11980153:91778524\",\"85001027:103139103\",\"34258656:107007786\",\"69245802:99737478\",\"35922926:19423480\"]}]}      f1_rec  1";
        System.out.println(s.getBytes().length);
    }
}
