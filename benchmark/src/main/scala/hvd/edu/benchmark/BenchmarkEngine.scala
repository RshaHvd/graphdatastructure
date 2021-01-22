package hvd.edu.benchmark

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.utils.{ BenchmarkCSVWriter, Table }
import hvd.edu.benchmark.workload.WorkloadTypes.LoadGraph
import hvd.edu.benchmark.workload.{ GraphTypes, WorkloadType, WorkloadTypes }
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
      """.stripMargin))

    logger.info(s"Starting benchmark with these config: \n ${config.toString} \n")

    // keeping it threadsafe just in case we want to run all workloads in parallel ?
    val threadSafeRecorder = new ThreadLocal[Recorder] {
      override def initialValue(): Recorder = new Recorder(config)
    }

    config.files.zipWithIndex.map {
      case (f, idx) =>
        config.workloadTypes.foreach {
          w =>
            WorkLoad.run(config, threadSafeRecorder.get(), w, f,
              config.fileDelimiters(idx), config.numLinesInFile(idx))
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
  fileDelimiters: List[String]       = Nil,
  nodesInGraph:   List[Int]          = Nil,
  edgesInGraph:   List[Int]          = Nil,
  numLinesInFile: List[Int]          = Nil,
  workloadTypes:  List[WorkloadType] = List(LoadGraph),
  graphTypes:     List[String]       = GraphTypes.ALL.map(_.entryName),
  randomNodeFE:   List[Int]          = Nil,
  csvFile:        Option[String]     = None) {

  def getRandomFor(file: String): Int = file match {
    case f1 if f1.equals("cit-HepTh.txt") => 9603161
    case f2 if f2.equals("wiki-Vote-part4.txt") => 29
    case f2 if f2.equals("com-youtube.ungraph.txt") => 268337
    case f2 if f2.equals("email-Enron.txt") => 36597
    case f2 if f2.equals("amazon0601.txt") => 169879
    case _ => throw new RuntimeException(s"No matching value for ${file}")
  }

  def bfsFromNode(file: String): Int = file match {
    case f1 if f1.equals("cit-HepTh.txt") => 9603161
    case f2 if f2.equals("wiki-Vote-part4.txt") => 29
    case f2 if f2.equals("com-youtube.ungraph.txt") => 268337
    case f2 if f2.equals("email-Enron.txt") => 36597
    case f2 if f2.equals("amazon0601.txt") => 169879
    case _ => throw new RuntimeException(s"No matching value for ${file}")
  }

  def dfsFromNode(file: String): Int = file match {
    case f1 if f1.equals("cit-HepTh.txt") => 9603161
    case f2 if f2.equals("wiki-Vote-part4.txt") => 29
    case f2 if f2.equals("com-youtube.ungraph.txt") => 268337
    case f2 if f2.equals("email-Enron.txt") => 36597
    case f2 if f2.equals("amazon0601.txt") => 169879
    case _ => throw new RuntimeException(s"No matching value for ${file}")
  }

  override def toString: String =
    s"""
       |numberRuns:         ${numberRuns}
       |files:              ${files.mkString(",")}
       |delimiters:         ${fileDelimiters.mkString(",")}
       |numberLinesInFiles: ${numLinesInFile.mkString(",")}
       |nodesInGraph:       ${nodesInGraph.mkString(",")}
       |edgesInGraph:       ${edgesInGraph.mkString(",")}
       |workloadTypes:      ${workloadTypes.mkString(",")}
       |graphTypes:         ${graphTypes.mkString(",")}
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

  opt[Seq[Int]]('l', "linesInFile")
    .action { (fl: Seq[Int], c: BenchmarkConfig) =>
      c.copy(numLinesInFile = fl.toList)
    }
    .text("FileDelimiter in same sequence as files")

  opt[Seq[Int]]('n', "nodesInGraph")
    .action { (ng: Seq[Int], c: BenchmarkConfig) =>
      c.copy(nodesInGraph = ng.toList)
    }
    .text("Nodes in Graph in same sequence as files")

  opt[Seq[Int]]('e', "edgesInGraph")
    .action { (eg: Seq[Int], c: BenchmarkConfig) =>
      c.copy(edgesInGraph = eg.toList)
    }
    .text("Edges In Graph in same sequence as files")

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

  opt[Seq[String]]('g', "graphTypes")
    .action { (gt: Seq[String], c: BenchmarkConfig) =>
      c.copy(graphTypes = gt.toList)
    }
    .text("Graph Types to run workload on")

}
