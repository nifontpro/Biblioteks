package ru.nifontbus.testmvp.presentation.repository.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nifontbus.testmvp.databinding.ItemRepoBinding

class ReposRvAdapter(
    private val presenter: IRepoListPresenter,
) : RecyclerView.Adapter<ReposRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {

        override var pos: Int = -1

        override fun showName(name: String) {
            vb.tvName.text = name
        }
    }
}