package com.avinash.currencyconverter

import org.mockito.ArgumentCaptor
import org.mockito.Mockito


fun <T> uninitialized(): T = null as T

/**
 * Helper functions that are workarounds to kotlin Runtime Exceptions when using kotlin.
 */

/**
 * Returns Mockito.eq() as nullable type to avoid java.lang.IllegalStateException when
 * null is returned.
 *
 * Generic T is nullable because implicitly bounded by Any?.
 */
fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

/**
 * Returns Mockito.any() as nullable type to avoid java.lang.IllegalStateException when
 * null is returned.
 */
fun <T> any(): T = Mockito.any<T>()

/**
 * Returns ArgumentCaptor.capture() as nullable type to avoid java.lang.IllegalStateException
 * when null is returned.
 */
fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

/**
 * Helper function for creating an com.gojek.testutils.argumentCaptor in kotlin.
 */
inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> =
    ArgumentCaptor.forClass(T::class.java)

/**
 * Create mocks of a type casted Generic Java object
 */
inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)