package ch.bbcag.foodwar.db.dao;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.bbcag.foodwar.db.entity.Rezept;

@Dao
public interface RezeptDao {

    @Query("SELECT * FROM Rezept Order by id")
    List<Rezept> getAll();

    @Query("SELECT * FROM Rezept WHERE id IN (:rezepteIds)")
    List<Rezept> loadAllByIds(int[] rezepteIds);

    @Query("SELECT * FROM Rezept WHERE gerichtname LIKE :gerichtsname LIMIT 1")
    Rezept findByGerichtname(String gerichtsname);

    @Query("SELECT * FROM Rezept WHERE favorit = :favorit Order by id")
    List<Rezept> findFavoriten(boolean favorit);

    @Query("UPDATE Rezept set favorit = :FavoritJaNein WHERE gerichtname = :gerichtsname")
    void updateFav(boolean FavoritJaNein, String gerichtsname);
    @Insert
    void insertAll(Rezept... rezepte);

    @Delete
    void delete(Rezept rezept);
}
