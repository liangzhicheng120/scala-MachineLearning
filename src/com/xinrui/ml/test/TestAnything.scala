package com.xinrui.ml.test

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.tools.nsc.doc.model.Val
import java.util.Collection
import java.sql.DriverManager
import java.sql.Connection
/**
 * 测试连接数据库
 */
object TestAnything {
  def main(arr: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("TestAnything")
    val sc = new SparkContext(conf)
    Logger.getLogger("org").setLevel(Level.ERROR)

    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost/mydemo"
    val username = "root"
    val password = "8532936"

    var connection: Connection = null

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM clustertab")
      while (resultSet.next()) {
        val filmId = resultSet.getString("filmId")
        val filmName = resultSet.getString("filmName")
        val direct = resultSet.getString("direct")
        println("filmId, filmName,direct = " + filmId + ", " + filmName + ", " + direct)
      }
    } catch {
      case e            => e.printStackTrace
      case _: Throwable => println("ERROR")
    }
    connection.close()
  }

}