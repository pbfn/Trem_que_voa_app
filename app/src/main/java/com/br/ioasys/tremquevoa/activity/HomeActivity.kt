package com.br.ioasys.tremquevoa.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.br.ioasys.tremquevoa.R
import com.br.ioasys.tremquevoa.databinding.ActivityHomeBinding
import com.br.ioasys.tremquevoa.presentation.ui.fragments.PerfilUserFragment
import com.br.ioasys.tremquevoa.presentation.ui.fragments.RegisterEventFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frame_contaier) as NavHostFragment
        val navController = navHostFragment.navController

        binding.menuBottomNavigation.setupWithNavController(navController)

        showDialog()
        replaceFragment(PerfilUserFragment())
        setBottomNavigation()
    }


    private fun setBottomNavigation() {
        binding.menuBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_perfil -> {
                    replaceFragment(PerfilUserFragment())
                }
                R.id.item_new_event -> {
                    replaceFragment(RegisterEventFragment())
                }
                else -> {
                    replaceFragment(PerfilUserFragment())
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
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.pop_up_home)
        builder.create()
        builder.show()
    }
}