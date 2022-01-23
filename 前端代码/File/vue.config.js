module.exports = {
    pages: {
        index: {
            entry: 'src/main.js'
        }
    },
    lintOnSave: false,
    devServer: { 
        disableHostCheck: true, 
        proxy: {  //配置跨域
            '/api': {
              target: 'http://localhost',  //这里后台的地址模拟的;应该填写你们真实的后台接口
              changOrigin: true,  //允许跨域
                pathRewrite: {
                    '^/api': '/' 
                }
            },
            '/block': {
                target: 'http://localhost:40001/blockchain',
                changOrigin: true,  //允许跨域
            }
        }
    }, 
}