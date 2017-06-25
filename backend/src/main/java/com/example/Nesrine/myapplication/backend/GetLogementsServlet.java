package com.example.Nesrine.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nesrine on 24/06/2017.
 */

public class GetLogementsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type=req.getParameter("type");
        String region=req.getParameter("region");
        String typeImage=req.getParameter("typeImage");
        List<Logement> logList=new DataBaseService().getLogement(type,region,typeImage);
        Gson gson=new Gson();
        resp.getWriter().print(gson.toJson(logList));
    }


}