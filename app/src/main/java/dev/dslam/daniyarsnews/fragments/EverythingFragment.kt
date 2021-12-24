package dev.dslam.daniyarsnews.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.dslam.daniyarsnews.Constants
import dev.dslam.daniyarsnews.viewmodels.EverythingFragmentViewModel

@AndroidEntryPoint
class EverythingFragment : Fragment() {

    private val search = "bitcoin"
    private val viewModel by viewModels<EverythingFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getEverythingObserver().observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this.requireContext(), "Данные получены", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this.requireContext(), "Упс, что-то пошло не так :-(", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.loadEverything(search = search, apiKey = Constants.API_TOKEN)
    }
}