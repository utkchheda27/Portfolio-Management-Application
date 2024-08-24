import React, { useEffect, useState } from "react";
import "./Dashboard.css"; // Import the CSS file for styling
import {Box} from "@mui/material";
import InstrumentPieChart from "./InstrumentPieChart";
import StockProfitBarChart from "./StockProfitBarChart";
import axios from "axios";

const App = () => {
  const [stockData, setStockData] = useState([]);

  const getStockData = async () => {
    try {
      const response = await axios.get("http://localhost:8080/stockdata/all");
      if (Array.isArray(response.data)) {
      
        const formattedData = response.data
          .map((stock) => ({
            tickerSymbol: stock.tickerSymbol,
            date: stock.timestamp,
            close: stock.close,
          }))
          .sort((a, b) => new Date(b.date) - new Date(a.date)); 

        setStockData(formattedData);
      } else {
        console.error("Unexpected data format:", response.data);
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    getStockData();
  }, []);
}


const graphsDashboard = ({ stockData }) => {
  return (
    <div>
      <Box mt={4}>
        <InstrumentPieChart />
      </Box>
      <Box mt={4}>
        <StockProfitBarChart stockData={stockData} />{" "}
       
      </Box>
    </div>
  );
};
export default graphsDashboard;
