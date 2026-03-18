package pl.slaszu.localstorage.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import pl.slaszu.localstorage.createLocalStorage
import pl.slaszu.localstorage.sample.application.SampleModel
import pl.slaszu.localstorage.sample.ui.theme.LocalstorageTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    val localStorage = this.createLocalStorage(SampleModel(), "sample_file_storage_name")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            val sample = localStorage.get().collectAsStateWithLifecycle(SampleModel.createFulfilled()).value

            LocalstorageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        Text("${sample}")
                        Button(
                            onClick = { changeSampleModel(sample) }
                        ) {
                            Text("shuffle data")
                        }
                        Button(
                            onClick = { resetSampleModel() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ) {
                            Text("reset data")
                        }
                    }
                }
            }
        }
    }

    fun changeSampleModel(sample: SampleModel) {
        lifecycleScope.launch {
            localStorage.save(
                sample.copy(
                    name = "Rand int ${Random.nextInt(1000, 9999)}",
                    age = sample.age + 1,
                    params = sample.params.map {
                        it.copy(
                            value = "value ${Random.nextInt(10, 99)}"
                        )
                    }
                ))
        }
    }

    fun resetSampleModel() {
        lifecycleScope.launch {
            localStorage.save(SampleModel.createFulfilled())
        }
    }
}
