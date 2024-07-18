package ru.netology

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.databinding.ActivityMainBinding
import android.util.Log
import ru.netology.databinding.MainPageLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainPageLayoutBinding
    private var likeCount = 999
    private var shareCount = 1099
    private var viewCount = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainPageLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateCounts()

        binding.root.setOnClickListener {
            Log.d("Тест", "Нажатие!")
        }


        binding.likeImageView.setOnClickListener {
            Log.d("Тест", "Понравилась запись!")
            if (binding.likeImageView.tag as? Boolean == true) {
                likeCount--
                binding.likeImageView.setImageResource(R.drawable.baseline_favorite_border_24)
                binding.likeImageView.tag = false
            } else {
                likeCount++
                binding.likeImageView.setImageResource(R.drawable.baseline_favorite_24_filled)
                binding.likeImageView.tag = true
            }
            updateCounts()
        }

        binding.shareImageView.setOnClickListener {
            Log.d("Тест", "Поделился!")
            if (binding.shareImageView.tag as? Boolean == true){
                shareCount--
                binding.shareImageView.tag = false
            } else {
                shareCount++
                binding.shareImageView.tag = true
            }

            updateCounts()
        }
    }
    private fun updateCounts(){
        binding.likeCountTextView.text = formatCount(likeCount)
        binding.shareCountTextView.text = formatCount(shareCount)
        binding.viewCountTextView.text = formatCount(viewCount)
    }
    private fun formatCount(count: Int): String {
        return when {
            count < 1000 -> count.toString()
            count < 10000 -> "${count / 1000}.${(count % 1000) / 100}k"
            count < 1000000 -> "${count / 1000}k"
            else -> String.format("%.1fM", count / 1000000.0)
        }
    }
}