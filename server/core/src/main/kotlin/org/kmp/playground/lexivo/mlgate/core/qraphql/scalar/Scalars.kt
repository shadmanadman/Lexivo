package org.kmp.playground.lexivo.mlgate.core.qraphql.scalar

import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import org.bson.types.ObjectId
import java.util.Date


val objectIdScalar: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("ObjectId")
    .description("A custom scalar to handle MongoDB ObjectId")
    .coercing(object : Coercing<ObjectId, String> {
        override fun serialize(dataFetcherResult: Any): String {
            return (dataFetcherResult as ObjectId).toHexString()
        }

        override fun parseValue(input: Any): ObjectId {
            return ObjectId(input.toString())
        }

        override fun parseLiteral(input: Any): ObjectId {
            return ObjectId(input.toString())
        }
    }).build()

val dateScalar: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("Date")
    .description("A custom scalar to handle java.util.Date")
    .coercing(object : Coercing<Date, String> {
        override fun serialize(dataFetcherResult: Any): String {
            return (dataFetcherResult as Date).toInstant().toString() // ISO-8601
        }

        override fun parseValue(input: Any): Date {
            return Date.from(java.time.Instant.parse(input.toString()))
        }

        override fun parseLiteral(input: Any): Date {
            return Date.from(java.time.Instant.parse(input.toString()))
        }
    }).build()

val longScalar: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("Long")
    .description("Built-in Long as custom scalar")
    .coercing(object : Coercing<Long, Long> {
        override fun serialize(dataFetcherResult: Any): Long {
            return when (dataFetcherResult) {
                is Long -> dataFetcherResult
                is Number -> dataFetcherResult.toLong()
                else -> throw IllegalArgumentException("Cannot serialize $dataFetcherResult as Long")
            }
        }

        override fun parseValue(input: Any): Long {
            return input.toString().toLong()
        }

        override fun parseLiteral(input: Any): Long {
            return input.toString().toLong()
        }
    }).build()