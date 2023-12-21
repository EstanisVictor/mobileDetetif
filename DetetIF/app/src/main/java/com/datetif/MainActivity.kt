package com.datetif

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.datetif.config.ResponseCredentials
import com.datetif.controller.UserController
import com.datetif.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    lateinit var binding:ActivityMainBinding
    lateinit var userController: UserController

    var countLogin:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userController = UserController(baseContext)

        registerEvents()
    }

    private fun registerEvents() {
        binding.loginBtn.setOnClickListener(this)
        binding.esqueceuSenhaTxt.setOnClickListener(this)
        binding.inscrevaTxt.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.loginBtn.id -> executeLogin()
            binding.esqueceuSenhaTxt.id -> executeChangePassword()
            binding.inscrevaTxt.id -> executeSignUp()
        }
    }

    private fun executeSignUp() {
        val transitionRegisterActivity:Intent = Intent(baseContext, RegisterActivity::class.java)
        startActivity(transitionRegisterActivity)
    }

    private fun executeChangePassword() {
        val transitionChangePassword:Intent = Intent(baseContext, ChangePassword::class.java)
        startActivity(transitionChangePassword)
    }

    private fun executeLogin() {
        val raInput = binding.raInput.text.toString()
        val passwordInput = binding.passwordInput.text.toString()

        binding.errorRaTxt.text = ""
        binding.errorPasswordTxt.text = ""

        if (raInput == "" && passwordInput == ""){
            Toast.makeText(baseContext, "RA e Senha não podem ser vazios", Toast.LENGTH_LONG).show()
        }else if (raInput == ""){
            Toast.makeText(baseContext, "RA não pode ser vazio", Toast.LENGTH_LONG).show()
        }else if (passwordInput == ""){
            Toast.makeText(baseContext, "Senha não pode ser vazia", Toast.LENGTH_LONG).show()
        }else{
            val valida = userController.validateLogin(
                raInput,
                passwordInput
            )

            when(valida){
                is ResponseCredentials.USER_NOT_FOUND -> {
                    Toast.makeText(baseContext, "Usuario nao encontrado. Inscreva-se!!", Toast.LENGTH_LONG).show()
                }
                is ResponseCredentials.INCORRECT_PASSWORD -> {
                    if(countLogin >= 3){
                        Toast.makeText(baseContext, "Você tentou o login $countLogin vezes, trocar senha", Toast.LENGTH_LONG).show()
                    }
                    countLogin++
                    binding.errorPasswordTxt.text = "Senha Incorreta"
                }
                is ResponseCredentials.SUCCESS -> {
                    val transitionListQuaters:Intent = Intent(baseContext, ListQuaters::class.java)
                    transitionListQuaters.putExtra("ra", raInput)
                    startActivity(transitionListQuaters)
                }
            }
        }
    }
}