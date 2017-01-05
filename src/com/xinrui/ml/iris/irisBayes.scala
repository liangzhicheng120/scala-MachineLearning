package com.xinrui.ml.iris;
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{ SparkContext, SparkConf }
import org.apache.spark.mllib.classification.{ NaiveBayes, NaiveBayesModel }
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.log4j.{ Level, Logger }
/**
 * 使用贝叶斯分类判别数据集类别
 */
object irisBayes {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisBayes") //设定名称
    Logger.getLogger("org").setLevel(Level.ERROR) // 定义日志输出级别
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = MLUtils.loadLabeledPoints(sc, "D:\\workspace\\scala-workspace\\scala-MachineLearning\\src\\com\\xinrui\\ml\\text\\bayes.txt") //读取数据集
    val model = NaiveBayes.train(data, 1.0) //训练贝叶斯模型
    val test = Vectors.dense(5.1,3.5,1.4,0.2) //创建待测定数据
    val result = model.predict(test) //打印结果
    print("贝叶斯预测类别：" + result + "\n")
  }
}
