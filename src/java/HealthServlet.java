package java;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/health")
public class HealthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().write("Healthy");
    }
}

//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//
//@WebServlet("/health")  // このパスでリクエストを受け付ける
//public class HealthCheckServlet extends javax.servlet.http.HttpServlet {
//
//    @Override
//    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        // レスポンスコード200で正常を返す
//        resp.setStatus(javax.servlet.http.HttpServletResponse.SC_OK);
//        resp.setContentType("text/plain");
//        PrintWriter writer = resp.getWriter();
//        writer.write("OK");
//        writer.flush();
//    }
//}
