package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.EquipoAdapter;
import com.sfkao.pokeviewer.utils.EquipoSingleton;
import com.sfkao.pokeviewer.utils.TestingUtils;

public class MisEquipos extends Fragment {

    RecyclerView recyclerEquipos;
    RecyclerView.Adapter adapterEquipos;

    FloatingActionButton floatingActionButton;

    MainActivity context;

    public MisEquipos() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mis_equipos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerEquipos = (RecyclerView) view.findViewById(R.id.recycler_mis_equipos);
        adapterEquipos = new EquipoAdapter();
        RecyclerView.LayoutManager layoutManagerDebilidades = new LinearLayoutManager(getContext());
        recyclerEquipos.setLayoutManager(layoutManagerDebilidades);
        recyclerEquipos.setAdapter(adapterEquipos);

        floatingActionButton= view.findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment anyadirEquipo = new new_equipo_fragment();
                FragmentManager fm = context.getSupportFragmentManager();
                anyadirEquipo.show(fm, "Añadir equipo");
            }
        });
        EquipoSingleton.getEquipos().addAll(TestingUtils.getEquipos());
        EquipoSingleton.getEquipos().addAll(TestingUtils.getEquipos());
        EquipoSingleton.getEquipos().addAll(TestingUtils.getEquipos());
        ((EquipoAdapter)adapterEquipos).setEquipos(EquipoSingleton.getEquipos());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity)context;
        super.onAttach(context);
    }
}