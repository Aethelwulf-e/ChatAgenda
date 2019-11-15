package com.ufc.br.chatagenda.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyDBFirebase implements DBFirebase {

    private static MyDBFirebase dataBase = null;
    private static FirebaseDatabase firebaseDatabase = null;
    private static DatabaseReference databaseReference = null;

    private MyDBFirebase(){ }

    private static void initFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static MyDBFirebase getInstance(){

        if ( dataBase == null ){
            dataBase = new MyDBFirebase();
        }

        initFirebase();

        return dataBase;

    }

    @Override
    public DatabaseReference getReference(){
        return databaseReference;
    }

}
