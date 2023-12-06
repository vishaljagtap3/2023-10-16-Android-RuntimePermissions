package com.bitcodetech.permissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnAction : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAction = findViewById(R.id.btnAction)

        btnAction.setOnClickListener {
            //val permRes = checkSelfPermission(android.Manifest.permission.READ_CONTACTS)
            val permRes = ActivityCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.READ_CONTACTS
            )
            if(permRes == PackageManager.PERMISSION_GRANTED) {
                readContacts()
            }
            else {

                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(
                        android.Manifest.permission.READ_CONTACTS,
                        android.Manifest.permission.READ_SMS
                    ),
                    1
                )

                /*requestPermissions(
                    arrayOf(
                        android.Manifest.permission.READ_CONTACTS,
                        android.Manifest.permission.READ_SMS
                    ),
                    1
                )*/
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            readContacts()
        }
        else {
            mt("Can not read contacts! Permission denied...")
        }

        if(grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            readSmses()
        }
        else {
            mt("Can not read messages! Permission denied")
        }
    }

    private fun readSmses() {
        mt("Reading messages")
    }

    private fun readContacts() {
        mt("Reading contacts");
    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}