package com.example.Nesrine.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nesrine on 27/06/2017.
 */

public class PostStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_rdv=req.getParameter("id_rdv");
        String status=req.getParameter("status");
        DataBaseService db=new DataBaseService();
        boolean b =db.updateRendezVous(id_rdv,status);
        resp.getWriter().print(b);
    }
}
