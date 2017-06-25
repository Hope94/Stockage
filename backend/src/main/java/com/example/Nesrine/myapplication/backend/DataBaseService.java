package com.example.Nesrine.myapplication.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sun.rmi.runtime.Log;

/**
 * Created by Nesrine on 30/04/2017.
 */

public class DataBaseService {
    public static final String className= "com.mysql.jdbc.Driver";
    public static final String chaine="jdbc:mysql://localhost:3306/myhome_db";
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
    public String getConnexion(String email,String password)
    {

        String query="select * from user where email=? and mot_de_passe=?";
        Connection connection = connecter();
        PreparedStatement st=null;
        String connected="failed";
        try {
            st=connection.prepareStatement(query);
            st.setString(1,email);
            st.setString(2,password);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                connected="ok";
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

        return  connected;

    }

    public List<ObjetRendezVous> getRendezVousList(){
        List<ObjetRendezVous> rendezVousList = new ArrayList<ObjetRendezVous>();
        String query="select * from rendezvous";
        Connection connection = connecter();
        Statement st=null;
        try {
            st=connection.createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next())
            {
                ObjetRendezVous rendezVous=new ObjetRendezVous();
                rendezVous.setUserName(rs.getString("client_name"));
                rendezVous.setNomAnn(rs.getString("nomann"));
                rendezVous.setStatus(rs.getString("etat"));
                rendezVous.setDate(rs.getString("date"));
                rendezVous.setHeure(rs.getString("heure"));
                rendezVous.setRegion(rs.getString("region"));
                rendezVousList.add(rendezVous);

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

        return  rendezVousList;
    }
    public List<Logement> getLogement(String type,String region,String typeImage)
    {
        List<Logement> list=new ArrayList<Logement>();
        String query="select id_log,prix,region,lat,long,url from logement  natural join imagelogement where type_log=?  and region=? and type=?";
        Connection connection = connecter();
        PreparedStatement st=null;
        try {
            st=connection.prepareStatement(query);
            st.setString(1,type);
            st.setString(2,region);
            st.setString(3,typeImage);
            ResultSet rs =st.executeQuery();
            while (rs.next()){
                Logement logement=new Logement();
                logement.setId(rs.getString("id_log"));
                logement.setPrix(rs.getInt("prix"));
                logement.setRegion(rs.getString("region"));
                logement.setType(rs.getString("type_log"));
                logement.setLat(rs.getDouble("lat"));
                logement.setLng(rs.getDouble("long"));
                logement.setMainImage(rs.getString("url"));
                list.add(logement);
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

        return list;

    }



}
