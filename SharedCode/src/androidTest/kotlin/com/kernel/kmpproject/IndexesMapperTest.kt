package com.kernel.kmpproject

import com.kernel.kmpproject.data.entity.IndexEntity
import com.kernel.kmpproject.data.entity.toDomain
import kotlin.test.Test
import org.junit.Assert

class IndexesMapperTest {

  @Test
  fun `Check mapping IndexEntity to Index`() {
    val inputData = listOf(
      IndexEntity(".DJI", 28.7089f, 23719.4f, "Dow Jones")
    )

    val outputData = inputData.toDomain()

    Assert.assertEquals(outputData.size, inputData.size)

    outputData.forEach {
      Assert.assertEquals(it.ticker, ".DJI")
      Assert.assertEquals(it.changes, 28.7089f)
      Assert.assertEquals(it.price, 23719.4f)
      Assert.assertEquals(it.indexName, "Dow Jones")
    }
  }
}