import React, { useEffect, useState } from "react";
import axios from "axios";
import {
  PieChart,
  Pie,
  Cell,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

const COLORS = [
  "#0088FE",
  "#00C49F",
  "#FFBB28",
  "#FF8042",
  "#A3A1FB",
  "#FF4567",
];

function InstrumentPieChart() {
  const [instrumentData, setInstrumentData] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/instruments")
      .then((response) => {
        if (Array.isArray(response.data)) {
          setInstrumentData(response.data);
        } else {
          console.error("Unexpected data format:", response.data);
        }
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);


  const pieData = instrumentData
    .filter((instrument) => instrument.type === "stock")
    .map((instrument) => ({
      name: instrument.companyName,
      value: instrument.averageBuyPrice * instrument.volume,
    }));


  const pieData2 = instrumentData
    .filter((instrument) => instrument.type === "bond")
    .map((instrument) => ({
      name: instrument.companyName,
      value: instrument.averageBuyPrice * instrument.volume,
    }));

  return (
    <div
      style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: "20px" }}
    >
      <div>
        <h2 style={{ textAlign: "center" }}>Stock Investment Distribution</h2>
        <ResponsiveContainer width="100%" height={400}>
          <PieChart margin={{ top: 20, right: 20, left: 20, bottom: 20 }}>
            <Pie
              data={pieData}
              cx="50%"
              cy="50%"
              outerRadius={120}
              innerRadius={60}
              fill="#8884d8"
              dataKey="value"
              paddingAngle={5}
              label={({ percent }) => `${(percent * 100).toFixed(2)}%`}
            >
              {pieData.map((entry, index) => (
                <Cell
                  key={`cell-${index}`}
                  fill={COLORS[index % COLORS.length]}
                />
              ))}
            </Pie>
            <Tooltip
              formatter={(value) =>
                `${(
                  (value / pieData.reduce((acc, cur) => acc + cur.value, 0)) *
                  100
                ).toFixed(2)}%`
              }
            />
            <Legend
              layout="vertical"
              verticalAlign="middle"
              align="right"
              wrapperStyle={{ right: -30 }}
              payload={pieData.map((entry, index) => ({
                value: entry.name,
                type: "square",
                color: COLORS[index % COLORS.length],
              }))}
            />
          </PieChart>
        </ResponsiveContainer>
      </div>

      <div>
        <h2 style={{ textAlign: "center" }}>Bond Investment Distribution</h2>
        <ResponsiveContainer width="100%" height={400}>
          <PieChart margin={{ top: 20, right: 20, left: 20, bottom: 20 }}>
            <Pie
              data={pieData2}
              cx="50%"
              cy="50%"
              outerRadius={120}
              innerRadius={60}
              fill="#8884d8"
              dataKey="value"
              paddingAngle={5}
              label={({ percent }) => `${(percent * 100).toFixed(2)}%`}
            >
              {pieData2.map((entry, index) => (
                <Cell
                  key={`cell-${index}`}
                  fill={COLORS[index % COLORS.length]}
                />
              ))}
            </Pie>
            <Tooltip
              formatter={(value) =>
                `${(
                  (value / pieData2.reduce((acc, cur) => acc + cur.value, 0)) *
                  100
                ).toFixed(2)}%`
              }
            />
            <Legend
              layout="vertical"
              verticalAlign="middle"
              align="right"
              payload={pieData2.map((entry, index) => ({
                value: entry.name,
                type: "square",
                color: COLORS[index % COLORS.length],
              }))}
            />
          </PieChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}


export default InstrumentPieChart;
