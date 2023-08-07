package nl.prismait.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerName {
    public static void main(String[] args) {
        try {
            InetAddress localMachine = InetAddress.getLocalHost();
            String serverName = localMachine.getHostName();
            System.out.println("Server Name: " + serverName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

//    private static final Logger LOG = LoggerFactory.getLogger(ServerName.class);
//    public static void main(String[] args) {
//
//
//        String serverName = System.getProperty("server.name");
//        LOG.info("Server Name: {}", serverName);
//
//
//
//
//    }
}
