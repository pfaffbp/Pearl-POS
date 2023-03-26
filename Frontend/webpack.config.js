const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

   // examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
    inventoryLevelsPage: path.resolve(__dirname, 'src', 'pages', 'inventoryLevelsPage.js'),

     examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
     addProductPage: path.resolve(__dirname, 'src', 'pages', 'addProductPage.js'),
      loginPage: path.resolve(__dirname, 'src', 'pages', 'loginPage.js'),
      productpage: path.resolve(__dirname, 'src', 'pages', 'productPage.js'),

=======
   // examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
    inventoryLevelsPage: path.resolve(__dirname, 'src', 'pages', 'inventoryLevelsPage.js'),
>>>>>>> 25c0d46 (first push getting an error when trying to run the boot run def)
=======
    examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
    commentPage: path.resolve(__dirname, 'src', 'pages', 'addProductPage.js'),
>>>>>>> 3cd46eb (save changes)
=======
     examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
     addProductPage: path.resolve(__dirname, 'src', 'pages', 'addProductPage.js'),
>>>>>>> 7d02c4d (my saves)
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
<<<<<<< HEAD
    openPage: 'http://localhost:8080/productpage.html',
=======
    openPage: 'http://localhost:8080/ManagerConsole.html',
>>>>>>> 7d02c4d (my saves)
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
        target: 'http://localhost:5001'
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
      template: './src/productpage.html',
      filename: 'productpage.html',
      inject: false
    }),
    new HtmlWebpackPlugin({
      template: './src/ManagerConsole.html',
      filename: 'ManagerConsole.html',
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
