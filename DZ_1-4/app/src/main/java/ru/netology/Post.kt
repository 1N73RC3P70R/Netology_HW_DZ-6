package ru.netology

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likeCount: Int,
    var shareCount: Int,
    var viewCount: Int,
    var liked: Boolean = false,
    var shared: Boolean = false
)
