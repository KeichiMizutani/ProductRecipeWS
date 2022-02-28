package com.example.productrecipews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.productrecipews.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.addButton.setOnClickListener{
            if(binding.memoTextField.editText != null) {
                val itemText: String = binding.memoTextField.editText!!.text.toString()
                val mainIntent: Intent = Intent(this, MainActivity::class.java)

                Log.d("AddActivityLog", itemText)
                mainIntent.putExtra("Item",itemText)
                startActivity(mainIntent)
            }
        }
    }
}