package tech.risinglight.financebuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import retrofit2.Callback
import tech.risinglight.financebuddy.api.RetrofitClient
import tech.risinglight.financebuddy.model.Article


class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



    }

    private fun getActivity(id: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitClient: RetrofitClient = retrofit.create(RetrofitClient::class.java)
        val call = retrofitClient.getArticle()
        call.enqueue(object : Callback<Article> {
            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.d("Response", "Error")
            }

            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                if (response.isSuccessful) {
                    val videoListAdapter =
                        VideoListRVAdapter(response.body()!!.videoList!!.toMutableList())
                    recyclerView.adapter = videoListAdapter

                }
            }
        })
    }

}
