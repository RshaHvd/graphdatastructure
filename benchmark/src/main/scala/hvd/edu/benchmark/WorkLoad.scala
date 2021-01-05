package hvd.edu.benchmark

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.workload.GraphTypes._
import hvd.edu.benchmark.workload._
import hvd.edu.graph.Node

trait WorkLoad {
  def benchmark[N <: Node](benchmarkConfig: BenchmarkConfig, recorder: Recorder,
                           gt: GraphType[N], iteration: Int, file: String, delimiter: String,
                           linesInFile: Int)

  def workLoadType: WorkloadType
}

object WorkLoad extends LazyLogging {

  private val allWorkLoads: Seq[WorkLoad] = Seq(
    LoadGraphWorkLoad,
    FindEdgesRandomNodeWorkLoad,
    BFWorkLoad,
    DFWorkLoad)

  def resolveWork(w: WorkloadType) = {
    allWorkLoads.find(p => p.workLoadType == w)
  }

  def run(benchmarkConfig: BenchmarkConfig, recorder: Recorder, w: WorkloadType,
          file: String, delimiter: String, linesInFile: Int) = {
    val resolvedWL = resolveWork(w).getOrElse(throw new RuntimeException(s"Must pass a workload to benchmark"))

    benchmarkConfig.graphTypes.foreach { gt =>
      var runCounter = 1
      while (runCounter <= benchmarkConfig.numberRuns) {
        gt match {
          case ALArrayType.entryName  => resolvedWL.benchmark(benchmarkConfig, recorder, ALArrayType, runCounter, file, delimiter, linesInFile)
          case ALMapType.entryName    => resolvedWL.benchmark(benchmarkConfig, recorder, ALMapType, runCounter, file, delimiter, linesInFile)
          case ALTreeType.entryName   => resolvedWL.benchmark(benchmarkConfig, recorder, ALTreeType, runCounter, file, delimiter, linesInFile)
          case CSRArrayType.entryName => resolvedWL.benchmark(benchmarkConfig, recorder, CSRArrayType, runCounter, file, delimiter, linesInFile)
          case CSRMapType.entryName   => resolvedWL.benchmark(benchmarkConfig, recorder, CSRMapType, runCounter, file, delimiter, linesInFile)
          case CSRTreeType.entryName  => resolvedWL.benchmark(benchmarkConfig, recorder, CSRTreeType, runCounter, file, delimiter, linesInFile)
          case _                      => throw new RuntimeException(s"Invalid graph type :${gt}")
        }
        runCounter += 1
      }
    }
  }
}