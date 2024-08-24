import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Box } from '@mui/material';
import { ShowChart, AttachMoney } from '@mui/icons-material'; // Import relevant icons
import NewsCard from './NewsCard';

// Button Component
export const HomeScreen = () => {
  const navigate = useNavigate();

  return (
    <div>
      <NewsCard />
      <div style={styles.container}>
        <Button
          variant="contained"
          color="primary"
          sx={{ ml: 2 }}
          onClick={() => navigate('/bondTable')}
          startIcon={<AttachMoney />} // Add icon for bond
        >
          Go to Bond
        </Button>
        <Button
          variant="contained"
          color="primary"
          sx={{ ml: 2 }}
          onClick={() => navigate('/stockTable')}
          startIcon={<ShowChart />} // Add icon for stock
        >
          Go to Stock
        </Button>
      </div>
    </div>
  );
};

// Styling for components
const styles = {
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '20vh',
    gap: '20px',
  },
};

export default HomeScreen;
