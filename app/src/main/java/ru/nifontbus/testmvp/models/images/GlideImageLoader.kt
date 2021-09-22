package ru.nifontbus.testmvp.models.images

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.nifontbus.testmvp.models.images.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}
