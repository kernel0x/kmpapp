package com.kernel.kmpproject.domain.mapper

interface Mapper<in T, out E> {
  fun transform(model: T): E
}