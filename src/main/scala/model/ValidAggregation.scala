package model

/** Aggregated data from a sensor if at least one measurement is valid */
case class ValidAggregation(min: Int, max: Int, sum: Int, count: Int) extends Aggregation {

  override def +(rhs: Option[Int]): Aggregation = rhs match {
    case None    => this
    case Some(v) => ValidAggregation(math.min(min, v), math.max(max, v), sum + v, count + 1)
  }

  /** String representation of [[model.ValidAggregation]] */
  override def toString: String = s"$min,${if (count == 0) "NaN" else average.get},$max"

  lazy val average: Option[Int] = Some(sum / count)
}
