package com.example.covid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.R
import com.example.covid.models.Favorito

class AdapterFavorito (val favoritos: ArrayList<Favorito>):RecyclerView.Adapter<AdapterFavorito.ViewHolder>(){
    var posFav=-1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFavorito.ViewHolder {
        var viFav = LayoutInflater.from(parent.context).inflate(R.layout.item_favorito, parent,false)
        return ViewHolder(viFav)
    }

    override fun getItemCount(): Int {
        return favoritos.size
    }

    override fun onBindViewHolder(holder: AdapterFavorito.ViewHolder, position: Int) {
        this.posFav = position
        holder.bindItems(favoritos[position])
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        init{

        }
        fun bindItems(paisFav: Favorito){
            val txtFav = itemView.findViewById<TextView>(R.id.item_favorito)
            txtFav.text=paisFav.nameF
        }
    }

}