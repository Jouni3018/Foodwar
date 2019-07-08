package ch.bbcag.foodwar.db;

        import android.content.Context;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.ArrayAdapter;

        import ch.bbcag.foodwar.Favragment;
        import ch.bbcag.foodwar.db.AppDatabase;
        import ch.bbcag.foodwar.db.DbHelper;
        import ch.bbcag.foodwar.db.entity.Rezept;

        import java.util.List;

public class RezeptReadFavAsyncTask extends AsyncTask<Void, Void, List<Rezept>> {

    private Context context;

    public RezeptReadFavAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<Rezept> doInBackground(Void... voids) {

        AppDatabase db = DbHelper.getDb(context);
        return db.rezeptDao().findFavoriten(true);
    }

    @Override
    protected void onPostExecute(List<Rezept> rezepte) {
        Favragment.addRezepteToClickableList(rezepte);
    }
}
