package com.example.kotlindemo.ui.gallery

import android.R
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemo.databinding.FragmentGalleryBinding
import com.example.kotlindemo.ui.home.HomeViewModel
import com.example.kotlindemo.data.Datasource
import com.example.kotlindemo.model.SpinnerItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.kotlindemo.adapter.DropdownAdapter


class GalleryFragment() : Fragment(), AdapterView.OnItemSelectedListener {

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
        //val textView2: TextView = binding.textView2
        val spinner: Spinner = binding.spinner
        val spinnerSize: Spinner = binding.spinner2

        val arrayList1 = ArrayList<String>()
        arrayList1.add("B");
        arrayList1.add("D");
        arrayList1.add("M");
        val spinmap = Datasource().loadSpinnerItem()
        val spinmapsize = Datasource().loadSpinnerItemSize()

        val drinkList: ArrayList<SpinnerItem> = ArrayList()
        val drinkSizeList: ArrayList<SpinnerItem> = ArrayList()
        //Add countries

        //Add countries
        for (item in spinmap){
            drinkList.add(SpinnerItem(item.key, item.value))
        }
        for (itemSize in spinmapsize){
            drinkSizeList.add(SpinnerItem(itemSize.key, itemSize.value))
        }

        val adptest: ArrayAdapter<SpinnerItem> = DropdownAdapter(requireContext(),
            R.layout.simple_spinner_dropdown_item,
            drinkList,spinner.selectedItemPosition)

        val adptest2: ArrayAdapter<SpinnerItem> = DropdownAdapter(requireContext(),
            R.layout.simple_spinner_dropdown_item,
            drinkSizeList,spinnerSize.selectedItemPosition)

        val adp: ArrayAdapter<SpinnerItem> = object: ArrayAdapter<SpinnerItem>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            drinkList
        ){
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view:TextView = super.getDropDownView(
                    position,
                    convertView,
                    parent
                ) as TextView

                // set item text bold and sans serif font
                view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)

                if (position == 0){
                    // set the spinner disabled item text color
                    view.setTextColor(Color.LTGRAY)
                }

                // set selected item style
                if (position == spinner.selectedItemPosition){
                    view.background = ColorDrawable(Color.parseColor("#07B2F9"))
                }

                return view
            }

//            override fun isEnabled(position: Int): Boolean {
//                // disable the third item of spinner
//                return position != 0
//            }
        }

        spinner.onItemSelectedListener = this
        spinner.adapter = adptest
        spinnerSize.onItemSelectedListener = this
        spinnerSize.adapter = adptest2

        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            //textView2.text = it

        })
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val selectItems: SpinnerItem = parent?.selectedItem as SpinnerItem
        Toast.makeText(
            context,
            "ID: " + selectItems.getId().toString() + ",  Name : " + selectItems.getName(),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }




}




