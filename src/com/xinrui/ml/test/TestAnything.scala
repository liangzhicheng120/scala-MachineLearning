package com.xinrui.ml.test

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level

/**
 * 测试切词
 */
object TestAnything {

  def main(arr: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("TestAnything")
    val sc = new SparkContext(conf)
    Logger.getLogger("org").setLevel(Level.ERROR)
    val source = sc.textFile("D:\\workspace\\scala-workspace\\scala-MachineLearning\\src\\com\\xinrui\\ml\\test\\冒险.txt")
    val result = source.map {
      line ⇒
        line.split(",")(0) map {
          word ⇒ word
        }
    }
    result.foreach(println)
  }

  /**
   * 返回切词后的数据
   */
  def changeWord(word: String): String = {
    if (word.equalsIgnoreCase("三少爷的剑")) return (word + "龙珠,") else return word
  }
}