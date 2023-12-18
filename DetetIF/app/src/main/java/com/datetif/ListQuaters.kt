package com.datetif

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.datetif.controller.QuaterController
import com.datetif.controller.UserController
import com.datetif.databinding.ActivityListQuatersBinding
import com.datetif.model.User
import com.datetif.mvvm.ListQuaterVM
import com.datetif.view.ListQuaterAdapter

class ListQuaters : AppCompatActivity() {
    lateinit var binding: ActivityListQuatersBinding
    lateinit var adapter: ListQuaterAdapter
    lateinit var viewModel: ListQuaterVM
    lateinit var quaterController: QuaterController
    lateinit var userController: UserController
    var ra: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListQuatersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quaterController = QuaterController(baseContext)
        userController = UserController(baseContext)
        viewModel = ViewModelProvider(this).get(ListQuaterVM::class.java)

        configuraRecycleView()

        ra = intent.getStringExtra("ra").toString()

        registerEvents()
    }

    private fun registerEvents() {
        binding.profileBtn.setOnClickListener {
            var user: User? = userController.getUserByRa(ra)
            var quaters = quaterController.getAll()

            if (quaters.isEmpty()) {
                createQuater()
                quaters = quaterController.getAll()
            }

            if (user != null) {
                val transitionProfile:Intent = Intent(baseContext, ProfileActivity::class.java)
                transitionProfile.putExtra("name_user", user.nome)
                transitionProfile.putExtra("ra", user.RA)
                transitionProfile.putExtra("turma", quaters.get(0).classe)
                startActivity(transitionProfile)
            }

        }
    }

    private fun configuraRecycleView() {
        var quaters = quaterController.getAll()

        if (quaters.isEmpty()) {
            createQuater()
            quaters = quaterController.getAll()
        }

        viewModel.loadQuaters(quaters)

        adapter = ListQuaterAdapter(viewModel.quatersList())

        binding.quaterList.layoutManager = LinearLayoutManager(baseContext)
        binding.quaterList.adapter = adapter

    }

    private fun createQuater() {
        for (i in 1..3) {
            quaterController.saveQuater("$i º Trimestre", "Técnico de Informática", 2023)
        }
    }
}