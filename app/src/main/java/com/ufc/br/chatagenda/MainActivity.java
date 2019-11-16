package com.ufc.br.chatagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.ufc.br.chatagenda.Firebase.ConexaoAuth;
import com.ufc.br.chatagenda.Firebase.MyConexaoAuth;

public class MainActivity extends AppCompatActivity {

    Button cadastrar = null;
    Button entrar = null;
    EditText email = null;
    EditText senha = null;
    TextView erro = null;
    ConexaoAuth auth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        auth = MyConexaoAuth.getInstance();

        email =  this.findViewById( R.id.editTextEmail );
        senha =  this.findViewById( R.id.editTextSenha );

        erro = this.findViewById( R.id.textViewError );
        erro.setTextColor( getResources().getColor(android.R.color.transparent) );

        cadastrar =  this.findViewById( R.id.buttonCadastrar );
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IrParaCadastro();
            }
        });

        entrar = this.findViewById( R.id.buttonEntrar );
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logar();
            }
        });

    }

    private void Logar() {

        String email = this.email.getText().toString().trim();
        String senha = this.senha.getText().toString().trim();

        if( email.equals("") || senha.equals("") ){
            erro.setTextColor( getResources().getColor(android.R.color.holo_red_light) );
            erro.setText("Campos inválidos");
            return;
        }

        auth.getFirebaseAuth().signInWithEmailAndPassword( email, senha )
        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if( task.isSuccessful() ){

                    erro.setTextColor( getResources().getColor(android.R.color.transparent) );
                    Toast.makeText(MainActivity.this, "Entrando", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,ListaContatosActivity.class);
                    startActivity(intent);

                }else{

                    erro.setText("Usuário inválido");
                    erro.setTextColor( getResources().getColor(android.R.color.holo_red_light) );
                    Toast.makeText(MainActivity.this, "Não entrou", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void IrParaCadastro(){

        erro.setTextColor( getResources().getColor(android.R.color.transparent) );
        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);

    }
}
