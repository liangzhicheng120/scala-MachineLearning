package com.xinrui.ml.test;

import org.junit.Test;
import com.hankcs.hanlp.HanLP;

/**
 * 
 * @ClassName: TestHanLPSeg
 * @Description: test HanLP API
 * @author liangzhicheng
 * @date 2017年1月9日 上午11:02:55
 *
 */
public class TestHanLPSeg {
	// test segment
	@Test
	public void testSegment() {
		System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
	}

}
