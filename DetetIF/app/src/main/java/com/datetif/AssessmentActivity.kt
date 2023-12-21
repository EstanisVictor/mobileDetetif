package com.datetif

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.datetif.databinding.ActivityAssessmentBinding

class AssessmentActivity : AppCompatActivity() {
    lateinit var binding: ActivityAssessmentBinding
    var disc_name:String? = null
    var ra: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssessmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disc_name = intent.getStringExtra("disc")
        ra = intent.getStringExtra("ra").toString()
        if (disc_name == null) {
            disc_name = "Não informado"
        }
        binding.newPasswordTxt.text = disc_name + " - " + ra
    }
}