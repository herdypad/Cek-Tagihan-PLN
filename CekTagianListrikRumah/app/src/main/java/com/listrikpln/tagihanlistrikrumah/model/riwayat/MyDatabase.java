package com.listrikpln.tagihanlistrikrumah.model.riwayat;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {RiwayatModel.class},version = 1)
public  abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    @VisibleForTesting
    public static final String DATABASE_NAME = "my_database";

    public abstract DaoModel daoModel();

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    public static MyDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, MyDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        MyDatabase database = MyDatabase.getInstance(context);
                        database.setDatabaseCreated();
                    }
                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static MyDatabase getInstance(final Context context){
        if (sInstance == null){
            synchronized (MyDatabase.class) {
                if (sInstance == null){
                    sInstance = buildDatabase(context);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }





}
