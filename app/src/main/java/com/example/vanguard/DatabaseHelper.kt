package com.example.vanguard.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.vanguard.model.Van

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "vanguard.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_VANS = "vans"
        const val COLUMN_ID = "id"
        const val COLUMN_MODELO = "modelo"
        const val COLUMN_PLACA = "placa"
        const val COLUMN_MOTORISTA = "motorista"
        const val COLUMN_PRECO = "preco"
        const val COLUMN_TELEFONE = "telefone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_VANS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_MODELO TEXT, "
                + "$COLUMN_PLACA TEXT, "
                + "$COLUMN_MOTORISTA TEXT, "
                + "$COLUMN_PRECO REAL, "
                + "$COLUMN_TELEFONE TEXT)")
        db.execSQL(createTable)

        // Inserir dados de teste para o POC
        insertMockData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_VANS")
        onCreate(db)
    }

    private fun insertMockData(db: SQLiteDatabase) {
        val vans = listOf(
            Van(modelo = "Mercedes-Benz Sprinter", placa = "VAN-2024", motorista = "Seu João", precoMensal = 350.00, telefone = "(11) 99999-0001"),
            Van(modelo = "Fiat Ducato", placa = "ESC-1234", motorista = "Dona Maria", precoMensal = 320.00, telefone = "(11) 98888-0002"),
            Van(modelo = "Renault Master", placa = "TCC-2025", motorista = "Carlos Silva", precoMensal = 300.00, telefone = "(11) 97777-0003")
        )

        vans.forEach { van ->
            val values = ContentValues().apply {
                put(COLUMN_MODELO, van.modelo)
                put(COLUMN_PLACA, van.placa)
                put(COLUMN_MOTORISTA, van.motorista)
                put(COLUMN_PRECO, van.precoMensal)
                put(COLUMN_TELEFONE, van.telefone)
            }
            db.insert(TABLE_VANS, null, values)
        }
    }

    fun getAllVans(): List<Van> {
        val vanList = mutableListOf<Van>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_VANS", null)

        if (cursor.moveToFirst()) {
            do {
                val van = Van(
                    id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    modelo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODELO)),
                    placa = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLACA)),
                    motorista = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOTORISTA)),
                    precoMensal = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECO)),
                    telefone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONE))
                )
                vanList.add(van)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return vanList
    }
}