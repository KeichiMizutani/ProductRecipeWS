package com.example.productrecipews

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.productrecipews.databinding.ActivityMainBinding
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var itemStrList: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }


        // 読み込み
        itemStrList =  loadArrayList("test")


        val addedItem = intent.getStringExtra("Item")
        if (addedItem != null) {
            itemStrList.add(addedItem)
        }

        // 保存
        saveArrayList("test", itemStrList)


        for(item in itemStrList){
            addItem(Item(item))
        }

        binding.addMemoButton.setOnClickListener{
            val addIntent: Intent = Intent(this, AddActivity::class.java)

            startActivity(addIntent)
        }
    }

    private fun saveArrayList(key: String, arrayList: ArrayList<String>){

        val shardPreferences = this.getPreferences(Context.MODE_PRIVATE)
        val shardPrefEditor = shardPreferences.edit()

        val jsonArray = JSONArray(arrayList)
        shardPrefEditor.putString(key, jsonArray.toString())
        shardPrefEditor.apply()

    }

    fun loadArrayList(key: String) : ArrayList<String> {

        val shardPreferences = this.getPreferences(Context.MODE_PRIVATE)

        val jsonArray = JSONArray(shardPreferences.getString(key, "[]"));

        val arrayList : ArrayList<String> = ArrayList()

        for (i in 0 until jsonArray.length()) {
            arrayList.add(jsonArray.get(i) as String)
        }

        return arrayList
    }

    private fun addItem(item: Item){
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val itemTextView = TextView(this)
        itemTextView.text = item.title
        itemTextView.textSize = 24.0f

        val lp = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        itemTextView.layoutParams = lp

        val mlp = itemTextView.layoutParams as ViewGroup.MarginLayoutParams
        mlp.setMargins(16,16,16,16)

        val divider = View(this)
        val dividerLP = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            1
        )
        divider.layoutParams = dividerLP
        divider.setBackgroundColor(Color.GRAY)

        layout.addView(itemTextView)
        layout.addView(divider)

        binding.container.addView(layout)

        /*
        android:id="@+id/item_text1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="8dp"
            android:text="Item1"
            android:textSize="36sp" />

         <View
            android:id="@+id/divider1"
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:background="?android:attr/listDivider" />
            ListView
         */

    }
}