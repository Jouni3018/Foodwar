package ch.bbcag.foodwar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

import ch.bbcag.foodwar.db.RezeptReadAllAsyncTask;
import ch.bbcag.foodwar.db.RezeptReadFavAsyncTask;
import ch.bbcag.foodwar.db.entity.Rezept;

public class UebersichtFragment extends Fragment {

    public static View view;

    public UebersichtFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_uebersicht, container, false);

        RezeptReadAllAsyncTask rezeptReadAsyncTask = new RezeptReadAllAsyncTask(getContext());
        rezeptReadAsyncTask.execute();


        return view;
    }
    public static void addRezepteToClickableList(List<Rezept> rezepteubergabe) {

        ListView rezepte = view.findViewById(R.id.Rezepte);
        ArrayAdapter<Rezept> rezeptArrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1);
        rezeptArrayAdapter.addAll(rezepteubergabe);
        rezepte.setAdapter(rezeptArrayAdapter);

        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id)     {
                Intent intent = new Intent(view.getContext(), DetailAnsichtActivity.class);
                Rezept selected = (Rezept) parent.getItemAtPosition(position);

                //String Gerichtname = selected.gerichtname;

                intent.putExtra("Bild", selected.bild);
                intent.putExtra("Gerichtname", selected.gerichtname);
                intent.putExtra("Zutaten", selected.zutaten);
                intent.putExtra("Zubereitung", selected.zubereitung);
                view.getContext().startActivity(intent);
            }
        };

        rezepte.setOnItemClickListener(mListClickedHandler);
    }

    public static UebersichtFragment newInstance() {
        UebersichtFragment fragment = new UebersichtFragment();
        return fragment;
    }
}