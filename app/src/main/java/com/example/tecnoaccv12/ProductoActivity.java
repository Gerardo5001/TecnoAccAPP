package com.example.tecnoaccv12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
//TODO:Agrgar funcionalidad a btn Agregar carrito -> Depende de funcion carrito
//TODO: Agregar funcionalidad a btn Comprar ahora -> Depende de funcion carrito
//TODO: La pantalla despliege el producto seleccionado

public class ProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
    }
    public void agregarProducto(View View){
        Toast.makeText(this, "Agregue funcion!", Toast.LENGTH_SHORT).show();
    }
}