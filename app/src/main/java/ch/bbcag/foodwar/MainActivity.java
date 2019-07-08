package ch.bbcag.foodwar;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import ch.bbcag.foodwar.db.AppDatabase;
import ch.bbcag.foodwar.db.RezeptInsertAsyncTask;
import ch.bbcag.foodwar.db.entity.Rezept;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entdecken);

        MainActivityPagerAdapter pagerAdapter = new MainActivityPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);

    }

    private void hinzufügen(){

        
        RezeptInsertAsyncTask insterTask = new RezeptInsertAsyncTask(getApplicationContext());
        Rezept rezept = new Rezept();

        rezept.gerichtname = "test";
        rezept.zutaten = "";
        rezept.zubereitung = "test";


        insterTask.execute(rezept);
    }

}