package ch.bbcag.foodwar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainActivityPagerAdapter extends FragmentPagerAdapter {

    public MainActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String name = "";
        switch(position){
            case 0:
                name = "Favoriten";
                break;
            case 1:
                name = "Übersicht";
                break;
            case 2:
                name = "Hinzufügen";
                break;
        }
        return name;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return Favragment.newInstance();
        }
        if (position == 1) {
            return UebersichtFragment.newInstance();
        }
        if (position == 2) {
            return HinzuFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
