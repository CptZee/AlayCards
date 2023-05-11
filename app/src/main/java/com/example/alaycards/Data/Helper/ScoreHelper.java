package com.example.alaycards.Data.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.alaycards.Data.Enum.Difficulty;
import com.example.alaycards.Data.Score;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ScoreHelper extends SQLiteOpenHelper {

    private ScoreHelper(@Nullable Context context) {
        super(context, "db_AlayCards.db", null, 1);
    }

    private static ScoreHelper instance;
    public static ScoreHelper get(Context context){
        if(instance == null)
            instance = new ScoreHelper(context);
        return instance;
    }

    private static final String TABLENAME = "tbl_scores";
    private final SQLiteDatabase db = getWritableDatabase();
    private final SQLiteDatabase dbr = getReadableDatabase();

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME + "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "difficulty TEXT," +
                    "date TEXT," +
                    "remainingTime INTEGER," +
                    "archived INTEGER)");
            Log.i(TABLENAME + " Logger", "Successfully created the table");
        }catch (SQLiteException e){
            Log.e(TABLENAME + " Logger", "Unable to create the table", e.getCause());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE " + TABLENAME);
            onCreate(db);
            Log.i(TABLENAME + " Logger", "Successfully created the table");
        }catch (SQLiteException e){
            Log.e(TABLENAME + " Logger", "Unable to create the table", e.getCause());
        }
    }

    public void insert(Score data){
        ContentValues contentValues = prepareData(data);
        new insertTask().execute(new PreparedData(getWritableDatabase(), data, contentValues));
    }

    public List<Score> get(){
        List<Score> list = new ArrayList<>();
        try{
            Cursor cursor = dbr.rawQuery("SELECT ID, fullName, email, type, credentialID FROM " + TABLENAME + " WHERE archived = 0",
                    null);
            while (cursor.moveToNext())
                list.add(prepareData(cursor));
            Log.i(TABLENAME + " Logger", "Successfully retrieved data from the table");
        }catch (SQLiteException e){
            Log.e(TABLENAME + " Logger", "Unable to retrieved data from the table", e.getCause());
        }
        return list;
    }

    public Score get(int ID){
        Score data = null;
        try{
            Cursor cursor = dbr.rawQuery("SELECT ID, fullName, email, type, credentialID FROM " + TABLENAME + " WHERE ID = " + ID,
                    null);
            while (cursor.moveToNext())
                data = prepareData(cursor);
            Log.i(TABLENAME + " Logger", "Successfully retrieved data from the table");
        }catch (SQLiteException e){
            Log.e(TABLENAME + " Logger", "Unable to retrieved data from the table", e.getCause());
        }
        return data;
    }

    public void update(Score data){
        ContentValues contentValues = prepareData(data);
        new updateTask().execute(new PreparedData(getWritableDatabase(), data, contentValues));
    }

    public void remove(Score data){
        ContentValues contentValues = prepareData(data);
        new removeTask().execute(new PreparedData(getWritableDatabase(), data, contentValues));
    }

    public int getNextID(){
        int ID = 0;
        try{
            Cursor cursor = dbr.rawQuery("SELECT MAX(ID) FROM " + TABLENAME,
                    null);
            while (cursor.moveToNext())
                ID = cursor.getInt(0);
            ID++;
            Log.i(TABLENAME + " Logger", "Successfully retrieved data from the table");
        }catch (SQLiteException e){
            Log.e(TABLENAME + " Logger", "Unable to retrieved data from the table", e.getCause());
        }
        return ID;
    }

    private ContentValues prepareData(Score data){
        ContentValues contentValues = new ContentValues();
        if(data.getDifficulty() != null)
            contentValues.put("difficulty", data.getDifficulty().toString());
        if(data.getDate() != null)
            contentValues.put("date", new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(data.getDate()));
        if(data.getRemainingTime() != 0)
            contentValues.put("remainingTime", data.getRemainingTime());
        contentValues.put("archived", 0);
        return contentValues;
    }

    private Score prepareData(Cursor cursor) {
        Score account = new Score();
        account.setID(cursor.getInt(0));
        account.setDifficulty(Difficulty.valueOf(cursor.getString(1)));
        try {
            account.setDate(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(cursor.getString(2)));
        }catch (ParseException ignored) {}
        account.setRemainingTime(cursor.getInt(3));
        return account;
    }

    private class PreparedData{
        private SQLiteDatabase db;
        private Score data;
        private ContentValues values;

        public PreparedData(SQLiteDatabase db, Score data, ContentValues values) {
            this.db = db;
            this.data = data;
            this.values = values;
        }
    }

    private class insertTask extends AsyncTask<PreparedData, Void, Void>{
        @Override
        protected Void doInBackground(PreparedData... preparedData) {
            SQLiteDatabase db = preparedData[0].db;
            ContentValues values = preparedData[0].values;
            try{
                db.insert(TABLENAME, null, values);
            }catch (SQLiteException e){
                Log.e("DBHelper", "Unable to insert into the " + TABLENAME, e.getCause());
            }
            return null;
        }
    }

    private class updateTask extends AsyncTask<PreparedData, Void, Void>{
        @Override
        protected Void doInBackground(PreparedData... preparedData) {
            SQLiteDatabase db = preparedData[0].db;
            ContentValues values = preparedData[0].values;
            Score data = preparedData[0].data;
            try{
                db.update(TABLENAME, values, "ID = ?", new String[]{String.valueOf(data.getID())});
            }catch (SQLiteException e){
                Log.e("DBHelper", "Unable to insert into the " + TABLENAME, e.getCause());
            }
            return null;
        }
    }

    private class removeTask extends AsyncTask<PreparedData, Void, Void>{
        @Override
        protected Void doInBackground(PreparedData... preparedData) {
            SQLiteDatabase db = preparedData[0].db;
            ContentValues values = new ContentValues();
            Score data = preparedData[0].data;
            values.put("archived", 1);
            try{
                db.update(TABLENAME, values, "ID = ?", new String[]{String.valueOf(data.getID())});
            }catch (SQLiteException e){
                Log.e("DBHelper", "Unable to insert into the " + TABLENAME, e.getCause());
            }
            return null;
        }
    }
}
