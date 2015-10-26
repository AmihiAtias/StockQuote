package com.example.eliadegabli.stockquote;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by eliadegabli on 06/10/2015.
 */
public class stockInfoActivity extends Activity{
    private static final String TAG="STOCKQUOTE";

    TextView companyNameTextView;
    TextView yearLowTextView;
    TextView yearHighTextView;
    TextView daysLowTextView;
    TextView daysHighTextView;
    TextView lastTradePriceTextView;
    TextView changeTextView;
    TextView daysRangeTextView;

    static final String KEY_ITEM= "quote";
    static final String KEY_NAME= "Name";
    static final String KEY_YEAR_LOW= "YearLow";
    static final String KEY_YEAR_HIGH= "TearHigh";
    static final String KEY_DAYS_LOW= "DaysLow";
    static final String KEY_DAYS_HIGH= "DaysHigh";
    static final String KEY_LEST_TRADE_PRICE= "LastTradePriceOnly";
    static final String KEY_CHSNGE= "Change";
    static final String KEY_DAYS_RANGE= "DaysRange";

    String Name="";
    String YearLow="";
    String YearHigh="";
    String DaysLow="";
    String DaysHigh="";
    String LastTradePriceOnly="";
    String Change="";
    String DaysRange="";

    String YahooUrlFirst= "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22";
    String YahooUrllast= "%22)&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_stock_info);
        Intent intent=getIntent();
        String stockSymbol=intent.getStringExtra(MainActivity.STOCK_SYMBOL);

        companyNameTextView=(TextView) findViewById(R.id.companyNameTextview);
        yearLowTextView=(TextView) findViewById(R.id.YareLowtextView);
        yearHighTextView=(TextView) findViewById(R.id.YearHightextView);
        daysLowTextView=(TextView) findViewById(R.id.DaysLowtextView2);
        daysHighTextView=(TextView) findViewById(R.id.daysHightextView2);
        lastTradePriceTextView=(TextView) findViewById(R.id.LastPricetextView2);
        changeTextView=(TextView) findViewById(R.id.changtextView2);
        daysRangeTextView=(TextView) findViewById(R.id.DayliPriceRAngetextView2);

        Log.d(TAG, "Before URL creation" + stockSymbol);

        final String yqlURL= YahooUrlFirst + stockSymbol + YahooUrllast;

        new MyAsyncTask().execute();
    }

    private class MyAsyncTask extends AsyncTask<String,String,String>{
        protected String doInBackground(String... args){

            try{
                URL url=new URL(args[0]);
                URLConnection connection;
                connection=url.openConnection();

                HttpURLConnection httpConnection= (HttpURLConnection) connection;

                int responceCode = httpConnection.getResponseCode();

                if(responceCode == HttpURLConnection.HTTP_OK){
                    InputStream in=httpConnection.getInputStream();

                    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();

                    DocumentBuilder db= dbf.newDocumentBuilder();

                    Document dom=db.parse(in);

                    org.w3c.dom.Element docEle=dom.getDocumentElement();

                    NodeList nl=docEle.getElementsByTagName("quote");

                    if(nl!=null && nl.getLength()>0){
                        for(int i=0;i<nl.getLength();i++){

                            StockInfo theStock=getStockInfomation(docEle);

                            Name="";
                            YearLow="";
                            YearHigh="";
                            DaysLow="";
                            DaysHigh="";
                            LastTradePriceOnly="";
                            Change="";
                            DaysRange="";
                        }
                    }


                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            return null;
        }
//dffdf
    }

}
