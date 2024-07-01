package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl("https://kominfo.kepriprov.go.id/beta/assets/")
    }
}