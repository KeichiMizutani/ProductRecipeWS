package com.example.productrecipews

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginStart
import com.example.productrecipews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }


        val item1 = Item("Item1")
        val item2 = Item("Item2")
        val item3 = Item("Item3")

        addItem(item1)
        addItem(item2)
        addItem(item3)

        binding.addMemoButton.setOnClickListener{
            val addIntent: Intent = Intent(this, AddActivity::class.java)
            startActivity(addIntent)
        }
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