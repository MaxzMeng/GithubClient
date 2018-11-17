/*
 * Copyright 2017 Bennyhuo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.maxandroid.retroapolloandroid.rxjava

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import me.maxandroid.retroapolloandroid.CallAdapter
import me.maxandroid.retroapolloandroid.CallAdapter.Factory
import me.maxandroid.retroapolloandroid.rxjava.RxReturnType.OBSERVABLE
import me.maxandroid.retroapolloandroid.rxjava.RxReturnType.SINGLE
import me.maxandroid.retroapolloandroid.utils.Utils
import rx.Observable
import rx.Scheduler
import rx.Single
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by benny on 8/5/17.
 */
enum class RxReturnType {
    OBSERVABLE, SINGLE
}

class RxJavaCallAdapter<T>(val rxReturnType: RxReturnType,
                           val dataType: Type,
                           val subscribeScheduler: Scheduler? = null,
                           val observableScheduler: Scheduler? = null) : CallAdapter<T, Any> {
    override fun responseType(): Type {
        return if (dataType is ParameterizedType) {
            Utils.getParameterUpperBound(0, dataType)
        } else {
            dataType
        }
    }

    override fun adapt(call: ApolloCall<T>): Any {
        val callFunc = CallExecuteOnSubscribe(call)
        var originalObservable = Observable.create(callFunc)
        originalObservable = subscribeScheduler?.let(originalObservable::subscribeOn) ?: originalObservable
        originalObservable = observableScheduler?.let(originalObservable::observeOn) ?: originalObservable

        val observable: Observable<*> =
                // Observable<Response<Data>>
                if (dataType is ParameterizedType) {
                    originalObservable
                } else {
                    originalObservable.map { it.data() }
                }

        return when (rxReturnType) {
            OBSERVABLE -> observable
            SINGLE -> observable.toSingle()
        }
    }
}

class RxJavaCallAdapterFactory : Factory() {

    private var subscribeScheduler: Scheduler? = null

    fun subscribeScheduler(scheduler: Scheduler): RxJavaCallAdapterFactory {
        subscribeScheduler = scheduler
        return this
    }

    private var observableScheduler: Scheduler? = null

    fun observableScheduler(scheduler: Scheduler): RxJavaCallAdapterFactory {
        observableScheduler = scheduler
        return this
    }

    override fun get(returnType: Type): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)
        val dataType = getParameterUpperBound(0, returnType as ParameterizedType)
        if(dataType is ParameterizedType){
            val responseType= getRawType(dataType)
            if(responseType != Response::class.java){
                return null
            }
        }
        val rxReturnType = when (rawType) {
            Single::class.java -> SINGLE
            Observable::class.java -> OBSERVABLE
            else -> null
        } ?: return null
        return RxJavaCallAdapter<Any>(rxReturnType, dataType, subscribeScheduler, observableScheduler)
    }
}