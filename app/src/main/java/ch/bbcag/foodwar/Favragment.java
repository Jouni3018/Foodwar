package ch.bbcag.foodwar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

import ch.bbcag.foodwar.db.RezeptReadFavAsyncTask;
import ch.bbcag.foodwar.db.RezeptUpdateFavAsyncTask;
import ch.bbcag.foodwar.db.dao.RezeptDao;
import ch.bbcag.foodwar.db.entity.Rezept;

public class Favragment extends Fragment {


    public static View view;
    public Favragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fav, container, false);

        //Rezept rezept = new Rezept();

        //rezept.gerichtname = "test";
        //rezept.zubereitung = "test";

        //RezeptInsertAsyncTask insterTask = new RezeptInsertAsyncTask(getContext());
        //insterTask.execute(rezept);

        RezeptReadFavAsyncTask rezeptReadAsyncTask = new RezeptReadFavAsyncTask(getContext());
        rezeptReadAsyncTask.execute();



        return view;
    }

    public static void addRezepteToClickableList(List<Rezept> rezepteubergabe) {

        final ListView rezepte = view.findViewById(R.id.Rezepte);
        final ArrayAdapter<Rezept> rezeptArrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1);
        rezeptArrayAdapter.addAll(rezepteubergabe);
        rezepte.setAdapter(rezeptArrayAdapter);

        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id)     {
                Intent intent = new Intent(view.getContext(), DetailAnsichtActivity.class);
                Rezept selected = (Rezept) parent.getItemAtPosition(position);

                //String Gerichtname = selected.gerichtname;

                intent.putExtra("Bild", selected.bild);
                intent.putExtra("JaNein", selected.favorit);
                intent.putExtra("Gerichtname", selected.gerichtname);
                intent.putExtra("Zutaten", selected.zutaten);
                intent.putExtra("Zubereitung", selected.zubereitung);
                view.getContext().startActivity(intent);
            }
        };

        rezepte.setOnItemClickListener(mListClickedHandler);
    }

    public static Favragment newInstance() {
        Favragment fragment = new Favragment();
        return fragment;
    }
}
