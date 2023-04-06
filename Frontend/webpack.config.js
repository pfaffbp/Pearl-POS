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
<<<<<<< HEAD
        // examplePage: path.resolve(dirname, 'src', 'pages', 'examplePage.js'),
        inventoryLevelsPage: path.resolve(__dirname, 'src', 'pages', 'inventoryLevelsPage.js'),
        generateReportPage: path.resolve(__dirname, 'src', 'pages', 'generateReportPage.js'),
=======
        // examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
        inventoryLevelsPage: path.resolve(__dirname, 'src', 'pages', 'inventoryLevelsPage.js'),
        createUserPage: path.resolve(__dirname, 'src', 'pages', 'createUserPage.js'),
        purchaseHistoryPage: path.resolve(__dirname, 'src', 'pages', 'purchaseHistoryPage.js'),
>>>>>>> abab043 (working productHistoryPage and addeded tests for Transaction service)
        examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
        addProductPage: path.resolve(__dirname, 'src', 'pages', 'addProductPage.js'),
        loginPage: path.resolve(__dirname, 'src', 'pages', 'loginPage.js'),
        productPage: path.resolve(__dirname, 'src', 'pages', 'productPage.js'),

<<<<<<< HEAD
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
=======
   // examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
    inventoryLevelsPage: path.resolve(__dirname, 'src', 'pages', 'inventoryLevelsPage.js'),
<<<<<<< HEAD
    createUserPage: path.resolve(__dirname, 'src', 'pages', 'createUserPage.js'),
    purchaseHistoryPage: path.resolve(__dirname, 'src', 'pages', 'purchaseHistoryPage.js'),
     examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
     addProductPage: path.resolve(__dirname, 'src', 'pages', 'addProductPage.js'),
=======

      examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
      addProductPage: path.resolve(__dirname, 'src', 'pages', 'addProductPage.js'),
>>>>>>> d60c250 (Made purchaseProducts accept multiple product id, and made Transaction service accept multiple productIDS)
      loginPage: path.resolve(__dirname, 'src', 'pages', 'loginPage.js'),
      productPage: path.resolve(__dirname, 'src', 'pages', 'productPage.js'),
>>>>>>> d6bc43a (preping to merge)

<<<<<<< HEAD
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
=======

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
        target: 'http://localhost:5001'
      }
>>>>>>> bb66005 (updating my levels and purchase history links)
    ]
<<<<<<< HEAD
}
=======
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
      template: './src/createUser.html',
      filename: 'createUser.html',
      inject: false
    }),
    new HtmlWebpackPlugin({
      template: './src/purchaseHistory.html',
      filename: 'purchaseHistory.html',
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
>>>>>>> d6bc43a (preping to merge)
=======
        // examplePage: path.resolve(dirname, 'src', 'pages', 'examplePage.js'),
        inventoryLevelsPage: path.resolve(dirname, 'src', 'pages', 'inventoryLevelsPage.js'),
        DashboardPage: path.resolve(dirname, 'src', 'pages', 'DashboardPage.js'),
        examplePage: path.resolve(dirname, 'src', 'pages', 'examplePage.js'),
        addProductPage: path.resolve(dirname, 'src', 'pages', 'addProductPage.js'),
        loginPage: path.resolve(dirname, 'src', 'pages', 'loginPage.js'),
        productPage: path.resolve(dirname, 'src', 'pages', 'productPage.js'),

=======
>>>>>>> abab043 (working productHistoryPage and addeded tests for Transaction service)
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
>>>>>>> bf1cc5f (webpack update)
=======
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
>>>>>>> ecfddbb (webpack update)
