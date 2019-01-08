const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const merge = require('webpack-merge');
const webpackBaseConfig = require('./webpack.base.config.js');
const fs = require('fs');

fs.open('./src/config/env.js', 'w', function(err, fd) {
    const buf = 'export default "development";';
    fs.write(fd, buf, 0, buf.length, 0, function(err, written, buffer) {});
});

module.exports = merge(webpackBaseConfig, {
    devtool: '#source-map',
    output: {
        publicPath: '/dist/',
        filename: '[name].js',
        chunkFilename: '[name].chunk.js'
    },
    plugins: [
        new ExtractTextPlugin({
            filename: '[name].css',
            allChunks: true
        }),
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendors',
            filename: 'vendors.js'
        }),
        new HtmlWebpackPlugin({
            filename: '../index.html',
            template: './src/template/index.ejs',
            inject: false
        }),

    ],
    //设置跨域代理
    devServer: {
        historyApiFallback: true,
        hot: true,
        inline: true,
        stats: { colors: true },
        proxy: {
            //匹配代理的url
            '/wish': {
                // 目标服务器地址
                target: 'http://118.126.116.211:8082',
                //路径重写
                pathRewrite: {'^/wish' : '/wish'},
                changeOrigin: true,
                secure: false
            },
            '/auth': {
                // 目标服务器地址
                target: 'http://118.126.116.211:8083',
                //路径重写
                pathRewrite: {'^/auth' : '/auth'},
                changeOrigin: true,
                secure: false
            },
            '/account': {
                target: 'http://118.126.116.211:8084',
                //路径重写
                pathRewrite: {'^/account' : '/account'},
                changeOrigin: true,
                secure: false
            },
            '/util': {
                target: 'http://localhost:8086',
                //路径重写
                pathRewrite: {'^/util' : '/util'},
                changeOrigin: true,
                secure: false
            }
        }
    }
});