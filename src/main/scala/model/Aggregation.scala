package model

trait Aggregation {
  /** Add an optional value to an aggregation.
    *
    * @param rhs
    *   value to add
    * @return
    *   new instance of aggregation, which includes rhs value.
    */
  def +(rhs: Option[Int]): Aggregation

  /** Average value */
  def average: Option[Int]
}
