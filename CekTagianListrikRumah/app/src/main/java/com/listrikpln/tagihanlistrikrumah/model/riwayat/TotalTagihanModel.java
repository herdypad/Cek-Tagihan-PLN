package com.listrikpln.tagihanlistrikrumah.model.riwayat;


import androidx.room.Entity;

@Entity
public class TotalTagihanModel {

    Integer totalTagihan;

    public TotalTagihanModel(Integer totalTagihan) {
        this.totalTagihan = totalTagihan;
    }

    public TotalTagihanModel() {

    }

    public Integer getTotalTagihan() {
        return totalTagihan;
    }

    public void setTotalTagihan(Integer totalTagihan) {
        this.totalTagihan = totalTagihan;
    }
}
