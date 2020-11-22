package hvd.edu.benchmark.utils

import com.github.tototoshi.csv._
import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.BenchmarkConfig

object BenchmarkCSVWriter extends LazyLogging {

  def apply(benchmarkConfig: BenchmarkConfig, data: Seq[Seq[Any]]) = {
    val timeLong = System.currentTimeMillis()
    val csvFileName = benchmarkConfig.csvFile.getOrElse(s"./output/benchmark:${timeLong}.csv")
    logger.info(s"Writing CSV file :${csvFileName}")
    val writer = CSVWriter.open(csvFileName)
    writer.writeAll(data)
    writer.close()
  }

}
