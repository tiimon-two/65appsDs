package com.gmail.aitama90p.ds

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.widget.Toolbar
import com.gmail.aitama90p.ds.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ContactListService.ContactListServiceInterface {
    private var contactListService: ContactListService? = null
    private var bound = false
    private var _binding:ActivityMainBinding? = null
    private  val binding get() = _binding!!

    private val connection = object:ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as ContactListService.ContactListBinder
            contactListService = binder.getService()
            bound = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val intent = Intent(this, ContactListService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        if(bound){
            unbindService(connection)
            bound = false
        }

            if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.contacts_container, ContactListFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroy() {
        unbindService(connection)
        bound = false
        super.onDestroy()
    }

    override fun getService() = contactListService
    }
