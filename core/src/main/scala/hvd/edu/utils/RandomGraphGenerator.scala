package hvd.edu.utils

import scala.collection.immutable
import scala.collection.mutable.ListBuffer
import scala.util.Random

/*
std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);

    std::freopen("out.txt", "w", stdout);

    srand(time(NULL));

    int MAXN = std::atoi(argv[1]); //100
    std::vector<int> *edges = new std::vector<int>[MAXN + 2];
    int len = std::atoi(argv[2]); //10
    int len2 = -1;
    if (argc == 4) {
        len2 = std::atoi(argv[3]);//1000
    }
    // i = 1 <- 100
    for (int i = 1; i <= MAXN; ++i) {

        // edged from 1 to 10
        for (int k = edges[i].size() + 1; k <= len; ++k) {
            // bool flag = false;
            int x;
            //while (!flag) {
            x = rand() % MAXN + 1;
            while (x == i)
                x = rand() % MAXN + 1;
            /* for (int j : edges[i])
                 if (j == x) {
                     flag = true;
                     break;
                 }
             if (!flag) {*/
            edges[i].push_back(x);
            edges[x].push_back(i);
            // flag = false;
            //   break;
            //}

            // }
        }
    }


    uint64_t sum = 0;
    for (int i = 1; i <= MAXN; ++i)
        sum = sum + (uint64_t)edges[i].size();

    // printing total nodes and total edges
    std::cout << MAXN << " " << sum << std::endl;

    for (int i = 1; i <= MAXN; ++i) {
        std::cout << edges[i].size() << " ";
        for (int j : edges[i])
            std::cout << j << " ";
        std::cout << "\n";
        for (int j : edges[i])
            std::cout << rand() % 100 + 20 << " ";
        std::cout << "\n";

    }

    for (int i = 0; i < len2; ++i) {
        std::cout << rand() % MAXN + 1 << " " << rand() % MAXN + 1 << " " << rand() % 50 + 1 << "\n";
    }

    for (int i = 0; i < MAXN + 2; ++i)
        edges[i].clear();
    delete[]edges;

    return 0;
 */
object RandomGraphGenerator {

  def main(args: Array[String]): Unit = {

    val fileName = args(0)
    val numNodes = args(1).toInt
    val edges = numNodes + 2
    val maxEdgesLen = args(2).toInt
    val randomNum = Random

    val arrayOfEdges = Array.ofDim[List[Int]](10)
    for (i <- 1 to numNodes)
      for (k <- arrayOfEdges(i).length + 1 to maxEdgesLen) {
        val x = randomNum.nextInt(numNodes)
        arrayOfEdges(i) = x :: arrayOfEdges(i)
        arrayOfEdges(x) = i :: arrayOfEdges(x)
      }

    // write to the output file

  }

}
