package com.ufc.br.chatagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ufc.br.chatagenda.Firebase.ConexaoAuth;
import com.ufc.br.chatagenda.Firebase.ConexaoDB;
import com.ufc.br.chatagenda.Firebase.MyConexaoAuth;
import com.ufc.br.chatagenda.Firebase.MyConexaoDB;
import com.ufc.br.chatagenda.Model.Contato;
import com.ufc.br.chatagenda.Model.User;

import java.util.ArrayList;


// Teste

public class AddContatoActivity extends AppCompatActivity {

    ConexaoAuth auth = null;

    EditText nome = null;
    EditText numero = null;
    EditText email = null;

    TextView nomeError = null;
    TextView numeroError = null;
    TextView emailError = null;

    Button confirmar = null;
    Button cancelar = null;

    public ConexaoDB dbFirebase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contato);

        auth = MyConexaoAuth.getInstance();
        dbFirebase = MyConexaoDB.getInstance();

        nome = this.findViewById( R.id.editTextNomeContato );
        numero = this.findViewById( R.id.editTextNumeroContato );
        email = this.findViewById( R.id.editTextEmailContato );

        nomeError = this.findViewById( R.id.textViewNomeError );
        numeroError = this.findViewById( R.id.textViewNumeroError );
        emailError = this.findViewById( R.id.textViewEmailError );

        nomeError.setTextColor( getResources().getColor(android.R.color.transparent) );
        emailError.setTextColor( getResources().getColor(android.R.color.transparent) );
        numeroError.setTextColor( getResources().getColor(android.R.color.transparent) );

        confirmar = this.findViewById( R.id.buttonConfirmarCadastro );
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExistContato();
            }
        });

        cancelar = this.findViewById( R.id.buttonCancelarCadastro );
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddContatoActivity.this.finish();
            }
        });

    }


    ArrayList<User> aux1 = new ArrayList<User>();

    private void ExistContato(){

        String email = this.email.getText().toString();
        String numero = this.numero.getText().toString();

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

        Query query = dbFirebase.getReference().child("User").orderByChild("email").equalTo(email);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                aux1.clear();

                for ( DataSnapshot obj : dataSnapshot.getChildren() ){
                    aux1.add( obj.getValue(User.class) );
                }

                if( aux1.size() == 0 ){
                    Toast.makeText(AddContatoActivity.this, "Esse contao não existe", Toast.LENGTH_SHORT).show();
                    emailError.setTextColor( getResources().getColor(android.R.color.holo_red_light) );
                }else{
                    Add(aux1.get(0).getId());
                    Toast.makeText(AddContatoActivity.this, "Existe", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Add(String idContato) {

        String nome = this.nome.getText().toString();
        String email = this.email.getText().toString();
        String numero = this.numero.getText().toString();

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

        Contato contato = new Contato(nome,numero,email);

        dbFirebase.getReference().child("Contatos").child(auth.getUser().getUid()).child(idContato).setValue(contato);
        AddContatoActivity.this.finish();

    }

}
