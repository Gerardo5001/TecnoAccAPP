package com.example.tecnoaccv12.baseDatos;

public class Usuario {

    private int _id;
    private String _correo;
    private String _password;
    private String _usuario;

    public String get_usuario() {
        return _usuario;
    }

    public void set_usuario(String _usuario) {
        this._usuario = _usuario;
    }

    public Usuario(int _id, String _correo, String _password){
        this._id = _id;
        this._correo = _correo;
        this._password = _password;
    }
    public  Usuario(String _correo, String _password, String _usuario){
        this._correo = _correo;
        this._password = _password;
        this._usuario = _usuario;
    }
    public Usuario(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_correo() {
        return _correo;
    }

    public void set_correo(String _correo) {
        this._correo = _correo;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
