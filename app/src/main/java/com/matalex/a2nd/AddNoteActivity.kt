package com.matalex.a2nd

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddNoteActivity : AppCompatActivity() {

    private lateinit var editTextNote: EditText
    private lateinit var radioButtonLow: RadioButton
    private lateinit var radioButtonMedium: RadioButton
    private lateinit var radioButtonHigh: RadioButton
    private lateinit var buttonSave: Button

    private val database = Database.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        initViews()
        buttonSave.setOnClickListener {
            saveNote()
        }
    }

    private fun initViews() {
        editTextNote = findViewById(R.id.editTextNote)
        radioButtonLow = findViewById(R.id.radioButtonLow)
        radioButtonMedium = findViewById(R.id.radioButtonMedium)
        radioButtonHigh = findViewById(R.id.radioButtonHigh)
        buttonSave = findViewById(R.id.buttonSave)
    }

    private fun saveNote() {
        val text = editTextNote.text.toString().trim()
        val priority = getPriority()
        val id = database.notes.size
        val note = Note(id, text, priority)
        if (text.isEmpty()) {
            Toast.makeText(this, "Заметка не может быть пустой!", Toast.LENGTH_LONG).show()
        } else {
            database.add(note)
            finish()
        }
    }

    private fun getPriority(): Int {
        val priority: Int = if (radioButtonLow.isChecked) {
            0
        } else if (radioButtonMedium.isChecked) {
            1
        } else {
            2
        }
        return priority
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddNoteActivity::class.java)
        }
    }
}