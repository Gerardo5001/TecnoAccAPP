package com.example.tecnoaccv12.baseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//TODO: Unificar Bases de datos para APP, WEB y DESK

public class ControlBD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TecnoAccBD3.bd";

    public ControlBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Estructura de las tablas
        String CREATE_TABLE_USUARIO = "CREATE TABLE " + "usuario" +
                " (" + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_correo" + " TEXT, " +
                "_password" + " TEXT, " +
                "_usuario" + " TEXT " +
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
        values.put("_usuario",user.get_usuario());
        // Obtenemos el objeto de la base de datos.
        SQLiteDatabase database = this.getWritableDatabase();
        // Insertamos nuestro registro en la tabla.
        long result = database.insert("usuario", null, values);
        // Cerramos la base de datos.
        database.close();
        return result;
    }
    public Usuario seachUsuario(String correo){
        // Definimos el query de búsqueda.
        String query = "SELECT * FROM " + "usuario" + " WHERE " + "_correo" + "=" + "\"" + correo +"\"";

        // Obtenemos el objeto de la base de datos.
        SQLiteDatabase database = this.getWritableDatabase();
        // Realizamos la búsqueda.
        Cursor cursor = database.rawQuery(query, null);
        // Creamos un objeto de tipo Product.
        Usuario user = new Usuario();
        // Verificamos si se encontró algún registro.
        if(cursor.moveToFirst()){
            // Obtenemos el id con el primer valor del string.
            user.set_id(Integer.parseInt(cursor.getString(0)));
            // Obtenemos el correo con el segundo valor del string.
            user.set_correo(cursor.getString(1));
            // Obtenemos el password con el tercer valor del string.
            user.set_password(cursor.getString(2));
            // Obtenemos el nombre del usuario con el tercer valor del string.
            user.set_usuario(cursor.getString(3));
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
    public boolean actualizarUsuario(String correo, String newPass){
        try {
            String query = "UPDATE " + "usuario" + " SET _password = " + "\"" + newPass + "\"" + "WHERE _correo = " + "\"" + correo + "\"";
            // Obtenemos el objeto de la base de datos.
            SQLiteDatabase database = this.getWritableDatabase();
            // Realizamos la búsqueda.
            database.execSQL(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_nombre","Audífonos Veeela A7s negro");
        values.put("_modelo","A7s negro");
        values.put("_descripcion","En la calle, en el colectivo o en la oficina, ten siempre a mano tus audífonos Huawei y ¡escápate de la rutina por un rato! Vas a poder disfrutar de la música que más te gusta y de tus podcasts favoritos cuando quieras y donde quieras.");
        values.put("_numParte","a7s");
        values.put("_categoria","Audifonos");
        values.put("_cantidad","100");
        values.put("_precio","188");
        values.put("_estatus", "MasVistos"); // MasVistos - Oferta
        database.insert("productos", null, values);
        values.clear();

        values.put("_nombre","Funda Acrigel Samsung Galaxy iPhone Huawei Xiaomi Oleo");
        values.put("_modelo","oem");
        values.put("_descripcion","Funda con orilla de TPU y parte trasera rígida.");
        values.put("_numParte","oem");
        values.put("_categoria","Fundas");
        values.put("_cantidad","100");
        values.put("_precio","69");
        values.put("_estatus", "MasVistos"); // MasVistos - Oferta
        database.insert("productos", null, values);
        values.clear();

        values.put("_nombre","Funda Para Xiaomi Redmi Note 8/ Note 8 Pro/note 9/ Note 9pro");
        values.put("_modelo","niunu");
        values.put("_descripcion","Hay demasiados modelos para tomar fotografías.");
        values.put("_numParte","niunu");
        values.put("_categoria","Fundas");
        values.put("_cantidad","100");
        values.put("_precio","105");
        values.put("_estatus", "MasVistos"); // MasVistos - Oferta
        database.insert("productos", null, values);
        values.clear();

        values.put("_nombre","Funda + Protector De Pantalla De Vidrio Templado P/samsung");
        values.put("_modelo","Tuyue");
        values.put("_descripcion","For galaxy Note 20");
        values.put("_numParte","tuyue");
        values.put("_categoria","Fundas");
        values.put("_cantidad","100");
        values.put("_precio","202");
        values.put("_estatus", "Oferta"); // MasVistos - Oferta
        database.insert("productos", null, values);
        values.clear();

        database.close();

    }
    public Producto buscarProducto(String numParte){
        // Definimos el query de búsqueda.
        String query = "SELECT * FROM " + "productos" + " WHERE " + "_numParte" + "=" + "\"" + numParte +"\"";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        Producto producto = new Producto();
        // Verificamos si se encontró algún registro.
        if(cursor.moveToFirst()){
            producto.setId(Integer.parseInt(cursor.getString(0)));
            producto.setNombre(cursor.getString(1));
            producto.setModelo(cursor.getString(2));
            producto.setDescripcion(cursor.getString(3));
            producto.setNumParte(cursor.getString(4));
            producto.setCategoria(cursor.getString(5));
            producto.setCantidad(Integer.parseInt(cursor.getString(6)));
            producto.setPrecio(Double.parseDouble(cursor.getString(7)));
            producto.setEstatus(cursor.getString(8));
            // Cerramos el cursor.
            cursor.close();
        }
        else {
            producto = null;
        }
        // Cerramos la base de datos.
        database.close();

        // Regresamos el objeto cliente
        return producto;
    }
}
