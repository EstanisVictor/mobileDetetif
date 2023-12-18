package com.datetif

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.datetif.databinding.ActivityAssessmentBinding

class AssessmentActivity : AppCompatActivity() {
    lateinit var binding: ActivityAssessmentBinding
    var disc_name:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssessmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disc_name = intent.getStringExtra("disc")
        if (disc_name == null) {
            disc_name = "Não informado"
        }
        binding.newPasswordTxt.text = disc_name
    }
}