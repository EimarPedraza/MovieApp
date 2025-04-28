package com.eapvlab.movieapp.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.eapvlab.movieapp.R
import com.eapvlab.movieapp.application.AppConstants.BASE_BACKGROUND_POSTER_URL
import com.eapvlab.movieapp.application.AppConstants.BASE_POSTER_URL
import com.eapvlab.movieapp.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        Glide.with(requireContext()).load("${BASE_POSTER_URL}${args.posterImageUrl}").centerCrop().into(binding.imgMovie)
        Glide.with(requireContext()).load("${BASE_BACKGROUND_POSTER_URL}${args.backgroundImageUrl}").centerCrop().into(binding.imgBackground)
        binding.txtTitle.text = args.title
        binding.txtDescription.text = args.overview
        binding.txtLanguage.text = getString(R.string.language_format, args.language)
        binding.txtRating.text = getString(R.string.rating_format, args.voteAverage.toString(), args.voteCount)
        binding.txtReleased.text = getString(R.string.released_format, args.releaseDate)
    }

}
