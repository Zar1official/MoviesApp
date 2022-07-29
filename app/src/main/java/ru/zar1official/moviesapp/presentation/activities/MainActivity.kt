package ru.zar1official.moviesapp.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.zar1official.moviesapp.R
import ru.zar1official.moviesapp.databinding.ActivityMainBinding
import ru.zar1official.moviesapp.presentation.adapter.MoviesAdapter
import ru.zar1official.moviesapp.presentation.viewModels.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setupRecyclerView()
        }.also { setContentView(it.root) }

        viewModel.movies.observe(this) {
            lifecycleScope.launch {
                moviesAdapter.submitData(lifecycle, it)
            }
        }

        lifecycleScope.launch {
            moviesAdapter.loadStateFlow.collectLatest {
                if (it.refresh is LoadState.NotLoading) {
                    binding.initialLoading.visibility = View.GONE
                }
                if (it.refresh is LoadState.Error || it.prepend is LoadState.Error || it.append is LoadState.Error) {
                    Snackbar.make(
                        this@MainActivity,
                        binding.root,
                        getString(R.string.network_error),
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .setAction(R.string.action_try_again) {
                            moviesAdapter.retry()
                        }.show()
                }
            }
        }
    }

    private fun ActivityMainBinding.setupRecyclerView() {
        this.moviesRecyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, VERTICAL))
            setHasFixedSize(true)
        }
    }
}