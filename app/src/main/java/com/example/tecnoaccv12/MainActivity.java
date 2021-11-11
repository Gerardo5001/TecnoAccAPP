package com.example.tecnoaccv12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecnoaccv12.baseDatos.ControlBD;
import com.example.tecnoaccv12.baseDatos.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText correo;
    private EditText contraseña;
    ControlBD controlBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = (EditText)findViewById(R.id.editTextTextEmailAddress);
        contraseña = (EditText)findViewById(R.id.editTextTextPassword2);
        controlBD = new ControlBD(this, null,null,1);
    }
    public void acceder(View view){
        if(ValidarMail(correo.getText().toString())){

            Usuario user = controlBD.seachUsuario(correo.getText().toString());
            if(user!=null){
                if(user.get_password().equals(contraseña.getText().toString())){
                    Intent InicioActivity = new Intent(this, InicioActivity.class);
                    startActivity(InicioActivity);
                }else {
                    Toast.makeText(this, "Contraseña no valida", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "El usuario no existe, favor de registrarse", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Correo no valido", Toast.LENGTH_SHORT).show();
        }
    }
    public void registrase(View view)   {
        EditText usuarioName = (EditText)findViewById(R.id.editTextName);
        if(usuarioName.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese un nombre de usuario.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (ValidarMail(correo.getText().toString()) && contraseña.getText().toString() != null) {
                Usuario usuario = controlBD.seachUsuario(correo.getText().toString());
                if (usuario != null) {
                    Toast.makeText(this, "Este correo ya esta registrado.", Toast.LENGTH_SHORT).show();
                } else {
                    EditText contraConfir = (EditText) findViewById(R.id.editTextTextPasswordConfir);
                    if (contraseña.getText().toString().equals(contraConfir.getText().toString())) {
                        Usuario user = new Usuario(correo.getText().toString(), contraseña.getText().toString(), usuarioName.getText().toString());
                        controlBD.addUsuario(user);
                        Intent InicioActivity = new Intent(this, InicioActivity.class);
                        startActivity(InicioActivity);
                        Toast.makeText(this, "Registro correcto.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "Correo no valido.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static boolean ValidarMail(String email) {
        // Patron para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);
        return mather.find();
    }
    public void textRegistroOn(View view){
        EditText usuario = (EditText)findViewById(R.id.editTextName);
        usuario.setVisibility(View.VISIBLE);
        EditText passwordConfir = (EditText)findViewById(R.id.editTextTextPasswordConfir);
        passwordConfir.setVisibility(View.VISIBLE);
        Button btnRegistrarse = (Button)findViewById(R.id.buttonRegistrarse);
        btnRegistrarse.setVisibility(View.VISIBLE);
        Button btnAcceder = (Button)findViewById(R.id.buttonAcceder);
        btnAcceder.setVisibility(View.INVISIBLE);
        TextView text1 = (TextView)findViewById(R.id.textView4); //6 9
        text1.setVisibility(View.INVISIBLE);
        TextView text2 = (TextView)findViewById(R.id.textView6); //6 9
        text2.setVisibility(View.INVISIBLE);
        TextView text3 = (TextView)findViewById(R.id.textView9); //6 9
        text3.setVisibility(View.INVISIBLE);

    }
}