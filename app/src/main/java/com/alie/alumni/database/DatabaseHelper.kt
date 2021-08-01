package com.alie.alumni.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.alie.alumni.model.Mahasiswa
import java.io.IOException

class DatabaseHelper(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{
    companion object
    {
        private val DATABASE_NAME = "Student.db"
        private val DATABASE_VERSION = 1

        val MAHASISWA_TABLE_NAME = "Kelas"
        val COLUMN_ID = "kelasID"
        val COLOUMN_JUDUL = "kelasJudul"
        val COLOUMN_DESCRIPTION = "kelasDescription"
        val COLOUMN_HARI = "kelasHari"
        val COLOUMN_TANGGAL = "kelasTanggal"
    }

    override fun onCreate(db: SQLiteDatabase?)
    {
        val CREATE_KELAS_TABLE = ("CREATE TABLE $MAHASISWA_TABLE_NAME ("+
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "$COLOUMN_JUDUL TEXT,"+
                "$COLOUMN_DESCRIPTION TEXT,"+
                "$COLOUMN_HARI TEXT,"+
                "$COLOUMN_TANGGAL TEXT)")
        db?.execSQL(CREATE_KELAS_TABLE)

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        db?.execSQL("DROP TABLE IF EXISTS $MAHASISWA_TABLE_NAME")
        onCreate(db)
    }

    fun insert(mContext : Context) : ArrayList<Mahasiswa>
    {
        val query = "Select * From $MAHASISWA_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query,null)
        val siswa = ArrayList<Mahasiswa>()

        if (cursor.count == 0) {

        }
        else
        {
            cursor.moveToFirst()
            while (!cursor.isAfterLast)
            {
                val mahasiswa = Mahasiswa()
                mahasiswa.kelasID = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                mahasiswa.kelasHari = cursor.getString(cursor.getColumnIndex(COLOUMN_HARI))
                mahasiswa.kelasTanggal = cursor.getString(cursor.getColumnIndex(COLOUMN_TANGGAL))
                mahasiswa.kelasName = cursor.getString(cursor.getColumnIndex(COLOUMN_JUDUL))
                mahasiswa.kelasDescription = cursor.getString(cursor.getColumnIndex(COLOUMN_DESCRIPTION))

                siswa.add(mahasiswa)
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return siswa
    }

    fun addMahasiswa(mContext: Context,mahasiswa: Mahasiswa)
    {
        val values = ContentValues()
        values.put(COLOUMN_HARI,mahasiswa.kelasHari)
        values.put(COLOUMN_TANGGAL,mahasiswa.kelasTanggal)
        values.put(COLOUMN_JUDUL,mahasiswa.kelasName)
        values.put(COLOUMN_DESCRIPTION,mahasiswa.kelasDescription)
        val db = this.writableDatabase

        try
        {
            db.insert(MAHASISWA_TABLE_NAME,null,values)
            Toast.makeText(mContext,"success to add",Toast.LENGTH_SHORT).show()
        }
        catch (e : IOException)
        {
            Toast.makeText(mContext,"failed to add" + e.stackTrace,Toast.LENGTH_SHORT).show()
        }
        db.close()

    }
    fun deleteMahasiswa(kelasID : Int) : Boolean
    {
        val query = "Delete from $MAHASISWA_TABLE_NAME where $COLUMN_ID = $kelasID"
        val db = this.writableDatabase
        var result = false
        try
        {
            val cursor = db.execSQL(query)
            result = true
        }
        catch (e : IOException)
        {
            e.message
        }
        db.close()
        return result
    }
    fun updateMahasiswa(id : String,  name: String, desc : String, hari : String, tanggal : String ) : Boolean
    {
        val db = this.writableDatabase
        val values = ContentValues()
        var result = false
        values.put(COLOUMN_JUDUL,name)
        values.put(COLOUMN_DESCRIPTION,desc)
        values.put(COLOUMN_HARI,hari)
        values.put(COLOUMN_TANGGAL,tanggal)

        try
        {
            db.update(MAHASISWA_TABLE_NAME,values,"$COLUMN_ID= ?", arrayOf(id))
            result = true
        }
        catch (e : IOException)
        {
            result = false
        }
        return result
    }
}