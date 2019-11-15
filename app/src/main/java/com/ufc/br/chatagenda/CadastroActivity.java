package com.ufc.br.chatagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ufc.br.chatagenda.DAO.UserDAO;
import com.ufc.br.chatagenda.Firebase.UserFirebase;
import com.ufc.br.chatagenda.Model.User;

public class CadastroActivity extends AppCompatActivity {

    UserDAO userDAO = null;

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

        userDAO = UserFirebase.getInstance();

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

        User user = new User( nome, numero, email, senha );

        userDAO.create(user);

        Toast.makeText(CadastroActivity.this, "Cadastro Realizado", Toast.LENGTH_SHORT).show();

        CadastroActivity.this.finish();

    }

}
