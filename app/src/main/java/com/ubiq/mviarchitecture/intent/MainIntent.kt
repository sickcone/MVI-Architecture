package com.ubiq.mviarchitecture.intent


sealed class MainIntent {
    data class MainCall(val tabId: Int) : MainIntent()
    data class SubmitClicked(val text: String) : MainIntent()
}