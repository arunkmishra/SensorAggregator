package model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AggregationSpec extends AnyFlatSpec with Matchers {
  import AggregationSpec._

  "A valid aggregation" should "be the same after combining with failed aggregation" in {
    val result = validAggregation + None
    result should be(validAggregation)
  }

  it should "be valid aggregation after combining with valid aggregation" in {
    val combine = validAggregation + validValue
    val expectedResult = ValidAggregation(10, 12, 40, 3)
    combine should be(expectedResult)
  }

  "A failed aggregation" should "be equal to the aggregation, with which it combines" in {
    val combined = FailedAggregation + Some(1)
    val expectedResult = ValidAggregation(1, 1, 1, 1)
    combined should be(expectedResult)
  }

  it should "be be failed after combining with failed aggregation" in {
    val combined = FailedAggregation + None
    combined should be(FailedAggregation)
  }
}

object AggregationSpec {
  private val validAggregation = ValidAggregation(10, 12, 30, 2)
  private val validValue = Some(10)
}
