package dev.emg.mvx.api

sealed class ApiState<out T> {

    data class Success<out T>(val data: T) : ApiState<T>()

    object Loading : ApiState<Nothing>()

    data class Error(val e: Exception, val msg: String?) : ApiState<Nothing>()

}