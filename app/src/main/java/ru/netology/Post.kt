package ru.netology

data class Post(
    var likeCount: Int,
    var shareCount: Int,
    var viewCount: Int,
    var liked: Boolean = false,
    var shared: Boolean =false
)
