package com.gmail.aitama90p.ds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        dataModel.idMessage.observe(this, {
            it
        })

        dataModel.nameMessage.observe(this, {
            it
        })

        dataModel.phoneMessage.observe(this, {
            it
        })

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.contacts_container, ContactListFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }
}