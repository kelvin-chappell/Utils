package specs2

class DataTableBuilder(values: List[Set[Any]],
                       exclusions: List[Exclusion] = Nil,
                       sep: String = "\t!!\t",
                       end: String = "\t|") {

  def build = {
    val combinations = cartesianProduct(values.map(_.toList))
    combinations.map {
      combination =>
        val row = combination.mkString("", sep, end)
        val applyingExclusion = exclusions.find(_.appliesTo(combination))
        applyingExclusion match {
          case Some(exclusion) => exclusion.format(row)
          case None => row
        }
    }
  }

  // see http://stackoverflow.com/questions/8217764/cartesian-product-of-two-lists
  private def cartesianProduct[T](xss: List[List[T]]): List[List[T]] = xss match {
    case Nil => List(Nil)
    case head :: tail => for (xh <- head; xt <- cartesianProduct(tail)) yield xh :: xt
  }
}

class Exclusion(predicate: List[Any] => Boolean, message: String) {

  def appliesTo(combination: List[Any]) = predicate(combination)

  def format(row: String) = "//\t%s\t%s".format(row, message)
}
