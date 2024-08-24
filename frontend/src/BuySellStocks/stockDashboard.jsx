import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {
  Typography,
  CircularProgress,
  Box,
  Grid,
  Card,
  CardContent,
  CardHeader,
  Modal,
  Button,
  Alert
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import StockForm from './stockForm';
import { ShowChart as ShowChartIcon, TrendingUp as TrendingUpIcon, TrendingDown as TrendingDownIcon, AttachMoney as AttachMoneyIcon } from '@mui/icons-material'; // Stock-related icons

const StockDashboard = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [orderDetails, setOrderDetails] = useState(null);
  const [openOrderModal, setOpenOrderModal] = useState(false);
  const [modalMessage, setModalMessage] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [tickerSymbols, setTickerSymbols] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/tradebook`);
        setData(response.data || []);
        setLoading(false);
        const symbols = response.data?.map(item => item.tickerSymbol) || [];
        setTickerSymbols(symbols);
        console.log('Ticker Symbols:', symbols);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchData();

    const intervalId = setInterval(fetchData, 30000);

    return () => clearInterval(intervalId);
  }, []);

  const handleOrderSubmit = async (order) => {
    setOrderDetails(order);
    setOpenOrderModal(true);
  };

  const handleConfirmOrder = async () => {
    setIsSubmitting(true);
    try {
      const response = await axios.post(`http://localhost:8080/api/createOrder?tickerSymbol=${orderDetails.tickerSymbol}&action=${orderDetails.action}&volume=${orderDetails.volume}&transactionDate=${orderDetails.transactionDate.toISOString()}`, 
      orderDetails);
      setModalMessage({ type: 'success', text: 'Order placed successfully!' });

      setTimeout(() => {
        setModalMessage(null);
        window.location.reload();
      }, 3000);
    } catch (error) {
      setModalMessage({ type: 'error', text: 'Failed to place order. Please try again.' });

      setTimeout(() => {
        setModalMessage(null);
      }, 3000);
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleCancelOrder = () => {
    setOpenOrderModal(false);
    setModalMessage(null);
    setOrderDetails(null);
  };

  return (
    <Box sx={{ mt: 4, mb: 4, px: 2 }}>
      
      {loading ? (
        <Box display="flex" justifyContent="center" alignItems="center" minHeight="60vh">
          <CircularProgress />
        </Box>
      ) : error ? (
        <Typography color="error" variant="h6" align="center">{`Error: ${error.message}`}</Typography>
      ) : (
        <Grid container spacing={2} justifyContent="center">
          <Grid item xs={12} md={6}>
            <Card sx={{ height: 'auto', maxHeight: '500px', backgroundColor: '#e3f2fd', boxShadow: 3 }}>
              <CardHeader
                title="Buy/Sell Stocks"
                sx={{ backgroundColor: '#0288d1', color: 'white', textAlign: 'center' }}
              />
              <CardContent sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', p: 2 }}>
                <StockForm onSubmit={handleOrderSubmit} />
              </CardContent>
            </Card>
          </Grid>
        </Grid>
      )}

      <Box sx={{ mb: 4, mt: 5, display: 'flex', justifyContent: 'center', gap: 2 }}>
        <Button
          variant="contained"
          color="primary"
          startIcon={<ShowChartIcon />}
          onClick={() => navigate('/assetBook')}
        >
          AssetBook
        </Button>
        <Button
          variant="contained"
          color="primary"
          startIcon={<TrendingUpIcon />}
          onClick={() => navigate('/orderbook')}
        >
          Orderbook
        </Button>
        <Button
          variant="contained"
          color="primary"
          startIcon={<AttachMoneyIcon />}
          onClick={() => navigate('/cashflowbook')}
        >
          CashFlowBook
        </Button>
      </Box>

      <Modal
        open={openOrderModal}
        onClose={handleCancelOrder}
        aria-labelledby="confirmation-modal-title"
        aria-describedby="confirmation-modal-description"
      >
        <Box
          sx={{
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: 400,
            bgcolor: 'background.paper',
            boxShadow: 24,
            p: 4,
            borderRadius: 2,
          }}
        >
          <Typography id="confirmation-modal-title" variant="h6" component="h2">
            Confirm Order
          </Typography>
          {orderDetails && (
            <Box sx={{ mt: 2 }}>
              <Typography>
                <strong>Ticker Symbol:</strong> {orderDetails.tickerSymbol}
              </Typography>
              <Typography>
                <strong>Volume:</strong> {orderDetails.volume}
              </Typography>
              <Typography>
                <strong>Action:</strong> {orderDetails.action.charAt(0).toUpperCase() + orderDetails.action.slice(1)}
              </Typography>
              <Typography>
                <strong>Transaction Date:</strong> {orderDetails.transactionDate.toLocaleDateString()}
              </Typography>
            </Box>
          )}

          {modalMessage && (
            <Box sx={{ mt: 2 }}>
              <Alert severity={modalMessage.type}>
                {modalMessage.text}
              </Alert>
            </Box>
          )}

          <Box sx={{ mt: 3, display: 'flex', justifyContent: 'flex-end', gap: 2 }}>
            <Button variant="outlined" onClick={handleCancelOrder} color="secondary">
              Cancel
            </Button>
            <Button
              variant="contained"
              onClick={handleConfirmOrder}
              color="primary"
              disabled={isSubmitting || modalMessage?.type === 'success'}
            >
              {isSubmitting ? 'Placing Order...' : 'Confirm Order'}
            </Button>
          </Box>
        </Box>
      </Modal>
    </Box>
  );
};

export default StockDashboard;
