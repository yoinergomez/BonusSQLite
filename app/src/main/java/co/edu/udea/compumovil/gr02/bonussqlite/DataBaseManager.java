package co.edu.udea.compumovil.gr02.bonussqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by esteban on 4/03/16.
 */
public class DataBaseManager {

    public static final String TABLE_NAME = "contactos";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nombre";
    public static final String CN_PHONE = "telefono";
    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_PHONE + " text);";
    private BdHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context){
        helper = new BdHelper(context);
        db = helper.getWritableDatabase();
    }

    private ContentValues generarContentValues(String nombre, String telefono) {
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME, nombre);
        valores.put(CN_PHONE, telefono);
        return valores;
    }

    public void insertar(String nombre, String telefono) {
        //bd.insert(TABLA, NullColumnHack, ContentValues);
        db.insert(TABLE_NAME, null, generarContentValues(nombre, telefono));
    }

    public void insertar2(String nombre, String telefono) {
        //INSERT INTO contactos   VALUES (null,'paco',9999)
        db.execSQL("insert into " + TABLE_NAME + " values (null,'" + nombre + "'," + telefono + ")");
    }

    public void eliminar(String nombre) {
        //bd.delete (Tabla, Claúsula Where, Argumentos Where)
        db.delete(TABLE_NAME, CN_NAME + "=?", new String[]{nombre});
    }

    public void eliminarMultiple(String nom1, String nom2) {
        db.delete(TABLE_NAME, CN_NAME + "IN (?,?)", new String[]{nom1, nom2});
    }

    public void eliminarTodo(){
        db.delete(TABLE_NAME, null, null);
    }

    public void modificarTelefono(String nombre, String nuevoTelefono) {
        /*bd.update(TABLA, ContentValues, Clausula Where, Argumentos Where)*/
        db.update(TABLE_NAME, generarContentValues(nombre, nuevoTelefono),
                CN_NAME + "=?", new String[]{nombre});
    }

    public Cursor cargarCursorContactos(){
        String[] columnas = new String[]{CN_ID,CN_NAME,CN_PHONE};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }

    public Cursor buscarContacto(String nombre) {
        String[] columnas = new String[]{CN_ID,CN_NAME,CN_PHONE};
        return db.query(TABLE_NAME,columnas,CN_NAME + "=?",new
                String[]{nombre},null,null,null);
    }
}
