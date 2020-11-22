package hvd.edu.benchmark

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.workload.{ BFWorkLoad, DFWorkLoad, FindEdgesRandomNodeWorkLoad, GraphType, GraphTypes, LoadGraphWorkLoad, WorkloadType }
import hvd.edu.graph.Node

trait WorkLoad {
  def benchmark[N <: Node](benchmarkConfig: BenchmarkConfig, recorder: Recorder,
                           gt: GraphType, iteration: Int, file: String, delimiter: String)
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
          file: String, delimiter: String) = {
    val resolvedWL = resolveWork(w).getOrElse(throw new RuntimeException(s"Must pass a workload to benchmark"))

    GraphTypes.values.foreach { gt =>
      var runCounter = 1
      while (runCounter <= benchmarkConfig.numberRuns) {
        resolvedWL.benchmark(benchmarkConfig, recorder, gt, runCounter, file, delimiter)
        runCounter += 1
      }
    }
  }
}