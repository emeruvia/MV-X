package dev.emg.mvx.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.emg.mvx.App
import dev.emg.mvx.PokedexAdapter
import dev.emg.mvx.api.ApiState
import dev.emg.mvx.databinding.ActivityMvvmPokedexBinding
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

class MVVMActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityMvvmPokedexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMvvmPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PokedexAdapter()

        binding.recyclerView.adapter = adapter
        val itemDecorator = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.recyclerView.addItemDecoration(itemDecorator)

        lifecycleScope.launchWhenStarted {
            viewModel.pokedex.collect {
                when (it) {
                    is ApiState.Success -> {
                        binding.progressbar.visibility = View.GONE
                        adapter.submitList(it.data.pokemonEntries)
                    }
                    is ApiState.Loading -> {
                        Timber.d("Loading")
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is ApiState.Error -> {
                        Timber.e(it.e, it.msg)
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }
}