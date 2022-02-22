package com.example.kotlindemo.ui.home

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.adapter.ItemAdapter
import com.example.kotlindemo.data.Datasource
import com.example.kotlindemo.R
import com.example.kotlindemo.data.SettingsDataStore
import com.example.kotlindemo.data.dataStore
import com.example.kotlindemo.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private var isLinearLayoutManager = true
    private lateinit var recyclerView: RecyclerView
    private val myDataset = Datasource().loadAffirmations()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var layoutDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        //val myDataset = Datasource().loadAffirmations()
        // val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        //recyclerView.adapter = ItemAdapter(view.context,myDataset, homeViewModel)
        recyclerView.setHasFixedSize(true)
        layoutDataStore = SettingsDataStore(requireContext().dataStore)

        layoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner, {  })
        layoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner, { value ->
            isLinearLayoutManager = value
            chooseLayout()
            // Redraw the menu
            activity?.invalidateOptionsMenu()
        })
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.home, menu)


        val layoutButton = menu.findItem(R.id.action_switch_layout)
        // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
        setIcon(layoutButton)

    }

    /**
     * Sets the LayoutManager for the [RecyclerView] based on the desired orientation of the list.
     */
    private fun chooseLayout() {
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        if (isLinearLayoutManager) {
            recyclerView.adapter = ItemAdapter(requireContext(),myDataset, homeViewModel, isLinearLayoutManager)
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.adapter = ItemAdapter(requireContext(),myDataset, homeViewModel, isLinearLayoutManager)
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        }
        //recyclerView.adapter = ItemAdapter(context, dataset = )
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        // Set the drawable for the menu icon based on which LayoutManager is currently in use

        // An if-clause can be used on the right side of an assignment if all paths return a value.
        // The following code is equivalent to
        // if (isLinearLayoutManager)
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.linear_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.grid_layout)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager

                // Launch a coroutine and write the layout setting in the preference Datastore
                lifecycleScope.launch {
                    layoutDataStore.saveLayoutToPreferencesStore(isLinearLayoutManager, requireContext())
                }

                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling

            // when clauses require that all possible paths be accounted for explicitly,
            //  for instance both the true and false cases if the value is a Boolean,
            //  or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }
    }
}