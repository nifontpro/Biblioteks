package ru.nifontbus.testmvp.presentation.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.databinding.FragmentUsersBinding
import ru.nifontbus.testmvp.di.component.UserSubcomponent
import ru.nifontbus.testmvp.presentation.screens.BackButtonListener
import ru.nifontbus.testmvp.presentation.users.adapter.UsersRvAdapter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var userSubcomponent: UserSubcomponent? = null

    private val presenter by moxyPresenter {
        UsersPresenter().apply {
            userSubcomponent = App.instance.initUserSubcomponent()
            userSubcomponent?.inject(this)
        }
    }
    private val adapter by lazy { UsersRvAdapter(presenter.usersListPresenter) }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = adapter
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
        fun newInstance() = UsersFragment()
    }
}