package ch.bbcag.foodwar.db.entity;

import android.media.Image;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.w3c.dom.Text;

@Entity
public class Rezept {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "bild")
    public String bild;

    @ColumnInfo(name = "gerichtname")
    public String gerichtname;

    @ColumnInfo(name = "zutaten")
    public String zutaten;

    @ColumnInfo(name = "zubereitung")
    public String zubereitung;

    @ColumnInfo(name = "favorit")
    public Boolean favorit;

    @Override
    public String toString() {
        return gerichtname;
    }

}
