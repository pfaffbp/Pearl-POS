const monthlySalesTable = document.getElementById('monthly-sales-table');
fetch('/api/sales/monthly')
    .then(response => response.json())
    .then(data => {
        const tbody = monthlySalesTable.querySelector('tbody');
        data.forEach(month => {
            month.averageSalePrice = month.averageSalePrice / 100
            month.totalSales = month.totalSales / 100
            const row = document.createElement('tr');
            row.innerHTML = `
                    <td>${month.month}</td>
                    <td>$${month.totalSales.toFixed(2)}</td>
                    <td>$${month.averageSalePrice.toFixed(2)}</td>
                `;
            tbody.appendChild(row);
        });
    })
    .catch(error => {
        console.error(error);
    });