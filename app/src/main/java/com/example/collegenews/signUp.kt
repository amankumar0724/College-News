package com.example.collegenews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.collegenews.R.id.btnSignIn
import com.example.collegenews.R.id.btnSignUp
import com.example.collegenews.R.id.usernameText
import com.example.intentsapp.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.FirebaseDatabaseKtxRegistrar
import org.w3c.dom.EntityReference
import org.w3c.dom.Text

//import com.example.collegenews.databinding.ActivityMainBinding


class signUp : AppCompatActivity() {
//    private lateinit var binding : ActivityMainBinding

    lateinit var datatbaseReference: DatabaseReference
    companion object{
        const val KEY1 = "com.example.collegenews.signIn.KEY1"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_sign_up)

        val VusernameText = findViewById<TextInputEditText>(R.id.usernameText)
        val VemailText = findViewById<TextInputEditText>(R.id.emailText)
        val VpaswdText = findViewById<TextInputEditText>(R.id.paswdText)
        val VbtnSignUp = findViewById<Button>(btnSignUp)
        val VclickSignIn = findViewById<TextView>(R.id.clickSignIn)

        VclickSignIn.setOnClickListener {
            val intent = Intent(this, signIn::class.java)
            startActivity(intent)
        }

        VbtnSignUp.setOnClickListener{
            val username = VusernameText.text.toString()
            val Emailid = VemailText.text.toString()
            val passwd = VpaswdText.text.toString()

            val user = User(username,Emailid,passwd)

            if(username.isNotEmpty()){
                val User = User(username,Emailid,passwd)
                datatbaseReference = FirebaseDatabase.getInstance().getReference("Users")
                datatbaseReference.child(username).get().addOnSuccessListener {
                    //if user exists
                    if(it.exists()){
                        //welcome user in your app
                        Toast.makeText(this,"This username already exists",Toast.LENGTH_SHORT).show()
                    }else{
                        datatbaseReference.child(username).setValue(user).addOnSuccessListener {
                            VusernameText.text?.clear()
                            VemailText.text?.clear()
                            VpaswdText.text?.clear()
                            Toast.makeText(this,"Data saved successfully",Toast.LENGTH_SHORT).show()
                        }
                        val intentwelcome = Intent(this,MainActivity::class.java)
//                      putextra
                        startActivity(intentwelcome)
                    }
                }.addOnFailureListener{
                    Toast.makeText(this,"Something went wrong with database !",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please enter username and password",Toast.LENGTH_SHORT).show()
            }
        }


    }

}