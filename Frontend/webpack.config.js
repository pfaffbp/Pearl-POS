const path = require('path');
const dirname = path.resolve();
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
    optimization: {
        usedExports: true
    },
    entry: {

        // examplePage: path.resolve(dirname, 'src', 'pages', 'examplePage.js'),
        inventoryLevelsPage: path.resolve(dirname, 'src', 'pages', 'inventoryLevelsPage.js'),
        DashboardPage: path.resolve(dirname, 'src', 'pages', 'DashboardPage.js'),
        examplePage: path.resolve(dirname, 'src', 'pages', 'examplePage.js'),
        addProductPage: path.resolve(dirname, 'src', 'pages', 'addProductPage.js'),
        loginPage: path.resolve(dirname, 'src', 'pages', 'loginPage.js'),
        productPage: path.resolve(dirname, 'src', 'pages', 'productPage.js'),

    },
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: '[name].js',
    },
    devServer: {
        https: false,
        port: 8080,
        open: true,
        openPage: 'http://localhost:8080/productpage.html',
        // diableHostChecks, otherwise we get an error about headers and the page won't render
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
            template: './src/ManagerConsole.html',
            filename: 'ManagerConsole.html',
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
            template: './src/GenerateReport.html',
            filename: 'DashboardPage.js.html',
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