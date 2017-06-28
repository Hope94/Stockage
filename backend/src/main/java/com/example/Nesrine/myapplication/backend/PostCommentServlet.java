package com.example.Nesrine.myapplication.backend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nesrine on 28/06/2017.
 */

public class PostCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment=req.getParameter("comment");
        String id_log=req.getParameter("id_log");
        String email=req.getParameter("email");
        DataBaseService db=new DataBaseService();
        boolean b =db.addComment(id_log,comment,email);
        resp.getWriter().print(b);
    }

}
