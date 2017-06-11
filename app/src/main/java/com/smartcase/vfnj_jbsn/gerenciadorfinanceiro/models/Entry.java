package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models;

import java.util.Date;

/**
 * Created by Dinho-PC on 29/05/2017.
 */

public class Entry {




    private int idGrupo;
    private int idUser;

    private int idEntry;
    private double valueEntry;
    private String dataEntry;
    private String descriptionEntry;
    private String categoryEntry;

    public int getIdGrupo() {
        return idGrupo;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdEntry() {
        return idEntry;
    }

    public double getValueEntry() {
        return valueEntry;
    }

    public void setValueEntry(double valueEntry) {
        this.valueEntry = valueEntry;
    }

    public String getDataEntry() {
        return dataEntry;
    }

    public void setDataEntry(String dataEntry) {
        this.dataEntry = dataEntry;
    }

    public String getDescriptionEntry() {
        return descriptionEntry;
    }

    public void setDescriptionEntry(String descriptionEntry) {
        this.descriptionEntry = descriptionEntry;
    }

    public String getCategoryEntry() {
        return categoryEntry;
    }

    public void setCategoryEntry(String categoryEntry) {
        this.categoryEntry = categoryEntry;
    }

    public String toStringiest (){
        String entry = "Entry:  \n" +
                getValueEntry() +"\n "+
                getDataEntry() +"\n "+
                getDescriptionEntry() +"\n "+
                getCategoryEntry() +"\n ";
        return entry;
    }


}
