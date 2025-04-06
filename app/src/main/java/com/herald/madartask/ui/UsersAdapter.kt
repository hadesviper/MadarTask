package com.herald.madartask.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.herald.madartask.R
import com.herald.madartask.data.UserModel
import com.herald.madartask.databinding.RecyclerUsersDataBinding

class UsersAdapter(private val userList: List<UserModel>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: RecyclerUsersDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerUsersDataBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val context = holder.itemView.context
        userList[position].apply {
            holder.binding.run {
                tvName.text   = assignTextView(context, R.string.full_name, name)
                tvAge.text    = assignTextView(context, R.string.age, age.toString())
                tvJob.text    = assignTextView(context, R.string.job_title, jobTitle)
                tvGender.text = assignTextView(context, R.string.gender, gender)
            }
        }
    }
    override fun getItemCount(): Int = userList.size

    private fun assignTextView(context: Context, id: Int, text: String): String =
        buildString {
            append(context.resources.getString(id))
            append(": ")
            append(text)
        }
}