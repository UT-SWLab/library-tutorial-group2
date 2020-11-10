package investing;
import org.junit.Test;
import java.util.*;

import static org.mockito.Mockito.*;
import org.junit.*;

import investing.*;
//import jdk.jfr.Timestamp;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioTest {
    private static final double DELTA = 0.0001; //the biggest difference there can be between the expected value
    //and the actual value. This is because doubles can differ in precision.

    //Attaches a runner with the test class to initialize the test data
    @InjectMocks
    Portfolio portfolio = new Portfolio();

    //Mock annotation is used to create the mock object to be injected
    @Mock
    StockService stockService;

    public void setUp(){
        portfolio.setStockService(stockService);

        List<Stock> stocks = new ArrayList<Stock>();
        Stock googleStock = new Stock("1","Google", 10);
        Stock microsoftStock = new Stock("2","Microsoft",100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        //mock the behavior of stock service to return the value of various stocks
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        when(stockService.getBeta(googleStock)).thenReturn(.15);
        when(stockService.getBeta(microsoftStock)).thenReturn(.75);

        when(stockService.getDividend(microsoftStock)).thenReturn(.05);
        when(stockService.getDividend(googleStock)).thenReturn(.02);

    }

    @Test
    public void testPortfolioValue(){
        setUp();
        //Creates a list of stocks to be added to the portfolio
        double marketValue = portfolio.getMarketValue();
        double correctMarketValue = 100500.0;
        Assert.assertEquals(correctMarketValue , marketValue, DELTA);
    }

    @Test
    public void portfolioValue(){
        portfolio.setStockService(stockService);

        List<Stock> stocks = new ArrayList<Stock>();
        Stock googleStock = new Stock("1","Google", 10);
        Stock microsoftStock = new Stock("2","Microsoft",100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        //mock the behavior of stock service to return the value of various stocks
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        portfolio.setStockService(stockService);

        double marketValue = portfolio.getMarketValue();
        double correctMarketValue = 100500.0;
        Assert.assertEquals(correctMarketValue , marketValue, DELTA);

        verify(stockService).getPrice(googleStock);
    }

    @Test
    public void testPortfolioBeta(){
        setUp();
        portfolio.setStockService(stockService);

        double portfolioBeta = portfolio.getPortfolioBeta();
        double correctBeta = 0.74701492537;
        Assert.assertEquals(correctBeta,portfolioBeta, DELTA);
    }

    @Test
    public void portfolioBeta(){
        portfolio.setStockService(stockService);

        List<Stock> stocks = new ArrayList<Stock>();
        Stock googleStock = new Stock("1","Google", 10);
        Stock microsoftStock = new Stock("2","Microsoft",100);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        stocks.add(googleStock);
        stocks.add(microsoftStock);

        when(stockService.getBeta(googleStock)).thenReturn(.15);
        when(stockService.getBeta(microsoftStock)).thenReturn(.75);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        double portfolioBeta = portfolio.getPortfolioBeta();
        double correctBeta = 0.74701492537;
        Assert.assertEquals(correctBeta,portfolioBeta, DELTA);

        verify(stockService).getBeta(googleStock);
    }

    @Test
    public void testPortfolioDividend(){
        setUp();
        portfolio.setStockService(stockService);

        double portfolioDividend = portfolio.getPortfolioDividendAmount();
        double correctDividend = 5010;
        Assert.assertEquals(correctDividend , portfolioDividend, DELTA);
    }
}

