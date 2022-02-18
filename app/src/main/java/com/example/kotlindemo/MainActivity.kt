package com.example.kotlindemo

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.databinding.ActivityMainBinding
import android.R.menu
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.adapter.ItemAdapter
import com.example.kotlindemo.data.Datasource
import com.example.kotlindemo.R
import com.example.kotlindemo.ui.gallery.GalleryFragment
import com.example.kotlindemo.ui.gallery.GalleryViewModel
import com.google.android.material.appbar.AppBarLayout


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val sharedViewModel: GalleryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get navGraph childFragment if activate
//        val navHostFragment: Fragment? =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//        navHostFragment?.childFragmentManager?.fragments?.get(0)



        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->



//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.nav_friendWebSites) {
                Log.d("tag", "onCreate: ")
                binding.appBarMain.fab.hide()
                supportActionBar?.hide()
            } else if(destination.id == R.id.nav_home){
                binding.appBarMain.fab.hide()
            }
            else if(destination.id == R.id.nav_gallery){
                binding.appBarMain.fab.show()
                // stop toolbar to scroll
                binding.appBarMain.toolbar.updateLayoutParams<AppBarLayout.LayoutParams> {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
                }

                binding.appBarMain.fab.setOnClickListener { view ->

                    val testD = sharedViewModel.orderData.value
                    testD?.get(0)?.let { Log.d("abctest", it.name) }
                    val textC = sharedViewModel.text.value
                    val orderSummary = getString(
                        R.string.order_details,
                        "q","w","e","1"

                    )

                    // Create an ACTION_SEND implicit intent with order details in the intent extras
                    val intent = Intent(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_SUBJECT, "TestMail")
                        .putExtra(Intent.EXTRA_TEXT, orderSummary)

                    // Check if there's an app that can handle this intent before launching it
                    //if (packageManager?.resolveActivity(intent, 0) != null) {
                        // Start a new activity with the given intent (this may open the share dialog on a
                        // device if multiple apps can handle this intent)
                        startActivity(intent)
                    //}
                    //bad: just reference
//                  val hostFragment1: Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
//                    val galleryFragment: GalleryFragment = hostFragment1?.childFragmentManager?.findFragmentById(R.id.nav_gallery) as GalleryFragment
//                    galleryFragment.sendOrderTest()

                }
            }
            else {
                binding.appBarMain.fab.show()

            }
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_friendWebSites
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        if (menu is MenuBuilder) {
            val m: MenuBuilder = menu as MenuBuilder
            m.setOptionalIconsVisible(true)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}