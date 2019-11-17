package com.ufc.br.chatagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

    String Uid;

    ListView listView = null;
    ArrayList<Contato> contatos = null;
    CustomListAdapter adapter = null;

    int selecionado = -1;

    Intent intentDetalhes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        auth = MyConexaoAuth.getInstance();
        dataBase = MyConexaoDB.getInstance();

        while ( auth.getUser() == null ){}

        contatos = new ArrayList<Contato>();
        listView = this.findViewById( R.id.listaContatos );

        Uid = auth.getUser().getUid();

        ReadAll();

        adapter = new CustomListAdapter(ListaContatosActivity.this, contatos);
        listView.setAdapter(adapter);

        listView.setChoiceMode( ListView.CHOICE_MODE_SINGLE );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selecionado = i;
                listView.setSelection(i);
                listView.setSelector(android.R.color.holo_blue_light);

            }
        });

        intentDetalhes = new Intent(ListaContatosActivity.this,DetalhesActivity.class);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                intentDetalhes.putExtra("Contato",contatos.get(i));
                startActivity(intentDetalhes);
                return true;
            }
        });

    }

    private void ReadAll() {

        Query query = dataBase.getReference().child("Contatos").child(Uid).orderByChild("nome");

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

    public void update(){

        Intent intent;

        if (selecionado >= 0 && selecionado < contatos.size()) {
            intent = new Intent(ListaContatosActivity.this, AddContatoActivity.class);
            intent.putExtra("Contato", contatos.get(selecionado));
            intent.putExtra("Request","UPD");
            startActivityForResult(intent,222);
        }

    }

    public void  delete() {

        if (selecionado >= 0 && selecionado < contatos.size()) {

            Contato c = contatos.get(selecionado);

            dataBase.getReference().child("Contatos").child(Uid).child(c.getId()).removeValue();
            contatos.remove(selecionado);
            adapter.notifyDataSetChanged();


        } else {
            Toast.makeText(ListaContatosActivity.this, "Selecione um elemento", Toast.LENGTH_SHORT).show();
        }
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
                intent.putExtra("Request","ADD");
                startActivityForResult(intent,111);

                break;
            case R.id.menu_DEL:

                delete();

                break;
            case R.id.menu_UPD:

                update();

                break;
        }

        return true;
    }

}