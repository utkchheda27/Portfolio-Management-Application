import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  Container,
  CssBaseline,
  Box,
} from "@mui/material";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import "./App.css";

import StockDashboard from "./BuySellStocks/stockDashboard";
import { HomeScreen } from "./Homepage/HomeScreen";
import BondTable from "./Instruments/BondTable";
import StockTable from "./Instruments/StockTable";
import AssetBook from "./Books/AssetBook";
import OrderBook from "./Books/OrderBook";
import CashFlowBook from "./Books/CashFlowBook";
import GraphsDashboard from "./Graphs/Dashboard"; 
import axios from "axios";

const theme = createTheme({
  palette: {
    primary: {
      main: "#1976d2",
    },
    secondary: {
      main: "#ff4081",
    },
  },
});

const App = () => {
  const [stockData, setStockData] = useState([]);

  useEffect(() => {
    const fetchStockData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/stockdata/all");
        if (Array.isArray(response.data)) {
          setStockData(response.data);
        } else {
          console.error("Unexpected data format:", response.data);
        }
      } catch (error) {
        console.error("Error fetching stock data:", error);
      }
    };

    fetchStockData();
  }, []);

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <Box display="flex" flexDirection="column" minHeight="100vh">
          <AppBar position="sticky">
            <Toolbar>
              <Typography variant="h6" component="div" sx={{ flexGrow: 1 }} >
                Portfolio Manager
              </Typography>
              <Button color="inherit" component={Link} to="/">
                Home
              </Button>
              <Button color="inherit" component={Link} to="/form">
                Buy/Sell Stock
              </Button>
              <Button color="inherit" component={Link} to="/graphDashboard">
                Dashboard
              </Button>
            </Toolbar>
          </AppBar>
          <Container sx={{ mt: 4, mb: 4 }}>
            <Routes>
              <Route path="/" element={<HomeScreen />} />
              <Route path="/form" element={<StockDashboard />} />
              <Route path="/bondTable" element={<BondTable />} />
              <Route path="/stockTable" element={<StockTable />} />
              <Route path="/assetbook" element={<AssetBook />} />
              <Route path="/orderbook" element={<OrderBook />} />
              <Route path="/cashflowbook" element={<CashFlowBook />} />
              <Route path="/graphDashboard" element={<GraphsDashboard stockData={stockData} />}/>
            </Routes>
          </Container>
          <Box
            component="footer"
            sx={{
              py: 3,
              px: 2,
              mt: "auto",
              backgroundColor: theme.palette.primary.main,
            }}
          >
             <Box
            component="footer"
            sx={{
              py: 3,
              px: 2,
              mt: 'auto',
              backgroundColor: theme.palette.primary.main,
              color: 'white',
              textAlign: 'center'
            }}
          >
            <Container maxWidth="sm">
              <Typography variant="body1" sx={{ mb: 1 }}>
                &copy; {new Date().getFullYear()} Portfolio Manager. All rights reserved.
              </Typography>
              <Typography variant="body2" sx={{ mb: 1 }}>
                Building a better financial future with insights and tools for managing your portfolio.
              </Typography>
              <Typography variant="body2">
                Disclaimer: The information provided on this site is for informational purposes only and should not be considered financial advice.
              </Typography>
            </Container>
          </Box>
          </Box>
        </Box>
      </Router>
    </ThemeProvider>
  );
};

export default App;