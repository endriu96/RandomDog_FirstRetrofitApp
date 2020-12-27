package com.example.randomdog_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import coil.api.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity(),CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button: Button = findViewById(R.id.btn_get_image)
        var imageModel:ImageView=findViewById(R.id.iv_dog_image)


        button.setOnClickListener {
            launch(Dispatchers.Main) {
                // Try catch block to handle exceptions when calling the API.
                try{
                    val response= ApiClient.ApiAdapter.apiClient.getRandomImage()

                    if(response.isSuccessful && response.body()!= null){
                        val data=response.body()!!

                        data.message.let{ imageUrl->imageModel.load(imageUrl) }
                    }
                    else Toast.makeText(
                            this@MainActivity,
                            "Error Occurred: ${response.message()}",
                            Toast.LENGTH_LONG).show()
                }
                catch (e:Exception){
                    Toast.makeText(this@MainActivity,
                            "Error Occurred: ${e.message}",
                            Toast.LENGTH_LONG).show()
                }
                }
        }
    }
}