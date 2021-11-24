package com.example.tecnoaccv12;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.tecnoaccv12.baseDatos.ControlBD;

public class InicioActivity extends AppCompatActivity {
    private TableLayout TableLayoutProductosMasVistos;
    private TableLayout TableLayoutProductosPromocion;
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
            if(fila.moveToNext()) {
                TextView tViewPrecio2 = registro.findViewById(R.id.textViewPrecio2);
                TextView tViewDescripcion2 = registro.findViewById(R.id.textViewDescripcion2);
                tViewDescripcion2.setText(fila.getString(3));
                tViewPrecio2.setText("$ " + fila.getString(7));
            }
            tabla.addView(registro);
        }
    }
    public void mostrarCarrito(View view){

    }
    public void mostrarAjustes(View view){
        Bundle datos = getIntent().getExtras();
        Intent UsuarioActivity = new Intent(this, UsuarioActivity.class);
        UsuarioActivity.putExtra("correo", datos.getString("correo"));
        startActivity(UsuarioActivity);
    }
    public void mostrarProducto(View view){
        Intent ProductoActivity = new Intent(this, ProductoActivity.class);
        startActivity(ProductoActivity);
    }
}