package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.SearchPokemonPagerAdapter;
import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;
import com.sfkao.pokeviewer.utils.PokemonSingleton;
import com.sfkao.pokeviewer.utils.Util;
import com.squareup.picasso.Picasso;

/**
 * El fragment que contiene tanto el buscador como el pager que contiene los datos del pokemon
 */
public class BuscadorFragment extends Fragment {

    public MainActivity context;

    Button botonBuscar;
    ImageView imagePokemon;
    AutoCompleteTextView textoPokemon;

    TextView textError;

    ImageView imageTipoIzquierda, imageTipoMedio, imageTipoDerecha;

    ViewPager2 buscadorDatos;
    SearchPokemonPagerAdapter buscadorDatosAdapter;
    TabLayout tabBuscadorDatos;

    public BuscadorFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BuscadorFragment newInstance(String param1, String param2) {
        return new BuscadorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscador, container, false);
    }

    //Busca al pokemon y imprimelo
    private void buscar(){
        if(textoPokemon.getText().toString().equals("")){
            Toast.makeText(context, R.string.pokemonOrNumber, Toast.LENGTH_SHORT).show();
            return;
        }
        Pokemon pokemon2 = ApiConexion.getInstance().getPokemon(String.valueOf(textoPokemon.getText()));
        if(pokemon2 == null) {
            textError.setText(R.string.pokemonNotFound);
            return;
        }
        imprimirPokemon(pokemon2);
        textError.setText("");

    }

    public void imprimirPokemon(Pokemon pokemon){
        //Coloca la imagen a partir de la url en la imageView
        Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(imagePokemon);

        //Hay 3 imageView, uno lo escondemos para que quede centrado el tipo si hay 1
        if(pokemon.getTypes().size()!=2){
            imageTipoMedio.setImageDrawable(Util.getType(pokemon.getTypes().get(0).getType().getName()));
            imageTipoDerecha.setVisibility(View.INVISIBLE);
            imageTipoIzquierda.setVisibility(View.INVISIBLE);
            imageTipoMedio.setVisibility(View.VISIBLE);
        }else{
            imageTipoIzquierda.setImageDrawable(Util.getType(pokemon.getTypes().get(0).getType().getName()));
            imageTipoDerecha.setImageDrawable(Util.getType(pokemon.getTypes().get(1).getType().getName()));
            imageTipoDerecha.setVisibility(View.VISIBLE);
            imageTipoIzquierda.setVisibility(View.VISIBLE);
            imageTipoMedio.setVisibility(View.INVISIBLE);
        }
        //Coloca el pokemon en su singleton, mas explicacion en su clase.
        PokemonSingleton.setPokemon(pokemon);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (MainActivity) context;
    }


    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs, @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Recojo los views
        botonBuscar = (Button) requireView().findViewById(R.id.buttonSearchPokemon);
        imagePokemon = (ImageView) requireView().findViewById(R.id.imagePokemon);

        textoPokemon = (AutoCompleteTextView) requireView().findViewById(R.id.textfieldPokemonNameOrNumber);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,Util.POKEMONS);
        textoPokemon.setAdapter(adapter);


        imageTipoIzquierda = (ImageView) requireView().findViewById(R.id.imageTypeLeft);
        imageTipoMedio = (ImageView) requireView().findViewById(R.id.imageTypeMiddle);
        imageTipoDerecha = (ImageView) requireView().findViewById(R.id.imageTypeRight);

        textError = (TextView) requireView().findViewById(R.id.textError);

        tabBuscadorDatos = requireView().findViewById(R.id.tabMainActivity);

        //Guardo el pager y coloco su adaptador
        buscadorDatos = requireView().findViewById(R.id.datosPokemonPager);
        buscadorDatosAdapter = new SearchPokemonPagerAdapter(context);
        buscadorDatos.setAdapter(buscadorDatosAdapter);
        //Para los 2 puntitos que hacen de tab layout del pager.
        new TabLayoutMediator(tabBuscadorDatos,buscadorDatos,true,true,(tab, position) -> tab.setText(null)).attach();

        //El boton de buscar
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar();
            }
        });

        //Que el enter funcione
        textoPokemon.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                buscar();
                return false;
            }
        });
        
    }
}