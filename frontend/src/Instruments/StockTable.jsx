import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  TextField,
  Typography,
  Box,
  Container,
  MenuItem,
  Select,
  FormControl,
  InputLabel,
  TablePagination,
  createTheme,
  ThemeProvider
} from "@mui/material";

// Define the initial state
const initialRows = [];

// Create a custom theme
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
  },
  components: {
    MuiTable: {
      styleOverrides: {
        root: {
          backgroundColor: '#ffffff',
        },
      },
    },
    MuiTableCell: {
      styleOverrides: {
        head: {
          backgroundColor: '#1976d2',
          color: '#ffffff',
        },
        body: {
          color: '#333333',
        },
      },
    },
    MuiTextField: {
      styleOverrides: {
        root: {
          backgroundColor: '#ffffff',
          borderRadius: '4px',
        },
      },
    },
    MuiSelect: {
      styleOverrides: {
        root: {
          backgroundColor: '#ffffff',
          borderRadius: '4px',
        },
        icon: {
          color: '#1976d2',
        },
      },
    },
    MuiFormControl: {
      styleOverrides: {
        root: {
          marginLeft: '16px',
        },
      },
    },
    MuiTablePagination: {
      styleOverrides: {
        root: {
          backgroundColor: '#ffffff',
          borderTop: '1px solid #e0e0e0',
        },
      },
    },
  },
});

function StockTable() {
  const [rows, setRows] = useState(initialRows);
  const [filteredRows, setFilteredRows] = useState(initialRows);
  const [searchQuery, setSearchQuery] = useState("");
  const [sortCriteria, setSortCriteria] = useState("");
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);

  useEffect(() => {
    const fetchData = async () => {
      console.log("hi")
      try {
        const response = await axios.get("http://localhost:8080/stock");
        const data = response.data.data || [];
        setRows(data);
        setFilteredRows(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  const handleSearch = (event) => {
    const query = event.target.value;
    setSearchQuery(query);
    filterAndSortRows(query, sortCriteria);
  };

  const handleSortChange = (event) => {
    const criteria = event.target.value;
    setSortCriteria(criteria);
    filterAndSortRows(searchQuery, criteria);
  };

  const filterAndSortRows = (query, criteria) => {
    let filtered = rows.filter((row) =>
      row.companyName.toLowerCase().includes(query.toLowerCase()) ||
      row.tickerSymbol.toLowerCase().includes(query.toLowerCase())
    );

    if (criteria) {
      switch (criteria) {
        case "priceAsc":
          filtered = filtered.sort((a, b) => a.stockPrice - b.stockPrice);
          break;
        case "priceDesc":
          filtered = filtered.sort((a, b) => b.stockPrice - a.stockPrice);
          break;
        case "volumeAsc":
          filtered = filtered.sort((a, b) => a.averageVolume - b.averageVolume);
          break;
        case "volumeDesc":
          filtered = filtered.sort((a, b) => b.averageVolume - a.averageVolume);
          break;
        default:
          break;
      }
    }

    setFilteredRows(filtered);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <ThemeProvider theme={theme}>
      <Container maxWidth="lg">
        <Box sx={{ mt: 4, mb: 2 }}>
          <Typography variant="h4" gutterBottom>
            Stock Data
          </Typography>
          <Box sx={{ display: 'flex', alignItems: 'center', gap: 2, mb: 2 }}>
            <TextField
              variant="outlined"
              fullWidth
              placeholder="Search by Company Name or Ticker Symbol"
              value={searchQuery}
              onChange={handleSearch}
              sx={{ flex: 1 }}
            />
            <FormControl variant="outlined" size="small" sx={{ minWidth: 180 }}>
              <InputLabel>Sort by</InputLabel>
              <Select
                value={sortCriteria}
                onChange={handleSortChange}
                label="Sort by"
              >
                <MenuItem value="">None</MenuItem>
                <MenuItem value="priceAsc">Price: Low to High</MenuItem>
                <MenuItem value="priceDesc">Price: High to Low</MenuItem>
                <MenuItem value="volumeAsc">Volume: Low to High</MenuItem>
                <MenuItem value="volumeDesc">Volume: High to Low</MenuItem>
              </Select>
            </FormControl>
          </Box>
        </Box>
        <TableContainer component={Paper}>
          <Table aria-label="stock table">
            <TableHead>
              <TableRow>
                <TableCell>Company Name</TableCell>
                <TableCell align="right">Ticker Symbol</TableCell>
                <TableCell align="right">Stock Price</TableCell>
                <TableCell align="right">52 Week High</TableCell>
                <TableCell align="right">52 Week Low</TableCell>
                <TableCell align="right">Average Volume</TableCell>
                <TableCell align="right">Industry</TableCell>
                <TableCell align="right">Market Exchange</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {filteredRows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((row) => (
                <TableRow key={row.id}>
                  <TableCell component="th" scope="row">
                    {row.companyName}
                  </TableCell>
                  <TableCell align="right">{row.tickerSymbol}</TableCell>
                  <TableCell align="right">{row.stockPrice.toFixed(2)}</TableCell>
                  <TableCell align="right">{row.week52High.toFixed(2)}</TableCell>
                  <TableCell align="right">{row.week52Low.toFixed(2)}</TableCell>
                  <TableCell align="right">{row.averageVolume.toLocaleString()}</TableCell>
                  <TableCell align="right">{row.industry}</TableCell>
                  <TableCell align="right">{row.marketExchange}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[5, 10, 25]}
          component="div"
          count={filteredRows.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </Container>
    </ThemeProvider>
  );
}

export default StockTable;
