const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
    CreateUserPage: path.resolve(__dirname, 'src', 'pages', 'CreateUserPage.js'),
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
  },
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        use: 'babel-loader',
      },
    ],
  },
  devServer: {
    https: false,
    port: 8080,
    open: {
      target: 'http://localhost:8080/user.html',
    },
    allowedHosts: 'all',
    static: {
      directory: path.resolve(__dirname, 'src'),
    },
    client: {
      overlay: true
    },
  },

  plugins: [
    new HtmlWebpackPlugin({
      template: './src/user.html',
      filename: 'user.html',
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
};
