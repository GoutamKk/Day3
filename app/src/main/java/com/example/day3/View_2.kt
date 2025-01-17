package com.example.day3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class View_2 : AppCompatActivity() {
    private lateinit var name:TextView
    private lateinit var email:TextView
    private lateinit var contact:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        name = this.findViewById<TextView>(R.id.UserName)
        email = this.findViewById<TextView>(R.id.UserEmail)
        contact = this.findViewById<TextView>(R.id.UserContact)

        name.setText(intent.getStringExtra("naan"))
        email.setText(intent.getStringExtra("email"))
        contact.setText(intent.getStringExtra("number"))
        var email = intent.getStringExtra("email")
        var number = intent.getStringExtra("number")

        findViewById<Button>(R.id.emailvisitBtn).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setData(Uri.parse("mailTo"))
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("$email"))
            intent.putExtra(Intent.EXTRA_SUBJECT,"Succesfull Registration !")
            intent.putExtra(Intent.EXTRA_TEXT,"Thank you registration !")
            startActivity(intent)
        }
        findViewById<Button>(R.id.ContactsmsBtn).setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.setData(Uri.parse("smsto:$number"))
            intent.putExtra("sms_body","Thank you registration !")
            startActivity(intent)
        }
    }
}