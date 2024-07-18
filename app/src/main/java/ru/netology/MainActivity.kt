package ru.netology

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.netology.databinding.MainPageLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainPageLayoutBinding
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainPageLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        post = Post(
            likeCount = 999_999,
            shareCount = 999,
            viewCount = 500
        )

        updateUI()

        binding.root.setOnClickListener {
            Log.d("Тест", "Нажатие!")
        }

        binding.likeImageView.setOnClickListener {
            Log.d("Тест", "Понравилась запись!")
            post.liked = !post.liked
            post.likeCount += if (post.liked) 1 else -1
            updateUI()
        }

        binding.shareImageView.setOnClickListener {
            Log.d("Тест", "Поделился!")
            post.shared = !post.shared
            post.shareCount += if (post.shared) 1 else -1
            updateUI()
        }
    }

    private fun updateUI() {
        with(binding) {
            likeCountTextView.text = formatCount(post.likeCount)
            shareCountTextView.text = formatCount(post.shareCount)
            viewCountTextView.text = formatCount(post.viewCount)
            likeImageView.setImageResource(
                if (post.liked) R.drawable.baseline_favorite_24_filled
                else R.drawable.baseline_favorite_border_24
            )
        }
    }

    private fun formatCount(count: Int): String {
        return when {
            count < 1000 -> count.toString()
            count < 1_000_000 -> {
                val thousands = count / 1000
                val remain = count % 1000
                if (thousands < 10 && remain < 100) {
                    "${thousands}K"
                } else {
                    val formatted = (count / 100).toDouble() / 10
                    String.format("%.1fK", formatted)
                }
            }

            else -> {
                val millions = count / 1_000_000
                val remainder = count % 1_000_000
                when {
                    remainder == 0 -> "${millions}M"
                    else -> {
                        val formatted = (count / 100_000).toDouble() / 10
                        String.format("%.1fM", formatted)
                    }
                }
            }
        }
    }
}