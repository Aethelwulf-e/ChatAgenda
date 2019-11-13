package com.ufc.br.chatagenda.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataBase {

    private static DataBase dataBase = null;
    private static FirebaseDatabase firebaseDatabase = null;
    private static DatabaseReference databaseReference = null;

    private DataBase(){ }

    private static void initFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static DataBase getInstance(){

        if ( dataBase == null ){
            dataBase = new DataBase();
        }

        initFirebase();

        return dataBase;

    }


}
