package com.ufc.br.chatagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button cadastrar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        cadastrar = (Button) this.findViewById( R.id.buttonCadastrar );
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrParaCadastro();
            }
        });

    }

    private void IrParaCadastro(){

        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);

    }
}
