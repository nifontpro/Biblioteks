package ru.nifontbus.testmvp.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.nifontbus.testmvp.databinding.FragmentDetailsBinding
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.models.utils.images.GlideImageLoader
import ru.nifontbus.testmvp.presentation.details.adapter.ReposRvAdapter
import ru.nifontbus.testmvp.presentation.screens.BackButtonListener

class DetailsFragment : MvpAppCompatFragment(), DetailsView,
    BackButtonListener {

    private val presenter by moxyPresenter { DetailsPresenter() }
    private val adapter by lazy { ReposRvAdapter(presenter.reposListPresenter) }

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

    override fun showDetailsUser(detailsUser: GithubUser) {
        binding.tvLogin.text = detailsUser.login
        GlideImageLoader().loadInto(detailsUser.avatarUrl, binding.ivAvatar)
    }

    override fun init() {
        binding.rvRepos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRepos.adapter = adapter
        binding.btnClose.setOnClickListener {
            presenter.backPressed()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }
}