package mx.unam.tic.docencia.volleyserviceexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import mx.unam.tic.docencia.volleyserviceexample.models.News
import mx.unam.tic.docencia.volleyserviceexample.services.APIController
import mx.unam.tic.docencia.volleyserviceexample.services.ServiceVolley
import mx.unam.tic.docencia.volleyserviceexample.utils.NetworkConnection
import mx.unam.tic.docencia.webserviceexample.adapters.ArticleAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val service = ServiceVolley()
        val apiController=APIController(service)

        initView()
        if (NetworkConnection(this).isNetworkConnected()){
            apiController.get("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a39dab7ee1cf4d468f90a8f30eb1fcb2", null,{ response ->
                val articlesList=Gson().fromJson<News>(response.toString(),News::class.java)
                setArticleList(articlesList)
                movieProgressBar.visibility=View.GONE
            })
        }else{
            Toast.makeText(this,"No está conectado", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        moviesRecyclerView.layoutManager = layoutManager
        moviesRecyclerView.setHasFixedSize(true)
    }

    fun setArticleList(news: News){
        val articleAdapter= ArticleAdapter(this,news.articles)
        articleAdapter.setOnArticleClickListener { title ->
            val detailIntent= Intent(this,DetailActivity::class.java)
            detailIntent.putExtra("Title",title)
            startActivity(detailIntent)
        }
        moviesRecyclerView.adapter=articleAdapter
    }

}
