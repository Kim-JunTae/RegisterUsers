package com.example.user.registerusers.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class UserInfo(val name:String = "No Name",
                    val email:String = "No Email",
                    val TelNum:String = "No TelNum",
                    val pic_path:String)

enum class UserData(val index:Int){
    _id(0),
    Name(1),
    Email(2),
    TelNum(3),
    PicPath(4)
}

class DBHandler : SQLiteOpenHelper {

    constructor(context:Context):super(context, DB_Name, null, DB_Version)

    companion object {
        val DB_Name = "user.db"
        val DB_Version = 1;
    }

    val TABLE_NAME = "user"
    val ID = "_id"
    val NAME = "name"
    val EMAIL = "email"
    val TELNUM = "telnum"
    val PIC_PATH = "pic_path"

    val TABLE_CREATE = "CREATE TABLE if not exists " + TABLE_NAME + " (" +
            "${ID} integer PRIMARY KEY ,t, ${NAME} text," +
            "${EMAIL} text, ${TELNUM} text, ${PIC_PATH} text"+  ")"

    fun getUserAllWithCursor():Cursor{
        val cursor = readableDatabase.query(TABLE_NAME, arrayOf(ID, NAME, EMAIL, TELNUM,PIC_PATH), null, null, null, null, null)
        return cursor
    }//end getUserAllWithCursor()

    fun addUser(user:UserInfo) {
        var info = ContentValues()
        info.put(NAME, user.name)
        info.put(EMAIL, user.email)
        info.put(TELNUM, user.TelNum)
        info.put(PIC_PATH, user.pic_path)
        writableDatabase.insert(TABLE_NAME, null, info)
        writableDatabase.close() // 메모리 누스 현상을 방지하기위해서 자원을 해제
    }//end addUser()

    fun deleteUser(id:Long) {
        writableDatabase.execSQL("DELETE FROM ${TABLE_NAME} WHERE ${ID} = ${id};")
        writableDatabase.close()
    }//end deleteUser()

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CREATE)   //user.db 이 데이터베이스가 생성되면 호출되는 함수
    }//end onCreate()

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }//end onUpgrade()
}