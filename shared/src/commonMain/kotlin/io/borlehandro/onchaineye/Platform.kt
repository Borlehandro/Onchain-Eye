package io.borlehandro.onchaineye

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform