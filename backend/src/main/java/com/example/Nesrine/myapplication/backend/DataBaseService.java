package com.example.Nesrine.myapplication.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nesrine on 30/04/2017.
 */

public class DataBaseService {
    public static final String className= "com.mysql.jdbc.Driver";
    public static final String chaine="jdbc:mysql://localhost:3306/book_db";
    public static final String username="root";
    public static final String password="";

    public Connection connecter(){
        Connection con=null;
        try{
            Class.forName(className);
            con=DriverManager.getConnection(chaine,username,password);

        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return con;

    }

    public List<Book> getBookList(){
        List<Book> bookList = new ArrayList<Book>();
        String query="select * from book";
        Connection connection = connecter();
        Statement st=null;
        try {
            st=connection.createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next())
            {
                Book book=new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setCategory(rs.getString("category"));
                bookList.add(book);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  bookList;
    }
    public boolean addBook(Book book)
    {
        String query="insert into book values(?,?,?)";
        Connection con=connecter();
        PreparedStatement prst=null;
        int i=-1;
        try {
            prst=con.prepareStatement(query);
            prst.setString(1,book.getIsbn());
           prst.setString(2,book.getTitle());
            prst.setString(3,book.getCategory());
            i=prst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            prst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (i!=-1);


    }


}
