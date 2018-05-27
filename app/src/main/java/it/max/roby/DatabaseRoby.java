package it.max.roby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import it.max.roby.db.TB_Comandi;

public class DatabaseRoby extends SQLiteOpenHelper {

    public static final String COL_ID = "id";
    public static final String COL_COMANDO = "comando";
    public static final String COL_IDCOMANDO = "id_comando";
    public static final String COL_PAROLA = "parola";
    public static final String COL_ATTINENZA= "attinenza";

    private static final String CREATE_TABLE_COMANDI = "CREATE TABLE TB_Comandi(id Integer PRIMARY KEY,comando TEXT,parola TEXT,attinenza INTEGER)";
    private static final String DATABASE_NAME = "DROIDCOMPANION";
    private static int DATABASE_VERSION = 0;

    public static final String TABLE_COMANDI = "TB_Comandi";

    static {
        DATABASE_VERSION = 1;
    }

    public DatabaseRoby(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_COMANDI);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TB_Comandi");
        onCreate(db);
    }

    public Integer getVersionDataBase() {
        return Integer.valueOf(getReadableDatabase().getVersion());
    }

    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        boolean z = true;
        if (tableName == null || db == null || !db.isOpen()) {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", tableName});
        if (!cursor.moveToFirst()) {
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        if (count <= 0) {
            z = false;
        }
        return z;
    }


    public boolean insertComandi (String com, String parol, int attin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_COMANDO, com);
        contentValues.put(COL_PAROLA, parol);
        contentValues.put(COL_ATTINENZA, attin);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public List<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COL_COMANDO)));
            res.moveToNext();
        }
        return array_list;
    }

    public void closeDB() {
        SQLiteDatabase db = getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
