package hvd.edu.benchmark

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.utils.{ BenchmarkCSVWriter, Table }
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

    logger.info(s"Starting benchmark with these config: \n ${config.toString} \n")

    // keeping it threadsafe just in case we want to run all workloads in parallel ?
    val threadSafeRecorder = new ThreadLocal[Recorder] {
      override def initialValue(): Recorder = new Recorder(config)
    }

    config.files.zipWithIndex.map {
      case (f, idx) =>
        config.workloadTypes.foreach {
          w => WorkLoad.run(config, threadSafeRecorder.get(), w, f, config.fileDelimiters(idx))
        }
    }

    val finalRecorder = threadSafeRecorder.get()

    logger.info(s"\n${Table.draw(finalRecorder.recordedDataAsSeq())}\n")
    logger.info(s"\n${BenchmarkCSVWriter(config, finalRecorder.recordedDataAsSeq())}\n")
  }

}

case class BenchmarkConfig(
  numberRuns:     Int                = 1,
  files:          List[String]       = List("cit-HepTh.txt"), // default file to bechmark on
  fileDelimiters: List[String]       = Nil, // default file to bechmark on
  workloadTypes:  List[WorkloadType] = Nil,
  randomNodeFE:   List[Long]         = Nil,
  csvFile:        Option[String]     = None) {

  def getRandomFor(file: String): Long = file match {
    case f1 if f1.equals("cit-HepTh.txt")       => 9603161
    case f2 if f2.equals("wiki-Vote-part4.txt") => 29
    case _                                      => throw new RuntimeException(s"No matching value for ${file}")
  }

  def bfsFromNode(file: String): Long = file match {
    case f1 if f1.equals("cit-HepTh.txt")       => 9603161
    case f2 if f2.equals("wiki-Vote-part4.txt") => 29
    case _                                      => throw new RuntimeException(s"No matching value for ${file}")
  }

  def dfsFromNode(file: String): Long = file match {
    case f1 if f1.equals("cit-HepTh.txt")       => 9603161
    case f2 if f2.equals("wiki-Vote-part4.txt") => 29
    case _                                      => throw new RuntimeException(s"No matching value for ${file}")
  }

  override def toString: String =
    s"""
       |numberRuns:         ${numberRuns}
       |files:              ${files.mkString(",")}
       |delimiters:         ${fileDelimiters.mkString(",")}
       |workloadTypes:      ${workloadTypes.mkString(",")}
       |""".stripMargin
}

class BenchmarkConfigParser extends OptionParser[BenchmarkConfig]("hvd.edu.benchmark.BenchmarkConfig") {

  opt[Int]('r', "repeat")
    .action { (i: Int, c: BenchmarkConfig) =>
      c.copy(numberRuns = i)
    }
    .text("Number of repeats")

  opt[Seq[String]]('f', "filePath")
    .action { (fs: Seq[String], c: BenchmarkConfig) =>
      c.copy(files = fs.toList)
    }
    .text("FileUrl to down or Local path to read file")

  opt[Seq[String]]('d', "fileDelimiter")
    .action { (fd: Seq[String], c: BenchmarkConfig) =>
      c.copy(fileDelimiters = fd.toList)
    }
    .text("FileDelimiter in same sequence as files")

  opt[String]('c', "csvFile")
    .action { (csvFile: String, c: BenchmarkConfig) =>
      c.copy(csvFile = Option(csvFile))
    }
    .text("CSVFilePath to write benchmarks to")

  opt[Seq[String]]('w', "workload").action {
    (ss: Seq[String], c: BenchmarkConfig) =>
      val wType = ss.flatMap(WorkloadTypes.withNameOption(_))
      c.copy(workloadTypes = wType.toList)
  }
    .text("The list of workloads to run and benchmark")

}
