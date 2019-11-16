package com.ufc.br.chatagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ufc.br.chatagenda.CustomAdapters.CustomListAdapter;
import com.ufc.br.chatagenda.Firebase.ConexaoAuth;
import com.ufc.br.chatagenda.Firebase.ConexaoDB;
import com.ufc.br.chatagenda.Firebase.MyConexaoAuth;
import com.ufc.br.chatagenda.Firebase.MyConexaoDB;
import com.ufc.br.chatagenda.Model.Contato;

import java.util.ArrayList;

public class ListaContatosActivity extends AppCompatActivity {

    ConexaoAuth auth = null;
    ConexaoDB dataBase = null;

    ListView listView = null;
    ArrayList<Contato> contatos = null;
    CustomListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        auth = MyConexaoAuth.getInstance();
        dataBase = MyConexaoDB.getInstance();

        while ( auth.getUser() == null ){}

        contatos = new ArrayList<Contato>();
        listView = this.findViewById( R.id.listaContatos );
        ReadAll( auth.getUser().getUid() );

        adapter = new CustomListAdapter(ListaContatosActivity.this, contatos);
        listView.setAdapter(adapter);

    }

    private void ReadAll(String uid) {

        Query query = dataBase.getReference().child("Contatos").child(uid).orderByChild("nome");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                contatos.clear();

                for ( DataSnapshot obj : dataSnapshot.getChildren() ){
                    contatos.add( obj.getValue(Contato.class) );
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contatos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;

        switch (item.getItemId()){
            case R.id.menu_ADD:

                intent = new Intent(ListaContatosActivity.this, AddContatoActivity.class);
                startActivity(intent);

                break;
            case R.id.menu_DEL:

                break;
            case R.id.menu_UPD:

                break;
        }

        return true;
    }

}