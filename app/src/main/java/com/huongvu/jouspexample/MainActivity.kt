package com.huongvu.jouspexample

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import org.jsoup.Jsoup
import java.io.IOException


class MainActivity : AppCompatActivity() {
    val url = "https://vt.tiktok.com/UuYVGp/"
    var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val content = Content()
        content.execute()
    }

    var progressDialog: ProgressDialog? = null
    var bitmap : Bitmap ? =null
    inner class Content : AsyncTask<Void?, Void?, Void?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog?.show()
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            progressDialog?.dismiss()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                //Connect to the website
                val document = Jsoup.connect(url).get()
                val html = document.getElementById("__next").html()
                val jsonObject = JSONObject(html.substring(html.indexOf("{")))
                Log.e("HVV1312","jsonObject : $jsonObject")
                //Get the title of the website
                title = document.title()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }
    }

}