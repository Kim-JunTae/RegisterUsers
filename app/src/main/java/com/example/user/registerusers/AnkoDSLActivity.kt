package com.example.user.registerusers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.button
import org.jetbrains.anko.editText
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.verticalLayout


class AnkoDSLActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout{
            val name = editText{
                hint = "이름을 넣으세요"
            }

            button("SHOW"){
                onClick { toast("안녕하세요, ${name.text}!") }
            }
        }
    }//end onCreate()
}