package com.ufc.br.chatagenda.CustomAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufc.br.chatagenda.Firebase.ConexaoAuth;
import com.ufc.br.chatagenda.Firebase.MyConexaoAuth;
import com.ufc.br.chatagenda.Model.Contato;
import com.ufc.br.chatagenda.Model.Mensagem;
import com.ufc.br.chatagenda.R;

import java.util.List;

public class ChatListAdapter extends BaseAdapter {

    private ConexaoAuth auth = MyConexaoAuth.getInstance();

    private Activity context;
    private List<Mensagem> mensagens;

    private String eu;
    private String contato;

    public ChatListAdapter(Activity context, List<Mensagem> mensagens, String contato) {
        this.context = context;
        this.mensagens = mensagens;
        this.eu = eu;
        this.contato = contato;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        LayoutInflater li = context.getLayoutInflater();
        View rowView = li.inflate(R.layout.chat_list_layout, null, true);

        TextView origem = rowView.findViewById(R.id.textViewOrigem);
        TextView conteudo = rowView.findViewById(R.id.textViewConteudo);
        TextView horario = rowView.findViewById(R.id.textViewHorario);

        Mensagem m = mensagens.get(position);

        if( m.getOrigem().equals( auth.getUser().getUid() ) ){

            origem.setText("Eu: ");

        }else{
            origem.setText(contato+": ");
        }
        conteudo.setText(m.getConteudo()+"");
        horario.setText(m.getHora());

        return rowView;
    }

    public void remove(int position) {
        mensagens.remove(position);
    }

}
