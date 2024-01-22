package com.example.collegenews

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.collegenews.R.id.btnSignIn
import com.example.collegenews.R.id.btnSignUp
import com.example.collegenews.R.id.usernameText
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.FirebaseDatabaseKtxRegistrar
import org.w3c.dom.EntityReference
import org.w3c.dom.Text

//import com.example.collegenews.databinding.ActivityMainBinding


class signIn : AppCompatActivity() {
//    private lateinit var binding : ActivityMainBinding

    lateinit var datatbaseReference: DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_sign_in)

        val VusernameText = findViewById<TextInputEditText>(R.id.usernameText2)
        val VpaswdText = findViewById<TextInputEditText>(R.id.paswdText2)
        val VbtnSignIn = findViewById<Button>(btnSignIn)

        VbtnSignIn.setOnClickListener{
            val username = VusernameText.text.toString()
            if(username.isNotEmpty()){
                readData(username)
            }else{
                Toast.makeText(this,"Please enter username and password",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(username: String) {
        datatbaseReference = FirebaseDatabase.getInstance().getReference("Users")
        datatbaseReference.child(username).get().addOnSuccessListener {
            //if user exists
            if(it.exists()){
                //welcome user in your app
                val intentwelcome = Intent(this,MainActivity::class.java)
//                putextra
                startActivity(intentwelcome)
            }else{
                Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong with database !",Toast.LENGTH_SHORT).show()
        }
    }
}