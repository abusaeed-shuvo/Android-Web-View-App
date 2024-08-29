package com.example.ebaywebviewapp

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ebaywebviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.loadUrl("https://www.ebay.com/")
        binding.webView.settings.javaScriptEnabled = true


        binding.webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.titleBar.text = "Loading..."
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
                binding.webView.visibility = View.VISIBLE
                binding.titleBar.text = "Ebay"
            }


        }

        binding.btnHome.setOnClickListener {
            binding.webView.loadUrl("https://www.ebay.com/")
        }

        binding.btnLeft.setOnClickListener {
            if (binding.webView.canGoBack()) binding.webView.goBack()
            else onBackPressed()
        }

        binding.btnRight.setOnClickListener {
            if (binding.webView.canGoForward()) binding.webView.goForward()
            else Toast.makeText(this@MainActivity, "Can't go forward.", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) binding.webView.goBack()
        else super.onBackPressed()

    }

}