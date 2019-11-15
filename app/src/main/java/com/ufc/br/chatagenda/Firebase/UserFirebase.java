package com.ufc.br.chatagenda.Firebase;

import com.ufc.br.chatagenda.DAO.UserDAO;
import com.ufc.br.chatagenda.Model.User;

import java.util.List;
import java.util.UUID;

public class UserFirebase implements UserDAO {

    private static DBFirebase dbFirebase;
    private static UserFirebase userFirebase;

    private UserFirebase(){
        dbFirebase = MyDBFirebase.getInstance();
    }

    public static UserFirebase getInstance(){

        if ( userFirebase == null ){
            userFirebase = new UserFirebase();
        }

        return userFirebase;

    }

    @Override
    public User create(User user) {

        user.setId(UUID.randomUUID().toString());
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
