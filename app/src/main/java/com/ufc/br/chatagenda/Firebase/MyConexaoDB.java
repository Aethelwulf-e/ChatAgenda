package com.ufc.br.chatagenda.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyConexaoDB implements ConexaoDB {

    private static MyConexaoDB dataBase = null;
    private static FirebaseDatabase firebaseDatabase = null;
    private static DatabaseReference databaseReference = null;

    private MyConexaoDB(){
        initFirebase();
    }

    private static void initFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static MyConexaoDB getInstance(){

        if ( dataBase == null ){
            dataBase = new MyConexaoDB();
        }

        return dataBase;

    }

    @Override
    public DatabaseReference getReference(){
        return databaseReference;
    }

}
