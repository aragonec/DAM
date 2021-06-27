package com.example.covid

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.covid.ui.main.SectionsPagerAdapter
import com.example.covid.databinding.ActivityTabsBinding

class Tabs : AppCompatActivity() {

    private lateinit var binding: ActivityTabsBinding
    lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        tabs = binding.tabs
        tabs.setupWithViewPager(viewPager)
        cargarIconos()

    }
    fun cargarIconos(){
        tabs.getTabAt(0)?.setIcon(android.R.drawable.ic_dialog_dialer)
        tabs.getTabAt(1)?.setIcon(android.R.drawable.ic_dialog_map)
    }
}