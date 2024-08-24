import React from "react";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  Legend,
} from "recharts";


const filterAlternateDays = (data) => {
  return data.filter((_, index) => index % 2 === 0);
};

const StockLineChart = ({ data }) => {
 
  const tickers = [...new Set(data.map((item) => item.tickerSymbol))];

  return (
    <div>
      {tickers.map((ticker, index) => {
      
        const tickerData = filterAlternateDays(
          data.filter((item) => item.tickerSymbol === ticker)
        );

        return (
          <div
            key={index}
            style={{
              marginBottom: "40px",
              width: "66%",
              marginLeft: "auto",
              marginRight: "auto",
            }}
          >
            <ResponsiveContainer width="100%" height={300}>
              <LineChart data={tickerData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="date" />
                <YAxis
                  label={{
                    value: "Price (Closing Price)",
                    angle: -90,
                    position: "left",
                  }}
                />
                <Tooltip />
                <Legend />
                <Line
                  type="monotone"
                  dataKey="close"
                  stroke="#8884d8"
                  dot={false}
                />
              </LineChart>
            </ResponsiveContainer>
          </div>
        );
      })}
    </div>
  );
};

export default StockLineChart;
