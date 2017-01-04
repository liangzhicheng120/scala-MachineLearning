package com.xinrui.ml.iris;
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.{ LabeledPoint, LinearRegressionWithSGD }
import org.apache.spark.{ SparkConf, SparkContext }
/**
 * 使用线性回归分析萼片长和宽
 */
object irisLinearRegression {
  def main(arr: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisLinearRegression ") //设定名称
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = sc.textFile("D:\\workspace\\scala-workspace\\MachineLearning\\src\\com\\xinrui\\ml\\text\\LinearRegression.txt")
    val parsedData = data.map { line =>
      val parts = line.split('|')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).toDouble))
    }.cache()
    val model = LinearRegressionWithSGD.train(parsedData, 10, 0.1) //创建模型
    println("回归公式为: y = " + model.weights + " * x + " + model.intercept) //打印回归公式
  }
}
