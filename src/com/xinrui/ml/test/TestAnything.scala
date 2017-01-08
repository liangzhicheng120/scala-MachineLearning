package com.xinrui.ml.test

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.tools.nsc.doc.model.Val
import java.util.Collection
import java.sql.DriverManager
import java.sql.Connection
import org.apache.spark.sql.SQLContext
import java.util.Properties
import org.apache.spark.sql.Row
import scala.xml.Null

/**
 * 测试连接数据库
 */
object TestAnything {
  def main(arr: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("TestAnything")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc);
    Logger.getLogger("org").setLevel(Level.ERROR)
    val tableDF = sqlContext.jdbc("jdbc:mysql://localhost/mydemo?user=root&password=8532936", "clustertab")
    //    tableDF.show()
    //    tableDF.printSchema()   // 打印表结构
    val frame1 = tableDF.filter(tableDF("filmId") === 85) //注意等于号，须为 "==="
    frame1.show
    val frame2 = tableDF.select("filmId", "filmName")
    frame2.show
    val frame3 = tableDF.select(tableDF("filmId") + 1, tableDF("filmName")).map(test)
    frame3.foreach(println)

    //    tableDF.foreach(row => (println(row.getString(1)))) //打印每一行位于第二列的name. （列计数从0开始）
  }
  def test(row: Row): Any = {
    if (row.getString(1).equalsIgnoreCase("航海王之黄金城")) {
      return row.mkString(",")
    } else { return Null }
  }
}