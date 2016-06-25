package com.vnpt.hddtcustomer.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "HDDT.sqlite";
    private static final String DB_PATH_NAME = "db/HDDT.sqlite";
    private static final String DBLOCATION = "/data/data/com.vnpt.hddtcustomer/databases/";
    private static final int DATABASE_VERSION = 2;
    private Context _context;
    private SQLiteDatabase _sqlDB;

    public DatabaseHelper(Context context){
        super(context, DBNAME, null, DATABASE_VERSION);
        this._context = context;
        boolean dbExist = checkDatabase();
        if(dbExist){
            openDatabase();
        }else {
            createDatabase();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createDatabase() {
        this.getReadableDatabase();
        try {
            copyDatabase();
        } catch(IOException e) {
            throw new Error("Error copying database");
        }
    }

    public boolean checkDatabase(){
        boolean checkdb = false;
        try{
            //String path = DBLOCATION + DBNAME;
            //File dbFile = new File(path);
            File dbFile = _context.getDatabasePath(DBNAME);
            checkdb = dbFile.exists();
        }catch (SQLiteException e){
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    public void copyDatabase() throws IOException{
        //open your local db as the input stream
        InputStream inputStream = _context.getAssets().open(DB_PATH_NAME);
        //path to output file
        String path = DBLOCATION + DBNAME;
        //open the empty db as the output stream
        OutputStream outputStream = new FileOutputStream(path);

        byte[] buffer = new byte[1024];
        int length = 0;
        //transfer bytes from the inputfile to the outputfile
        while ((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        /*inputStream.close();*/
    }

    public void openDatabase(){
        String dbPath = _context.getDatabasePath(DBNAME).getPath();
        if(_sqlDB != null && _sqlDB.isOpen()){
            return;
        }
        _sqlDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDatabase(){
        if(_sqlDB != null){
            _sqlDB.close();
        }
    }

    public Cursor selectQuery(String tbName, int id){
        openDatabase();
        String query = "SELECT * FROM " + tbName + " WHERE ID = ?";
        Cursor cursor = _sqlDB.rawQuery(query, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        closeDatabase();
        return cursor;
    }

    public long INSERT_TABLE(String tbName, ContentValues contentValues){
        openDatabase();
        long returnValue = _sqlDB.insert(tbName, null, contentValues);
        closeDatabase();
        return returnValue;
    }

    public boolean DELETE_TABLE(String tbName, String whereClause, String[] whereArgs){
        openDatabase();
        int result = _sqlDB.delete(tbName, whereClause, whereArgs);
        closeDatabase();
        return (result != 0);
    }

    public long UPDATE_TABLE(String tbName, ContentValues contentValues, String whereClause, String[] whereArgs){
        openDatabase();
        long returnValue = _sqlDB.update(tbName, contentValues, whereClause, whereArgs);
        closeDatabase();
        return returnValue;
    }

    public Cursor QUERY_SELECT_ALL(String tbName){
        openDatabase();
        String query = "SELECT * FROM " + tbName;
        Cursor cursor = _sqlDB.rawQuery(query, null);
        cursor.moveToFirst();
        closeDatabase();
        return cursor;
    }

    public Cursor QUERY_SELECT_OWNER(String tbName, String whereOwner){
        openDatabase();
        String query = "SELECT * FROM " + tbName + " WHERE " + whereOwner;
        Cursor cursor = _sqlDB.rawQuery(query, null);
        cursor.moveToFirst();
        closeDatabase();
        return cursor;
    }

}
