package com.example.Nesrine.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nesrine on 16/06/2017.
 */

public class GetConnexionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String connected=new DataBaseService().getConnexion(email,password);
        Gson gson=new Gson();
        resp.getWriter().print(gson.toJson(connected));
    }
}
