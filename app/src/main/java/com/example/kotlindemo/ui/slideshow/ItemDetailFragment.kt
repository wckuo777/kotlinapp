package com.example.kotlindemo.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlindemo.InventoryApplication
import com.example.kotlindemo.InventoryViewModel
import com.example.kotlindemo.InventoryViewModelFactory
import com.example.kotlindemo.R
import com.example.kotlindemo.data.Item
import com.example.kotlindemo.data.getFormattedPrice
import com.example.kotlindemo.databinding.FragmentItemDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ItemDetailFragment : Fragment() {
    private val navigationArgs: ItemDetailFragmentArgs by navArgs()
    lateinit var item: Item

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //activate hide optionMenu (top right menu)
        setHasOptionsMenu(true)
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Binds views with the passed in item data.
     */
    private fun bind(item: Item) {
        binding.apply {
            itemName.text = item.itemName
            itemPrice.text = item.getFormattedPrice()
            itemCount.text = item.quantityInStock.toString()
            sellItem.isEnabled = viewModel.isStockAvailable(item)
            sellItem.setOnClickListener { viewModel.sellItem(item) }
            deleteItem.setOnClickListener { showConfirmationDialog() }
            editItem.setOnClickListener { editItem() }
        }
    }

    /**
     * Navigate to the Edit item screen.
     */
    private fun editItem() {
        val action = ItemDetailFragmentDirections.actionItemDetailFragmentToAddItemFragment(
            getString(R.string.edit_fragment_title),
            item.id
        )
        this.findNavController().navigate(action)
    }

    /**
     * Displays an alert dialog to get the user's confirmation before deleting the item.
     */
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteItem()
            }
            .show()
    }

    /**
     * Deletes the current item and navigates to the list fragment.
     */
    private fun deleteItem() {
        viewModel.deleteItem(item)
        findNavController().navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.itemId
        // Retrieve the item details using the itemId.
        // Attach an observer on the data (instead of polling for changes) and only update the
        // the UI when the data actually changes.
        viewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedItem ->
            item = selectedItem
            bind(item)
        }
    }

    // hide optionmenu (top right menu)
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    /**
     * Called when fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}