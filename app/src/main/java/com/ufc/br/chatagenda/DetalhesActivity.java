package com.ufc.br.chatagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.ufc.br.chatagenda.Model.Conversa;
import com.ufc.br.chatagenda.Model.Mensagem;
import com.ufc.br.chatagenda.Model.User;

import java.util.ArrayList;

public class DetalhesActivity extends AppCompatActivity {

    Contato contato = null;
    String Uid;

    ConexaoAuth auth = MyConexaoAuth.getInstance();

    TextView idContato = null;
    TextView nomeContato = null;
    TextView numeroContato = null;

    Button irChat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        contato = (Contato) getIntent().getSerializableExtra("Contato");

        Uid = auth.getUser().getUid();

        idContato = this.findViewById(R.id.textViewIdContato);
        nomeContato = this.findViewById(R.id.textViewNomeContato);
        numeroContato = this.findViewById(R.id.textViewNumeroContato);

        irChat = this.findViewById(R.id.buttonChat);

        irChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irChatClick();
            }
        });

        idContato.setText("@ID: "+contato.getId());
        nomeContato.setText("@"+contato.getNome());
        numeroContato.setText("@Número: "+contato.getNumero());

    }

    private void irChatClick() {

        Intent intent = new Intent(DetalhesActivity.this,ChatActivity.class);
        intent.putExtra("Contato",contato);
        startActivity(intent);

    }
}