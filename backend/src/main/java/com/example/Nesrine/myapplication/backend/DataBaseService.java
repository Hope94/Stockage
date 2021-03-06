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
                rendezVous.setId_rdv(rs.getString("id_rdv"));
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
    public List<Logement> getLogement(String type,String region)
    {
        List<Logement> list=new ArrayList<Logement>();
        String query="select * from logement  natural join imagelogement where type_log=? and" +
                " region=? and type=?";
        Connection connection = connecter();
        PreparedStatement st=null;
        try {
            st=connection.prepareStatement(query);
            st.setString(1,type);
            st.setString(2,region);
            st.setString(3,"main");
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

    public Logement getDetailLogement(String id_log)
    {
        Logement logement=new Logement();
        String query="select * from logement  natural join imagelogement where id_log=? and type=?";
        Connection connection = connecter();
        PreparedStatement st=null;

        try {
            st=connection.prepareStatement(query);
            st.setString(1,id_log);
            st.setString(2,"detail");
            ResultSet rs =st.executeQuery();
            if (rs.first()) {
                logement.setDescriptif(rs.getString("descriptif"));
                List<String> listUrls = new ArrayList<String>();
                // Get detail images of the logemenr
                while (!(rs.isAfterLast()) && (rs.getString("id_log").equals(id_log))) {
                    listUrls.add(rs.getString("url"));
                    rs.next();
                }
                logement.setListDetailImages(listUrls.toArray(new String[listUrls.size()]));
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

        return logement;

    }

    public DateVisites getDateVisites(String id_log)
    {
        DateVisites dates=new DateVisites();
        String query="select * from annonce natural join disponibilite where id_log=?";
        Connection connection = connecter();
        PreparedStatement st=null;

        try {
            st=connection.prepareStatement(query);
            st.setString(1,id_log);
            ResultSet rs =st.executeQuery();
            if (rs.first()) {

                List<String> listDates = new ArrayList<String>();
                // Get detail images of the logemenr
                while (!(rs.isAfterLast()) && (rs.getString("id_log").equals(id_log))) {
                    listDates.add(rs.getString("date"));
                    rs.next();
                }
                dates.setListDates(listDates);
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

        return dates;

    }

    public boolean updateRendezVous(String id_rdv,String status)
    {
        String query="update rendezvous set etat=?  where id_rdv=?";
        Connection con=connecter();
        PreparedStatement prst=null;
        int i=-1;
        try {
            prst=con.prepareStatement(query);
            prst.setString(1,status);
            prst.setString(2,id_rdv);
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
    public boolean addRendezVous(ObjetRendezVous rendezVous,String id_log,String id_ann,String id_user)
    {
        String query="insert into rendezvous values(?,?,?,?,?,?,?,?,?,?)";
        Connection con=connecter();
        PreparedStatement prst=null;
        int i=-1;
        try {
            prst=con.prepareStatement(query);
            prst.setString(1,rendezVous.getId_rdv());
            prst.setString(2,rendezVous.getUserName());
            prst.setString(3,id_ann);
            prst.setString(4,id_user);
            prst.setString(5,id_log);
            prst.setString(6,"Invalide");
            prst.setString(7,rendezVous.getDate());
            prst.setString(8,rendezVous.getHeure());
            prst.setString(9,rendezVous.getRegion());
            prst.setString(10,rendezVous.getNomAnn());
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

    public String getIdAnnByIdLg(String id_log)
    {

        String query="select * from annonce where id_log=?";
        Connection connection = connecter();
        PreparedStatement st=null;
        String id_ann=null;
        try {
            st=connection.prepareStatement(query);
            st.setString(1,id_log);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                id_ann=rs.getString("id_ann");
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

        return  id_ann;

    }

    public String getNomAnnByIdLg(String id_log)
    {

        String query="select * from annonce where id_log=?";
        Connection connection = connecter();
        PreparedStatement st=null;
        String nom_ann=null;
        try {
            st=connection.prepareStatement(query);
            st.setString(1,id_log);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                nom_ann=rs.getString("nom_annonce");
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

        return  nom_ann;

    }

    public String getIdUserByIdLg(String id_log)
    {

        String query="select * from annonce where id_log=?";
        Connection connection = connecter();
        PreparedStatement st=null;
        String id_user=null;
        try {
            st=connection.prepareStatement(query);
            st.setString(1,id_log);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                id_user=rs.getString("id_user");
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

        return  id_user;

    }

    public String getRegionByIdLg(String id_log)
    {

        String query="select * from logement where id_log=?";
        Connection connection = connecter();
        PreparedStatement st=null;
        String region=null;
        try {
            st=connection.prepareStatement(query);
            st.setString(1,id_log);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                region=rs.getString("region");
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

        return  region;

    }


    public String getClientByEmail(String email)
    {

        String query="select * from user where email=?";
        Connection connection = connecter();
        PreparedStatement st=null;
        String client=null;
        try {
            st=connection.prepareStatement(query);
            st.setString(1,email);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                client=rs.getString("nom_user");
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

        return  client;

    }

    public boolean addRate(String id_log,float note,String email)
    {
        String query="insert into rate (note,id_log,email) values(?,?,?)";
        Connection con=connecter();
        PreparedStatement prst=null;
        int i=-1;
        try {
            prst=con.prepareStatement(query);
            prst.setFloat(1,note);
            prst.setString(2,id_log);
            prst.setString(3,email);
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

    public boolean addComment(String id_log,String comment,String email)
    {
        String query="insert into commentaire (commentaire,id_log,email) values(?,?,?)";
        Connection con=connecter();
        PreparedStatement prst=null;
        int i=-1;
        try {
            prst=con.prepareStatement(query);
            prst.setString(1,comment);
            prst.setString(2,id_log);
            prst.setString(3,email);
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
