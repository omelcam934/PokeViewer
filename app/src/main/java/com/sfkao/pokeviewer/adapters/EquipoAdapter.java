package com.sfkao.pokeviewer.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador que se encarga de mostrar los equipos en un recycler view
 */
public class EquipoAdapter extends RecyclerView.Adapter {

    private static final int VIEW_ITEM = 1;
    private static final int VIEW_LOADING = 2;
    private ArrayList<EquipoForAdapterInterface> equipos;

    OnItemLongClickListener onItemLongClickListener;
    Context c;


    public EquipoAdapter(ArrayList<EquipoForAdapterInterface> equipos) {
        this.equipos = equipos;
    }

    public EquipoAdapter() {
        equipos = new ArrayList<>();
        equipos.add(null);
    }

    public EquipoAdapter(Context c){
        this();
        this.c = c;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if( viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipo, parent, false);
            return new ViewHolder(v);
        }else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            return new ViewHolderLoading(v);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Coloco los datos del equipo en el holder.
        if(holder instanceof ViewHolder) {
            EquipoForAdapterInterface e = equipos.get(position);
            ((ViewHolder) holder).nombre.setText(e.getName());
            ((ViewHolder) holder).autor.setText(MessageFormat.format("{0}{1}", ((ViewHolder) holder).view.getResources().getString(R.string.autor), e.getUser()==null ? "DELETED" : e.getUser()));
            ((ViewHolder) holder).codigo.setText(MessageFormat.format("{0}{1}", ((ViewHolder) holder).view.getResources().getString(R.string.id),
                    e.getApiId() != null ? e.getApiId() : "Local"));
            ((ViewHolder) holder).likes.setText(String.valueOf(e.getLikes()));
            ((ViewHolder) holder).favoritos.setText(String.valueOf(e.getFavs()));
            //Coloco las 6 imagenes de los pokemon
            for (int i = 0; i < ((ViewHolder) holder).pokemons.length; i++) {
                if (e.isPokemon(i)) {
                    Picasso.get().load(e.getPokImg(i)).into(((ViewHolder) holder).pokemons[i]);
                }
            }
            if(e.getDadoLike()){
                ((ViewHolder) holder).likeImg.setImageResource(R.drawable.like_presssed);
                ((ViewHolder) holder).likeImg.setColorFilter(c.getColor(R.color.lime), PorterDuff.Mode.MULTIPLY);
            }else{
                ((ViewHolder) holder).likeImg.setImageResource(R.drawable.like_unpressed);
                ((ViewHolder) holder).likeImg.setColorFilter(c.getColor(R.color.text), android.graphics.PorterDuff.Mode.MULTIPLY);
            }

            if(e.getDadoFav()){
                ((ViewHolder) holder).favImg.setImageResource(R.drawable.star_pressed);
                ((ViewHolder) holder).favImg.setColorFilter(c.getColor( R.color.yellow), android.graphics.PorterDuff.Mode.MULTIPLY);
            }else{
                ((ViewHolder) holder).favImg.setImageResource(R.drawable.star_unpressed);
                ((ViewHolder) holder).favImg.setColorFilter(c.getColor(R.color.text), android.graphics.PorterDuff.Mode.MULTIPLY);
            }

            ((ViewHolder) holder).view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onItemLongClickListener != null)
                        onItemLongClickListener.onItemLongClicked(e);
                    return true;
                }
            });

        }
    }


    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public ArrayList<EquipoForAdapterInterface> getEquipos() {
        return equipos;
    }


    public void setEquipos(ArrayList<EquipoForAdapterInterface> equipos) {
        this.equipos = equipos;
        //Si cambio los equipos desde fuera, notifico de que se han cambiado y se ha de redibujar
        notifyDataSetChanged();
    }

    public void finnishedLoading(List<EquipoForAdapterInterface> toAdd){

        int size = equipos.size();
        equipos.remove(size -1);
        equipos.addAll(toAdd);
        equipos.add(null);
        notifyItemRangeChanged(size-1, toAdd.size()+1);
    }

    @Override
    public int getItemViewType(int position) {
        return equipos.get(position) != null ? VIEW_ITEM : VIEW_LOADING;
    }

    public interface OnItemLongClickListener{
        boolean onItemLongClicked(EquipoForAdapterInterface e);
    }

    /**
     * Clase que muestra los datos del equipo
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final ImageView[] pokemons;
        public final TextView nombre, autor, codigo, likes, favoritos;
        public final ImageView likeImg, favImg;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            pokemons = new ImageView[6];

            pokemons[0] = view.findViewById(R.id.imagenPokemon1);
            pokemons[1] = view.findViewById(R.id.imagenPokemon2);
            pokemons[2] = view.findViewById(R.id.imagenPokemon3);
            pokemons[3] = view.findViewById(R.id.imagenPokemon4);
            pokemons[4] = view.findViewById(R.id.imagenPokemon5);
            pokemons[5] = view.findViewById(R.id.imagenPokemon6);

            nombre = view.findViewById(R.id.nombre_equipo);
            autor = view.findViewById(R.id.autor_equipo);
            codigo = view.findViewById(R.id.codigo_equipo);
            likes = view.findViewById(R.id.likes_equipo);
            favoritos = view.findViewById(R.id.favoritos_equipo);

            likeImg = view.findViewById(R.id.equipo_like_img);
            favImg = view.findViewById(R.id.equipo_favorito_img);
        }
    }
    
    public static class ViewHolderLoading extends RecyclerView.ViewHolder {
        public final View view;
        public final ProgressBar progressBar;
        
        public ViewHolderLoading(View view){
            super(view);
            this.view = view;
            progressBar = view.findViewById(R.id.loading_bar);
        }
    }

}
