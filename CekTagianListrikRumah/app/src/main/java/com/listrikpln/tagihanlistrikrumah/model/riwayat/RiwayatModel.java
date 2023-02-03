package com.listrikpln.tagihanlistrikrumah.model.riwayat;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "riwayat_table")
public class RiwayatModel {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String noMeteran;
    private String nama;
    private String periode;
    private Integer jmhTagihan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoMeteran() {
        return noMeteran;
    }

    public void setNoMeteran(String noMeteran) {
        this.noMeteran = noMeteran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Integer getJmhTagihan() {
        return jmhTagihan;
    }

    public void setJmhTagihan(Integer jmhTagihan) {
        this.jmhTagihan = jmhTagihan;
    }

}
