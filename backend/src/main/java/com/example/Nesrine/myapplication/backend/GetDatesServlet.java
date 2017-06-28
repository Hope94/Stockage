package com.example.Nesrine.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nesrine on 26/06/2017.
 */

public class GetDatesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_log=req.getParameter("id_log");
        DateVisites dates=new DataBaseService().getDateVisites(id_log);
        Gson gson=new Gson();
        resp.getWriter().print(gson.toJson(dates));
    }
}
