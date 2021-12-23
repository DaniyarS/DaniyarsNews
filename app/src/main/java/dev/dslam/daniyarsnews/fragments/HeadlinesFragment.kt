package dev.dslam.daniyarsnews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dslam.daniyarsnews.Constants
import dev.dslam.daniyarsnews.viewmodels.HeadlinesFragmentViewModel

@Module
@InstallIn(SingletonComponent::class)
class HeadlinesFragment : Fragment() {

    private val search = "bitcoin"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViewModel()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initViewModel() {
        val viewModel: HeadlinesFragmentViewModel = ViewModelProvider(this)[HeadlinesFragmentViewModel::class.java]
        viewModel.getHeadlineObserver().observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this.requireContext(), "Данные получены", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this.requireContext(), "Упс, что-то пошло не так :-(", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.loadHeadline(search = search, apiKey = Constants.API_TOKEN)
    }
}