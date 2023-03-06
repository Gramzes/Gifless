package com.gramzin.gifless.presentation.fragments

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.gramzin.gifless.R
import com.gramzin.gifless.appComponent
import com.gramzin.gifless.databinding.FragmentTopGifBinding
import com.gramzin.gifless.domain.models.Gif
import com.gramzin.gifless.presentation.viewModel.TopFragmentViewModel
import com.gramzin.gifless.presentation.viewModel.factory.ViewModelFactory
import jp.wasabeef.glide.transformations.BlurTransformation
import javax.inject.Inject

class TopGifFragment : Fragment() {
    lateinit var binding: FragmentTopGifBinding
    val viewModel: TopFragmentViewModel by viewModels{ factory.get() }

    @Inject
    lateinit var factory: dagger.Lazy<ViewModelFactory>

    private val gifLoadListener = object: RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                                  isFirstResource: Boolean): Boolean {
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                     dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            binding.base.progressBar.visibility = View.GONE
            return false
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopGifBinding.inflate(inflater, container, false)
        requireContext().appComponent.inject(this)

        init()
        viewModel.nextGif()
        return binding.root
    }

    fun init(){
        viewModel.currentGif.observe(viewLifecycleOwner){
            loadGif(it)
        }
        viewModel.isPossibleGoBack.observe(viewLifecycleOwner){
            switchPrevBtnMode(it)
        }
        binding.base.nextGifBtn.setOnClickListener {
            playBtnClickAnim(it)
            viewModel.nextGif()
        }
        binding.base.prevGifBtn.setOnClickListener {
            playBtnClickAnim(it)
            viewModel.previousGif()
        }
        viewModel.isErrorMessage.observe(viewLifecycleOwner){
            switchErrorState(it)
        }
        binding.base.retryBtn.setOnClickListener {
            viewModel.nextGif()
        }
    }

    private fun playBtnClickAnim(view: View){
        val animSet = (AnimatorInflater
            .loadAnimator(requireContext(), R.animator.button_animator) as AnimatorSet)
        animSet.setTarget(view)
        animSet.start()
    }

    private fun switchPrevBtnMode(isPossibleGoBack: Boolean){
        binding.base.prevGifBtn.isEnabled = isPossibleGoBack
        binding.base.prevGifBtn.elevation = if(isPossibleGoBack) 8f else 0f
    }

    private fun loadGif(gif: Gif){
        binding.base.progressBar.visibility = View.VISIBLE
        Glide
            .with(requireContext())
            .load(gif.gifURL)
            .listener(gifLoadListener)
            .into(binding.base.gif)

        Glide.with(requireContext())
            .load(gif.gifURL)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(binding.base.gifBlur)

        binding.base.gifTitle.text = gif.description
    }

    private fun switchErrorState(isError: Boolean) = with(binding.base){
        if (isError){
            gifBack.visibility = View.GONE
            prevGifBtn.visibility = View.GONE
            nextGifBtn.visibility = View.GONE
            errorImage.visibility = View.VISIBLE
            errorMessage.visibility = View.VISIBLE
            retryBtn.visibility = View.VISIBLE
        } else{
            gifBack.visibility = View.VISIBLE
            prevGifBtn.visibility = View.VISIBLE
            nextGifBtn.visibility = View.VISIBLE
            errorImage.visibility = View.GONE
            errorMessage.visibility = View.GONE
            retryBtn.visibility = View.GONE
        }
    }

}