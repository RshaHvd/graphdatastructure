package hvd.edu.benchmark

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.workload.{ WorkloadType, WorkloadTypes }
import scopt.OptionParser
import scopt.Read._

/**
 */
object BenchmarkEngine extends App with LazyLogging {

  override def main(args: Array[String]): Unit = {

    val config = new BenchmarkConfigParser()
      .parse(args, BenchmarkConfig())
      .getOrElse(
        sys.error(
          """Failed to parse command-line arguments
            |Detailed Usage: hvd.edu.benchmark.BenchmarkEngine [ --repeat] [--filePath]
            |
            | - optional repeat - Defaults to 1
            | - optional filePath = Defaults to a file in resources dir
            | - Examples:
            |
            |     BenchmarkEngine --repeat 5 --filePath
      """.stripMargin
        )
      )

    logger.info(s" config is ${config.toString}")

    // keeping it threadsafe just in case we want to run all workloads in parallel ?
    val threadSafeRecorder = new ThreadLocal[Recorder] {
      override def initialValue(): Recorder = new Recorder(config)
    }

    config.workloadTypes.foreach {
      w => w.workLoad.benchmark(config, threadSafeRecorder.get())
    }
  }

}

case class BenchmarkConfig(
  numberRuns:    Int                = 1,
  filePath:      String             = "cit-HepTh.txt", // default file to bechmark on
  workloadTypes: List[WorkloadType] = Nil) {

  override def toString: String =
    s"""
       |numberRuns: ${numberRuns}
       |filePath: ${filePath}
       |workloadTypes: ${workloadTypes.mkString(",")}
       |""".stripMargin
}

class BenchmarkConfigParser extends OptionParser[BenchmarkConfig]("hvd.edu.benchmark.BenchmarkConfig") {

  opt[Int]('r', "repeat")
    .action { (i: Int, c: BenchmarkConfig) =>
      c.copy(numberRuns = i)
    }
    .text("Number of repeats")

  opt[String]('f', "filePath")
    .action { (s: String, c: BenchmarkConfig) =>
      c.copy(filePath = s)
    }
    .text("FileUrl to down or Local path to read file")

  opt[Seq[String]]('w', "workload").action {
    (ss: Seq[String], c: BenchmarkConfig) =>
      val wType = ss.flatMap(WorkloadTypes.withNameOption(_))
      c.copy(workloadTypes = wType.toList)
  }
    .text("The list of workloads to run and benchmark")

}
