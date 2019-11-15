package com.ufc.br.chatagenda.Firebase;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyConexaoAuth implements ConexaoAuth {

    private static MyConexaoAuth auth = null;
    private static FirebaseAuth firebaseAuth = null;
    private static FirebaseAuth.AuthStateListener authStateListener = null;
    private static FirebaseUser firebaseUser = null;

    private MyConexaoAuth(){
        initFirebaseAuth();
    }

    private static void initFirebaseAuth(){
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if( user != null ){
                    firebaseUser = user;
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public static MyConexaoAuth getInstance(){

        if ( auth == null ){
            auth = new MyConexaoAuth();
        }

        return auth;

    }

    @Override
    public FirebaseAuth getFirebaseAuth(){

        return firebaseAuth;

    }

    @Override
    public FirebaseUser getUser(){

        return firebaseUser;

    }

    @Override
    public void logout(){

        firebaseAuth.signOut();

    }

}
