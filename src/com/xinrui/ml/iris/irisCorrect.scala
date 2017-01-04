package com.xinrui.ml.iris;
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{ SparkConf, SparkContext }
/**
 * 计算相同类别的不同属性的相关系数(相关性系数越大说明属性越接近,这两个属性可以舍弃其中一个)
 */
object irisCorrect {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("irisCorrect")
    val sc = new SparkContext(conf) //创建环境变量实例
    val dataX = sc.textFile("D:\\workspace\\scala-workspace\\MachineLearning\\src\\com\\xinrui\\ml\\text\\Sepal.Width.X.txt") //读取数据
      .flatMap(_.split(' ')
        .map(_.toDouble)) //转化为Double类型
    val dataY = sc.textFile("D:\\workspace\\scala-workspace\\MachineLearning\\src\\com\\xinrui\\ml\\text\\Sepal.Length.Y.txt") //读取数据
      .flatMap(_.split(' ')
        .map(_.toDouble)) //转化为Double类型
    val correlation: Double = Statistics.corr(dataX, dataY) //计算不同数据之间的相关系数
    println(correlation) //打印结果
  }
}