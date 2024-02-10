package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val dataList: List<Data>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    lateinit var myListener : OnItemClickListener

    interface OnItemClickListener{
        fun OnItemClick(position: Int)
    }

    fun setOnItemClickListener(Listener: OnItemClickListener){
        myListener = Listener
    }

    class MyViewHolder(itemView:View,Listener: OnItemClickListener):RecyclerView.ViewHolder(itemView) {

//        val search : TextInputEditText
        val tittle : TextView
        val image : ImageView
        val play : ImageView
        val pause : ImageView
        val stop : ImageView

        init {
//            search = itemView.findViewById(R.id.searchBar)
            tittle = itemView.findViewById(R.id.songTittle)
            image = itemView.findViewById(R.id.songImage)
            play = itemView.findViewById(R.id.playBtn)
            pause = itemView.findViewById(R.id.pauseBtn)
            stop = itemView.findViewById(R.id.stopBtn)

            itemView.setOnClickListener {
                Listener.OnItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem,parent,false)
        return MyViewHolder(itemView,myListener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = dataList[position]

        Picasso.get().load(currentData.album.cover).into(holder.image);

        holder.tittle.text = currentData.title

        val mediaPLayer = MediaPlayer.create(context,currentData.preview.toUri())

        holder.play.setOnClickListener {
            mediaPLayer.start()
        }
        holder.pause.setOnClickListener {
            mediaPLayer.pause()
        }
        holder.stop.setOnClickListener {
            mediaPLayer.stop()
            mediaPLayer.reset()
        }

    }
}