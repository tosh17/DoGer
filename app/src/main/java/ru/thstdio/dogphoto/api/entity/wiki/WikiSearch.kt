package ru.thstdio.dogphoto.api.entity.wiki

class WikiSearch {
    var ns: Long = 0
    lateinit var title: String
    var pageid: Long = 0
    var size: Long = 0
    var wordcount: Long = 0
    lateinit var snippet: String
    lateinit var timestamp: String
}