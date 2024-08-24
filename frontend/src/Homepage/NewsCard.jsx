
import React, { useState, useEffect } from "react";
import axios from "axios";
import Slider from "react-slick";
import {
  Card,
  CardMedia,
  CardContent,
  Typography,
  CardActions,
  Button,
  Box,
  Container,
  createTheme,
  ThemeProvider,
  IconButton,
} from "@mui/material";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { ArrowBackIos, ArrowForwardIos } from "@mui/icons-material";

// Create a theme for the application
const theme = createTheme({
  palette: {
    primary: {
      main: '#1976d2',
    },
    secondary: {
      main: '#dc004e',
    },
    background: {
      default: '#f5f5f5',
    },
    text: {
      primary: '#333',
    },
  },
  typography: {
    h4: {
      fontWeight: 600,
      color: '#1976d2',
    },
    subtitle1: {
      fontSize: '1rem',
      fontWeight: 500,
    },
    button: {
      textTransform: 'none',
    },
  },
});

// Custom arrow components
const CustomNextArrow = (props) => {
  const { onClick } = props;
  return (
    <IconButton
      sx={{
        position: 'absolute',
        top: '50%',
        right: 16,
        transform: 'translateY(-50%)',
        zIndex: 1,
        backgroundColor: 'rgba(255, 255, 255, 0.8)',
        borderRadius: '50%',
        boxShadow: '0 2px 4px rgba(0, 0, 0, 0.2)',
        '&:hover': {
          backgroundColor: 'rgba(255, 255, 255, 1)',
        },
      }}
      onClick={onClick}
    >
      <ArrowForwardIos />
    </IconButton>
  );
};

const CustomPrevArrow = (props) => {
  const { onClick } = props;
  return (
    <IconButton
      sx={{
        position: 'absolute',
        top: '50%',
        left: 16,
        transform: 'translateY(-50%)',
        zIndex: 1,
        backgroundColor: 'rgba(255, 255, 255, 0.8)',
        borderRadius: '50%',
        boxShadow: '0 2px 4px rgba(0, 0, 0, 0.2)',
        '&:hover': {
          backgroundColor: 'rgba(255, 255, 255, 1)',
        },
      }}
      onClick={onClick}
    >
      <ArrowBackIos />
    </IconButton>
  );
};

function NewsCard() {
  const [articles, setArticles] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "https://newsapi.org/v2/everything?q=stock&apiKey=d1bf3b02c3244c94969b9511989b84ff"
        );
        setArticles(response.data.articles); // Load all articles
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  // Settings for the carousel slider
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2,
        },
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        },
      },
    ],
    nextArrow: <CustomNextArrow />,
    prevArrow: <CustomPrevArrow />,
  };

  return (
    <ThemeProvider theme={theme}>
      <Container maxWidth="lg">
        <Box sx={{ mt: 4, mb: 2, position: 'relative' }}>
          <Typography variant="h4" gutterBottom align="center">
            Stock Market News
          </Typography>
          <Slider {...settings}>
            {articles.map((article, index) => (
              <div key={index}>
                <Card
                  sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    maxWidth: 320,
                    minHeight: 320,
                    margin: '0 16px',
                    perspective: '1000px',
                    transition: 'box-shadow 0.3s, background-color 0.3s',
                    borderRadius: '12px',
                    background: 'linear-gradient(135deg, #e3f2fd 30%, #bbdefb 100%)',
                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
                    '&:hover': {
                      boxShadow: '0 8px 16px rgba(0, 0, 0, 0.3)',
                      backgroundColor: '#e3f2fd',
                    },
                  }}
                >
                  <CardMedia
                    component="img"
                    image={article.urlToImage || 'https://via.placeholder.com/320x120'}
                    alt={article.title}
                    sx={{ height: 120, objectFit: 'cover' }}
                  />
                  <CardContent sx={{ flexGrow: 1, padding: '16px' }}>
                    <Typography gutterBottom variant="subtitle1" component="div">
                      {article.title}
                    </Typography>
                  </CardContent>
                  <CardActions sx={{ padding: '16px', justifyContent: 'center' }}>
                    <Button
                      size="small"
                      color="primary"
                      href={article.url}
                      target="_blank"
                      rel="noopener noreferrer"
                      sx={{
                        borderRadius: '20px',
                        textTransform: 'none',
                        padding: '8px 16px',
                        backgroundColor: theme.palette.primary.main,
                        color: '#ffffff',
                        '&:hover': {
                          backgroundColor: theme.palette.primary.dark,
                        },
                      }}
                    >
                      Read More
                    </Button>
                  </CardActions>
                </Card>
              </div>
            ))}
          </Slider>
        </Box>
      </Container>
    </ThemeProvider>
  );
}

export default NewsCard;

