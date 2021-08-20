package dev.emg.mvx.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.emg.mvx.App
import dev.emg.mvx.PokedexAdapter
import dev.emg.mvx.R
import dev.emg.mvx.api.ApiState
import dev.emg.mvx.databinding.ActivityMvvmBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MVVMActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PokedexAdapter()

        binding.recyclerView.adapter = adapter
        val itemDecorator = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.recyclerView.addItemDecoration(itemDecorator)

        lifecycleScope.launchWhenStarted {
            viewModel.pokedex.collect {
                when (it) {
                    is ApiState.Success -> {
                        adapter.submitList(it.data.pokemonEntries)
                    }
                    is ApiState.Loading -> {
                        // TODO
                    }
                    is ApiState.Error -> {
                    }
                }
            }
        }
    }
}