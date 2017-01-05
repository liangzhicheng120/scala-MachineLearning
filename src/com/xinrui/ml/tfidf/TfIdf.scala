package com.xinrui.ml.tfidf
import org.apache.spark.{ SparkContext, SparkConf }
import org.apache.spark.ml.feature.{ HashingTF, IDF, Tokenizer }
import org.apache.log4j.{ Level, Logger }
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
object TfIdf {
  case class RawDataRecord(category: String, text: String)
  val conf = new SparkConf().setMaster("local").setAppName("TfIdf")
  val sc = new SparkContext(conf)
  val sqlContext = new org.apache.spark.sql.SQLContext(sc)
  import sqlContext.implicits._

  def main(arr: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR) // 定义日志输出级别

    //将原始数据映射到DataFrame中，字段category为分类编号，字段text为分好的词，以空格分隔
    var srcDF = sc.textFile("D:\\workspace\\scala-workspace\\scala-MachineLearning\\src\\com\\xinrui\\ml\\tfidf\\1.txt").map {
      x =>
        var data = x.split(",")
        RawDataRecord(data(0), data(1))
    }.toDF()

    srcDF.select("category", "text").take(2).foreach(println)

    //将分好的词转换为数组
    var tokenizer = new Tokenizer().setInputCol("text").setOutputCol("words")
    var wordsData = tokenizer.transform(srcDF)

    wordsData.select($"category", $"text", $"words").take(2).foreach(println)

    //将每个词转换成Int型，并计算其在文档中的词频（TF）
    var hashingTF =
      new HashingTF().setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(100)
    var featurizedData = hashingTF.transform(wordsData)
    //计算TF-IDF值
    var idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
    var idfModel = idf.fit(featurizedData)
    var rescaledData = idfModel.transform(featurizedData)
    rescaledData.select($"category", $"words", $"features").take(2).foreach(println)

  }
}