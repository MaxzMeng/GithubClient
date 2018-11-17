package me.maxandroid.retroapolloandroid.annotations

import kotlin.annotation.AnnotationRetention.RUNTIME

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(RUNTIME)
annotation class GraphQLQuery(val value: String)
