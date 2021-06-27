package com.example.covid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.R
import com.example.covid.models.Pais

class AdapterPais (val paises: ArrayList<Pais>):RecyclerView.Adapter<AdapterPais.ViewHolder>(){
    var position=-1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPais.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_pais, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return paises.size
    }

    override fun onBindViewHolder(holder: AdapterPais.ViewHolder, position: Int) {
        this.position = position
        holder.bindItems(paises[position])
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        init{

        }
        fun bindItems(pais: Pais){
            val txtPais = itemView.findViewById<TextView>(R.id.item_pais)
            txtPais.text=pais.name
        }
    }

}