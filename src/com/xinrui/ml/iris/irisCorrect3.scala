package com.xinrui.ml.iris;
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{ SparkConf, SparkContext }
/**
 * 计算不同种类间的斯皮尔曼相关系数计算法
 */
object irisCorrect3 {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisCorrect3 ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val dataX = sc.textFile("D:\\workspace\\scala-workspace\\MachineLearning\\src\\com\\xinrui\\ml\\text\\Versicolor.Length.X.txt") //读取数据
      .flatMap(_.split(' ') //进行分割
        .map(_.toDouble)) //转化为Double类型
    val dataY = sc.textFile("D:\\workspace\\scala-workspace\\MachineLearning\\src\\com\\xinrui\\ml\\text\\Sepal.Length.Y.txt") //读取数据
      .flatMap(_.split(' ') //进行分割
        .map(_.toDouble)) //转化为Double类型
    val correlation: Double = Statistics.corr(dataX, dataY,"spearman") //计算不同数据之间的相关系数
    println("setosa和versicolor中Sepal.Length的相关系数为：" + correlation) //打印相关系数                                      
  }
}
