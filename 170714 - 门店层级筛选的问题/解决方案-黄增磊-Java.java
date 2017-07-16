

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShopLevel {

	public static void OneToTwoToJson() {
		String[] arrOne = { "商行||华北||龙华西路店", "商行||华北||龙华北路店", "农行||华东||龙华北路店", "工行||浦东||金桥路店", "中行||宝山||宝山路||石头街",
				"农行||总店" };

		/**
		 * 第一步：根据一维数组来定义二维数组的长度
		 */
		int XL = arrOne.length;
		int YL = 0;
		for (int i = 0; i < arrOne.length; i++) {
			String[] items = arrOne[i].split("\\|\\|");
			if (YL < items.length) {
				YL = items.length;
			}
		}
		String[][] arrTwo = new String[XL][YL];

		/**
		 * 第二步：将一维数组转化为二维数组
		 */

		for (int i = 0; i < arrOne.length; i++) { // 维度未知
			String[] items = arrOne[i].split("\\|\\|");
			for (int j = 0; j < items.length; j++) {
				arrTwo[i][j] = items[j];
			}
		}
		
		System.out.println();
		
		for (int i = 0; i < arrTwo.length; i++) { // 遍历二维数组
			for (int j = 0; j < arrTwo[i].length; j++) {
				if (arrTwo[i][j] != null) {
					System.out.print(arrTwo[i][j] + i + j + " ");
				}
			}
		}
		// ---------------层级装载----------------------------------------------

		/**
		 * 第四步：先遍历第一层级。用于第五步做外层循环次数
		 */
		Set<String> bankName = new HashSet<>();
		for (int i = 0; i < arrTwo.length; i++) {
			bankName.add(arrTwo[i][0]);
		}

		/**
		 * 第五步：
		 */
		Map<String, Object> title = new HashMap<>(); // 最终用来装载的集合
		for (String bName : bankName) {
			Set<String> areaName = new HashSet<>();

			for (int i = 0; i < arrTwo.length; i++) { // 循环装载第二层级
				for (int j = 0; j < arrTwo[i].length; j++) {
					if (arrTwo[i][0].equals(bName)) {
						areaName.add(arrTwo[i][1]);
					}
				}
			}

			Map<String, Object> itemsMap = new HashMap<>();
			for (String area : areaName) { // 遍历第二层级
				Set<String> shopName = new HashSet<>();
				for (int i = 0; i < arrTwo.length; i++) { // 循环装载第三层级
					if (arrTwo[i][0].equals(bName) && arrTwo[i][1].equals(area)) {
						if (arrTwo[i][2] != null) {
							shopName.add(arrTwo[i][2]);
						}
					}
				}
				itemsMap.put(area, shopName);
			}
			title.put(bName, itemsMap);
			// 最终装载：形式Map<String, Map<String, Set<String>>>
		}

		System.out.println("层级方式：");
		System.out.println(title);

	}

	public static void main(String[] args) {
		OneToTwoToJson();
	}
}
