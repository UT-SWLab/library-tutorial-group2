package investing;
import java.util.List;

public class Portfolio {
    private StockService stockService;
    private List<Stock> stocks;

    public StockService getStockService() {
        return stockService;
    }

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public double getMarketValue(){
        double marketValue = 0.0;

        for(Stock stock:stocks){
            marketValue += stockService.getPrice(stock) * stock.getQuantity();
        }
        return marketValue;
    }

    public double getPortfolioBeta(){
        double beta = 0;
        double marketValue = getMarketValue();
        for(Stock stock:stocks){
            double value = stockService.getPrice(stock) * stock.getQuantity();
            beta += (value/marketValue) * stockService.getBeta(stock);
        }
        return beta;
    };

    public double getPortfolioDividendAmount(){
        double dividend = 0.0;

        for(Stock stock:stocks){
            double div= stockService.getDividend(stock);
            double quant =stock.getQuantity();
            double price = stockService.getPrice(stock);
            dividend += stockService.getDividend(stock) * stock.getQuantity() *stockService.getPrice(stock);
        }

        return dividend;
    }
}