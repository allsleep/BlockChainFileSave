module.exports = {
    pages: {
        index: {
            entry: 'src/main.js'
        }
    },
    lintOnSave: false,
    devServer: { 
        disableHostCheck: true, 
        proxy: {
            '/api': {
                target: 'http://localhost/',
                // 允许跨域
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}