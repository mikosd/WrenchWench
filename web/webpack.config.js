const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
   module: {
     rules: [
      {
         test: /\.js$/, // Match JavaScript files
         exclude: /node_modules/, // Exclude the node_modules directory
         use: {
           loader: 'babel-loader', // Use the Babel loader
         },
       },
     ],
   },
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),
  ],
  optimization: {
    usedExports: true
  },
  entry: {
    createVehicle: path.resolve(__dirname, 'src', 'pages', 'createVehicle.js'),
    viewVehicle: path.resolve(__dirname, 'src', 'pages', 'viewVehicle.js'),
    //viewRecords: path.resolve(__dirname, 'src', 'pages', 'viewRecords.js'),
    landingPage: path.resolve(__dirname, 'src', 'pages', 'landingPage.js'),
    loadGarage: path.resolve(__dirname, 'src', 'pages', 'loadGarage.js'),
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}
