package ru.nifontbus.testmvp.models.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String = "",
    @Expose val reposUrl: String? = null
) : Parcelable