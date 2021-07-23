package dev.emg.mvx.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import dev.emg.mvx.App
import dev.emg.mvx.PokedexAdapter
import dev.emg.mvx.R
import dev.emg.mvx.api.ApiState
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MVVMActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        val adapter = PokedexAdapter()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter

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