package com.example.tecnoaccv12;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.tecnoaccv12.baseDatos.ControlBD;
import com.example.tecnoaccv12.baseDatos.Producto;

public class InicioActivity extends AppCompatActivity {
    private TableLayout TableLayoutProductosMasVistos;
    private TableLayout TableLayoutProductosPromocion;
    Producto producto = new Producto();
    ControlBD controlBD;

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder confirmacion = new AlertDialog.Builder(this);
        confirmacion.setMessage("Esta seguro que desea salir de la aplicacion?");
        confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Intent cierre = new Intent(Intent.ACTION_MAIN);
                cierre.addCategory(Intent.CATEGORY_HOME);
                cierre.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(cierre);
            }
        });
        confirmacion.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert = confirmacion.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        TableLayoutProductosMasVistos = findViewById(R.id.TableLayoutProductosMasVistos);
        TableLayoutProductosPromocion = findViewById(R.id.TableLayoutProductosPromocion);
        controlBD = new ControlBD(this,null,null,1);
        //controlBD.addProducto();
        llenarTabla("MasVistos", TableLayoutProductosMasVistos);
        llenarTabla("Oferta", TableLayoutProductosPromocion);
    }

    public void llenarTabla(String status, TableLayout tabla){
        Cursor fila = controlBD.mostrarProductos(status);
        while(fila.moveToNext()) {

            View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout_tecnoacc, null, false);
            TextView tViewPrecio = registro.findViewById(R.id.textViewPrecio);
            TextView tViewDescripcion = registro.findViewById(R.id.textViewNombre);
            tViewDescripcion.setText(fila.getString(3));
            tViewPrecio.setText("$ " + fila.getString(7));
            cambiarImagen(registro, fila.getString(4));

            if(fila.moveToNext()) {
                TextView tViewPrecio2 = registro.findViewById(R.id.textViewPrecio2);
                TextView tViewDescripcion2 = registro.findViewById(R.id.textViewDescripcion2);
                tViewDescripcion2.setText(fila.getString(3));
                tViewPrecio2.setText("$ " + fila.getString(7));
                cambiarImagen2(registro, fila.getString(4));
            }
            tabla.addView(registro);
        }
    }
    public void cambiarImagen(View registro, String numParte){
        ImageView imagenProducto = registro.findViewById(R.id.imageProducto);
        switch (numParte){
            case "t0003":
                imagenProducto.setImageResource(R.drawable.t0003);
                imagenProducto.setContentDescription("t0003");
                break;
            case "a7s":
                imagenProducto.setImageResource(R.drawable.a7s);
                imagenProducto.setContentDescription("a7s");
                break;
            case "c28":
                imagenProducto.setImageResource(R.drawable.c28);
                imagenProducto.setContentDescription("c28");
                break;
            case "inear":
                imagenProducto.setImageResource(R.drawable.inear);
                imagenProducto.setContentDescription("inear");
                break;
            case "niunu":
                imagenProducto.setImageResource(R.drawable.niunu);
                imagenProducto.setContentDescription("niunu");
                break;
            case "oem":
                imagenProducto.setImageResource(R.drawable.oem);
                imagenProducto.setContentDescription("oem");
                break;
            case "tuyue":
                imagenProducto.setImageResource(R.drawable.tuyue);
                imagenProducto.setContentDescription("tuyue");
                break;
            default:
                imagenProducto.setImageResource(R.drawable.defa);
                imagenProducto.setContentDescription("default");
        }
    }
    public void cambiarImagen2(View registro, String numParte){
        ImageView imagenProducto = registro.findViewById(R.id.imageImagenProducto2);
        switch (numParte){
            case "t0003":
                imagenProducto.setImageResource(R.drawable.t0003);
                imagenProducto.setContentDescription("t0003");
                break;
            case "a7s":
                imagenProducto.setImageResource(R.drawable.a7s);
                imagenProducto.setContentDescription("a7s");
                break;
            case "c28":
                imagenProducto.setImageResource(R.drawable.c28);
                imagenProducto.setContentDescription("c28");
                break;
            case "inear":
                imagenProducto.setImageResource(R.drawable.inear);
                imagenProducto.setContentDescription("inear");
                break;
            case "niunu":
                imagenProducto.setImageResource(R.drawable.niunu);
                imagenProducto.setContentDescription("niunu");
                break;
            case "oem":
                imagenProducto.setImageResource(R.drawable.oem);
                imagenProducto.setContentDescription("oem");
                break;
            case "tuyue":
                imagenProducto.setImageResource(R.drawable.tuyue);
                imagenProducto.setContentDescription("tuyue");
                break;
            default:
                imagenProducto.setImageResource(R.drawable.defa);
                imagenProducto.setContentDescription("default");
        }
    }
    public void mostrarCarrito(View view){
        //Bundle datos = getIntent().getExtras();
        Intent CarroComprasActivity = new Intent(this, CarroComprasActivity.class);
        //CarritoActivity.putExtra("correo", datos.getString("correo"));
        startActivity(CarroComprasActivity);
    }
    public void mostrarAjustes(View view){
        Bundle datos = getIntent().getExtras();
        Intent UsuarioActivity = new Intent(this, UsuarioActivity.class);
        UsuarioActivity.putExtra("correo", datos.getString("correo"));
        startActivity(UsuarioActivity);
    }
    public void mostrarProducto(View view){
        ImageView imagen = (ImageView) view;
        Intent ProductoActivity = new Intent(this, ProductoActivity.class);
        producto = controlBD.buscarProducto(imagen.getContentDescription().toString());
        ProductoActivity.putExtra("numParteProducto", producto.getNumParte());
        startActivity(ProductoActivity);
    }
}