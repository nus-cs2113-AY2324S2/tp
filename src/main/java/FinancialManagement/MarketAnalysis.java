package FinancialManagement;

public class MarketAnalysis {

    public void simulateCompetitorBehavior() {
        double marketShareChange = Math.random() - 0.5; // 随机增减市场份额
        System.out.println("Market share change due to competitors: " + marketShareChange + "%");
    }

    public void forecastCustomerDemand() {
        double demandIncrease = Math.random() * 10; // 随机增加需求
        System.out.println("Forecasted customer demand increase: " + demandIncrease + "%");
    }
}