package com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;

import java.util.List;

public class EquipoApi implements EquipoForAdapterInterface {


    @Expose
    @SerializedName("pokemons")
    private List<PokemonsMinimal> pokemons;
    @Expose
    @SerializedName("usuario")
    private String usuario;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private String id;

    public static class PokemonsMinimal {
        @Expose
        @SerializedName("imgS")
        private String imgs;
        @Expose
        @SerializedName("img")
        private String img;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;
    }

    public List<PokemonsMinimal> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonsMinimal> pokemons) {
        this.pokemons = pokemons;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUser() {
        return usuario;
    }

    @Override
    public String getPokImg(int pok) {
        return pokemons.get(pok).img;
    }

    @Override
    public String getPokSImg(int pok) {
        return pokemons.get(pok).imgs;
    }

    @Override
    public String getPokName(int pok) {
        return pokemons.get(pok).name;
    }

    @Override
    public int getPokId(int pok) {
        return pokemons.get(pok).id;
    }

    @Override
    public int getLikes() {
        return 0;
    }

    @Override
    public int getFavs() {
        return 0;
    }

    @Override
    public boolean isPokemon(int pok) {
        return pokemons.get(pok) != null;
    }

    @Override
    public void setUser(String s) {
        usuario = s;
    }

    @Override
    public void setApiID(String s) {
        id = s;
    }

    @Override
    public void setPokemon(int pos, int id, String img, String imgS,String name) {
        pokemons.get(pos).id = id;
        pokemons.get(pos).img = img;
        pokemons.get(pos).imgs = imgS;
        pokemons.get(pos).name = name;
    }

    @Override
    public void setLikes(int l) {
        //TODO
    }

    @Override
    public void setFavs(int f) {

    }
}
