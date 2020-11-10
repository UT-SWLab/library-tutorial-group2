package investing;
import java.util.*;

public interface StockService {
    public double getPrice(Stock stock);
    public double getBeta(Stock stock);
    public double getDividend(Stock stock);
}