package com.xinrui.ml.action

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.recommendation.{ ALS, Rating }
/**
 * 测试ALS推荐算法
 */
object TestALS {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("ALS")
    val sc = new SparkContext(conf)
    val data = sc.textFile("D:\\ALS.txt")
    val ratings = data.map(_.split(" ") match {
      case Array(user, item, rate) ⇒ Rating(user.toInt, item.toInt, rate.toDouble)
    })
    val rank = 2
    val numIterations = 2
    val model = ALS.train(ratings, rank, numIterations, 0.01)
    val rs = model.recommendProducts(2, 1)
    rs.foreach(println)
  }

}