package ru.nifontbus.testmvp.models.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepository(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forksUrl: String? = null,
    @Expose @SerializedName("forks") val forksCount: Int = 0,
) : Parcelable