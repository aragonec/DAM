package com.example.covid.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.covid.R
import com.example.covid.adapters.AdapterPais
import com.example.covid.adapters.AdapterFavorito
import com.example.covid.conexion.Conexion
import com.example.covid.config.Config
import com.example.covid.models.Pais
import com.example.covid.models.Favorito
import org.json.JSONArray
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        var conexion = Conexion(view.context)
        var db = conexion.writableDatabase
        //db.execSQL("insert into favoritos (nombre, imagen) values('China', 'bandera.png')")

        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewPaises)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)
        var paises=ArrayList<Pais>()
        paises.add(Pais("Mexico", "bandera.png",120.00))
        paises.add(Pais("Canada","bandera.png",10.00))
        paises.add(Pais("Brazil","bandera.png",140.00))
        paises.add(Pais("EUA","bandera.png",200.00))
        paises.add(Pais("Japon","bandera.png",80.00))
        paises.add(Pais("Luxemburgo","bandera.png",20.00))
        paises.add(Pais("India","bandera.png",170.00))
        val adapter = AdapterPais(paises)
        recyclerView.adapter = adapter

        var recyclerViewFav = view.findViewById<RecyclerView>(R.id.recyclerViewFavoritos)
        recyclerViewFav.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL,false)
        var favoritos= ArrayList<Favorito>()
        var respuesta = db.rawQuery("select * from favoritos", null)
        if(respuesta.moveToFirst()){
            do{
                Log.e("DATO",respuesta.getString(1) )
                favoritos.add(Favorito(respuesta.getString(1), respuesta.getString(2)))
            }while(respuesta.moveToNext())
        }else{
            Log.e("DATO","SIN DATOS" )
        }

        val adapterFav = AdapterFavorito(favoritos)
        recyclerViewFav.adapter = adapterFav
        recargar(view.context)

        return view
    }
    fun recargar(context:Context){
        var config = Config()
        var url=config.ipServidor+"/countries"
        var jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url , null, Response.Listener {
            respuesta:JSONArray->
            for(i in 0 until respuesta.length()){
                val item=respuesta.getJSONObject(i)
                Log.e("SLUG", item.getString("Slug").toString())
            }
        },Response.ErrorListener{

        })
        val queue = Volley.newRequestQueue(context)
        queue.add(jsonObjectRequest)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}