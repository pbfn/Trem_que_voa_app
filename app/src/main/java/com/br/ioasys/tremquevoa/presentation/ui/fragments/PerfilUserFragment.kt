package com.br.ioasys.tremquevoa.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        setCardLayout()
        setRecyclerView()
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


    private fun addObserver() {
        perfilViewModel.user.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    user = response.data
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
    }


}