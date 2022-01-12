package com.example.fomo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.NonNull
import com.example.fomo.databinding.ActivityRecipeBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class RecipeActivity : AppCompatActivity() {

    private var binding : ActivityRecipeBinding? = null

    private lateinit var name: String
    private lateinit var youtube: String
    private lateinit var desc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        name = intent.getStringExtra("name").toString()
        youtube = intent.getStringExtra("youtube").toString()
        youtube=youtube.drop(17)
        desc = intent.getStringExtra("desc").toString()


        setData()
        initializeYoutubevideo()
    }

    private fun setData() {
        binding!!.dish.text = name
        binding!!.desc.text = desc
    }

    private fun initializeYoutubevideo() {
        val youTubePlayerView: YouTubePlayerView = binding!!.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = youtube
                youTubePlayer.loadVideo(videoId, 0f)
                youTubePlayer.pause()
            }
        })
    }


}