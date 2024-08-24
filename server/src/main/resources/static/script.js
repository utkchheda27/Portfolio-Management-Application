document.addEventListener('DOMContentLoaded', function() {
    fetchOrderSummaries();
});

function fetchOrderSummaries() {
    fetch('/api/orderSummaries')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#orderSummaryTable tbody');
            tableBody.innerHTML = ''; // Clear existing rows

            data.forEach(orderSummary => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <td>${orderSummary.instrumentName}</td>
                    <td>${new Date(orderSummary.dateOfPurchase).toLocaleDateString()}</td>
                    <td>${orderSummary.volume}</td>
                    <td>${orderSummary.boughtPrice}</td>
                    <td>${orderSummary.currentMarketPrice}</td>
                    <td>${orderSummary.currentMarketValue}</td>
                    <td>${orderSummary.pnl}</td>
                    <td>${orderSummary.pnlPercentage.toFixed(2)}%</td>
                `;

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching order summaries:', error));
}
