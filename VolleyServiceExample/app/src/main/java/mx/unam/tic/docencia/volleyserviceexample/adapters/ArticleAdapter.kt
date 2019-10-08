package mx.unam.tic.docencia.webserviceexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import mx.unam.tic.docencia.volleyserviceexample.R
import mx.unam.tic.docencia.volleyserviceexample.models.Article


class ArticleAdapter (val context: Context,val search:List<Article>):
    RecyclerView.Adapter<ArticleViewHolder>(){

    private var onArticleClickListener: (String) -> Unit={}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(
            context
        ).inflate(R.layout.item_movie,parent,false))
    }

    override fun getItemCount(): Int {
        return search.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        Picasso.get().load(search[position].urlToImage).into(holder.posterImageView)
        //holder.posterImageView.setImageResource(R.mipmap.ic_launcher)
        holder.titleTextView.text=search[position].title
        holder.yearTextView.text=search[position].author
        holder.setArticle(search[position].title)
        holder.setOnArticleClickListener(onArticleClickListener)
    }

    fun setOnArticleClickListener(listener:(String)->Unit){
        this.onArticleClickListener=listener
    }
}

class ArticleViewHolder(view: View):RecyclerView.ViewHolder(view){
    val posterImageView=view.posterImageView
    val titleTextView=view.titleTextView
    val yearTextView=view.yearTextView
    private lateinit var title:String

    fun setArticle(title:String){
        this.title=title
    }
    fun setOnArticleClickListener(listener: (String) -> Unit){
        if (listener!=null)
            itemView.setOnClickListener{
                listener(this.title)
            }
    }
}