package com.example.eliadegabli.stockquote;

/**
 * Created by eliadegabli on 11/10/2015.
 */
public class StockInfo {

    String Name="";
    String YearLow="";
    String YearHigh="";
    String DaysLow="";
    String DaysHigh="";
    String LastTradePriceOnly="";
    String Change="";
    String DaysRange="";

    public StockInfo(String Name, String YearLow, String YearHigh, String DaysLow,
            String DaysHigh, String LastTradePriceOnly, String Change, String DaysRange){
        super();
        this.Name= Name;
        this.YearLow=YearLow;
        this.YearHigh=YearHigh;
        this.DaysLow=DaysLow;
        this.DaysHigh=DaysHigh;
        this.LastTradePriceOnly=LastTradePriceOnly;
        this.Change=Change;
        this.DaysRange=DaysRange;
    }
}
