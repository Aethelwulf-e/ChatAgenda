package com.ufc.br.chatagenda.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public interface ConexaoAuth {

    FirebaseAuth getFirebaseAuth();
    FirebaseUser getUser();
    void logout();

}
