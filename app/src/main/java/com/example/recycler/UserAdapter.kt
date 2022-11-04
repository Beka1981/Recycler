package com.example.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var arrayList: ArrayList<User> = ArrayList()
    var viewBinderHelper: ViewBinderHelper = ViewBinderHelper()

    fun setData(arrayList: List<User>) {
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        //val user: User = arrayList[position] ?: return
        viewBinderHelper.setOpenOnlyOne(true)
        viewBinderHelper.bind(holder.swipeRevealLayout, arrayList[position].Name)
        viewBinderHelper.closeLayout(arrayList[position].Name)
        holder.bindData(arrayList[position])
        /*   holder.title.text = user.Name
           holder.delete.setOnClickListener {
               arrayList.removeAt(holder.adapterPosition)
               notifyItemRemoved(holder.adapterPosition)
           }*/
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(user: User) {
            title.text = user.Name

            delete.setOnClickListener {
                onItemClickListener?.onItemClicked(user)
            }
        }

        var swipeRevealLayout: SwipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout)
        var delete: TextView = itemView.findViewById(R.id.tvDelete)
        var edit: TextView = itemView.findViewById(R.id.tvEdit)
        var title: TextView = itemView.findViewById(R.id.tv_username)

    }

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun interface OnItemClickListener {
        fun onItemClicked(data: User)
    }

}



