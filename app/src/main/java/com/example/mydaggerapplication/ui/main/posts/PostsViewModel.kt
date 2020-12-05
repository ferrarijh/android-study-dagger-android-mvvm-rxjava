package com.example.mydaggerapplication.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.mydaggerapplication.model.Post
import com.example.mydaggerapplication.network.MainApi
import com.example.mydaggerapplication.network.Resource
import com.example.mydaggerapplication.network.SessionManager
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    val mainApi: MainApi,
    val sessionManager: SessionManager
): ViewModel() {

    private var _posts: MediatorLiveData<Resource<List<Post>>>? = null
    private val posts
        get() = _posts!!

    fun getPosts(): LiveData<Resource<List<Post>>> {
        if (_posts == null) {
            _posts = MediatorLiveData()
            posts.value = Resource.Loading()

            val userId = sessionManager.cachedUser.value!!.data!!.id    //don't do this on production ;)

            val source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPosts(userId)
                            .onErrorReturn {
                                it.printStackTrace()
                                listOf()
                            }
                            .map {
                                if (it.isEmpty())
                                    Resource.Error("Something went wrong :(")
                                else
                                    Resource.Success(it)
                            }
                            .subscribeOn(Schedulers.io())
            )

            posts.addSource(source) {
                posts.value = it
                posts.removeSource(source)
            }
        }
        return posts
    }
}