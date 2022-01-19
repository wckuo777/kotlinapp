package com.example.kotlindemo.ui.friendwebsites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.FriendWebSitesFragmentBinding
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FriendWebSitesFragment : Fragment() {
    private var _binding: FriendWebSitesFragmentBinding ?= null
    private val binding get() = _binding!!





    companion object {
        fun newInstance() = FriendWebSitesFragment()
    }

    private lateinit var viewModel: FriendWebSitesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FriendWebSitesFragmentBinding.inflate(inflater, container, false)
        val mWebView = _binding!!.mywebview
        mWebView.loadUrl("https://wckuo777.github.io/webdemo")




        Handler(Looper.getMainLooper()).postDelayed({
            Runnable { mWebView.loadDataWithBaseURL("https://wckuo777.github.io/webdemo", "", "text/html", "UTF-8", null) }
        }, 1000)

        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }
        }
//        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FriendWebSitesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroy() {
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onDestroy()
    }










}


