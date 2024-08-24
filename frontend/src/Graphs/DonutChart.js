
import React from 'react';
import { PieChart, Pie, Cell, Legend, Tooltip } from 'recharts';
import jsonData from '../company.json'; 


const aggregateData = (data) => {
  const total = {
    open: 0,
    close: 0,
    high: 0,
    low: 0
  };
  
  data.forEach(item => {
  
    total.open += item.open;
    console.log("total open",total.open);
    total.close += item.close;
    total.high += item.high;
    total.low += item.low;
  });
  
  
  return [
    { name: 'Open', value: total.open },
    { name: 'Close', value: total.close },
    { name: 'High', value: total.high },
    { name: 'Low', value: total.low }
  ];
};

const DonutChart = () => {
  const data = aggregateData(jsonData);
  
  console.log(data);

  return (
    <PieChart width={600} height={400}>
      <Pie
        data={data}
        cx={300}
        cy={200}
        innerRadius={100}
        outerRadius={150}
        fill="#8884d8"
        paddingAngle={5}
        dataKey="value"
      >
        {data.map((entry, index) => (
          <Cell key={`cell-${index}`} fill={['#8884d8', '#82ca9d', '#ff7300', '#ff0000'][index]} />
        ))}
      </Pie>
      <Tooltip />
      <Legend />
    </PieChart>
  );
};

export default DonutChart;
