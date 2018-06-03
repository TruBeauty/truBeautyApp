package srilanka.sliit.apps.trubeauty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rajitha Shavinda on 2018-05-30.
 */

public class sqLite_DB extends SQLiteOpenHelper {


    public sqLite_DB(Context context) {
        super(context, "truBeauty.db", null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE UserDetails(id INTEGER PRIMARY KEY, number TEXT, name TEXT, email TEXT,  gender TEXT);");

        ContentValues contentValues=new ContentValues();
        contentValues.put("id",1001);
        contentValues.put("number","+947");
        contentValues.put("name","User");
        contentValues.put("email"," ");
        contentValues.put("gender", "Female");

        db.insert("UserDetails",null,contentValues);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS UserDetails");

        onCreate(db);
    }



    public Cursor getUserTable() {


        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data =db.rawQuery("SELECT * FROM UserDetails WHERE id=1001",null);

        return data;
    }




    public void UpdateUserTable(String n, String pn, String e, String g) {

        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues data = new ContentValues();
        data.put("name",n);
        data.put("number",pn);
        data.put("email",e);
        data.put("gender",g);

        db.update("UserDetails", data, "id="+1001, null);

    }


}
