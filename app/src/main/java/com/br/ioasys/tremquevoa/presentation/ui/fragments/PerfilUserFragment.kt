package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.br.ioasys.tremquevoa.databinding.FragmentPerfilUserBinding
import com.br.ioasys.tremquevoa.domain.model.Interests
import com.br.ioasys.tremquevoa.domain.model.User
import com.br.ioasys.tremquevoa.presentation.adapters.AdapterInterestsPerfil
import com.br.ioasys.tremquevoa.presentation.viewmodel.PerfilViewModel
import com.br.ioasys.tremquevoa.util.ViewState
import org.koin.androidx.viewmodel.ext.android.getViewModel


class PerfilUserFragment : Fragment() {

    private var _binding: FragmentPerfilUserBinding? = null
    private val binding: FragmentPerfilUserBinding get() = _binding!!

    private lateinit var adapterInterests: AdapterInterestsPerfil
    private lateinit var user: User

    private val perfilViewModel: PerfilViewModel by lazy {
        getViewModel()
    }
    private var listInterests: List<Interests> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPerfilUserBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
        setListeners()
    }

    private fun setCardLayout() {
        binding.apply {
            cardEvents.apply {
                title.text = "2"
                subtitle.text = "Eventos"
            }
            cardNivel.apply {
                title.visibility = View.GONE
                icon.visibility = View.VISIBLE
                subtitle.text = "Nível 2"
            }
            cardMinutes.apply {
                title.text = "140"
                subtitle.text = "Minutos"
            }

        }
    }

    private fun setRecyclerView() {
        val lst = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        // val layout = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        adapterInterests = AdapterInterestsPerfil()
        //adapterInterests.differ.submitList(MockInteresses.listaInteresses)
        binding.recyclerViewInterests.apply {
            adapter = adapterInterests
            layoutManager = lst
        }
    }

    private fun setListeners() {
        binding.apply {
            buttonEditAboutMe.setOnClickListener {
                editAboutMe.isEnabled = true
            }

            editAboutMe.doAfterTextChanged {
                perfilViewModel.updateAboutMe(token = user.token, aboutMe = it.toString())
            }
        }
    }

    private fun addObserver() {
        perfilViewModel.userLocal.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    user = response.data
                    setLayout()
                    setCardLayout()
                    setRecyclerView()
                }

                is ViewState.Error -> {

                }
                else -> {

                }
            }
        }
        perfilViewModel.interests.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    listInterests = response.data
                    adapterInterests.differ.submitList(listInterests)
                }

                is ViewState.Error -> {

                }
                else -> {

                }
            }

        }

        perfilViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    val updatedUser = response.data
                    updatedUser.token = user.token
                    updatedUser.refreshToken = user.refreshToken
                    updatedUser.maintainLogin = user.maintainLogin
                    perfilViewModel.updateUserLocal(updatedUser)
                }

                is ViewState.Error -> {

                }
                else -> {

                }
            }
        }

    }

    private fun setLayout(){
        binding.apply {
            if(!user.aboutMe.isNullOrEmpty()){
                editAboutMe.text = Editable.Factory.getInstance().newEditable(user.aboutMe)
            }
            textViewName.text = user.name
        }
    }

}