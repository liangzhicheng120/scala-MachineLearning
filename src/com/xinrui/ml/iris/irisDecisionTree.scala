package com.xinrui.ml.iris;
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{ SparkContext, SparkConf }
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.util.MLUtils
import org.apache.log4j.{ Level, Logger }

/**
 * 使用决策树对数据集进行分类
 */
object irisDecisionTree {
  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("irisDecisionTree ") //设定名称
    Logger.getLogger("org").setLevel(Level.ERROR) // 定义日志输出级别
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = MLUtils.loadLibSVMFile(sc, "D:\\workspace\\scala-workspace\\scala-MachineLearning\\src\\com\\xinrui\\ml\\text\\DecisionTree.txt") //输入数据集
    val numClasses = 5 //设定分类数量
    val categoricalFeaturesInfo = Map[Int, Int]() //设定输入格式
    val impurity = "entropy" //设定信息增益计算方式
    val maxDepth = 5 //设定树高度	
    val maxBins = 3 //设定分裂数据集
    val model = DecisionTree.trainClassifier(data, numClasses, categoricalFeaturesInfo,
      impurity, maxDepth, maxBins) //建立模型
    val test = Vectors.dense(Array(5.1,3.5,1.4,0.2))
    val result = model.predict(test)
    println("决策树预测结果是：" + result + "\n")
  }
}
