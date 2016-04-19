package com.mycompany;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="FwkCommonServlet", urlPatterns={"/fwk/healthcheck", "/fwk/info"})
public class FwkCommonServlet extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

        if(request.getRequestURI().contains("healthcheck")){
            String result= "{\"statusApplication\": \"OK\", \"dataBase\" : \"OK\" }";
            response.getWriter().println(result);
        }

        if(request.getRequestURI().contains("info")){
            String result= "{\"listFwk\": \"blabla\"}";
            response.getWriter().println(result);

        }

    }

}
