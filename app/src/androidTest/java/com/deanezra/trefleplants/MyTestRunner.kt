package com.deanezra.trefleplants

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * A custom test runner required by Hilt when doing unit tests in the Instrumented tests.
 * This class is referred to in the gradle file under:
 * testInstrumentationRunner "com.deanezra.trefleplants.MyTestRunner"
 *
 * The full steps are shown here: https://dagger.dev/hilt/instrumentation-testing.html
 *
 * It basically tests Android instrumented tests to not use out production MyApplication class,
 * but use the HiltTestApplication that Hilt generated for us.
  */
class MyTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader,
        appName: String,
        context: Context
    ) : Application {
        return super.newApplication(
            cl, HiltTestApplication::class.java.getName(), context)
    }
}
