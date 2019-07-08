package ch.bbcag.foodwar.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ch.bbcag.foodwar.db.dao.RezeptDao;
import ch.bbcag.foodwar.db.entity.Rezept;

@Database(entities = {Rezept.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RezeptDao rezeptDao();
}