package mx.unam.tic.docencia.webserviceexample.listener

import mx.unam.tic.docencia.volleyserviceexample.models.News

interface OnSetArticleListListener {

    fun onSetArticleList(news: News)

}