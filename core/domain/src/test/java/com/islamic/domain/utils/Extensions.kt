package com.islamic.domain.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlin.time.Duration
import kotlin.time.toJavaDuration

@ExperimentalCoroutinesApi
fun TestScope.advanceTimeBy(duration:Duration, clock: MutableClock){
    advanceTimeBy(duration)
    clock.advanceTimeBy(duration.toJavaDuration())
}