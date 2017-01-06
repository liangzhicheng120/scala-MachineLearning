package com.xinrui.ml.read

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.tools.nsc.doc.model.Val
import java.util.Collection
/**
 * 测试读取文本并切分文本
 */
object ReadTxt {
  def main(arr: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("TestAnything")
    val sc = new SparkContext(conf)
    Logger.getLogger("org").setLevel(Level.ERROR)
    val source = sc.textFile("D:\\workspace\\scala-workspace\\scala-MachineLearning\\src\\com\\xinrui\\ml\\test\\冒险.txt").cache()
    //    for (word ← source) {
    //      val data = word.stripLineEnd.split(",")
    //      val content = data(0)
    //      if (content.equals("三少爷的剑1")) println(word) else println("NO")
    //    }
    val result = source.map(flag)
    for (w ← result) {
      println(w.mkString)
    }
  }

  def flag(sentence: String): List[String] = {
    //    val data = sentence.stripLineEnd.split(",")
    //    val result = if (data(0).equals(keyWord)) sentence else "NO"
    //    return result
    val result = ".*三少爷的剑.*".r findAllIn sentence
    return result.toList
  }
}