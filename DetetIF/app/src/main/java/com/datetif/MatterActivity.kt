package com.datetif

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.datetif.controller.MatterController
import com.datetif.databinding.ActivityMatterBinding
import com.datetif.model.Matter
import com.datetif.mvvm.ListMatterVM
import com.datetif.view.ListMatterAdapter
import java.util.Locale

class MatterActivity : AppCompatActivity() {

    lateinit var binding:ActivityMatterBinding
    lateinit var controller: MatterController
    lateinit var adapter: ListMatterAdapter
    lateinit var viewModel: ListMatterVM
    lateinit var searchView:SearchView
    var classe:String? = null
    var year:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = MatterController(baseContext)
        viewModel = ViewModelProvider(this).get(ListMatterVM::class.java)
        searchView = findViewById(R.id.searchView)


        getExtras()
        configuraRecycleView()
        setQueryListener()
    }

    private fun setQueryListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
        searchView.setOnClickListener {
            searchView.isIconified = false
        }
    }

    private fun filterList(query: String?){
        if (query != null){
            val filteredList = mutableListOf<Matter>()
            for (matter in viewModel.mattersList()!!){
                if (matter.name.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(matter)
                }
            }
            if (filteredList.isNotEmpty()){
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun getExtras() {
        classe = intent.getStringExtra("class")
        year = intent.getIntExtra("year", 0)
        if (classe == null) {
            classe = "Não informado"
        }
        binding.quaterLabelTxt.text = classe
    }

    private fun configuraRecycleView() {
        var matters = controller.getAll()

        if (matters.isEmpty()) {
            createDisciplines()
            matters = controller.getAll()
        }

        viewModel.loadMatters(matters)

        adapter = ListMatterAdapter(viewModel.mattersList(), year.toString())

        binding.matterList.layoutManager = LinearLayoutManager(baseContext)
        binding.matterList.adapter = adapter

    }

    private fun createDisciplines() {
        controller.saveMatter("OBMAT123", "Matematica")
        controller.saveMatter("OBFIS123", "Fisica")
        controller.saveMatter("OBCHE123", "Quimica")
        controller.saveMatter("OBINF123", "Informatica")
        controller.saveMatter("OBING123", "Ingles")
        controller.saveMatter("OBPOO123", "Programação Orientada a Objetos")
        controller.saveMatter("OBBD123", "Banco de Dados")
        controller.saveMatter("OBWEB123", "Desenvolvimento Web")
        controller.saveMatter("OBIA123", "Inteligencia Artificial")
        controller.saveMatter("OBES123", "Engenharia de Software")
    }
}