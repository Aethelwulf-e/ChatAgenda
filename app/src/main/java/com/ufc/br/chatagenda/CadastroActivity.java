package com.ufc.br.chatagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.ufc.br.chatagenda.Firebase.ConexaoAuth;
import com.ufc.br.chatagenda.Firebase.ConexaoDB;
import com.ufc.br.chatagenda.Firebase.MyConexaoAuth;
import com.ufc.br.chatagenda.Firebase.MyConexaoDB;
import com.ufc.br.chatagenda.Model.User;

public class CadastroActivity extends AppCompatActivity {

    ConexaoAuth auth = null;
    ConexaoDB dataBase = null;

    User novoUser = null;

    EditText nome = null;
    EditText numero = null;
    EditText email = null;
    EditText senha = null;

    TextView nomeError = null;
    TextView numeroError = null;
    TextView emailError = null;
    TextView senhaError = null;

    Button confirmar = null;
    Button cancelar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = this.findViewById( R.id.editTextNomeCadastro );
        email = this.findViewById( R.id.editTextEmailCadastro );
        numero = this.findViewById( R.id.editTextNumeroCadastro );
        senha = this.findViewById( R.id.editTextSenhaCadastro );

        nomeError = this.findViewById( R.id.textViewErrorNome );
        emailError = this.findViewById( R.id.textViewErrorEmail );
        numeroError = this.findViewById( R.id.textViewErrorNumero );
        senhaError = this.findViewById( R.id.textViewErrorSenah );

        nomeError.setTextColor( getResources().getColor(android.R.color.transparent) );
        emailError.setTextColor( getResources().getColor(android.R.color.transparent) );
        numeroError.setTextColor( getResources().getColor(android.R.color.transparent) );
        senhaError.setTextColor( getResources().getColor(android.R.color.transparent) );

        confirmar = this.findViewById( R.id.buttonConfirmarCadastro );
        cancelar = this.findViewById( R.id.buttonCancelarCadastro );

        dataBase = MyConexaoDB.getInstance();
        auth = MyConexaoAuth.getInstance();

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CadastroActivity.this, "Cadastro Cancelado", Toast.LENGTH_SHORT).show();
                CadastroActivity.this.finish();
            }
        });

    }

    private void cadastrar(){

        String nome = this.nome.getText().toString();
        String numero = this.numero.getText().toString();
        String email = this.email.getText().toString();
        String senha = this.senha.getText().toString();

        if( nome.equals("") ){
            nomeError.setTextColor( getResources().getColor(android.R.color.holo_red_light) );
            return;
        }else{
            nomeError.setTextColor( getResources().getColor(android.R.color.transparent) );
        }
        if( numero.equals("") ){
            numeroError.setTextColor( getResources().getColor(android.R.color.holo_red_light) );
            return;
        }else{
            numeroError.setTextColor( getResources().getColor(android.R.color.transparent) );
        }
        if( email.equals("") ){
            emailError.setTextColor( getResources().getColor(android.R.color.holo_red_light) );
            return;
        }else{
            emailError.setTextColor( getResources().getColor(android.R.color.transparent) );
        }
        if( senha.equals("") ){
            senhaError.setTextColor( getResources().getColor(android.R.color.holo_red_light) );
            return;
        }else{
            senhaError.setTextColor( getResources().getColor(android.R.color.transparent) );
        }

        novoUser = new User( nome, numero, email, senha );

        auth.getFirebaseAuth().createUserWithEmailAndPassword( email,senha )
                .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if( task.isSuccessful() ){
                            Toast.makeText(CadastroActivity.this, "Cadastro Ok", Toast.LENGTH_SHORT).show();

                            while ( auth.getUser() == null ){}
                            novoUser.setId( auth.getUser().getUid() );
                            dataBase.getReference().child( "User" ).child(novoUser.getId()).setValue(novoUser);
                            CadastroActivity.this.finish();

                        }else{
                            emailError.setTextColor( getResources().getColor(android.R.color.holo_red_light) );
                            Toast.makeText(CadastroActivity.this, "Cadastro Falho", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

}
