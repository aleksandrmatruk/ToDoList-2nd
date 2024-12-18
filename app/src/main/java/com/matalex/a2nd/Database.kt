package com.matalex.a2nd

import kotlin.random.Random

object Database {

    private var instance: Database? = null

    fun getInstance(): Database {

        if (instance == null) {
            instance = Database
        }
        return instance as Database
    }

    private val _notes = ArrayList<Note>()
    val notes
        get() = _notes.toList()


    fun add(note: Note) {
        _notes.add(note)
    }

    fun remove(id: Int) {
        _notes.removeIf { it.id == id }
    }

    init {
        for (i in 0..5) {
            _notes.add(Note(i, "Note $i", Random.nextInt(3)))
        }
    }
}

