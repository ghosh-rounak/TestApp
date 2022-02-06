package com.rounak.testapp.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rounak.testapp.R
import com.rounak.testapp.data.db.entities.User
import com.rounak.testapp.databinding.RowUserBinding
import javax.inject.Inject


class UserListAdapter @Inject constructor()
    : RecyclerView.Adapter<UserViewHolder>() {
    internal lateinit var clickListener:(User,Int)->Unit

    private val differCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : RowUserBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_user,parent,false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(differ.currentList[position],clickListener)
    }

}

class UserViewHolder(private val binding: RowUserBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(user: User,clickListener:(User,Int)->Unit){
        binding.userItem=user

        binding.rowUserLayout.setOnClickListener{
            clickListener(user,bindingAdapterPosition)
        }

        binding.executePendingBindings()
    }
}