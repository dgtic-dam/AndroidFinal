package mx.unam.tic.docencia.volleyserviceexample.models


import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("abstract")
    val `abstract`: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("keyword")
    val keyword: String,
    @SerializedName("notice_date")
    val noticeDate: String,
    @SerializedName("photo_credit")
    val photoCredit: String,
    @SerializedName("photo_file_name")
    val photoFileName: String,
    @SerializedName("published")
    val published: String,
    @SerializedName("title")
    val title: String
)