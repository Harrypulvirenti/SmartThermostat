package com.hpdev.sdk.logging

import com.crashlytics.android.Crashlytics
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class SmartLogger {

    companion object {
        fun init(debug: Boolean) {

            if (debug) {
                Logger.addLogAdapter(AndroidLogAdapter())
            }
        }

        fun i(message: String, vararg args: Any?) = Logger.i(message, args)

        fun d(message: String, vararg args: Any?) = Logger.d(message, args)

        fun d(obj: Any?) = Logger.d(obj)

        fun e(errorMessage: String, vararg args: Any?) {
            Crashlytics.log(errorMessage)
            Logger.e(errorMessage, args)
        }

        fun e(throwable: Throwable?, errorMessage: String, vararg args: Any?) {
            throwable?.let {
                Crashlytics.logException(it)
            }
            Crashlytics.log(errorMessage)
            Logger.e(throwable, errorMessage, args)
        }

        fun v(message: String, vararg args: Any?) = Logger.v(message, args)

        fun w(message: String, vararg args: Any?) = Logger.w(message, args)
    }
}