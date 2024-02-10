package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    lateinit var dataList: List<MyData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {

                var dataList =response.body()?.data!!

                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                myAdapter = MyAdapter(this@MainActivity,dataList)
                myRecyclerView.adapter = myAdapter

                myAdapter.setOnItemClickListener(object :  MyAdapter.OnItemClickListener{
                    override fun OnItemClick(position: Int) {
                        val intent = Intent(this@MainActivity,SecondActivity::class.java)
                        intent.putExtra("cover",dataList[position].preview)
                        intent.putExtra("tittle",dataList[position].title)
                        startActivity(intent)
                    }

                })

                Log.d("TAG:onResponse", "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("TAG:onFailure", "onFailure: "+t.message)
            }
        })

    }

}

