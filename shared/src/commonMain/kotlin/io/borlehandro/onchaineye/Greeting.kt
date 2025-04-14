package io.borlehandro.onchaineye

class Greeting {

    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}