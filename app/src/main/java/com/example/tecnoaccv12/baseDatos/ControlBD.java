package com.example.tecnoaccv12.baseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ControlBD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TecnoAccBD2.bd";

    public ControlBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Estructura de las tablas
        String CREATE_TABLE_USUARIO = "CREATE TABLE " + "usuario" +
                " (" + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_correo" + " TEXT, " +
                "_password" + " TEXT " +
                ")";
        String CREATE_TABLE_PRODUCTOS = "CREATE TABLE " + "productos" +
                " (" + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_nombre" + " TEXT, " +
                "_modelo" + " TEXT, " +
                "_descripcion" + " TEXT, " +
                "_numParte" + " TEXT, " +
                "_categoria" + " TEXT, " +
                "_cantidad" + " INTEGER, " +
                "_precio" + " DOUBLE, " +
                "_estatus" + " TEXT " +
                ")";

        // Creamos las tablas.
        sqLiteDatabase.execSQL(CREATE_TABLE_USUARIO);
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCTOS);

        //Pruebas
        //addProducto();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Borramos la tabla de productos si existe.
        String DROP_TABLE_USUARIO = "DROP TABLE IF EXISTS " + "usuario";
        String DROP_TABLE_PRODUCTOS = "DROP TABLE IF EXISTS " + "productos";

        // Ejecutamos la eliminacion de la tabla.
        sqLiteDatabase.execSQL(DROP_TABLE_USUARIO);
        sqLiteDatabase.execSQL(DROP_TABLE_PRODUCTOS);
        // Creamos la nueva tabla
        onCreate(sqLiteDatabase);
    }

    public long addUsuario(Usuario user){
        // Creamos el contenedor de valores.
        ContentValues values = new ContentValues();
        // Agregamos cada dato requerido de nuestra columna.
        values.put("_correo", user.get_correo());
        values.put("_password",user.get_password());
        // Obtenemos el objeto de la base de datos.
        SQLiteDatabase database = this.getWritableDatabase();
        // Insertamos nuestro registro en la tabla.
        long result = database.insert("usuario", null, values);
        // Cerramos la base de datos.
        database.close();
        return result;
    }
    public Usuario seachUsuario(String correo){
        // Definimos el query de b??squeda.
        String query = "SELECT * FROM " + "usuario" + " WHERE " + "_correo" + "=" + "\"" + correo +"\"";

        // Obtenemos el objeto de la base de datos.
        SQLiteDatabase database = this.getWritableDatabase();
        // Realizamos la b??squeda.
        Cursor cursor = database.rawQuery(query, null);
        // Creamos un objeto de tipo Product.
        Usuario user = new Usuario();
        // Verificamos si se encontr?? alg??n registro.
        if(cursor.moveToFirst()){
            // Obtenemos el id con el primer valor del string.
            user.set_id(Integer.parseInt(cursor.getString(0)));
            // Obtenemos el correo con el segundo valor del string.
            user.set_correo(cursor.getString(1));
            // Obtenemos la direccion con el tercer valor del string.
            user.set_password(cursor.getString(2));
            // Cerramos el cursor.
            cursor.close();
        }
        else {
            user = null;
        }
        // Cerramos la base de datos.
        database.close();

        // Regresamos el objeto cliente
        return user;
    }

    public Cursor mostrarProductos(String status){
        List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();

        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + "productos WHERE _estatus= "+ "\"" + status +"\"";
        Cursor filas = database.rawQuery(query, null);
        return filas;
    }
    public void addProducto(){
        ContentValues values = new ContentValues();
        values.put("_nombre","Aud??fonos in-ear inal??mbricos");
        values.put("_modelo","FreeBuds");
        values.put("_descripcion","En la calle, en el colectivo o en la oficina, ten siempre a mano tus aud??fonos Huawei y ??esc??pate de la rutina por un rato! Vas a poder disfrutar de la m??sica que m??s te gusta y de tus podcasts favoritos cuando quieras y donde quieras.");
        values.put("_numParte","T0003");
        values.put("_categoria","Audifonos");
        values.put("_cantidad","100");
        values.put("_precio","3260");
        values.put("_estatus", "Oferta");
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert("productos", null, values);
        database.close();
    }
}
