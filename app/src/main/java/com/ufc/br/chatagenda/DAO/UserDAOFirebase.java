package com.ufc.br.chatagenda.DAO;

import android.Manifest;

import com.ufc.br.chatagenda.Firebase.ConexaoAuth;
import com.ufc.br.chatagenda.Firebase.ConexaoDB;
import com.ufc.br.chatagenda.Firebase.MyConexaoAuth;
import com.ufc.br.chatagenda.Firebase.MyConexaoDB;
import com.ufc.br.chatagenda.Model.User;

import java.util.List;
import java.util.UUID;

public class UserDAOFirebase implements UserDAO {

    private static ConexaoDB dbFirebase;
    private static UserDAOFirebase userFirebase;

    private UserDAOFirebase(){
        dbFirebase = MyConexaoDB.getInstance();
    }

    public static UserDAOFirebase getInstance(){

        if ( userFirebase == null ){
            userFirebase = new UserDAOFirebase();
        }

        return userFirebase;

    }

    @Override
    public User create(User user) {

        dbFirebase.getReference().child( "User" ).child(user.getId()).setValue(user);
        return user;

    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
