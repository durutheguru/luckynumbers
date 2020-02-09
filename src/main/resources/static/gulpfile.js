var gulp = require('gulp');
var cssnano = require('gulp-cssnano');
var sass = require('gulp-sass');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var ts = require("gulp-typescript");
var terser = require("gulp-terser");
var sourcemaps = require('gulp-sourcemaps');
const gprint = require('gulp-print');
const gutil = require("gulp-util");
const del = require('del');
const vinylPaths = require('vinyl-paths');
const webpack_stream = require('webpack-stream')
const webpack_config = require('./webpack.config.js');

var gulp_helper = require("./app/internal/gulp_helper");



/**
 * CSS task
 */

var VENDOR_CSS_ROOT = "./app/vendor/css";

var cssVendorList = [
    "/bootstrap.css",
    "/bootstrap-reset.css",
    "/font-awesome.css",
    "/style.css",
    "/style-responsive.css"
];

gulp.task('vendorCSSMin', function() {

    return gulp.src(gulp_helper.mergePathRoot(VENDOR_CSS_ROOT, cssVendorList))
        .pipe(concat("compile_000.css"))
        .pipe(cssnano())
        .pipe(gulp.dest("dist/css"));

});

/**
 * End CSS Task
 * #############################################
 */



/**
 * SASS task
 */
gulp.task('sassCompile', function () {
    return gulp.src('app/sass/**/*.scss')
        .pipe(sass())
        .pipe(concat("app.css"))
        .pipe(cssnano())
        .pipe(gulp.dest('dist/css'));
});

/**
 * End SASS Task
 * #############################################
 */



/**
 * Javascript task
 */

var VENDOR_JS_ROOT = "./app/vendor/js";

var vendorJSList = gulp_helper.mergePathRoot(VENDOR_JS_ROOT, [
    "/jquery.js",
    "/bootstrap.min.js",
    "/jquery.dcjqaccordion.2.7.js",
    "/hover-dropdown.js",
    "/jquery.scrollTo.min.js",
    "/jquery.nicescroll.js",
    "/respond.min.js",
    "/slidebars.min.js",
    "/common-scripts.js"
]);

//vendorJSList = vendorJSList.concat([
//    "./node_modules/vue/dist/vue.js"
//]);


gulp.task('vendorJSMin', function() {

    return gulp.src(vendorJSList)
        .pipe(concat("compile_000.js"))
        .pipe(uglify())
        .pipe(gulp.dest("./dist/js"));

});


/**
 * End Javascript task
 * #############################################
 */



/**
 * TypeScript task
 */

var tsProject = ts.createProject("tsconfig.json");

// Array containing base for Page Typescripts
var baseTSList = [];

gulp.task('package-back_office_user', function () {
    //return gulp.src(
    //    baseTSList.concat([
    //        "./app/ts/back_office_user/index.ts"
    //    ])
    //)
    //    .pipe(sourcemaps.init())
    //    .pipe(tsProject())
    //    .pipe(concat("app.js"))
    //    .pipe(terser())
    //    .pipe(sourcemaps.write("maps"))
    //    .pipe(gulp.dest("./dist/js/back_office_user"));
    return webpack_stream(webpack_config)
        .pipe(gulp.dest("./dist/js/"));
});

/**
 * End TypeScript task
 * #############################################
 */



/**
 * Default task
 */

gulp.task("default", gulp.parallel("vendorCSSMin", "sassCompile", "vendorJSMin", "package-back_office_user"));

/**
 * End Default task
 * #############################################
 */
