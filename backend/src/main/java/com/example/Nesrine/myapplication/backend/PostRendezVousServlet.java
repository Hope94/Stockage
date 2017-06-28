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

public class PostRendezVousServlet extends HttpServlet {
    private int id=100;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseService db=new DataBaseService();
        String date=req.getParameter("date");
        String email=req.getParameter("email");
        String id_log=req.getParameter("id_log");
        ObjetRendezVous rdv=new ObjetRendezVous();
        rdv.setUserName(db.getClientByEmail(email));
        rdv.setId_rdv(Integer.toString(id));
        rdv.setDate(date);
        id++;
        rdv.setRegion(db.getRegionByIdLg(id_log));
        rdv.setNomAnn(db.getNomAnnByIdLg(id_log));
        rdv.setHeure("9:00 - 16:00");

        boolean b =db.addRendezVous(rdv,id_log,db.getIdAnnByIdLg(id_log),db.getIdUserByIdLg(id_log));
        resp.getWriter().print(b);
    }
}
