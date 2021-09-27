package ru.nifontbus.testmvp.presentation.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.databinding.ItemUserBinding
import ru.nifontbus.testmvp.models.utils.images.IImageLoader
import ru.nifontbus.testmvp.presentation.IUserListPresenter
import javax.inject.Inject

class UsersRvAdapter(
    private val presenter: IUserListPresenter,
) : RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
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

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos: Int = -1

        override fun showLogin(login: String) {
            vb.tvLogin.text = login
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, vb.ivAvatar)
        }
    }
}