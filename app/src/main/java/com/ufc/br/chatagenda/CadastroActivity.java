package com.ufc.br.chatagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.ufc.br.chatagenda.DAO.UserDAO;
import com.ufc.br.chatagenda.DAO.UserDAOFirebase;
import com.ufc.br.chatagenda.Firebase.ConexaoAuth;
import com.ufc.br.chatagenda.Firebase.MyConexaoAuth;
import com.ufc.br.chatagenda.Model.User;

public class CadastroActivity extends AppCompatActivity {

    UserDAO userDAO = null;
    ConexaoAuth auth = null;

    User novoUser = null;

    TextView nome = null;
    TextView numero = null;
    TextView email = null;
    TextView senha = null;

    Button confirmar = null;
    Button cancelar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (TextView) this.findViewById( R.id.editTextNomeCadastro );
        email = (TextView) this.findViewById( R.id.editTextEmailCadastro );
        numero = (TextView) this.findViewById( R.id.editTextNumeroCadastro );
        senha = (TextView) this.findViewById( R.id.editTextSenhaCadastro );

        confirmar = (Button) this.findViewById( R.id.buttonConfirmarCadastro );
        cancelar = (Button) this.findViewById( R.id.buttonCancelarCadastro );

        userDAO = UserDAOFirebase.getInstance();
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

        novoUser = new User( nome, numero, email, senha );

        auth.getFirebaseAuth().createUserWithEmailAndPassword( email,senha )
                .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if( task.isSuccessful() ){
                            Toast.makeText(CadastroActivity.this, "Cadastro Ok", Toast.LENGTH_SHORT).show();

                            while ( auth.getFirebaseAuth() == null ){}
                            novoUser.setId( auth.getUser().getUid() );
                            userDAO.create(novoUser);

                        }else{
                            Toast.makeText(CadastroActivity.this, "Cadastro Falho", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

}
