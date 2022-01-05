package com.example.kotlindemo.ui.friendwebsites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlindemo.R

class FriendWebSitesFragment : Fragment() {

    companion object {
        fun newInstance() = FriendWebSitesFragment()
    }

    private lateinit var viewModel: FriendWebSitesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.friend_web_sites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FriendWebSitesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}