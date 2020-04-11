package com.kernel.kmpproject

import com.kernel.kmpproject.data.entity.QuoteEntity
import com.kernel.kmpproject.data.entity.toDomain
import kotlin.test.Test
import org.junit.Assert

class QuoteMapperTest {

  @Test
  fun `Check mapping QuoteEntity to Quote`() {
    val inputData = QuoteEntity("", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0)

    val outputData = inputData.toDomain()

    Assert.assertEquals(outputData.dayHigh, "0.0")
  }
}