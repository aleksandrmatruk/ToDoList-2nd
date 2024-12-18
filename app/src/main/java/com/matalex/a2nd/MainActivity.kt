package com.matalex.a2nd

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutNotes: LinearLayout
    private lateinit var buttonAddNote: FloatingActionButton

    private val notes = ArrayList<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        for (i in 0..20) {
            val note = Note(i, "Note $i", Random.nextInt(3))
            notes.add(note)
        }

        showNotes()
        buttonAddNote.setOnClickListener {
            val intent = AddNoteActivity.newIntent(this)
            startActivity(intent)
        }
    }

    private fun initViews() {
        linearLayoutNotes = findViewById(R.id.linearLayoutNotes)
        buttonAddNote = findViewById(R.id.buttonAddNote)
    }

    private fun showNotes() {
        for (note in notes) {
            val view = layoutInflater.inflate(
                R.layout.note_item,
                linearLayoutNotes,
                false
            )
            val textViewNote = view.findViewById<TextView>(R.id.textViewNote)
            textViewNote.text = note.text
            val colorId = when (note.priority) {

                0 -> {
                    android.R.color.holo_green_light
                }

                1 -> {
                    android.R.color.holo_orange_light
                }

                else -> {
                    android.R.color.holo_red_light
                }
            }

            val color = ContextCompat.getColor(this, colorId)
            textViewNote.setBackgroundColor(color)
            linearLayoutNotes.addView(view)
        }
    }
}