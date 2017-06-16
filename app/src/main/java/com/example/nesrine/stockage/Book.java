package com.example.nesrine.stockage;

import android.text.Editable;

/**
 * Created by Nesrine on 16/04/2017.
 */

public class Book {
    private String ISBN;
    private String titre;
    private String année_edition;
    private String auteur;

    public Book(String ISBN, String titre, String année_edition, String auteur) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.année_edition = année_edition;
        this.auteur = auteur;
    }
    public Book() {

    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitre() {
        return titre;
    }

    public String getAnnée_edition() {
        return année_edition;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAnnée_edition(String année_edition) {
        this.année_edition = année_edition;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
