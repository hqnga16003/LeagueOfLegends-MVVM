package com.example.leagueoflegendsapp

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), SingletonImageLoader.Factory {
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader(this).newBuilder().memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
                MemoryCache.Builder().maxSizePercent(this).strongReferencesEnabled(true).build()
            }.diskCachePolicy(CachePolicy.ENABLED).diskCache {
                DiskCache.Builder().maxSizePercent(0.03).directory(cacheDir).build()
            }.logger(DebugLogger()).build()
    }
}