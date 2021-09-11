package ru.nifontbus.testmvp

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.nifontbus.testmvp.databinding.ActivityMainBinding
import ru.nifontbus.testmvp.models.GithubUsersRepo
import ru.nifontbus.testmvp.views.ui.UsersRvAdapter

class MainActivity : MvpAppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }

    private val adapter by lazy { UsersRvAdapter(presenter.usersListPresenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }
}