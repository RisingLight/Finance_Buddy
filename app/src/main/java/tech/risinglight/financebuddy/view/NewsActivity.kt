package tech.risinglight.financebuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_news.*
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.adapter.NewsRecyclerAdapter
import tech.risinglight.financebuddy.api.RetrofitClient
import tech.risinglight.financebuddy.model.Newsmodel


class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        newsRV.layoutManager = LinearLayoutManager(applicationContext)
        getArticle()
    }

    private fun getArticle() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitClient: RetrofitClient = retrofit.create(RetrofitClient::class.java)
        val call = retrofitClient.getArticle()
        call.enqueue(object : Callback<Newsmodel> {
            override fun onFailure(call: Call<Newsmodel>, t: Throwable) {
                Log.d("Response", t.message.toString())
            }


            override fun onResponse(call: Call<Newsmodel>, response: Response<Newsmodel>) {
                if (response.isSuccessful) {
                    print(response.body()!!.articles.toMutableList().size)
                    val adapter = NewsRecyclerAdapter(response.body()!!.articles.toMutableList())
                    newsRV.adapter = adapter

                }
            }
        })
    }

}
