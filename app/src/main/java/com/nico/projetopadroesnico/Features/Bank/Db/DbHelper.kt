package com.nico.projetopadroesnico.Features.Bank.Db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.mobologicplus.kotlinmvpandroid.db.FriendsTable
import com.nico.projetopadroesnico.Application.NicoApplication
import org.jetbrains.anko.db.*

/**
 * Created by sunil on 5/30/2017.
 */
class DbHelper(var context : Context = NicoApplication.getInstance()) : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            db.createTable(
                    FriendsTable.TABLE_NAME,
                    true,
                    FriendsTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                    FriendsTable.FRIEND_ID to TEXT,
                    FriendsTable.NAME to TEXT,
                    FriendsTable.EMAIL to TEXT
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmField
        val DB_VERSION = 1
        @JvmField
        val DB_NAME = "dbFriends"
    }
}