package com.example.mydaggerapplication.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydaggerapplication.databinding.FragmentPostsBinding
import com.example.mydaggerapplication.di.factory.ViewModelFactory
import com.example.mydaggerapplication.model.Post
import com.example.mydaggerapplication.network.Resource
import com.example.mydaggerapplication.ui.dialog.LoadingDialog
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment: DaggerFragment() {
    companion object{
        const val TAG = "PostsFragment"
    }

    @Inject lateinit var factory: ViewModelFactory
    @Inject lateinit var pAdapter: PostsAdapter
    @Inject lateinit var linearLayoutManager: LinearLayoutManager
    private val loadingDialog by lazy{ LoadingDialog(requireContext()) }

    private var _vBinding: FragmentPostsBinding? = null
    private val vBinding
        get() = _vBinding!!

    private val pViewModel by lazy{ViewModelProvider(this, factory).get(PostsViewModel::class.java)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _vBinding = FragmentPostsBinding.inflate(inflater, container, false)
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setObservers()
    }

    private fun setAdapter(){
        vBinding.rvPosts.apply{
            adapter = pAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun setObservers(){
        pViewModel.getPosts().apply{
            removeObservers(viewLifecycleOwner)
            observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> loadingDialog.show()
                    is Resource.Success -> {
                        pAdapter.posts = it.data!!
                        loadingDialog.dismiss()
                        Log.d(TAG, "list size: ${it.data.size}")
                        Log.d(TAG, "adapter data size: ${pAdapter.posts.size}")
                    }
                    is Resource.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}