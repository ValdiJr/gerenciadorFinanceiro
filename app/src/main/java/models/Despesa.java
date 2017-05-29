package models;

import java.util.Date;

/**
 * Created by Dinho-PC on 29/05/2017.
 */

public class Despesa {

    private int idDespesa;
    private int idGrupo;
    private int idUser;

    private long custoDespesa;
    private Date dataDespesa;
    private String descricaoDespesa;
    private String tipoDespesa;

    public int getIdGrupo() {
        return idGrupo;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public long getCustoDespesa() {
        return custoDespesa;
    }

    public void setCustoDespesa(long custoDespesa) {
        this.custoDespesa = custoDespesa;
    }

    public Date getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(Date dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public String getDescricaoDespesa() {
        return descricaoDespesa;
    }

    public void setDescricaoDespesa(String descricaoDespesa) {
        this.descricaoDespesa = descricaoDespesa;
    }

    public String getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }




}
