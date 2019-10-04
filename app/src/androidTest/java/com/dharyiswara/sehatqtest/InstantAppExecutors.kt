package com.dharyiswara.sehatqtest

import com.dharyiswara.sehatqtest.helper.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor { it.run() }
    }
}