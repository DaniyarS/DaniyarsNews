package dev.dslam.daniyarsnews.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.dslam.daniyarsnews.Constants
import dev.dslam.daniyarsnews.viewmodels.HeadlinesFragmentViewModel

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {

    private val search = "bitcoin"
    private val viewModel by viewModels<HeadlinesFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(this.requireContext(), "initViewModel", Toast.LENGTH_SHORT).show()
        initViewModel()
    }

    private fun initViewModel() {
        Log.d("initViewModel", "ViewModel Start")
        Toast.makeText(this.requireContext(), "initViewModel", Toast.LENGTH_SHORT).show()
        viewModel.getHeadlineObserver().observe(viewLifecycleOwner, {
            Log.d("initViewModel", it.toString())
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this.requireContext(), "Данные получены", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this.requireContext(), "Упс, что-то пошло не так :-(", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.loadHeadline(search = search, apiKey = Constants.API_TOKEN)
    }
}