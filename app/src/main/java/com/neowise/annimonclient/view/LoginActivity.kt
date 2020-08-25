package com.neowise.annimonclient.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.neowise.annimonclient.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        token_edit.setText(sharedPreferences.getString("token", ""))

        login_btn.setOnClickListener {
            val token = token_edit.text.toString().trim()
            if (!token.isEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("token", token_edit.text.toString())
                editor.apply()
                launchMain()
            }
            else {
                Toast.makeText(this, "Введите токен", Toast.LENGTH_LONG).show()
            }
        }

        login_as_guest_btn.setOnClickListener {
            launchMain()
        }
    }

    private fun launchMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}