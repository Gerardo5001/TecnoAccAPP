package com.example.tecnoaccv12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecnoaccv12.baseDatos.ControlBD;
import com.example.tecnoaccv12.baseDatos.Producto;
//TODO:Agrgar funcionalidad a btn Agregar carrito -> Depende de funcion carrito
//TODO: Agregar funcionalidad a btn Comprar ahora -> Depende de funcion carrito
//TODO: La pantalla despliege el producto seleccionado

public class ProductoActivity extends AppCompatActivity {

    private TextView textViewNombre;
    private TextView textViewModelo;
    private TextView textViewDescripcion;
    private TextView textViewNumParte;
    private TextView textViewPrecioProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        textViewNombre = findViewById(R.id.textViewNombre);
        textViewModelo = findViewById(R.id.textViewModelo);
        textViewDescripcion = findViewById(R.id.textViewDescripcion);
        textViewNumParte = findViewById(R.id.textViewNumParte);
        textViewPrecioProducto = findViewById(R.id.textViewPrecioProducto);
        Bundle datos = getIntent().getExtras();
        cambiarImagen(datos.getString("numParteProducto"));
        agregarInformacion(datos.getString("numParteProducto"));
    }
    public void agregarProducto(View View){
        Toast.makeText(this, "Agregue funcion!", Toast.LENGTH_SHORT).show();
    }
    private void cambiarImagen(String numParte){
        ImageView imagenProducto = findViewById(R.id.imageViewProducto);
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
    private void agregarInformacion(String numParte){
        ControlBD conexion = new ControlBD(this,null,null,1);
        Producto producto = conexion.buscarProducto(numParte);
        textViewNombre.setText(producto.getNombre());
        textViewModelo.setText(producto.getModelo());
        textViewDescripcion.setText(producto.getDescripcion());
        textViewNumParte.setText(producto.getNumParte());
        textViewPrecioProducto.setText(producto.getPrecio()+"");
    }
}