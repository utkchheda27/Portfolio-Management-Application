import React, { useEffect, useState } from "react";
import axios from "axios";
import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer } from "recharts";
import StockLineChart from "./StockLineChart";


const monthToNumber = {
  January: 1,
  February: 2,
  March: 3,
  April: 4,
  May: 5,
  June: 6,
  July: 7,
  August: 8,
  September: 9,
  October: 10,
  November: 11,
  December: 12,
};

const StockProfitBarChart = () => {
  const [profitData, setProfitData] = useState({});
  const [lineChartData, setLineChartData] = useState({});

  useEffect(() => {
    const fetchData = async () => {
      try {

        const stockResponse = await axios.get("http://localhost:8080/stockdata/all");
        const stockData = stockResponse.data;

     
        const instrumentResponse = await axios.get("http://localhost:8080/instruments");
        const instrumentData = instrumentResponse.data;

        const stockInstruments = instrumentData.filter(instrument => instrument.type === "stock");

      
        const profitDataByStock = {};
        const lineChartDataByStock = {};

        stockInstruments.forEach((instrument) => {
          const tickerSymbol = instrument.tickerSymbol;
          const avgBuyPrice = instrument.averageBuyPrice;

          
          const filteredStockData = stockData
            .filter(stock => stock.tickerSymbol === tickerSymbol)
            .sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp)); 


          lineChartDataByStock[tickerSymbol] = filteredStockData.map(stock => ({
            date: stock.timestamp,
            close: stock.close,
          })).reverse(); 
      
          const monthlyProfitMap = {};
          filteredStockData.forEach((stock) => {
            const date = new Date(stock.timestamp);
            const month = date.toLocaleString("default", { month: "long" });
            const closePrice = stock.close;
            const profit = closePrice - avgBuyPrice;

            if (monthToNumber[month] !== undefined) { 
              if (!monthlyProfitMap[month]) {
                monthlyProfitMap[month] = 0;
              }
              monthlyProfitMap[month] += profit;
            }
          });

         
          profitDataByStock[tickerSymbol] = Object.keys(monthlyProfitMap)
            .map(month => ({
              month,
              profit: Math.abs(monthlyProfitMap[month]), 
            }))
            .filter(data => monthToNumber[data.month] !== undefined) 
            .sort((a, b) => monthToNumber[a.month] - monthToNumber[b.month])
            .reverse(); 
        });

        setProfitData(profitDataByStock);
        setLineChartData(lineChartDataByStock);
        console.log("profit", profitDataByStock);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <h2 style={{ textAlign: "center" }}>Monthly Timeseries and Stock Profit Comparison Charts</h2>
      {Object.keys(profitData).map((tickerSymbol) => (
        <div key={tickerSymbol} style={{ display: "flex", justifyContent: "space-between", marginBottom: "40px" }}>
          <div style={{ width: "48%" }}>
            <h3 style={{ textAlign: "center" }}>{tickerSymbol} - Line Chart</h3>
            <ResponsiveContainer width="130%" height={300}>
              <StockLineChart data={lineChartData[tickerSymbol]} />
            </ResponsiveContainer>
          </div>
          <div style={{ width: "48%" }}>
            <h3 style={{ textAlign: "center" }}>{tickerSymbol} - Monthly Profit</h3>
            <ResponsiveContainer width="100%" height={300}>
              <BarChart data={profitData[tickerSymbol]} margin={{ top: 20, right: 20, left: 20, bottom: 20 }}>
                <XAxis dataKey="month" />
                <YAxis />
                <Tooltip />
                <Bar dataKey="profit" fill="#00C49F" />
              </BarChart>
            </ResponsiveContainer>
          </div>
        </div>
      ))}
    </div>
  );
};

export default StockProfitBarChart;
