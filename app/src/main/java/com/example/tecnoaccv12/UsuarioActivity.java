package com.example.tecnoaccv12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecnoaccv12.baseDatos.ControlBD;
import com.example.tecnoaccv12.baseDatos.Usuario;


public class UsuarioActivity extends AppCompatActivity {
    ControlBD controlBD;
    private TextView nombreUsuario;
    private TextView password;
    private TextView passwordNew;
    private TextView passwordNewConfir;
    private String correo;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        controlBD = new ControlBD(this, null,null,1);
        nombreUsuario = (TextView)findViewById(R.id.textViewUsuario);
        password = (TextView)findViewById(R.id.editTextTextPassword);
        passwordNew = (TextView)findViewById(R.id.editTextTextPasswordNuevo);
        passwordNewConfir = (TextView)findViewById(R.id.editTextPasswordNewConf);
        Bundle datos = getIntent().getExtras();
        correo = datos.getString("correo");
        usuario = controlBD.seachUsuario(correo);
        nombreUsuario.setText(usuario.get_usuario());
    }
    public void actualizarDatos(View view){
        if (usuario.get_password().equals(password.getText().toString())){
            if(passwordNew.getText().toString().equals(passwordNewConfir.getText().toString())){
                if(controlBD.actualizarUsuario(usuario.get_correo(),passwordNew.getText().toString())) {
                    usuario = controlBD.seachUsuario(correo);
                    Toast.makeText(this, "Se actualizo su contraseña", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "Ocurrio un error, verifique", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }

    }
}