package com.example.Nesrine.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nesrine on 28/06/2017.
 */

public class PostRateServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String note=req.getParameter("rate");
        String id_log=req.getParameter("id_log");
        String email=req.getParameter("email");
        float rate=Float.parseFloat(note);
        DataBaseService db=new DataBaseService();
        boolean b =db.addRate(id_log,rate,email);
        resp.getWriter().print(b);
    }

}
