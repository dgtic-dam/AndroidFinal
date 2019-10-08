package mx.unam.tic.docencia.volleyserviceexample


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import mx.unam.tic.docencia.volleyserviceexample.models.Article
import mx.unam.tic.docencia.volleyserviceexample.models.News
import mx.unam.tic.docencia.volleyserviceexample.services.APIController
import mx.unam.tic.docencia.volleyserviceexample.services.ServiceVolley
import mx.unam.tic.docencia.webserviceexample.listener.OnSetArticleListener

class DetailActivity : AppCompatActivity(), OnSetArticleListener {


    override fun onSetArticle(article: Article) {
        titleTextView.text=article.author
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val title=intent.getStringExtra("Title")
        val service = ServiceVolley()
        val apiController= APIController(service)
        apiController.get("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=a39dab7ee1cf4d468f90a8f30eb1fcb2&q="+title,null, { response ->
            val ar =Gson().fromJson<News>(response.toString(),News::class.java)
           // titleTextView.text=ar.status+ar.totalResults.toString()
           titleTextView.text=ar.articles.last().title
            contentTextView.text=ar.articles.last().content

            Picasso.get().load(ar.articles.last().urlToImage).into(articleImageView)
        })
    }

}
