package kz.podcast.payloadsexample.regular.withpayload

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.collectLatest
import kz.podcast.payloadsexample.PersonsViewModel
import kz.podcast.payloadsexample.R
import kz.podcast.payloadsexample.databinding.ActivityWithAdapterBinding
import kz.podcast.payloadsexample.regular.withoutpayload.AdapterWithoutPayload
import kz.podcast.payloadsexample.utils.addVerticalLineDivider
import kz.podcast.payloadsexample.utils.launchWhenResumed

class AdapterWithPayloadActivity : AppCompatActivity(R.layout.activity_with_adapter) {

    private var viewBinding: ActivityWithAdapterBinding? = null
    private val adapter: AdapterWithPayload by lazy {
        AdapterWithPayload()
    }
    private val viewModel: PersonsViewModel by lazy {
        ViewModelProvider(this)[PersonsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityWithAdapterBinding.bind(findViewById(R.id.root))
        initViews()
        collectViewModelFlows()
    }

    private fun initViews() {
        with(viewBinding ?: return) {
            toolbar.title = "With payloads"
            toolbar.setBackgroundResource(R.color.teal_200)
            recyclerView.addVerticalLineDivider()
            recyclerView.adapter = adapter
        }
    }

    private fun collectViewModelFlows() {
        launchWhenResumed {
            viewModel.personsFlow.collectLatest { persons ->
                adapter.submitList(persons)
            }
        }
    }
}
