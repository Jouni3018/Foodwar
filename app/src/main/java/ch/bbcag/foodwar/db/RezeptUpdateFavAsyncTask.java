package ch.bbcag.foodwar.db;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import ch.bbcag.foodwar.Favragment;
import ch.bbcag.foodwar.db.entity.Rezept;

public class RezeptUpdateFavAsyncTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private boolean b;
    private String string;

    public RezeptUpdateFavAsyncTask(Context context, boolean b, String s) {
        this.context = context;
        this.b=b;
        string=s;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        AppDatabase db = DbHelper.getDb(context);
        db.rezeptDao().updateFav(b,string);

        return null;
    }
}
