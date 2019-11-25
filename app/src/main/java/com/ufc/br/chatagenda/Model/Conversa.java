package com.ufc.br.chatagenda.Model;

import java.util.ArrayList;
import java.util.List;

public class Conversa {

    private String cid;
    private String uids; // uid1|uid2
    private ArrayList<Mensagem> mensagens = new ArrayList<>();

    public Conversa(){}

    public Conversa(String cid, String uids) {
        this.cid = cid;
        this.uids = uids;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUids() {
        return uids;
    }

    public void setUid2(String uids) {
        this.uids = uids;
    }

    public ArrayList<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(ArrayList<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }
}
