package com.ufc.br.chatagenda.CustomAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.ufc.br.chatagenda.Model.Contato;
import com.ufc.br.chatagenda.Model.User;
import com.ufc.br.chatagenda.R;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity context;
    private List<Contato> users;

    public CustomListAdapter(Activity context, List<Contato> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        LayoutInflater li = context.getLayoutInflater();
        View rowView = li.inflate(R.layout.custom_list_layout, null, true);

        TextView tv1 = rowView.findViewById(R.id.item1);
        ImageView imageView = rowView.findViewById(R.id.imageViewIcon);

        String nome = users.get(position).getNome();

        tv1.setText(nome);
        imageView.setImageResource(R.mipmap.ic_contato);

        return rowView;
    }

    public void remove(int position) {
        users.remove(position);
    }
}