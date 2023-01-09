package com.listrikpln.tagiahanlistrikrumah.model.riwayat;

public class RiwayatModel {
    private String id;
    private String noMeteran;
    private String nama;
    private String periode;
    private String jmhTagihan;

    public String getNoMeteran() {
        return noMeteran;
    }

    public void setNoMeteran(String noMeteran) {
        this.noMeteran = noMeteran;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getJmhTagihan() {
        return jmhTagihan;
    }

    public void setJmhTagihan(String jmhTagihan) {
        this.jmhTagihan = jmhTagihan;
    }
}
