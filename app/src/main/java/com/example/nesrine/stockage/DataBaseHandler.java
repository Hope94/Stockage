package com.example.nesrine.stockage;

/**
 * Created by Nesrine on 16/04/2017.
 */



        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;
        import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private Context context;
    static final String DB_NAME ="livredb";
    static final int DB_VERSION = 2;
    static final String bookCreation = "create table book (id integer primary key,ISBN,titre,année_edition,auteur)";


    public DataBaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(bookCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS book");
        onCreate(db);
    }

    public boolean addBook(Book book) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ISBN",book.getISBN());
        contentValues.put("titre",book.getTitre());
        contentValues.put("année_edition",book.getAnnée_edition());
        contentValues.put("auteur",book.getAuteur());
        long rowId = db.insert("book", null, contentValues);
        return (rowId!=-1);
    }

    public List<String> getBooks() {
        List<String> listBook = new ArrayList<>();
        String query ="select * from book";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Book book = new Book();
            book.setISBN(cursor.getString(1));
            book.setTitre(cursor.getString(2));
            book.setAnnée_edition(cursor.getString(3));
            book.setAuteur(cursor.getString(4));
            listBook.add(book.getTitre());
            cursor.moveToNext();
        }
        db.close();
        return listBook;
    }

    public Book getBookByTitle(String titre) {
        Book book=null;
        String query ="select * from book where lower(titre)=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{book.getTitre().toLowerCase()});
        if (cursor.moveToFirst()) {
            book = new Book();
            book.setISBN(cursor.getString(1));
            book.setTitre(cursor.getString(2));
            book.setAnnée_edition(cursor.getString(3));
            book.setAuteur(cursor.getString(4));
        }
        db.close();
        return book;
    }



}
