package kz.podcast.payloadsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.podcast.payloadsexample.databinding.ActivityMainBinding
import kz.podcast.payloadsexample.regular.withoutpayload.AdapterWithoutPayloadActivity
import kz.podcast.payloadsexample.regular.withpayload.AdapterWithPayloadActivity
import kz.podcast.payloadsexample.utils.startActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var viewBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.bind(findViewById(R.id.root))
        viewBinding?.buttonRegularWith?.setOnClickListener {
            startActivity(AdapterWithPayloadActivity::class.java)
        }
        viewBinding?.buttonRegularWithout?.setOnClickListener {
            startActivity(AdapterWithoutPayloadActivity::class.java)
        }
    }
}
