import React from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
  Cell,
} from "recharts";

const ProfitLossChart = ({ data }) => {
  return (
    <ResponsiveContainer width="100%" height={300}>
      <BarChart data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="tickerSymbol" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Bar dataKey="profitOrLoss">
          {data.map((entry, index) => (
            <Cell
              key={`cell-${index}`}
              fill={entry.profitOrLoss >= 0 ? "green" : "red"}
            />
          ))}
        </Bar>
      </BarChart>
    </ResponsiveContainer>
  );
};

export default ProfitLossChart;
