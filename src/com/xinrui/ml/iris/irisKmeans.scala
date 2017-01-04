package com.xinrui.ml.iris;
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.regression.LabeledPoint
/**
 * 使用kmeans对数据进行聚类
 */
object irisKmeans {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisKmeans") //设定名称
    val sc = new SparkContext(conf)  //创建环境变量实例
    val data = sc.textFile("D:\\workspace\\scala-workspace\\scala-MachineLearning\\src\\com\\xinrui\\ml\\text\\IrisAll.txt")
    val parsedData = data.map { line =>
      Vectors.dense(line.split('|').map(_.toDouble))
    }.cache()
    val numClusters = 3 //最大分类数
    val numIterations = 20 //迭代次数
    val model = KMeans.train(parsedData, numClusters, numIterations) //训练模型
    model.clusterCenters.foreach(println) //打印中心点坐标
  }

}
 