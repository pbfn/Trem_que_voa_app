package com.br.ioasys.tremquevoa.activity

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.ActivityHomeBinding
import com.br.ioasys.tremquevoa.presentation.ui.fragments.HomeFragment
import com.br.ioasys.tremquevoa.presentation.ui.fragments.PerfilUserFragment
import com.br.ioasys.tremquevoa.presentation.ui.fragments.RegisterEventFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private lateinit var customAlertDialogView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frame_contaier) as NavHostFragment
        val navController = navHostFragment.navController

        binding.menuBottomNavigation.setupWithNavController(navController)
        customAlertDialogView = LayoutInflater.from(this)
            .inflate(R.layout.pop_up_home, null, false)

        showDialog()
        replaceFragment(HomeFragment())
        setBottomNavigation()
    }


    private fun setBottomNavigation() {
        binding.menuBottomNavigation.itemIconTintList = null
        binding.menuBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHome -> {
                    replaceFragment(HomeFragment())
                }
                R.id.menuPerfil -> {
                    replaceFragment(PerfilUserFragment())
                }
                R.id.menuNewEvent -> {
                    replaceFragment(RegisterEventFragment())
                }
                R.id.menuCalendar -> {

                }
                R.id.menuSaves -> {

                }
            }

            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_contaier, fragment)
            .addToBackStack("Fragment").commit()
    }

    private fun showDialog() {
        val dialog =
            MaterialAlertDialogBuilder(
                this,
                R.style.MaterialAlertDialog_Rounded
            )
        dialog.setView(customAlertDialogView)
        val text =
            customAlertDialogView.findViewById(R.id.textViewMotivationalMessage) as AppCompatTextView
        dialog.setPositiveButtonIcon(ContextCompat.getDrawable(this,R.drawable.ic_error))
//        dialog.setPositiveButton(
//            "Confirmar",
//            DialogInterface.OnClickListener { dialogInterface, i ->
//                Unit
//            })
        dialog.create()
        dialog.show()
    }
}