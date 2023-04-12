package com.example.mealapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mealapp.R
import com.example.mealapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // find the sign out button in the layout
//        val signOutButton = findViewById<Button>(R.id.)
//
//        // set a click listener on the sign out button
//        signOutButton.setOnClickListener {
//            // navigate to the loginFragment when the button is clicked
//            navController.navigate(R.id.action_signOutFragment_to_loginFragment)
//        }
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_areas,
                    R.id.navigation_categories,
                    R.id.navigation_ingredients,
                    R.id.navigation_signout
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)


            //to hide bottom navigationm in details page
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {

                    R.id.loginFragment -> {
                        supportActionBar?.setDisplayHomeAsUpEnabled(false)
                        navView.visibility = View.GONE
                    }
                    R.id.navigation_areas -> {
                        navView.visibility = View.VISIBLE

                    }
                    R.id.navigation_categories -> {
                        navView.visibility = View.VISIBLE
                    }
                    R.id.navigation_ingredients -> {
                        navView.visibility = View.VISIBLE
                    }
                    else -> {
                        navView.visibility = View.GONE
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    }
                }
            }
        }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_signout -> {
//                val navController = findNavController(R.id.nav_host_fragment_activity_main)
//                navController.navigate(R.id.action_signOutFragment_to_loginFragment)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
    //}

    override fun onSupportNavigateUp(): kotlin.Boolean {
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            return navController.navigateUp() || super.onSupportNavigateUp()
        }

//        override fun onCreateOptionsMenu(menu: Menu): Boolean {
//            menuInflater.inflate(R.menu.signout_menu, menu)
////            menu.findItem(R.id.navigation_areas).isVisible = false
////            menu.findItem(R.id.navigation_categories).isVisible = false
////            menu.findItem(R.id.navigation_ingredients).isVisible = false
//            return true
//        }
    }