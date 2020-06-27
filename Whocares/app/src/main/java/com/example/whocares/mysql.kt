package com.example.whocares
import android.os.StrictMode
import android.util.Log
import java.sql.DriverManager
import java.sql.SQLException


class mysql{
    // 資料庫定義
    var mysql_ip =  "172.20.10.3"
    var mysql_port = 3306 // Port 預設為 3306
    var db_name = "project"
    var url = "jdbc:mysql://${mysql_ip}:${mysql_port}/${db_name}?useUnicode=true&characterEncoding=UTF-8&useSSL=false"
    var db_user = "root"
    var db_password = "loge717764"
    fun run() {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            Log.v("DB", "加載驅動成功")
        } catch (e: ClassNotFoundException) {
            Log.e("DB", "加載驅動失敗")
            return
        }

        // 連接資料庫
        try {
            val con =
                DriverManager.getConnection(url, db_user, db_password)
            Log.v("DB", "遠端連接成功")
        } catch (e: SQLException) {
            Log.e("DB", "遠端連接失敗")
            Log.e("DB", e.toString())
        }
    }

    fun getdata():ArrayList<String>{
            var data_temp = ""
            var data_hu = ""
            var data_gas = ""
            var dataList = arrayListOf<String>() //宣告一個陣列放入資料庫資料
            dataList.clear()
            try { //ERROR CODE
                val con =
                    DriverManager.getConnection(url, db_user, db_password)
                val sql = "SELECT * FROM value"
                val st = con.createStatement()
                val rs = st.executeQuery(sql) //使用SQL語法
                while (rs.next()) {
                    val a = rs.getString("a") //取得資料庫table name 放入變數
                    val b = rs.getString("b")
                    val c = rs.getString("c")
                    data_temp = a
                    data_hu = b
                    data_gas = c
                }
                dataList.add(data_temp)
                dataList.add(data_hu)
                dataList.add(data_gas)
                st.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            return dataList
        }
}