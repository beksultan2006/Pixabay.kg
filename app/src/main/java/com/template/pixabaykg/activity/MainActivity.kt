package com.template.pixabaykg.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.template.pixabaykg.adapter.ImageAdapter
import com.template.pixabaykg.api.RetrofitService
import com.template.pixabaykg.databinding.ActivityMainBinding
import com.template.pixabaykg.model.PixabayModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var adapter = ImageAdapter(arrayListOf())
    var page = 1
    var isAddToList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            button.setOnClickListener {
                isAddToList = false
                page = 1
                getImage()
            }
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    val itemCount = layoutManager!!.itemCount
                    val lastVisibleItemPosition =
                        layoutManager.findLastCompletelyVisibleItemPosition()
                    if (lastVisibleItemPosition == itemCount - 1) {
                        isAddToList = true
                        ++page
                        getImage()
                    }
                }
            })
        }
    }

    private fun ActivityMainBinding.getImage() {
        RetrofitService.api.importPictures(keyWord = editText.text.toString(), page = page)
            .enqueue(object : Callback<PixabayModel> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<PixabayModel>, response: Response<PixabayModel>
                ) {
                    if (response.isSuccessful) {
                        if (!isAddToList) {
                            adapter.list.clear()
                        }
                        adapter.list.addAll(response.body()?.hits!!)
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    Log.e("ololo", t.message.toString())
                }
            })
    }
}