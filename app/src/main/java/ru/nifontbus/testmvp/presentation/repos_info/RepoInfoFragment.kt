package ru.nifontbus.testmvp.presentation.repos_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.databinding.FragmentRepoInfoBinding
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.models.utils.images.GlideImageLoader
import ru.nifontbus.testmvp.models.utils.images.IImageLoader
import ru.nifontbus.testmvp.presentation.repository.RepositoryFragment
import ru.nifontbus.testmvp.presentation.screens.BackButtonListener
import javax.inject.Inject

class RepoInfoFragment : MvpAppCompatFragment(), RepoInfoView, BackButtonListener {

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(RepositoryFragment.USER_ARG) as GithubUser
        val repository = arguments?.getParcelable<GithubRepository>(REPOS_ARG) as GithubRepository
        RepoInfoPresenter(user, repository).apply {
            App.instance.repositorySubcomponent?.inject(this)
        }
    }

    private var _binding: FragmentRepoInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        binding.btnClose.setOnClickListener {
            presenter.backPressed()
        }
    }

    override fun showDetailsUser(detailsUser: GithubUser) {
        binding.tvLogin.text = detailsUser.login
        GlideImageLoader().loadInto(detailsUser.avatarUrl, binding.ivAvatar)
    }

    override fun showRepoInfo(repo: GithubRepository) {
        binding.tvRepositoryName.text = repo.name.orEmpty()
        binding.tvForkCount.text = repo.forksCount.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    companion object {
        fun newInstance(user: GithubUser, repository: GithubRepository) =
            RepoInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(RepositoryFragment.USER_ARG, user)
                    putParcelable(REPOS_ARG, repository)
                }
            }

        private const val REPOS_ARG = "repos"
    }
}