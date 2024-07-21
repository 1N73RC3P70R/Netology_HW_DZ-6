package ru.netology

import android.annotation.SuppressLint
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
            id = 0,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен — http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likeCount = 129999,
            shareCount = 9_999_999,
            viewCount = 10999
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

    @SuppressLint("DefaultLocale")
    private fun formatCount(count: Int): String {
        return when {
            count < 1000 -> count.toString()
            count < 10000 -> {
                if (count < 1100) "${count / 1000}K"
                else String.format("%.1fK", count / 1000.0)
            }

            count < 1_000_000 -> "${count / 1000}K"
            count < 10_000_000 -> {
                val millions = count / 1_000_000
                val remainder = (count % 1_000_000) / 100_000
                if (remainder == 0) "${millions}M" else "$millions.${remainder}M"
            }

            else -> "${count / 1_000_000}M"
        }
    }
}