package com.example.pokemon_tracker_from;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PokemonDBProvider extends ContentProvider {
    public static final String DBNAME = "PokeDex.db"; // database file
    public static final String TABLE_NAME = "Pokemon"; // table name

    // Column names
    public static final String _ID = "_id";
    public static final String NatNum = "NationalNumber";
    public static final String Name = "Name";
    public static final String Species = "Species";
    public static final String Gender = "Gender";
    public static final String Height = "Height";
    public static final String Weight = "Weight";
    public static final String Level = "Level";
    public static final String HP = "HP";
    public static final String Attack = "Attack";
    public static final String Defence = "Defence";
    private static final String SQL_CREATE_MAIN =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NatNum + " TEXT, " +
                    Name + " TEXT, " +
                    Species + "TEXT" +
                    Gender + " TEXT, " +
                    Height + " REAL, " + //decimal
                    Weight + " REAL, " +
                    Level + " INTEGER, " +
                    HP + " INTEGER, " +
                    Attack + " INTEGER, " +
                    Defence + " INTEGER" +
                    ");";
    public static final Uri CONTENT_URI =
            Uri.parse("content://my.package.PokemonDBProvider.provider");
    MainDatabaseHelper mHelper;
    protected final class MainDatabaseHelper extends SQLiteOpenHelper {

        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        }
        }


@Override
public int delete(Uri uri, String whereClause,
                  String[] whereArgs) {
    return mHelper.getWritableDatabase().
            delete(TABLE_NAME, whereClause,
                    whereArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        assert values != null;
        Integer natNum = values.getAsInteger("NationalNumber");
        String name = values.getAsString("Name");
        String species = values.getAsString("Species");
        String gender = values.getAsString("Gender");
        Double height = values.getAsDouble("Height");
        Double weight = values.getAsDouble("Weight");
        Integer level = values.getAsInteger("Level");
        Integer hp = values.getAsInteger("HP");
        Integer attack = values.getAsInteger("Attack");
        Integer defence = values.getAsInteger("Defence");

        SQLiteDatabase db = mHelper.getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, values);
        return Uri.withAppendedPath(uri," "+ id);
    }

    @Override
    public boolean onCreate() {
        mHelper = new MainDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor c = mHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs,
                null,null,null,sortOrder);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        return mHelper.getWritableDatabase()
                .update("Pokemon", values, selection, selectionArgs);
    }
}