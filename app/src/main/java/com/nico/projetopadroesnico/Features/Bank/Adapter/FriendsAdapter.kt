package com.nico.projetopadroesnico.Features.Bank.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nico.projetopadroesnico.Features.Bank.Model.User
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * Created by sunil on 5/31/2017.
 */
class FriendsAdapter(val callback: onItemClickListener, private val context: Context, private val friendsList: MutableList<User>) : RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): FriendViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false)
        return FriendViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FriendViewHolder?, position: Int) {
        var friends = friendsList[position]

        val view = holder!!.itemView
        with(view) {
            name.text = friends.name
            email.text = friends.email
            delete.setOnClickListener {
                friendsList.remove(friends)
                notifyItemRemoved(position)
                callback.itemRemoveClick(friends)
            }
            relative.setOnClickListener {
                callback.itemDetail(friends)
            }
        }
    }

    override fun getItemCount(): Int {
        return friendsList.size
    }

    inner class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface onItemClickListener {
        fun itemRemoveClick(user: User)
        fun itemDetail(user : User)
    }
}