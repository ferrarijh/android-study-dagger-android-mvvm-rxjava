package com.example.mydaggerapplication.ui.main.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydaggerapplication.R
import com.example.mydaggerapplication.model.Post
import javax.inject.Inject

class PostsAdapter @Inject constructor() : RecyclerView.Adapter<PostsAdapter.PostViewHolder>(){

    var posts = listOf<Post>()
        set(newPosts){
            field = newPosts
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size


    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(post: Post){
            itemView.findViewById<TextView>(R.id.tv_post).text = post.body
        }
    }

}