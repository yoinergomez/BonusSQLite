package co.edu.udea.compumovil.gr02.bonussqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by esteban on 4/03/16.
 */
public class BdHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "contactos.sql";
    private static final int DB_SCHEME_VERSION = 1;


    public BdHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
