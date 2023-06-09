const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
    optimization: {
        usedExports: true
    },
    entry: {
        examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
        addProductPage: path.resolve(__dirname, 'src', 'pages', 'addProductPage.js'),
        loginPage: path.resolve(__dirname, 'src', 'pages', 'loginPage.js'),
        productPage: path.resolve(__dirname, 'src', 'pages', 'productPage.js'),
        inventoryLevelsPage: path.resolve(__dirname, 'src', 'pages', 'inventoryLevelsPage.js'),
        createUserPage: path.resolve(__dirname, 'src', 'pages', 'createUserPage.js'),
        purchaseHistoryPage: path.resolve(__dirname, 'src', 'pages', 'purchaseHistoryPage.js'),
        userManagerConsole: path.resolve(__dirname, 'src', 'pages', 'userManagerConsole.js'),
        forgotPassword: path.resolve(__dirname, 'src', 'pages', 'forgotPassword.js'),
        generateReportPage: path.resolve(__dirname, 'src', 'pages', 'generateReportPage.js'),

    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: '[name].js',
    },
    devServer: {
        headers: {'Access-Control-Allow-Origin': '*'},
        https: false,
        port: 8080,
        open: true,
        openPage: 'http://localhost:8080/login.html',
        // disableHostChecks, otherwise we get an error about headers and the page won't render
        disableHostCheck: true,
        contentBase: 'packaging_additional_published_artifacts',
        // overlay shows a full-screen overlay in the browser when there are compiler errors or warnings
        overlay: true,
        proxy:[
            {
                context: [
                    '/'
                ],
                target: 'http://localhost:5001/'
            }
        ]

    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/login.html',
            filename: 'login.html',
            inject: false
        }),
        new HtmlWebpackPlugin({
            template: './src/addProduct.html',
            filename: 'addProduct.html',
            inject: false
        }),
        new HtmlWebpackPlugin({
            template: './src/inventoryLevels.html',
            filename: 'inventoryLevels.html',
            inject: false
        }),
        new HtmlWebpackPlugin({
            template: './src/productPage.html',
            filename: 'productPage.html',
            inject: false
        }),
        new HtmlWebpackPlugin({
            template: './src/createUser.html',
            filename: 'createUser.html',
            inject: false
        }),
        new HtmlWebpackPlugin({
            template: './src/purchaseHistory.html',
            filename: 'purchaseHistory.html',
            inject: false
        }),
        new HtmlWebpackPlugin({
            template: './src/userManagerConsole.html',
            filename: 'userManagerConsole.html',
            inject: false
        }),
        new HtmlWebpackPlugin({
            template: './src/forgotPassword.html',
            filename: 'forgotPassword.html',
            inject: false
        }),
        new HtmlWebpackPlugin({
            template: './src/generateReport.html',
            filename: 'generateReport.html',
            inject: false
        }),


        new CopyPlugin({
            patterns: [
                {
                    from: path.resolve('src/css'),
                    to: path.resolve("dist/css")
                }
            ]
        }),
        new CleanWebpackPlugin()
    ]
}
