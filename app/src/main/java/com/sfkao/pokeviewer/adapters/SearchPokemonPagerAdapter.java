package com.sfkao.pokeviewer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sfkao.pokeviewer.fragment.DebilidadesFragment;
import com.sfkao.pokeviewer.fragment.StatsFragment;

public class SearchPokemonPagerAdapter extends FragmentStateAdapter {


    public SearchPokemonPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public SearchPokemonPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new DebilidadesFragment();
            case 1: return new StatsFragment();
            default:
                return new DebilidadesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
