/*package com.neueda.portfolio.Controller;

import com.neueda.portfolio.Entity.StockData;
import com.neueda.portfolio.Repo.StockDataRepo;
import com.neueda.portfolio.Service.StockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stockdata")
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class StockDataController {
    @Autowired
    private StockDataService stockDataService;
    @GetMapping("/all")
    public List<StockData> orderBook(){
        return stockDataService.getStockData();
    }

}*/
package com.neueda.portfolio.Controller;

import com.neueda.portfolio.Entity.StockData;
import com.neueda.portfolio.Service.StockDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stockdata")
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
public class StockDataController {

    private static final Logger logger = LoggerFactory.getLogger(StockDataController.class);

    @Autowired
    private StockDataService stockDataService;

    @GetMapping("/all")
    public List<StockData> orderBook() {
        logger.info("Entered orderBook method");
        try {
            logger.debug("Fetching all stock data");
            List<StockData> stockDataList = stockDataService.getStockData();
            logger.info("Successfully fetched {} stock data entries", stockDataList.size());
            return stockDataList;
        } catch (Exception e) {
            logger.error("Error fetching stock data", e);
            throw e;
        } finally {
            logger.info("Exiting orderBook method");
        }
    }
}

