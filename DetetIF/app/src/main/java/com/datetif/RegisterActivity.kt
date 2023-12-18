package com.datetif

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.datetif.controller.UserController
import com.datetif.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var userController: UserController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userController = UserController(baseContext)
        registerEvents()
    }

    private fun registerEvents() {
        binding.saveBtn.setOnClickListener(View.OnClickListener {
            saveUser()

        })
        binding.cancelBtn.setOnClickListener(View.OnClickListener { finish() })
    }

    private fun saveUser() {
        if(this.userController.saveUser(
                binding.nameInput.text.toString(),
                binding.raInput.text.toString(),
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString())
            ){
            Toast.makeText(this, "usuario salvo com sucesso", Toast.LENGTH_LONG).show()
            finish()
        }else{
            Toast.makeText(this, "dados incorretos", Toast.LENGTH_LONG).show()
        }
    }

}