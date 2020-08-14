package com.example.user.registerusers.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

//org.jetbrains.anko 라이브러리를 사용한 DB
class DBHanlder_Anko(context: Context) : SQLiteOpenHelper(context, DB_Name, null, DB_Version) {
    companion object {
        val DB_Name = "user.db"
        val DB_Version = 1;
    }

    object UserTable {
        val TABLE_NAME = "user"
        val ID = "_id"
        val NAME = "name"
        val EMAIL = "email"
        val TELNUM = "telnum"
        val PIC_PATH = "pic_path"
    }

    fun getUserAllWithCursor(): Cursor {
        return readableDatabase.query(UserTable.TABLE_NAME,
                arrayOf(UserTable.ID, UserTable.NAME, UserTable.EMAIL, UserTable.TELNUM, UserTable.PIC_PATH),
                null, null, null, null, null)
    }//end getUserAllWithCursor()

    fun addUser(user:UserInfo) {
        var info = ContentValues()
        info.put(UserTable.NAME, user.name)
        info.put(UserTable.EMAIL, user.email)
        info.put(UserTable.TELNUM, user.TelNum)
        info.put(UserTable.PIC_PATH, user.pic_path)

        writableDatabase.use {
            writableDatabase.insert(UserTable.TABLE_NAME, null, info)
        }
        //writableDatabase.close() , use 함수를 쓰게 되서 자원해제를 알아서 해준다.
    }//end addUser()

    fun deleteUser(id:Long) {
        writableDatabase.use {
            writableDatabase.execSQL("DELETE FROM ${UserTable.TABLE_NAME} WHERE ${UserTable.ID} = ${id};")
        }
        //writableDatabase.close()
    }//end deleteUser()

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(UserTable.TABLE_NAME, true,
                Pair(UserTable.ID, INTEGER + PRIMARY_KEY),
                Pair(UserTable.NAME, TEXT),
                Pair(UserTable.EMAIL, TEXT),
                Pair(UserTable.TELNUM, TEXT),
                Pair(UserTable.PIC_PATH, TEXT));
    }//end onCreate

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }//end onUpgrade()
}