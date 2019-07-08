package ch.bbcag.foodwar.db;

import android.content.Context;

import androidx.room.Room;

public class DbHelper {

    public static AppDatabase getDb(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "app-db").build();
    }

}
