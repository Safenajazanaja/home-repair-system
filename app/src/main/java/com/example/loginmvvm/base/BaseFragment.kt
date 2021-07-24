package com.example.loginmvvm.base

import android.os.Bundle
import android.os.StrictMode
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loginmvvm.data.datasource.DataSource

import org.jetbrains.exposed.sql.Database

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    val dataSource= DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        val host = "192.168.0.199"
        val databaseName = "callm"
        val url = "jdbc:mysql://$host:3306/$databaseName?useUnicode=true&characterEncoding=utf-8"
        Database.connect(
            url = url,
            driver = "com.mysql.jdbc.Driver",
            user = "computerrepair",
            password = "1234",
        )
    }

}
