package com.example.noteapp.ui.detail

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.noteapp.data.NoteDao
import com.example.noteapp.data.NoteDatabase
import com.example.noteapp.model.Note
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailViewModelTest : TestCase() {

    val TAG = "TAG"

    private lateinit var db: NoteDatabase
    private lateinit var dao: NoteDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        dao = db.notedao
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadNotes() = runBlocking {

        val testInsertedNote = Note("note-name", "note-description", true, "2022-07-14", 3614)
        dao.insertNote(testInsertedNote)

        val lastInsertedNote = dao.getLastInsertedNote()
        Log.d(TAG, "writeAndReadNotes: lastInsertedNote ---> $lastInsertedNote")
        assertTrue("note-name" == testInsertedNote.title)
        assertTrue("note-description" == testInsertedNote.description)
        assertTrue(testInsertedNote.isDone)
        assertTrue("2022-07-14" == testInsertedNote.date)
        assertTrue(3614 == testInsertedNote.id)


    }
}