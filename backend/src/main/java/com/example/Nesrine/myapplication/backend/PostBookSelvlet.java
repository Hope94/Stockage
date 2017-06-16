package com.example.Nesrine.myapplication.backend;

import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nesrine on 07/05/2017.
 */

public class PostBookSelvlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String livre=req.getParameter("book");
        Gson gson=new Gson();
        Book book=gson.fromJson(livre,Book.class);
        DataBaseService db=new DataBaseService();
        boolean b =db.addBook(book);
        resp.getWriter().print(b);
    }
}
