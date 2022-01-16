package com.example.kotlindemo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.FragmentHomeDetailBinding
import com.example.kotlindemo.ui.gallery.GalleryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeDetailFragment : Fragment() {

    
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var hominid: String
    private var _binding: FragmentHomeDetailBinding ? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by activityViewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            hominid = it.getString(HOMEINFO).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // Inflate the layout for this fragment
        _binding = FragmentHomeDetailBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.detailText;
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return binding.root
        //return inflater.inflate(R.layout.fragment_home_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.detailText.text = homeViewModel.text.value.toString()
        when (homeViewModel.drinksComment.value?.get(hominid.toInt()).toString()){
            "1" ->  binding.detailText.text = "one"
            "2" -> binding.detailText.text = "two"
            "3" -> binding.detailText.text = "three"
            else -> binding.detailText.text = "You have no comment"
        }
        //binding.detailText.text = homeViewModel.drinksComment.value?.get(hominid.toInt()).toString()
        //binding.detailText.text = homeViewModel.drinksComment.
//        homeViewModel.drinksComment.observe(viewLifecycleOwner, Observer {
//            binding.detailText.text = "abc"
//        })
    }

    companion object {
        val HOMEINFO = "homeinfo"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}