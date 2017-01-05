package com.xinrui.ml.iris;
import org.apache.spark.{ SparkContext, SparkConf }
import org.apache.spark.mllib.tree.GradientBoostedTrees
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel
import org.apache.spark.mllib.util.MLUtils
import org.apache.log4j.{ Level, Logger }
import org.apache.spark.mllib.linalg.Vectors
/**
 * 使用梯度提升算法GBDT（Gradient Boosting Decision Tree）迭代决策树对数据集进行分类
 */
object GDTree {

  def main(args: Array[String]) {
    val conf = new SparkConf() //创建环境变量
      .setMaster("local") //设置本地化处理
      .setAppName("GDTree") //设定名称
    Logger.getLogger("org").setLevel(Level.ERROR) // 定义日志输出级别
    val sc = new SparkContext(conf) //创建环境变量实例
    val data = MLUtils.loadLibSVMFile(sc, "D:\\workspace\\scala-workspace\\scala-MachineLearning\\src\\com\\xinrui\\ml\\text\\DecisionTree.txt") //输入数据集
    val boostingStrategy = BoostingStrategy.defaultParams("Classification") //创建算法类型
    boostingStrategy.numIterations = 3 //迭代次数
    boostingStrategy.treeStrategy.numClasses = 2 //分类数目
    boostingStrategy.treeStrategy.maxDepth = 5 //决策树最高层数
    boostingStrategy.treeStrategy.categoricalFeaturesInfo = Map[Int, Int]() //数据格式
    val model = GradientBoostedTrees.train(data, boostingStrategy) //训练模型
    val test = Vectors.dense(Array(5.1, 3.5, 1.4, 0.2))
    val result = model.predict(test)
    println("梯度提升算法预测的分类：" + result)
  }
}
