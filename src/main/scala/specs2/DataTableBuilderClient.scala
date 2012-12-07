package specs2

// TODO: quotes around strings
// TODO: pad each column out to its max value length
object DataTableBuilderClient extends App {

  val cantHappen = new Exclusion(((combination: List[Any]) => combination == List("a", 1, true, "randomrandomrandom2")
    || combination(1).asInstanceOf[Int] > 2),
    "This can't happen")

  val illegal = new Exclusion(((combination: List[Any]) => combination == List("a", 1, true, "randomrandomrandom2")
    || combination(1).asInstanceOf[Int] == 4),
    "This is illegal")

  new DataTableBuilder(
    values = List(
      Set("a", "b", "c"),
      Set(1, 2, 3, 4),
      Set(true, false),
      Set("rand", "random", "randomrandomrandom2", "randomrandomrandom4", "randomrandomrandom5")),
    exclusions = illegal :: cantHappen :: Nil
  ).build.foreach(println)
}
