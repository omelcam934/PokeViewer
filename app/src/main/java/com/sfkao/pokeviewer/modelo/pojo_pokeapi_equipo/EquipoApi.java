package com.sfkao.pokeviewer.modelo.pojo_pokeapi_equipo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_pokemon.PokemonsMinimal;

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
    @Expose
    @SerializedName("likes")
    private int likes;
    @Expose
    @SerializedName("favoritos")
    private int favoritos;
    @Expose
    @SerializedName("dadoLike")
    private boolean dadoLike;
    @Expose
    @SerializedName("dadoFavoritos")
    private boolean dadoFavoritos;


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

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(int favoritos) {
        this.favoritos = favoritos;
    }

    public boolean isDadoLike() {
        return dadoLike;
    }

    public void setDadoLike(boolean dadoLike) {
        this.dadoLike = dadoLike;
    }

    public boolean isDadoFavoritos() {
        return dadoFavoritos;
    }

    public void setDadoFavoritos(boolean dadoFavoritos) {
        this.dadoFavoritos = dadoFavoritos;
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
        return likes;
    }

    @Override
    public int getFavs() {
        return favoritos;
    }

    @Override
    public boolean getDadoLike() {
        return dadoLike;
    }

    @Override
    public boolean getDadoFav() {
        return dadoFavoritos;
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
    public void setFavs(int f) {
        favoritos = f;
    }

}
