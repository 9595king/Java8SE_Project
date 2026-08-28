package mylab.book.control;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import mylab.book.entity.*;

public class StatisticsAnalyzer {

    private static String getPublicationType(Publication pub) {
        if (pub instanceof Novel) return "소설";
        if (pub instanceof ReferenceBook) return "참고서";
        if (pub instanceof Magazine) return "잡지";
        return "기타";
    }

    public static Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Integer> sumMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();

        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            sumMap.put(type, sumMap.getOrDefault(type, 0) + pub.getPrice());
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> avgMap = new HashMap<>();
        for (String type : sumMap.keySet()) {
            avgMap.put(type, (double) sumMap.get(type) / countMap.get(type));
        }
        return avgMap;
    }

    public static Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> countMap = new HashMap<>();
        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> distMap = new HashMap<>();
        int total = publications.length;
        for (String type : countMap.keySet()) {
            distMap.put(type, ((double) countMap.get(type) / total) * 100);
        }
        return distMap;
    }

    public static double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int count = 0;
        for (Publication pub : publications) {
            if (pub.getPublishDate() != null && pub.getPublishDate().startsWith(year)) {
                count++;
            }
        }
        return ((double) count / publications.length) * 100;
    }

    public static void printStatistics(Publication[] publications) {
        DecimalFormat priceFmt = new DecimalFormat("#,##0");
        DecimalFormat pctFmt = new DecimalFormat("0.00");

        Map<String, Double> avgPrices = calculateAveragePriceByType(publications);
        Map<String, Double> distributions = calculatePublicationDistribution(publications);
        double ratio2007 = calculatePublicationRatioByYear(publications, "2007");

        System.out.println("===== 출판물 통계 분석 =====");
        System.out.println("1. 타입별 평균 가격:");
        System.out.println("   - 소설: " + priceFmt.format(avgPrices.getOrDefault("소설", 0.0)) + "원");
        System.out.println("   - 참고서: " + priceFmt.format(avgPrices.getOrDefault("참고서", 0.0)) + "원");
        System.out.println("   - 잡지: " + priceFmt.format(avgPrices.getOrDefault("잡지", 0.0)) + "원");

        System.out.println("\n2. 출판물 유형 분포:");
        System.out.println("   - 소설: " + pctFmt.format(distributions.getOrDefault("소설", 0.0)) + "%");
        System.out.println("   - 참고서: " + pctFmt.format(distributions.getOrDefault("참고서", 0.0)) + "%");
        System.out.println("   - 잡지: " + pctFmt.format(distributions.getOrDefault("잡지", 0.0)) + "%");

        System.out.println("\n3. 2007년에 출판된 출판물 비율: " + pctFmt.format(ratio2007) + "%");
    }
}