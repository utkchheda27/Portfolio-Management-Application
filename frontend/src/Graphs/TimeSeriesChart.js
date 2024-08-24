
import React from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';
import jsonData from '../company.json';

const transformData = (data) => {
  return data.map(item => ({
    date: item.timestamp,
    close: item.close,
    open: item.open,
    high: item.high,
    low: item.low
  }));
};

const TimeSeriesChart = () => {
  const data = transformData(jsonData);

  return (
    <LineChart width={600} height={400} data={data}>
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="date" />
      <YAxis />
      <Tooltip />
      <Legend />
      <Line type="monotone" dataKey="close" stroke="#8884d8" dot={false} />
      <Line type="monotone" dataKey="open" stroke="#82ca9d" dot={false} />
      <Line type="monotone" dataKey="high" stroke="#ff7300" dot={false} />
      <Line type="monotone" dataKey="low" stroke="#ff0000" dot={false}/>
    </LineChart>
  );
};

export default TimeSeriesChart;
