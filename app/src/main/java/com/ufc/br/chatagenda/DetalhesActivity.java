package com.ufc.br.chatagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ufc.br.chatagenda.Firebase.ConexaoDB;
import com.ufc.br.chatagenda.Firebase.MyConexaoDB;
import com.ufc.br.chatagenda.Model.Contato;
import com.ufc.br.chatagenda.Model.User;

public class DetalhesActivity extends AppCompatActivity {

    Contato contato = null;
    User user = null;

    ConexaoDB dataBase = MyConexaoDB.getInstance();

    TextView idContato = null;
    TextView nomeContato = null;
    TextView numeroContato = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        contato = (Contato) getIntent().getSerializableExtra("Contato");

        idContato = this.findViewById(R.id.textViewIdContato);
        nomeContato = this.findViewById(R.id.textViewNomeContato);
        numeroContato = this.findViewById(R.id.textViewNumeroContato);

        idContato.setText(""+contato.getId());
        nomeContato.setText(""+contato.getNome());
        numeroContato.setText(""+contato.getNumero());
    }
}