const http = require('http');
const fs = require('fs');



http.createServer(function (req, res) {
    if (req.url === '/upload' && req.method.toLowerCase() === 'post') {
        let body = '';

        // Read the request body
        req.on('data', function (data) {
            body += data;

            // If the image is too large, stop reading and return an error
            if (body.length > 1e6) {
                res.writeHead(413, { 'Content-Type': 'text/plain' }).end();
                req.connection.destroy();
            }
        });

        // When the request has ended, write the file to disk
        req.on('end', function () {
            // Parse the content type to get the file extension
            const contentType = req.headers['content-type'];
            const ext = contentType.split('/')[1];

            // Generate a unique filename for the uploaded image
            const fileName = Date.now() + '.' + ext;

            // Write the file to disk
            fs.writeFile(__dirname + '/uploads/' + fileName, body, function (err) {
                if (err) {
                    console.log(err);
                    res.writeHead(500, { 'Content-Type': 'text/plain' });
                    res.end('Error writing file to disk');
                } else {
                    res.writeHead(200, { 'Content-Type': 'text/plain' });
                    res.end('File uploaded successfully');
                }
            });
        });
    } else {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.end('Not found');
    }
}).listen(8080);

console.log('Server running on port 8080');