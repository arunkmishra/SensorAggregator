package model

/** Aggregated data from a sensor in case of all failed measurements */
case object FailedAggregation extends Aggregation {

  override def +(rhs: Option[Int]): Aggregation = rhs match {
    case None    => this
    case Some(v) => ValidAggregation(v, v, v, 1)
  }

  /** String representation of [[model.FailedAggregation]] */
  override def toString: String = "NaN,NaN,NaN"

  /** Average value */
  override def average: Option[Int] = None
}
