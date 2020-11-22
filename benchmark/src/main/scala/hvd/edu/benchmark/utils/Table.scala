package hvd.edu.benchmark.utils

import com.typesafe.scalalogging.LazyLogging

object Table extends LazyLogging {

  /**
   * Head1, Head2
   * Sarah Rachna Ann Marie
   *
   * @param rows
   */
  def draw(rows: Seq[Seq[Any]]) = {
    val cellSizes: Seq[Seq[Int]] = rows.map(r => r.map(s => if (s.toString.isEmpty) 0 else s.toString.length))
    val colSize: Seq[Int] = cellSizes.transpose.map { cols => cols.max }
    val allRowsFormatted = rows.map {
      row =>
        val rowWithColSize: Seq[(Any, Int)] = row.zip(colSize)
        val allCellsForRow = rowWithColSize.map {
          case (t1, t2) => String.format(s"%${t2}s", t1.toString)
        }
        allCellsForRow.mkString("|", "|", "|")
    }

    val headerCols = colSize.map(s => "=" * s).mkString("+", "+", "+")
    val rowSeparator = colSize.map(s => "_" * s).mkString("+", "+", "+")
    (headerCols ::
      allRowsFormatted.head ::
      headerCols ::
      allRowsFormatted.tail.toList.map(thisR => s"${thisR}\n${rowSeparator}") :::
      Nil).mkString("\n")

  }

  //  def main(args: Array[String]): Unit = {
  //    logger.info("Hello Table")
  //    val makeRows = List(List("Header1", "Head2"), List("Sarah", "Rachna Sha"), List("20", "40"))
  //    val finalString = draw(makeRows)
  //    logger.info(s"\n${finalString}\n")
  //  }

}
