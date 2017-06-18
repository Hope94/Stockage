package com.example.Nesrine.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nesrine on 18/06/2017.
 */

public class GetRendezVousServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ObjetRendezVous> rendezVousList=new DataBaseService().getRendezVousList();
        Gson gson=new Gson();
        resp.getWriter().print(gson.toJson(rendezVousList));
    }
}
