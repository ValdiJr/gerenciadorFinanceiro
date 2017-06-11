package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.models;

import java.util.Date;

/**
 * Created by Dinho-PC on 29/05/2017.
 */

public class Entry {




    private int idGrupo;
    private int idUser;

    private int idEntry;
    private long valueEntry;
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

    public long getValueEntry() {
        return valueEntry;
    }

    public void setValueEntry(long valueEntry) {
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




}
