package com.kernel.kmpproject

import platform.Foundation.NSLog

actual fun logError(tag: String, message: String, error: Throwable?) {
  NSLog("$tag: $message")
}

actual fun logInfo(tag: String, message: String) {
  NSLog("$tag: $message")
}