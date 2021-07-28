const server = require('./server/server');
const BASE_URL = process.env.NODE_ENV === 'production' ? '/' : '/';

module.exports = {
    publicPath: BASE_URL,
    lintOnSave: false,
    productionSourceMap: false,
    devServer: server
}