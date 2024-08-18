package com.ubiq.mviarchitecture.ui.main.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.ubiq.mviarchitecture.data.model.DataClassOne
import com.ubiq.mviarchitecture.data.model.DataClassTwo
import com.ubiq.mviarchitecture.intent.MainIntent
import com.ubiq.mviarchitecture.ui.main.viewmodels.MainViewModel
import com.ubiq.mviarchitecture.ui.theme.MVIArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Greeting("Ubiquitous")
                Spacer(Modifier.size(20.dp))
                LazyColumn {
                    items(viewModel.items) {
                        Row(
                            modifier = Modifier.wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = it.id.toString())
                            Spacer(modifier = Modifier
                                .size(5.dp)
                                .background(Color.DarkGray))
                            Text(text = it.name.toString())
                        }
                    }
                }
            }
        }
        setUpObservers()
        val bundle = intent.extras
        viewModel.viewModelScope.launch {
            viewModel.userIntent.send(MainIntent.MainCall(0))
        }
    }

    private fun setUpObservers() {
        viewModel.getCallApiButtonClick().observe(this) {
            handleUIChangeOne(it)
        }
        viewModel.getSubmitButtonClick().observe(this) {
            handleUIChangeTwo(it)
        }
    }


    private fun handleUIChangeOne(question: DataClassOne) {

    }

    private fun handleUIChangeTwo(question: DataClassTwo) {

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVIArchitectureTheme {
        Greeting("Android")
    }
}