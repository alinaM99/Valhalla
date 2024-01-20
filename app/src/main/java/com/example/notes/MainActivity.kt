package com.example.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.notes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(binding.root)
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.goBtn.setOnClickListener{
            if (binding.etCode.text.toString().isEmpty()){
              showAlertDialogg("Enter password to continue")
            }
            else if (!binding.etCode.text.toString().equals("2112")){
                showAlertDialogg("Wrong password")
            }
            else{
                //showAlertDialogg("Correct password")
                val intent = Intent(this, NotesListActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun showAlertDialogg(text: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(text)
        builder.setPositiveButton("Ok") { dialogInterface, which ->
            (dialogInterface as AlertDialog).dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}