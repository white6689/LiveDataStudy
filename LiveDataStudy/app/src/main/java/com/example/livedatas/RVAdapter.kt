package com.example.livedatas

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.livedatas.database.Chat
import com.example.livedatas.databinding.ItemChatListDefaultBinding

class RVAdapter(): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private val chatList=ArrayList<Chat>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ItemChatListDefaultBinding= ItemChatListDefaultBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount()=chatList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(chats:List<Chat>){
        Log.d("notificationService", "addItems implemented")
        Log.d("notificationService", chats.toString())
        chatList.clear()
        chatList.addAll(chats as ArrayList)

        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding:ItemChatListDefaultBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(chat:Chat){
            binding.itemName.text=chat.name
            binding.itemText.text=chat.content
        }
    }
}