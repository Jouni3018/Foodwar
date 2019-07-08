package ch.bbcag.foodwar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;

import ch.bbcag.foodwar.db.RezeptUpdateFavAsyncTask;

public class DetailAnsichtActivity extends AppCompatActivity {

    private String Gerichtname;
    private boolean JaNein;
    private String Bild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ansicht);
        final TextView zutaten = findViewById(R.id.txtZutaten);
        final TextView zubereitung = findViewById(R.id.txtSoWirdsGemacht);


        Switch Favorit = findViewById(R.id.switchTest);

        Bundle extras = getIntent().getExtras();

        Bild=extras.getString("Bild");
        JaNein = extras.getBoolean("JaNein");
        Gerichtname = extras.getString("Gerichtname");
        String Zutaten = extras.getString("Zutaten");
        String Zubereitung = extras.getString("Zubereitung");

        File imgFile = new File(Bild);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = findViewById(R.id.BildGericht);

            myImage.setImageBitmap(myBitmap);
        }

        if (JaNein == true){
            Favorit.setChecked(true);
        }
        else {
            Favorit.setChecked(false);
        }

        this.setTitle(Gerichtname);
        zubereitung.setText(Zubereitung);
        zutaten.setText(Zutaten);

    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu){
           /*getMenuInflater().inflate(R.menu.action_menu, menu);

            MenuItem itemSwitch = menu.findItem(R.id.mySwitch);
            itemSwitch.setActionView(R.layout.use_switch);*/
            final Switch sw = findViewById(R.id.switchTest);
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        Toast.makeText(getBaseContext(),"Gericht zu Favoriten hinzugefügt", Toast.LENGTH_LONG).show();


                        RezeptUpdateFavAsyncTask task = new RezeptUpdateFavAsyncTask(getBaseContext(), true, Gerichtname);
                        task.execute();


                    }
                    else{
                        Toast.makeText(getBaseContext(),"Gericht aus Favoriten entfernt", Toast.LENGTH_LONG).show();
                        RezeptUpdateFavAsyncTask task = new RezeptUpdateFavAsyncTask(getBaseContext(), false, Gerichtname);
                        task.execute();

                    }

                }
            });
           return true;
        }




}
