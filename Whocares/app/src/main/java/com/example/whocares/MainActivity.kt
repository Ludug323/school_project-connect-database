package com.example.whocares


import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT > 9) { // 使手機app的網路可以連接的設定
            val policy =
                StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        var tv_temp = findViewById<TextView>(R.id.tv_temp)
        var tv_hu = findViewById<TextView>(R.id.tv_humidity)
        var tv_gas = findViewById<TextView>(R.id.tv_gas)

        val con = mysql()
        con.run()
        var data = con.getdata()
        tv_temp.post(Runnable { //如果要取得資料庫資料必須得用Thread
            run(){
                tv_temp.setText(data[0])
            }
        })
        tv_hu.post(Runnable {
            run(){
                tv_hu.setText(data[1])
            }
        })
        tv_gas.post(Runnable {
            run(){
                tv_gas.setText(data[2])
            }
        })
        bt_updata.setOnClickListener {//更新資料Button按下去,才會取得資料庫資料,並且顯示在app上
            con.run()
            data = con.getdata()
            tv_temp.post(Runnable {
                run(){
                    tv_temp.setText(data[0])
                }
            })
            tv_hu.post(Runnable {
                run(){
                    tv_hu.setText(data[1])
                }
            })
            tv_gas.post(Runnable {
                run(){
                    tv_gas.setText(data[2])
                }
            })
            Toast.makeText(this,"已刷新資料",Toast.LENGTH_SHORT).show()
        }
    }

    fun aletbut(){ //刷新資料的提示語
        val tView : String? = null
        AlertDialog.Builder(this)
            .setTitle("")
            .setMessage("${tView}")
            .setPositiveButton("確認"){
                dialog,whith -> dialog.cancel()
            }
            .create()
            .show()
    }
}

