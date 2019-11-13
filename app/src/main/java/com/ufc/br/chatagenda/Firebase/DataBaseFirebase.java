package com.ufc.br.chatagenda.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataBaseFirebase{

    private static DataBaseFirebase dataBase = null;
    private static FirebaseDatabase firebaseDatabase = null;
    private static DatabaseReference databaseReference = null;

    private DataBaseFirebase(){ }

    private static void initFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static DataBaseFirebase getInstance(){

        if ( dataBase == null ){
            dataBase = new DataBaseFirebase();
        }

        initFirebase();

        return dataBase;

    }

    public DatabaseReference reference(){
        return databaseReference;
    }

}
