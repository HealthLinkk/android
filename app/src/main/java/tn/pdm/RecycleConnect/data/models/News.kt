package tn.pdm.RecycleConnect.data.models

data class News(
    val id: String,
    val title: String,
    val description: String,
    val newsPhoto: String,
    val source: String,
    val date: String,

)
//constructor for only title, newsPhoto, source, date
{
    constructor(
        title: String,
        discription: String,
        newsPhoto: String,
        source: String,
        date: String
    ) : this(
        "",
        title,
        discription,
        newsPhoto,
        source,
        ""
    )
}