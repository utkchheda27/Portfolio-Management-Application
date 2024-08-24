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


const initialRows = [];


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

export default function AssetbookTable() {
  const [rows, setRows] = useState(initialRows);
  const [filteredRows, setFilteredRows] = useState(initialRows);
  const [searchQuery, setSearchQuery] = useState("");
  const [sortCriteria, setSortCriteria] = useState("");
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/assetBook");
        const data = response.data || [];
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
          filtered = filtered.sort((a, b) => a.pnl - b.pnl);
          break;
        case "priceDesc":
          filtered = filtered.sort((a, b) => b.pnl - a.pnl);
          break;
        case "volumeAsc":
          filtered = filtered.sort((a, b) => a.volume - b.volume);
          break;
        case "volumeDesc":
          filtered = filtered.sort((a, b) => b.volume - a.volume);
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
            Asset Book Data
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
                <MenuItem value="priceAsc">Pnl: Low to High</MenuItem>
                <MenuItem value="priceDesc">Pnl: High to Low</MenuItem>
                <MenuItem value="volumeAsc">Volume: Low to High</MenuItem>
                <MenuItem value="volumeDesc">Volume: High to Low</MenuItem>
              </Select>
            </FormControl>
          </Box>
        </Box>
        <TableContainer component={Paper}>
          <Table aria-label="tradebook table">
            <TableHead>
              <TableRow>
                <TableCell>Company Name</TableCell>
                <TableCell align="right">Ticker Symbol</TableCell>
                <TableCell align="right">Average Buy Price</TableCell>
                <TableCell align="right">Volume</TableCell>
                <TableCell align="right">Current Market Price</TableCell>
                <TableCell align="right">PnL</TableCell>
                <TableCell align="right">PnL Percentage</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {filteredRows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((row) => (
                <TableRow key={row.tickerSymbol}>
                  <TableCell component="th" scope="row">
                    {row.companyName}
                  </TableCell>
                  <TableCell align="right">{row.tickerSymbol}</TableCell>
                  <TableCell align="right">
                    {row.averageBuyPrice !== undefined ? row.averageBuyPrice.toFixed(2) : "N/A"}
                  </TableCell>
                  <TableCell align="right">{row.volume}</TableCell>
                  <TableCell align="right">
                    {row.currentMarketPrice !== undefined ? row.currentMarketPrice.toFixed(2) : "N/A"}
                  </TableCell>
                  <TableCell align="right">
                    {row.pnl !== undefined ? row.pnl.toFixed(2) : "N/A"}
                  </TableCell>
                  <TableCell align="right">
                    {row.pnlPercentage !== undefined ? row.pnlPercentage.toFixed(2) + "%" : "N/A"}
                  </TableCell>
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