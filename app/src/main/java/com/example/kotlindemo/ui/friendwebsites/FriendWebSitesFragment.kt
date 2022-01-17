package com.example.kotlindemo.ui.friendwebsites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.FriendWebSitesFragmentBinding
import android.webkit.WebSettings
import android.webkit.WebViewClient







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
        mWebView.loadUrl("https://www.google.com/")
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        mWebView.webViewClient = WebViewClient()


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FriendWebSitesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}