package com.example.sql_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.RowId;

public class StudentInfo {
    public static final String KEY_ROWID="Student id";
    public static final String KEY_NAME="Student name";
    public static final String KEY_CELL="Student number";
    public static final String KEY_MAIL="Student mail";

    private final String DATABASE_NAME="StudentInfo";
    private final String DATABASE_TABLE="Studentdata";
    private final int DATABASE_VERSION=1;

    private  DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public StudentInfo(Context context){
        ourContext=context;
    }
    private class DBHelper extends SQLiteOpenHelper{
     public DBHelper(Context context){
     super(context,DATABASE_NAME,null,DATABASE_VERSION);

     }
        @Override
        public void onCreate(SQLiteDatabase db) {
         /*Creating table */
            String sqlCode="CREATE Table"+DATABASE_TABLE+"("
                    +KEY_ROWID+"INTEGER PRIMARY KEY AUTOINCREMENY,"
                    +KEY_NAME+"TEXT NOT NULL,"
                    +KEY_CELL+"TEXT NOT NULL,"
                    +KEY_MAIL+"TEXT NOT NULL);";
            db.execSQL(sqlCode);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                  db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
                  onCreate(db);
        }
    }
     public StudentInfo open(){
        ourHelper=new DBHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
                return this;
     }
     public void close()
     {
         ourHelper.close();
     }
     public Long createEntry(String name,String phone,String mail){

         ContentValues cv = new ContentValues();
         cv.put(KEY_NAME,name);
         cv.put(KEY_CELL,phone);
         cv.put(KEY_MAIL,mail);
         return ourDatabase.insert(DATABASE_TABLE,null,cv);
     }
     public String getData(){
       String []col=new String[]{KEY_ROWID,KEY_NAME,KEY_CELL,KEY_MAIL};
         Cursor c=ourDatabase.query(DATABASE_TABLE,col,null,null,null,null,null,null);
      String result="";
      int iRowid=c.getColumnIndex(KEY_ROWID);
      int iRowName=c.getColumnIndex(KEY_NAME);
      int iRowcell=c.getColumnIndex(KEY_CELL);
      int iRowmail=c.getColumnIndex(KEY_MAIL);
       for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
                result=result + c.getString(iRowid)+" : "+c.getString(iRowName)+" : "+c.getString(iRowcell)+" : "+
                        c.getString(iRowmail)+"\n";
       }
       c.close();
       return result;
    }
    public  long updateEntry(String rowid,String newName,String newCell,String newMail){
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,newName);
        cv.put(KEY_CELL,newCell);
        cv.put(KEY_MAIL,newMail);
        return ourDatabase.update(DATABASE_TABLE,cv,KEY_ROWID+"=?",new String[]{rowid});
    }
    public long deleteEntry(String rowId){
        return ourDatabase.delete(DATABASE_TABLE,KEY_ROWID+"=?",new String[]{rowId});
    }
}
