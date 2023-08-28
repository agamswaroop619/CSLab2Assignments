import java.util.Scanner;

class Stock {
    private String tickerSymbol;
    private int quantity = 0; // total shares purchased
    private double totalCost = 0; // total cost for all shares
    private double currentPrice;

    public Stock(String tickerSymbol, double currentPrice) {
        this.tickerSymbol = tickerSymbol;
        this.currentPrice = currentPrice;
    }

    public void purchase(int num, double price) {
        totalCost += num * price;
        quantity += num;
    }

    public String getSymbol() {
        return tickerSymbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getProfit() {
        double currentValue = quantity * currentPrice;
        return (currentValue - totalCost);
    }
}

class Investor {
    private String investorName;
    private Stock[] cosList;

    public Investor(String name, Stock[] cosList) {
        this.investorName = name;
        this.cosList = cosList;
    }

    public String getInvestorName() {
        return investorName;
    }

    public Double totalProfit() {
        Double totalProfit = 0.0;
        for (Stock stock : cosList) {
            totalProfit += stock.getProfit();
        }
        return totalProfit;
    }

    public Stock mostProfitableStock() {
        Stock maxPStock = cosList[0];
        for (int l = 1; l < cosList.length; l++) {
            if (maxPStock.getProfit() < cosList[l].getProfit()) {
                maxPStock = cosList[l];
            }
        }
        return maxPStock;
    }

    public Stock leastProfitableStock() {
        Stock minPStock = cosList[0];
        for (int l = 1; l < cosList.length; l++) {
            if (minPStock.getProfit() > cosList[l].getProfit()) {
                minPStock = cosList[l];
            }
        }
        return minPStock;
    }

    public void displayStockSummary() {
        System.out.print("\n\nStock\tCurr. Price\tProfit/Loss\n");
        for (int k = 0; k < cosList.length; k++) {  // Start from 0
            System.out.printf("%s\t%.2f\t\t%.2f\n", cosList[k].getSymbol(), cosList[k].getCurrentPrice(),
                    cosList[k].getProfit());
        }
        System.out.printf("\n\n%s's most profitable scrip is: %s, currently priced at %.2f. Profit is $%.2f\n",
                getInvestorName(), mostProfitableStock().getSymbol(),
                mostProfitableStock().getCurrentPrice(), mostProfitableStock().getProfit());
        System.out.printf("%s should probably sell off ASAP: %s, currently priced at %.2f. Profit is $%.2f\n",
                getInvestorName(),
                leastProfitableStock().getSymbol(), leastProfitableStock().getCurrentPrice(),
                leastProfitableStock().getProfit());
        System.out.printf("Total Profit/Loss = %.2f\n", totalProfit());
    }
}

public class _2104373_2b {
    private static Investor I1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        readInput(sc);
        sc.close();
        System.out.println(I1.getInvestorName());
        I1.displayStockSummary();
    }

    public static void readInput(Scanner sc) {
        String investorInfo = sc.nextLine();
        String[] parts = investorInfo.split(",");
        String investorName = parts[0].trim();
        int nComps = Integer.parseInt(parts[1].trim());
        Stock[] cosList = new Stock[nComps];

        for (int i = 0; i < nComps; i++) {
            String stockInfo = sc.nextLine();
            String[] partsStocks = stockInfo.split(",");
            int numofTrades = 0;
            Double price;
            String stockName;

            if (partsStocks.length == 3) {
                stockName = partsStocks[0];
                price = Double.parseDouble(partsStocks[1].trim());
                numofTrades = Integer.parseInt(partsStocks[2].trim());
                cosList[i] = new Stock(stockName, price);
            }

            for (int j = 0; j < numofTrades; j++) {
                String purchaseInfo = sc.nextLine();
                String[] partsTrade = purchaseInfo.split(",");
                int numofStocks = Integer.parseInt(partsTrade[0].trim());
                double priceofPurchase = Double.parseDouble(partsTrade[1].trim());
                cosList[i].purchase(numofStocks, priceofPurchase);
            }
        }
        I1 = new Investor(investorName, cosList);
    }
}