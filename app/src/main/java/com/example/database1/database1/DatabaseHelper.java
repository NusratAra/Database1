package com.example.database1.database1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ARA on 28-May-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_Name= "Test.db";
    String Table_Name="users";
    String Column_ID="_id";
    String Column_Email="email";
    public static final String Column_Name="name";
    private static final int Database_Version=1;

    String Database_Create="create table"+Table_Name+"("+ Column_ID+"integer primary key autoincrement,"
            + Column_Email+ "text not null,"+Column_Name+"text not null"+");";


    DatabaseHelper(Context context){
        super(context, DB_Name, null, Database_Version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Database_Create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ Table_Name);
        onCreate(db);


    }

    public User createUser(String email, String name ){
        ContentValues values= new ContentValues();
        values.put(Column_Email, email);
        values.put(Column_Name, name);


        long insertID= this.getWritableDatabase().insert(Table_Name, null, values);
        User user= new User();
        user.setId(insertID);
        user.setEmail(email);
        user.setName(name);
        return user;

    }

    private User cursorToUser(Cursor cursor){
        User user= new User();
        user.setId(cursor.getLong(0));
        user.setEmail(cursor.getString(1));
        user.setName(cursor.getString(2));
        return user;
    }

    public User getUserByEmail(String email){
        Cursor cursor= this.getReadableDatabase().query(Table_Name, new String[]{Column_ID, Column_Email, Column_Name},
                Column_Email+ "=?",
                new String[]{email},
                null,
                null,
                null);
        cursor.moveToFirst();
        User user= cursorToUser(cursor);
        return user;
    }

    public List<User> getAllUser(){
        List<User> users= new ArrayList<>();
        Cursor cursor= this.getReadableDatabase().query(Table_Name,
                new String[]{Column_ID, Column_Email, Column_Name},
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        return users;

    }


}
