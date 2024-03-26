package FinancialManagement;

import java.util.HashMap;
import java.util.Map;

public class MarketReport {
    private Map<String, Double> economicIndicators;

    public MarketReport() {
        economicIndicators = new HashMap<>();
        // 填充一些模拟数据
        economicIndicators.put("GDP Growth Rate", 3.0); // 假设的增长率
        economicIndicators.put("Unemployment Rate", 5.0); // 假设的失业率

    }

    public void generateReport() {
        System.out.println("Market Report:");
        economicIndicators.forEach((indicator, value) -> System.out.println(indicator + ": " + value + "%"));
    }
}
