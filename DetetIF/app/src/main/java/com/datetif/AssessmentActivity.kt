package com.datetif

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.datetif.databinding.ActivityAssessmentBinding
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class AssessmentActivity : AppCompatActivity() {
    lateinit var binding: ActivityAssessmentBinding
    lateinit var downloader: Runnable
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

        downloadImageFromUrl()
    }

    fun downloadImageFromUrl() {
        val policyAct = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policyAct)

        downloader = Runnable {
            val url = URL("https://detetifserver.pagekite.me/api/answerCorrection/response/2_TRIM_2023/$ra")
            val req: HttpsURLConnection = url.openConnection() as HttpsURLConnection
            req.requestMethod = "GET"
            val contentType = req.getHeaderField("Content-Type")
            if (contentType?.startsWith("image/") == true) {
                val inputStream = req.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)

                runOnUiThread {
                    binding.assessmentImage.visibility = View.VISIBLE
                    binding.assessmentImage.setImageBitmap(bitmap)
                }
            } else {
                val resposta = "  Não há nenhum gabarito\n      corrigido para o RA \n              $ra"
                val spannable = SpannableString(resposta)

                spannable.setSpan(ForegroundColorSpan(Color.WHITE), 0, 49, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                spannable.setSpan(ForegroundColorSpan(Color.RED), 50, resposta.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                binding.assessmentReponse.visibility = View.VISIBLE
                binding.assessmentReponse.text = spannable
            }
        }
        downloader.run()
    }
}