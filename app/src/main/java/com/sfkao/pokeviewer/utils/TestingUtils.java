package com.sfkao.pokeviewer.utils;

import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.modelo.Equipo;
import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase para pruebas, sin uso actual
 */
public class TestingUtils {

    public static ArrayList<Equipo> getEquipos(){

        ArrayList<Equipo> equipos = new ArrayList<>();
        Pokemon pokemons[] = new Pokemon[6];
        Equipo e = new Equipo();
        for(int i = 0; i < 6; i++){
            pokemons[i] = ApiConexion.getInstance().getPokemon(String.valueOf(new Random().nextInt(900)));
        }
        e.setPokemons(pokemons);
        e.setAutor("Test");
        e.setIdentificador("#42ceC");
        e.setLikes(12);
        e.setFavoritos(24);
        equipos.add(e);
        return equipos;

    }

}
