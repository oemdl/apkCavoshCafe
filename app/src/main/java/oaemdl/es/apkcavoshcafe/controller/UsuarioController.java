package oaemdl.es.apkcavoshcafe.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import oaemdl.es.apkcavoshcafe.db.Db;
import oaemdl.es.apkcavoshcafe.model.Usuario;

public class UsuarioController {
    Db db;

    public UsuarioController(Context context) {
        db = new Db( context );
    }

    public Usuario getUsuario() {
        db.Sentencia("select * from Usuario");
        Cursor cursor = db.getCursor();

        Usuario usuario = new Usuario();
        if ( cursor.moveToNext() ) {
            usuario.setId( cursor.getInt(0) );
            usuario.setNombres( cursor.getString(1) );
            usuario.setCorreo( cursor.getString(2) );
            usuario.setPasswordd( cursor.getString(3) );
            usuario.setLogin( cursor.getInt(4) );
        }

        cursor.close();
        return usuario;
    }

    public void Login(Usuario usuario) {
        db.Sentencia(String.format("select * from Usuario where Correo = '%s' and Passwordd = '%s'", usuario.getCorreo(), usuario.getPasswordd() ) );
        Cursor cursor = db.getCursor();

        if ( cursor.moveToNext() ) {
            usuario.setId( cursor.getInt(0) );
            usuario.setNombres( cursor.getString(1) );
            usuario.setLogin( 1 );
            Sesion( usuario );
        }

        cursor.close();
    }

    private void Sesion(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("Login", usuario.getLogin());

        db.Update("Usuario", values, "id = " + usuario.getId() );
    }

    public int Guardar(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("id", usuario.getId());
        values.put("Nombres", usuario.getNombres());
        values.put("Correo", usuario.getCorreo());
        values.put("Passwordd", usuario.getPasswordd());
        values.put("Login", usuario.getLogin());

        db.Sentencia( "select count(*) from Usuario" );
        Cursor cursor = db.getCursor();
        boolean bExiste = cursor.moveToNext();
        cursor.close();

        if ( bExiste )
            return db.Update("Usuario", values, "id=" + usuario.getId());
        else return db.Insert("Usuario", values);


    }
}
