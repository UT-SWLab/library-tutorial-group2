import java.util.*;

import static org.mockito.Mockito.*;
import org.junit.*;

import investing.*;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioTester {

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

        when(stockService.getDividend(microsoftStock)).thenReturn(.02);
        when(stockService.getDividend(googleStock)).thenReturn(.05);
    }

    @Test
    public void testPortfolioValue(){
        setUp();
        //Creates a list of stocks to be added to the portfolio
        double marketValue = portfolio.getMarketValue();
        double correctMarketValue = 100500.0;		
        Assert.assertEquals(correctMarketValue , marketValue);
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
        Assert.assertEquals(correctMarketValue , marketValue);

        verify(stockService).getPrice(googleStock);
    }

    @Test
    public void testGetBeta(){
        portfolio.setStockService(stockService);

        double portfolioBeta = portfolio.getPortfolioBeta();
        double correctBeta = 0.74701492537;
        Assert.assertEquals(correctBeta,portfolioBeta);
    }

    @Test
    public void portfolioBeta(){
        portfolio.setStockService(stockService);

        List<Stock> stocks = new ArrayList<Stock>();
        Stock googleStock = new Stock("1","Google", 10);
        Stock microsoftStock = new Stock("2","Microsoft",100);	
 
        stocks.add(googleStock);
        stocks.add(microsoftStock);

        when(stockService.getBeta(googleStock)).thenReturn(.15);
        when(stockService.getBeta(microsoftStock)).thenReturn(.75);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        double portfolioBeta = portfolio.getPortfolioBeta();
        double correctBeta = 0.74701492537;
        Assert.assertEquals(correctBeta,portfolioBeta);

        verify(stockService).getBeta(googleStock);
    }

    @Test

}
