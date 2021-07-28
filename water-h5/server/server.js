const contexts = ['water'];
let proxyObj = {};

contexts.forEach((ctx) => {
    proxyObj['/' + ctx] = {
        target: process.env['VUE_APP_' + ctx],
        changeOrigin: true,
        pathRewrite: {
            [`^/${ctx}`]: `/${ctx}`
        }
    }
})

let proxyObj1 = {
    '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
            '^/api': '/static/mock'
        }
    }
};
proxyObj = { ...proxyObj1, ...proxyObj }
module.exports = {
    host: '0.0.0.0',
    port: 8080,
    https: false,
    proxy: proxyObj
}