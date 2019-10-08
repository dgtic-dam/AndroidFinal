package mx.unam.tic.docencia.webserviceexample.listener

import mx.unam.tic.docencia.volleyserviceexample.models.Article
import mx.unam.tic.docencia.volleyserviceexample.models.News

interface OnSetArticleListener {

    fun onSetArticle(article: Article)
//  fun onSetArticle(news: News)
}