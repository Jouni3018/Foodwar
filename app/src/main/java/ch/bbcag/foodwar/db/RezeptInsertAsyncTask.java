package ch.bbcag.foodwar.db;

import android.content.Context;
import android.os.AsyncTask;

import ch.bbcag.foodwar.db.AppDatabase;
import ch.bbcag.foodwar.db.DbHelper;
import ch.bbcag.foodwar.db.entity.Rezept;

public class RezeptInsertAsyncTask extends AsyncTask<Rezept, Void, Void> {

    private Context context;

    public RezeptInsertAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Rezept... rezepte) {

        AppDatabase db = DbHelper.getDb(context);
        db.rezeptDao().insertAll(rezepte[0]);

        return null;
    }
}
