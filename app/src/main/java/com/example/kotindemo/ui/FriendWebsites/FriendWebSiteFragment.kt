package com.example.kotindemo.ui.FriendWebsites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotindemo.R

class FriendWebSiteFragment : Fragment() {

    companion object {
        fun newInstance() = FriendWebSiteFragment()
    }

    private lateinit var viewModel: FriendWebSiteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.friend_web_site_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FriendWebSiteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}