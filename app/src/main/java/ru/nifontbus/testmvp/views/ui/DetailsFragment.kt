package ru.nifontbus.testmvp.views.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.databinding.FragmentDetailsBinding
import ru.nifontbus.testmvp.models.DetailsUserRepo
import ru.nifontbus.testmvp.models.GithubUser
import ru.nifontbus.testmvp.presentation.DetailsPresenter
import ru.nifontbus.testmvp.views.BackButtonListener
import ru.nifontbus.testmvp.views.DetailsView

class DetailsFragment(val login: String) : MvpAppCompatFragment(), DetailsView, BackButtonListener {

    private val presenter by moxyPresenter {
        DetailsPresenter(DetailsUserRepo(), App.instance.router)
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init(detailsUser: GithubUser) {
        binding.tvLogin.text = login

        binding.btnClose.setOnClickListener {
            presenter.backPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    companion object {
        fun newInstance(login: String) = DetailsFragment(login)
    }
}