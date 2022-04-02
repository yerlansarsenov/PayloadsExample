package kz.podcast.payloadsexample.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T : AppCompatActivity> AppCompatActivity.startActivity(activity: Class<T>) {
    val intent = Intent(this, activity)
    startActivity(intent)
    overridePendingTransition(0, 0)
}

inline fun AppCompatActivity.launchWhenResumed(
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    return lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
            block.invoke(this)
        }
    }
}
