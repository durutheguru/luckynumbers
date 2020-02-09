var path = require('path');
var webpack = require('webpack');


module.exports = {
    entry: {
        back_office: './app/ts/back_office_user/index.ts'
    },

    //resolve: {
    //    extensions: ["", ".webpack.js", ".web.js", ".js", ".ts"]
    //},

    output: {
        path: path.join(__dirname, '/dist/js'),
        filename: '[name].build.js'
    },

    module: {
        rules: [
            {
                test: /\.ts$/,
                use: 'ts-loader'
            }
        ]
    }
};


