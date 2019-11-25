package com.ufc.br.chatagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ufc.br.chatagenda.CustomAdapters.ChatListAdapter;
import com.ufc.br.chatagenda.Firebase.ConexaoAuth;
import com.ufc.br.chatagenda.Firebase.ConexaoDB;
import com.ufc.br.chatagenda.Firebase.MyConexaoAuth;
import com.ufc.br.chatagenda.Firebase.MyConexaoDB;
import com.ufc.br.chatagenda.Model.Contato;
import com.ufc.br.chatagenda.Model.Conversa;
import com.ufc.br.chatagenda.Model.Mensagem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class ChatActivity extends AppCompatActivity {

    ConexaoDB dataBase = MyConexaoDB.getInstance();
    ConexaoAuth auth = MyConexaoAuth.getInstance();
    Contato contato = null;
    String Uid;

    Conversa conversa;

    EditText Nmensagem = null;
    Button enviar = null;
    ListView listView = null;

    ArrayList<Mensagem> mensagens = new ArrayList<>();
    //ArrayAdapter<Mensagem> adapter = null;
    ChatListAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        contato = (Contato) getIntent().getSerializableExtra("Contato");
        Uid = auth.getUser().getUid();

        enviar = this.findViewById(R.id.buttonEnviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarMsgm();
            }
        });

        Nmensagem = this.findViewById(R.id.editTextMensagem);

        listView = this.findViewById(R.id.listViewChat);

        adapter = new ChatListAdapter( this, mensagens, contato.getNome() );
        //adapter = new ArrayAdapter<Mensagem>(this,android.R.layout.simple_list_item_1, mensagens);
        listView.setAdapter(adapter);

        conversa = new Conversa( UUID.randomUUID().toString(), Uid+"|"+contato.getId() );

        inicializaMensagens();

    }

    private void enviarMsgm() {

        String m = this.Nmensagem.getText().toString().trim();

        if( !m.equals("") ){

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");

            Date data = new Date();

            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            Date data_atual = cal.getTime();

            String data_completa = dateFormat.format(data_atual);

           Mensagem mensagem = new Mensagem(Uid, contato.getId(), data_completa, m);
           conversa.getMensagens().add(mensagem);
           dataBase.getReference().child("Chat").child(conversa.getCid()).setValue(conversa);

        }

    }

    public void inicializaMensagens(){

        buscaSentido1();
        buscaSentido2();

    }

    private void buscaSentido1() {

        Query query = dataBase.getReference().child("Chat").orderByChild("uids").equalTo(Uid+"|"+contato.getId());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for( DataSnapshot obj : dataSnapshot.getChildren() ){

                    conversa = obj.getValue(Conversa.class);
                    mensagens.clear();
                    for( Mensagem m : conversa.getMensagens()){
                        mensagens.add(m);
                        adapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void buscaSentido2() {

        Query query = dataBase.getReference().child("Chat").orderByChild("uids").equalTo(contato.getId()+"|"+Uid);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for( DataSnapshot obj : dataSnapshot.getChildren() ){

                    conversa = obj.getValue(Conversa.class);

                    mensagens.clear();
                    for( Mensagem m : conversa.getMensagens()){
                        mensagens.add(m);
                        adapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
