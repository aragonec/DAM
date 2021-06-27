package com.example.covid

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.covid.ui.main.SectionsPagerAdapter
import com.example.covid.databinding.ActivityTabsBinding
import com.google.zxing.integration.android.IntentIntegrator


class Tabs : AppCompatActivity() {

    private lateinit var binding: ActivityTabsBinding
    lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setLogo(R.mipmap.ic_launcher_round)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        tabs = binding.tabs
        tabs.setupWithViewPager(viewPager)
        cargarIconos()

    }

    private fun initScanner(){
        //IntentIntegrator(this).initiateScan()
        var integrator =IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanea el covid19 app para info")
        integrator.setTorchEnabled(true)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null){
            if(result.contents==null){
                Toast.makeText(applicationContext,"Cancelado", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"El valor escaneado es"+result.contents, Toast.LENGTH_LONG).show()
                if(result.contents.equals("https://www.worldometers.info/coronavirus/")){
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.camara_menu->{
                initScanner()
                Toast.makeText(applicationContext, "Hola", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu_dos->{
                Toast.makeText(applicationContext, "Hola2", Toast.LENGTH_LONG).show()
                return true
            }else ->super.onOptionsItemSelected(item)
        }
    }

    fun cargarIconos(){
        tabs.getTabAt(0)?.setIcon(android.R.drawable.ic_dialog_dialer)
        tabs.getTabAt(1)?.setIcon(android.R.drawable.ic_dialog_map)
    }
}