package ch.bbcag.foodwar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.io.File;

import ch.bbcag.foodwar.db.RezeptInsertAsyncTask;
import ch.bbcag.foodwar.db.entity.Rezept;

public class HinzuFragment extends Fragment {

    private final static int PICTURE_SELECTION = 1234;
    private static String imagepath = "";
    private View view;

    public HinzuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hinzu, container, false);

        Button bildauswahl = view.findViewById(R.id.bildauswahl);
        final ImageView bildGericht=view.findViewById(R.id.bildGericht);

        bildauswahl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICTURE_SELECTION);
            }
        });
        final EditText zutaten = view.findViewById(R.id.zutat);
        final EditText gerichtname = view.findViewById(R.id.name);
        final EditText zubereitung = view.findViewById(R.id.zubereitung);
        view.findViewById(R.id.speichern).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rezept rezept = new Rezept();
                RezeptInsertAsyncTask rAT = new RezeptInsertAsyncTask(getContext());

                rezept.zutaten = zutaten.getText().toString();
                rezept.gerichtname = gerichtname.getText().toString();
                rezept.bild = imagepath;
                rezept.zubereitung = zubereitung.getText().toString();
                rAT.execute(rezept);

                getActivity().finish();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);

            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICTURE_SELECTION) {
            Uri fileUri = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContext().getContentResolver().query(fileUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            String imgDecodableString = cursor.getString(columnIndex);
            cursor.close();
            imagepath=imgDecodableString;
        }
        File imgFile = new File(imagepath);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = view.findViewById(R.id.bildGericht);

            myImage.setImageBitmap(myBitmap);
        }

    }

    public static HinzuFragment newInstance() {
        HinzuFragment fragment = new HinzuFragment();
        return fragment;
    }
}