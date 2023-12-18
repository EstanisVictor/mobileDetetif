package com.datetif

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.datetif.config.ResponseCredentials
import com.datetif.controller.UserController
import com.datetif.databinding.ActivityChangePasswordBinding

class ChangePassword : AppCompatActivity() {
    lateinit var binding: ActivityChangePasswordBinding
    lateinit var userController: UserController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userController = UserController(baseContext)

        registerEvents()
    }

    fun registerEvents(){
        binding.saveBtn.setOnClickListener { updateUser() }
    }

    fun updateUser(){
        val raInput = binding.raInput.text.toString()
        val passwordInput = binding.passwordInput.text.toString()
        val confirmPasswordInput = binding.confirmPasswordInput.text.toString()

        if (confirmPasswordInput != passwordInput){
            binding.passwordNotMatchTxt.text = "As senhas não conferem"
        }else{
            binding.passwordNotMatchTxt.text = ""

            val checkCredentials = userController.updateUser(
                raInput,
                passwordInput
            )

            when(checkCredentials){
                is ResponseCredentials.SUCCESS-> {
                    Toast.makeText(baseContext, "Senha atualizada com sucesso", Toast.LENGTH_LONG).show()
                    finish()
                }
                is ResponseCredentials.USER_NOT_FOUND-> {
                    Toast.makeText(baseContext, "Usuário $raInput não encontrado ", Toast.LENGTH_LONG).show()
                }

                else -> {
                    Toast.makeText(baseContext, "Erro ao atualizar senha", Toast.LENGTH_LONG).show()}
            }
        }
    }
}