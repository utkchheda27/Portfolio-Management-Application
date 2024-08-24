import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Button, TextField, Box, MenuItem, Select, InputLabel, FormControl, Typography } from '@mui/material';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const StockForm = ({ onSubmit }) => {
  const [tickerSymbol, setTickerSymbol] = useState('');
  const [volume, setVolume] = useState('0');
  const [action, setAction] = useState('buy');
  const [transactionDate, setTransactionDate] = useState(new Date());
  const [tickerSymbols, setTickerSymbols] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/tradebook');
        const symbols = response.data?.map(item => item.tickerSymbol) || [];
        setTickerSymbols(symbols);
        setLoading(false);
      
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchData();

    const intervalId = setInterval(fetchData, 30000);

    return () => clearInterval(intervalId);
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    const order = {
      tickerSymbol,
      volume: parseInt(volume, 10),
      action,
      transactionDate,
    };
    onSubmit(order); 
    setTickerSymbol('');
    setVolume('');
    setAction('buy');
    setTransactionDate(new Date());
  };
  console.log("form" + transactionDate)

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
      <FormControl fullWidth>
        <InputLabel id="ticker-symbol-label">Ticker Symbol</InputLabel>
        <Select
          labelId="ticker-symbol-label"
          value={tickerSymbol}
          label="Ticker Symbol"
          onChange={(e) => setTickerSymbol(e.target.value)}
          required
          MenuProps={{
            PaperProps: {
              style: {
                maxHeight: 200, 
                overflowY: 'auto', 
              },
            },
          }}
        >
          {tickerSymbols.length > 0 ? (
            tickerSymbols.map((symbol) => (
              <MenuItem key={symbol} value={symbol}>
                {symbol}
              </MenuItem>
            ))
          ) : (
            <MenuItem disabled>No Ticker Symbols Available</MenuItem>
          )}
        </Select>
      </FormControl>

      <TextField
        label="Volume"
        value={volume}
        onChange={(e) => setVolume(e.target.value)}
        type="number"
        required
      />

<DatePicker
  selected={transactionDate}
  onChange={(date) => setTransactionDate(date)}
  dateFormat="yyyy/MM/dd"  
  customInput={<TextField label="Transaction Date" required />}
/>


      <FormControl fullWidth>
        <InputLabel id="action-label">Action</InputLabel>
        <Select
          labelId="action-label"
          value={action}
          label="Action"
          onChange={(e) => setAction(e.target.value)}
          required
        >
          <MenuItem value="buy">Buy</MenuItem>
          <MenuItem value="sell">Sell</MenuItem>
        </Select>
      </FormControl>

      <Button type="submit" variant="contained" color="primary">
        Submit
      </Button>

      {loading && (
        <Typography variant="body2" color="textSecondary">
          Loading ticker symbols...
        </Typography>
      )}
      {error && (
        <Typography variant="body2" color="error">
          Error fetching ticker symbols: {error.message}
        </Typography>
      )}
    </Box>
  );
};

export default StockForm;




