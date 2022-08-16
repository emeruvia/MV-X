package dev.emg.mvx.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.emg.mvx.App
import dev.emg.mvx.PokedexAdapter
import dev.emg.mvx.databinding.ActivityStateMachineBinding
import dev.emg.mvx.di.assisted.StateMachineFactory
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

class MVIStateMachineActivity : AppCompatActivity(), StateCallback<MVIState> {

    @Inject
    lateinit var stateMachineFactory: StateMachineFactory

    private lateinit var binding: ActivityStateMachineBinding
    private lateinit var adapter: PokedexAdapter
    private lateinit var stateMachine: StateMachineImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        stateMachine = stateMachineFactory.create(
            stateCallback = this,
            coroutineScope = lifecycle.coroutineScope
        )

        super.onCreate(savedInstanceState)
        binding = ActivityStateMachineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PokedexAdapter()

        binding.recyclerView.adapter = adapter
        val itemDecorator = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.recyclerView.addItemDecoration(itemDecorator)

        stateMachine.submit(MVIAction.View(Action.Init))
    }

    override var state: MVIState by Delegates.observable(
        MVIState.View(State.Init)
    ) { _, _, currentState ->
        when (currentState) {
            is MVIState.View -> {
                when (currentState.viewState) {
                    is State.Loading -> {
                        Timber.d("Loading")
                        runOnUiThread {
                            binding.progressbar.visibility = View.VISIBLE
                        }
                    }
                    is State.Error -> {
                        Timber.e(currentState.viewState.throwable, currentState.viewState.msg)
                        runOnUiThread {
                            binding.progressbar.visibility = View.GONE
                        }
                    }
                }
            }
            is MVIState.Success -> {
                runOnUiThread {
                    binding.progressbar.visibility = View.GONE
                    adapter.submitList(currentState.data)
                }
            }
        }
    }
}