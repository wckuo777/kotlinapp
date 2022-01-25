package com.example.kotlindemo.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.FragmentGalleryBinding
import com.example.kotlindemo.ui.home.HomeViewModel
import android.widget.ArrayAdapter





class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        homeViewModel.cleanComment()
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        val textView2: TextView = binding.textView2
        val spinner: Spinner = binding.spinner

        val arrayList1 = ArrayList<String>()
        arrayList1.add("Bangalore");
        arrayList1.add("Delhi");
        arrayList1.add("Mumbai");
        val adp: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayList1)
        spinner.adapter = adp



        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            textView2.text = it

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


