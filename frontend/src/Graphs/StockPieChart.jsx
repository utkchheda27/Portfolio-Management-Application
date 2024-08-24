// import React from "react";
// import {
//   PieChart,
//   Pie,
//   Cell,
//   Tooltip,
//   Legend,
//   ResponsiveContainer,
// } from "recharts";

// const StockPieChart = ({ stockData }) => {
//   const data = [
//     { name: "Stock Price", value: stockData.stockPrice },
//     {
//       name: "52 Week High",
//       value: stockData.week52High - stockData.stockPrice,
//     },
//     { name: "52 Week Low", value: stockData.stockPrice - stockData.week52Low },
//   ];

//   const COLORS = ["#0088FE", "#00C49F", "#FFBB28"];

//   return (
//     <ResponsiveContainer width="100%" height={300}>
//       <PieChart>
//         <Pie
//           data={data}
//           cx="50%"
//           cy="50%"
//           innerRadius={60}
//           outerRadius={80}
//           fill="#8884d8"
//           paddingAngle={5}
//           dataKey="value"
//         >
//           {data.map((entry, index) => (
//             <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
//           ))}
//         </Pie>
//         <Tooltip />
//         <Legend />
//       </PieChart>
//     </ResponsiveContainer>
//   );
// };

// export default StockPieChart;
