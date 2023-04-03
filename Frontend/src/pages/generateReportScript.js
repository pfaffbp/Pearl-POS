function generateReport() {
    // make a request to the backend to retrieve all transactions
    fetch('/report')
        .then(response => response.json())
        .then(transactions => {
            // create an empty report array
            let report = [];

            // loop through all transactions and add them to the report
            transactions.forEach(transaction => {
                report.push(transaction);
            });

            // display the report on the webpage
            document.getElementById('report').innerText = JSON.stringify(report, null, 2);
        })
        .catch(error => console.error(error));
}
