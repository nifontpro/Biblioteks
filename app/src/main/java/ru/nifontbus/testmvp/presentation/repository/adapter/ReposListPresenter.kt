package ru.nifontbus.testmvp.presentation.repository.adapter

import ru.nifontbus.testmvp.models.data.GithubRepository

class ReposListPresenter : IRepoListPresenter {

    val repos = mutableListOf<GithubRepository>()

    override var itemClickListener: ((RepoItemView) -> Unit)? = null

    override fun bindView(view: RepoItemView) {
        val repo = repos[view.pos]
        repo.name?.let { view.showName(it) }
    }

    override fun getCount(): Int = repos.size
}
