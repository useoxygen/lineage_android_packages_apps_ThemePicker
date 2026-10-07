/*
 * Copyright 2026 TextGavel. Licensed under the Apache License, Version 2.0.
 */

package com.android.wallpaper.customization.ui.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log

/**
 * Cyclon: the Highlight row of Wallpaper & style (design draft 4). The owner's one highlight
 * colour is chosen in the launcher's highlight picker, which writes the system theme setting
 * (theme_customization_overlay_packages); this row opens that picker, like Settings home's
 * Highlight row. Shown only while the launcher exports the picker.
 */
object CyclonHighlight {
    private const val TAG = "CyclonHighlight"
    private const val ACTION_HIGHLIGHT_SETTINGS = "ai.cyclon.action.HIGHLIGHT_SETTINGS"
    private const val LAUNCHER_PACKAGE = "com.cyclon.app"

    private fun intent() = Intent(ACTION_HIGHLIGHT_SETTINGS).setPackage(LAUNCHER_PACKAGE)

    fun isAvailable(context: Context): Boolean =
        context.packageManager.resolveActivity(intent(), PackageManager.MATCH_SYSTEM_ONLY) != null

    fun open(context: Context) {
        try {
            context.startActivity(intent())
        } catch (e: ActivityNotFoundException) {
            Log.w(TAG, "No highlight picker", e)
        }
    }
}
