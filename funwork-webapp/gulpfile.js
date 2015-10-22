var gulp = require('gulp');
var gutil = require('gulp-util');
var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var config = require('./webpack.config');

gulp.task('webpack', function (callback) {
  webpack(config, function (err, stats) {
    callback();
  });
});

gulp.task("webpack-dev-server", function (callback) {

  //config.entry.app.unshift("webpack-dev-server/client?http://localhost:8090", "webpack/hot/dev-server");
  var compiler = webpack(config);

  new WebpackDevServer(compiler, {

    //hot: true,

    proxy: {
      "/api": "http://localhost:8080"
    }

  }).listen(8090, "localhost", function (err) {
    if (err) {
        throw new gutil.PluginError("webpack-dev-server", err);
    }
    // Server listening
    gutil.log("[webpack-dev-server]", "http://localhost:8090/webpack-dev-server/index.html");

    // keep the server alive or continue?
    callback();
  });
});

gulp.task('default', ['webpack-dev-server']);
