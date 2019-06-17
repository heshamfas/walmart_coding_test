package com.heshamfas.walmart_coding.shared

import android.view.View

typealias VoidLambda = () -> Unit
typealias StringLambda = (String) -> Unit
typealias ItemClickedlambda = (v: View, position: Int) -> Unit

class NullBox<T>(val value: T?)

class EmptyDescriptionException(override var message:String): Exception(message)