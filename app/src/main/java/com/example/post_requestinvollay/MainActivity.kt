package com.example.post_requestinvollay

import androidx.appcompat.app.AppCompatActivity

import android.app.ProgressDialog
import android.nfc.Tag
import android.os.Bundle
import android.util.Log

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import org.json.JSONObject

import java.util.HashMap

class MainActivity<MyStringRequest> : AppCompatActivity() {

    /*
    * Here we have used Post Method for post our data into our server.
    * if you are using object request then use below link.
    * "https://jsonplaceholder.typicode.com/todos/1"
    * if you are using array request then use below link.
    * "https://jsonplaceholder.typicode.com/todos"
    * here i have added a progress dialog in our  app.
    * all code is same like json get method.
    * check the gradle file where i have added one link.
    * in android manifest i hava added internet permission.
    * all data will be show in our 4:Run folder nechay dekhna.
    *
    * */

    private var mRequestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mRequestQueue = Volley.newRequestQueue(this)

        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...PLease wait")
        pDialog.show()

        val jsonObjReq = object : JsonObjectRequest(Request.Method.POST,"https://jsonplaceholder.typicode.com/todos/1" ,
                null,
                Response.Listener { response ->
                    Log.d("My App", response.toString())
                    pDialog.hide()
                },
                Response.ErrorListener { error ->
                    VolleyLog.d("myapp", "Error: " + error.message)
                    pDialog.hide()
                }) {

            override fun getParams(): MutableMap<String, String>? {
                val params = hashMapOf()
                params.put("name", "Androidhive")
                params.put("email", "abc@androidhive.info")
                params.put("password", "password123")

                return params
            }

        }

        mRequestQueue!!.add(jsonObjReq)


    }
}

