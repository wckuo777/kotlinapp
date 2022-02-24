package com.example.kotlindemo.ui.gallery

import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.adapter.DropdownAdapter
import com.example.kotlindemo.adapter.OrderListAdapter
import com.example.kotlindemo.data.Datasource
import com.example.kotlindemo.databinding.FragmentGalleryBinding
import com.example.kotlindemo.model.DataGroup
import com.example.kotlindemo.model.SpinnerItem
import com.example.kotlindemo.ui.home.HomeViewModel
import com.google.android.material.snackbar.Snackbar


class GalleryFragment() : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var galleryViewModel: GalleryViewModel
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listViewArray = mutableListOf<DataGroup>()


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
        val editText: EditText = binding.editTextNumber
        val listRecycle: RecyclerView = binding.listView
        val addBtn: Button = binding.buttonConfirm


        addBtn.setOnClickListener {

            val a = binding.spinner.selectedItem as SpinnerItem
            val b = binding.spinner2.selectedItem as SpinnerItem
            val c = binding.editTextNumber.text.toString().toInt()
            val spinnerId: Any = a.getId().toString()
            val spinnerName: Any? = a.getName()
            Toast.makeText(
                context,
                "ID: $spinnerId,  Name : $spinnerName",
                Toast.LENGTH_SHORT
            ).show()



            listViewArray.add(
                DataGroup(
                    spinnerName as String,
                    b.getName().toString(),
                    if (b.getName() == "M") 30 * c else 60 * c,
                    c
                )
            )
            listViewArray = listViewArray.asReversed().toMutableList()
            listRecycle.adapter = OrderListAdapter(
                requireContext(),
                listViewArray as ArrayList<DataGroup>
            )
            galleryViewModel.setOrderData(listViewArray as ArrayList<DataGroup>)
//            observeInput()


        }

        //list.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_multiple_choice, listViewArray)
        //list.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listRecycle.adapter = OrderListAdapter(
            requireContext(),
            listViewArray as ArrayList<DataGroup>
        )
        //layout is necessary or in xml
        //listRecycle.layoutManager = LinearLayoutManager(context)
        listRecycle.setHasFixedSize(true)

        // Assigning filters
        editText.filters = arrayOf<InputFilter>(MinMaxFilter(1, 20))
        //editText.inputType = InputType.TYPE_NULL

        // hide keyboard after press enter
        editText.onFocusChangeListener = OnFocusChangeListener { view, focused ->
            val keyboard =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (focused) keyboard.showSoftInput(editText, 0) else keyboard.hideSoftInputFromWindow(
                editText.windowToken,
                0
            )
        }


        val arrayList1 = ArrayList<String>()
        arrayList1.add("B");
        arrayList1.add("D");
        arrayList1.add("M");
        val spinmap = Datasource().loadSpinnerItem()
        val spinmapsize = Datasource().loadSpinnerItemSize()

        val drinkList: ArrayList<SpinnerItem> = ArrayList()
        val drinkSizeList: ArrayList<SpinnerItem> = ArrayList()
        //Add drinkList

        //Add drinkList
        for (item in spinmap) {
            drinkList.add(SpinnerItem(item.key, item.value))
        }
        for (itemSize in spinmapsize) {
            drinkSizeList.add(SpinnerItem(itemSize.key, itemSize.value))
        }

        val adptest: ArrayAdapter<SpinnerItem> = DropdownAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            drinkList, spinner.selectedItemPosition
        )

        val adptest2: ArrayAdapter<SpinnerItem> = DropdownAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            drinkSizeList, spinnerSize.selectedItemPosition
        )

        val adp: ArrayAdapter<SpinnerItem> = object : ArrayAdapter<SpinnerItem>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            drinkList
        ) {
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView = super.getDropDownView(
                    position,
                    convertView,
                    parent
                ) as TextView

                // set item text bold and sans serif font
                view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)

                if (position == 0) {
                    // set the spinner disabled item text color
                    view.setTextColor(Color.LTGRAY)
                }

                // set selected item style
                if (position == spinner.selectedItemPosition) {
                    view.background = ColorDrawable(Color.parseColor("#07B2F9"))
                }

                return view
            }

//            override fun isEnabled(position: Int): Boolean {
//                // disable the third item of spinner
//                return position != 0
//            }


        }

        spinner.adapter = adptest
        //initial select do not trigger
        spinner.setSelection(0, false)
        spinner.onItemSelectedListener = this
//        spinner.isFocusable = true
//        spinner.isFocusableInTouchMode = true
//        spinner.requestFocus()


        spinnerSize.adapter = adptest2
        spinner.setSelection(0, false)
        spinnerSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //binding.editTextNumber.isFocusable = true
                //binding.editTextNumber.isFocusableInTouchMode =true
                //binding.editTextNumber.requestFocus()
            }

        }

        // default focus exception spinner
        binding.editTextNumber.clearFocus()


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


        //this.binding.spinner.performClick()
//        if(position == 0){
//            this.binding.spinner.isFocusable = true
//            this.binding.spinner.isFocusableInTouchMode = true
//            this.binding.spinner.requestFocus()
//
//        }else{
//            this.binding.spinner2.isFocusable = true
//            this.binding.spinner2.isFocusableInTouchMode = true
//            this.binding.spinner2.requestFocus()
//        }


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

    // Custom class to define min and max for the edit text
    inner class MinMaxFilter() : InputFilter {
        private var intMin: Int = 0
        private var intMax: Int = 0

        // Initialized
        constructor(minValue: Int, maxValue: Int) : this() {
            this.intMin = minValue
            this.intMax = maxValue
        }

        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dStart: Int,
            dEnd: Int
        ): CharSequence? {
            try {
                val input = Integer.parseInt(dest.toString() + source.toString())
                if (isInRange(intMin, intMax, input)) {


                    return null
                } else {
                    val snack = Snackbar.make(requireView(), "最大20", Snackbar.LENGTH_LONG)
                    snack.anchorView = this@GalleryFragment.binding.editTextNumber
                    snack.show()

                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            return ""
        }

        // Check if input c is in between min a and max b and
        // returns corresponding boolean
        private fun isInRange(a: Int, b: Int, c: Int): Boolean {
            return if (b > a) c in a..b else c in b..a
        }
    }

    open class Item(val name: String, val size: Int, val count: Int)

    fun observeInput() {
//        galleryViewModel.orderData.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                //Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
//            }
//        })

        galleryViewModel.summary.observe(viewLifecycleOwner, Observer {
            it?.let {
                //Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//                val orderSummary = getString(
//                    com.example.kotlindemo.R.string.order_details,
//                    "q","w","e","1"
//
//                )

                // Create an ACTION_SEND implicit intent with order details in the intent extras
                val intent = Intent(Intent.ACTION_SEND)
                    .setType("text/plain")
                    .putExtra(Intent.EXTRA_SUBJECT, "TestMail")
                    .putExtra(Intent.EXTRA_TEXT, it)

                // Check if there's an app that can handle this intent before launching it
                //if (packageManager?.resolveActivity(intent, 0) != null) {
                // Start a new activity with the given intent (this may open the share dialog on a
                // device if multiple apps can handle this intent)
                startActivity(intent)
            }
        })


    }


}






