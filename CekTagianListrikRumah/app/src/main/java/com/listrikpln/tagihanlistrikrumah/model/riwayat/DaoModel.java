package com.listrikpln.tagihanlistrikrumah.model.riwayat;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoModel {

    @Query("SELECT * FROM riwayat_table")
    List<RiwayatModel> getAll();

    @Query("SELECT * FROM riwayat_table WHERE periode LIKE (:first)")
    List<RiwayatModel> findPeriode(String first);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RiwayatModel... riwayatModels);

    @Delete
    void delete(RiwayatModel riwayatModels);

    @Query("SELECT * FROM riwayat_table ORDER BY id DESC")
    RiwayatModel getAlphabetizedWords();

    @Query("DELETE FROM riwayat_table")
    void deleteAll();

    @Query("SELECT SUM(jmhTagihan) as totalTagihan FROM riwayat_table")
    TotalTagihanModel total();


}
