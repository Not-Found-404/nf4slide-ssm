(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error('Cannot find module "' + req + '".');
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app-routing.module.ts":
/*!***************************************!*\
  !*** ./src/app/app-routing.module.ts ***!
  \***************************************/
/*! exports provided: AppRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppRoutingModule", function() { return AppRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _sign_in_sign_in_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./sign-in/sign-in.component */ "./src/app/sign-in/sign-in.component.ts");
/* harmony import */ var _sign_up_sign_up_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./sign-up/sign-up.component */ "./src/app/sign-up/sign-up.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};




var routes = [
    { path: 'sign-in', component: _sign_in_sign_in_component__WEBPACK_IMPORTED_MODULE_2__["SignInComponent"] },
    { path: 'sign-up', component: _sign_up_sign_up_component__WEBPACK_IMPORTED_MODULE_3__["SignUpComponent"] },
    { path: '',
        redirectTo: '/sign-in',
        pathMatch: 'full'
    },
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forRoot(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".sign-middle{\r\n    /* 柔性布局居中显示 */\r\n    display: flex;\r\n    align-items:center;\r\n    height: 100%;\r\n}\r\n.sign-context{\r\n    margin-left: auto;\r\n    margin-right: auto;\r\n    margin-top: auto;\r\n    margin-bottom: auto;\r\n    max-width: 400px;\r\n    width: calc(100% - 40px);\r\n    padding: 20px;\r\n    /* margin-bottom: 28px;\r\n    margin-top: 28px; */\r\n    /* 背景颜色 */\r\n    background-color: #fff;\r\n    /* 阴影 */\r\n    box-shadow: 0 2px 3px rgba(0,0,0,.55);\r\n    /* 边框 */\r\n    border: 1px solid #818c94;\r\n    border: 1px solid rgba(0,0,0,.4);\r\n    min-width: 320px;\r\n    min-height: 338px;\r\n}\r\n\r\n  "

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"sign-middle\" >\n    <div class=\"sign-context\" >\n        <router-outlet></router-outlet>\n    </div>\n</div>\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'app';
    }
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser/animations */ "./node_modules/@angular/platform-browser/fesm5/animations.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var ng_zorro_antd__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ng-zorro-antd */ "./node_modules/ng-zorro-antd/esm5/antd.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_common_locales_zh__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/common/locales/zh */ "./node_modules/@angular/common/locales/zh.js");
/* harmony import */ var _angular_common_locales_zh__WEBPACK_IMPORTED_MODULE_8___default = /*#__PURE__*/__webpack_require__.n(_angular_common_locales_zh__WEBPACK_IMPORTED_MODULE_8__);
/* harmony import */ var angular_font_awesome__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! angular-font-awesome */ "./node_modules/angular-font-awesome/dist/angular-font-awesome.es5.js");
/* harmony import */ var _sign_in_sign_in_component__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! ./sign-in/sign-in.component */ "./src/app/sign-in/sign-in.component.ts");
/* harmony import */ var _sign_up_sign_up_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! ./sign-up/sign-up.component */ "./src/app/sign-up/sign-up.component.ts");
/* harmony import */ var _brand_brand_component__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! ./brand/brand.component */ "./src/app/brand/brand.component.ts");
/* harmony import */ var _app_routing_module__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! .//app-routing.module */ "./src/app/app-routing.module.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};














Object(_angular_common__WEBPACK_IMPORTED_MODULE_7__["registerLocaleData"])(_angular_common_locales_zh__WEBPACK_IMPORTED_MODULE_8___default.a);
var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_2__["AppComponent"],
                _sign_in_sign_in_component__WEBPACK_IMPORTED_MODULE_10__["SignInComponent"],
                _sign_up_sign_up_component__WEBPACK_IMPORTED_MODULE_11__["SignUpComponent"],
                _brand_brand_component__WEBPACK_IMPORTED_MODULE_12__["BrandComponent"]
            ],
            imports: [
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_3__["BrowserAnimationsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_4__["FormsModule"],
                _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClientModule"],
                ng_zorro_antd__WEBPACK_IMPORTED_MODULE_6__["NgZorroAntdModule"],
                angular_font_awesome__WEBPACK_IMPORTED_MODULE_9__["AngularFontAwesomeModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_4__["ReactiveFormsModule"],
                _app_routing_module__WEBPACK_IMPORTED_MODULE_13__["AppRoutingModule"],
            ],
            providers: [{ provide: ng_zorro_antd__WEBPACK_IMPORTED_MODULE_6__["NZ_I18N"], useValue: ng_zorro_antd__WEBPACK_IMPORTED_MODULE_6__["zh_CN"] }],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_2__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/brand/brand.component.css":
/*!*******************************************!*\
  !*** ./src/app/brand/brand.component.css ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "/* brand  */\r\n.nf4-brand{\r\n    font-size:2em;\r\n    box-sizing: border-box;\r\n    display: flex;\r\n    justify-content: center;\r\n  }\r\n/* logo  */\r\n.nf4-brand__logo{\r\n    color: #0078d7;\r\n  }\r\n/* logo后面的“NF4 园丁助手” */\r\n.nf4-brand__font{\r\n    font-size:26px;\r\n    margin-left:5px;\r\n  }"

/***/ }),

/***/ "./src/app/brand/brand.component.html":
/*!********************************************!*\
  !*** ./src/app/brand/brand.component.html ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!-- brand -->\n<div class=\"nf4-brand\">\n    <!-- logo -->\n    <span class=\"nf4-brand__logo\"><fa name=\"paper-plane\"></fa>&nbsp;</span>\n    <!-- NF4 园丁助手 -->\n    <span class=\"nf4-brand__font\">NF4 园丁助手</span>\n  </div>"

/***/ }),

/***/ "./src/app/brand/brand.component.ts":
/*!******************************************!*\
  !*** ./src/app/brand/brand.component.ts ***!
  \******************************************/
/*! exports provided: BrandComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "BrandComponent", function() { return BrandComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var BrandComponent = /** @class */ (function () {
    function BrandComponent() {
    }
    BrandComponent.prototype.ngOnInit = function () {
    };
    BrandComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'brand-layout',
            template: __webpack_require__(/*! ./brand.component.html */ "./src/app/brand/brand.component.html"),
            styles: [__webpack_require__(/*! ./brand.component.css */ "./src/app/brand/brand.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], BrandComponent);
    return BrandComponent;
}());



/***/ }),

/***/ "./src/app/services/user.service.ts":
/*!******************************************!*\
  !*** ./src/app/services/user.service.ts ***!
  \******************************************/
/*! exports provided: UserService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserService", function() { return UserService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({ 'Content-Type': 'application/json' })
};
var UserService = /** @class */ (function () {
    /**
     * 构造函数，注入一个 HttpClient服务
     * @param {HttpClient} http
     */
    function UserService(http) {
        this.http = http;
        this.userUrl = 'user/';
        // this.userUrl = 'http://localhost:80/user/';
    }
    /**
     * 得到当前登录的用户的信息 (测试完成)
     * @returns {Observable<User>}
     * 调用此方法的结构如下：
     * this.userService.getLoginUser().subscribe((data: User) => this.loginUser = data);
     */
    UserService.prototype.getLoginUser = function () {
        return this.http.post(this.userUrl + "fetchLoginInfo", this.userParam, httpOptions);
    };
    /**
     * 进行登陆操作 (测试完成)
     * @param {User} user 要登录的用户信息, phoneNum 和 password 字段必须有值， 用这两个值进行登录验证
     * @returns {Observable<Result>}
     this.userService.login({
        phoneNum: '10086',
        password: '10086',
        username: null,
        userId: null,
        avator: null
      }).subscribe((data: Result) => this.result = data);
     */
    UserService.prototype.login = function (user) {
        return this.http.post(this.userUrl + "loginWithAngular", user, httpOptions);
    };
    /**
     * 注册用户
     * @param {User} user
     * @returns {Observable<Result>}
     */
    UserService.prototype.register = function (user) {
        return this.http.post(this.userUrl + "registerWithAngular", user, httpOptions);
    };
    /**
     * 验证手机号是否已经被使用过
     * @param {String} phoneNum 需要验证的手机号码
     * @returns {Observable<Result>}
     */
    UserService.prototype.exitPhoneNum = function (phoneNum) {
        return this.http.post(this.userUrl + "exitPhoneNumWithAngular", phoneNum, httpOptions);
    };
    /**
     * 手机发送验证码 (测试完成)
     * @param {String} phoneNum 被发送验证码的手机号
     * @returns {Observable<Result>}
     */
    UserService.prototype.sendVerifyCode = function (phoneNum) {
        return this.http.post(this.userUrl + "sendVerifyCodeWithAngular", phoneNum, httpOptions);
    };
    UserService = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])({
            providedIn: 'root'
        })
        /**
         * 用户服务
         * @author wildhunt_unique
         */
        ,
        __metadata("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
    ], UserService);
    return UserService;
}());



/***/ }),

/***/ "./src/app/sign-in/sign-in.component.css":
/*!***********************************************!*\
  !*** ./src/app/sign-in/sign-in.component.css ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "/* “登录” 二字 */\r\n.nf4-sign__sign-in-text{\r\n  display: flex;\r\n  justify-content: center;\r\n  text-align: left;\r\n  color: #404040;\r\n  font-size: 2rem;\r\n  font-weight: 600;\r\n  padding: 0;\r\n  margin-top: 20px;\r\n  margin-bottom: 12px;\r\n}\r\n/* 登录表单上层div */\r\n.nf4-sign__sign-in-form{\r\n  display: flex;\r\n  justify-content: center;\r\n}\r\n/* 登录表单 */\r\n.sign-in-form {\r\n  max-width: 300px;\r\n}\r\n/* 忘记密码 */\r\n.sign-in-forgot {\r\n  float: right;\r\n}\r\n/* 登录按钮 */\r\n.sign-in-button {\r\n  width: 100%;\r\n}"

/***/ }),

/***/ "./src/app/sign-in/sign-in.component.html":
/*!************************************************!*\
  !*** ./src/app/sign-in/sign-in.component.html ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!-- 登录模块 -->\n<div class=\"sign-in\">\n  <!-- brand__logo||brand__name -->\n  <brand-layout></brand-layout>\n  <!-- 登录模块 -->\n  <div class=\"nf4-sign__sign-in\">\n    <!-- 登录标题 -->\n    <div class=\"nf4-sign__sign-in-text\">\n      <span>登录</span>\n    </div>\n    <!-- 登录表单div -->\n    <div class=\"nf4-sign__sign-in-form\">\n      <!-- 登录表单 （含验证） -->\n      <form nz-form [formGroup]=\"validateForm\" class=\"sign-in-form\" (ngSubmit)=\"submitForm()\">\n        <!-- 手机号码 -->\n        <!-- nz-form-item 表单项用于区分表单中不同的区域 -->\n        <nz-form-item [nzType]=\"flex\">\n          <!-- 表单域 -->\n          <nz-form-control >\n            <!-- 带有前缀图标 “人头”-->\n            <nz-input-group nzPrefixIcon=\"anticon anticon-user\">\n              <!-- 最多11位 -->\n              <!-- 起别名\"phoneNum\" -->\n              <input id=\"phoneNum\" #phoneNum nz-input formControlName=\"phoneNum\" placeholder=\"手机号码\" maxlength=\"11\" >\n            </nz-input-group>\n            <!-- 验证是否是否碰过这个输入域&&是否输入了手机号&&若没有则提示错误信息 -->\n            <nz-form-explain *ngIf=\"validateForm.get('phoneNum').dirty && validateForm.get('phoneNum').errors\">请输入手机号码</nz-form-explain>\n          </nz-form-control>\n        </nz-form-item>\n        <!-- 密码 -->\n        <nz-form-item [nzType]=\"flex\">\n          <nz-form-control>\n            <!-- 带有前缀图标 \"锁\"-->\n            <nz-input-group nzPrefixIcon=\"anticon anticon-lock\">\n              <!-- 起别名\"password\" -->\n              <input type=\"password\" #password nz-input formControlName=\"password\" placeholder=\"密码\">\n            </nz-input-group>\n            <!-- 验证是否输入了密码 && 若没有则提示错误信息 -->\n            <nz-form-explain *ngIf=\"validateForm.get('password').dirty && validateForm.get('password').errors\">请输入密码</nz-form-explain>\n          </nz-form-control>\n        </nz-form-item>\n\n        <nz-form-item [nzType]=\"flex\">\n          <nz-form-control>\n            <!-- 是否记住密码 -->\n            <label nz-checkbox formControlName=\"remember\">\n              <span>记住我</span>\n            </label>\n            <!-- 忘记密码 -->\n            <a class=\"sign-in-forgot\" >忘记密码</a>\n            <!-- 登录 -->\n            <button nz-button (click)=\"signIn(phoneNum.value,password.value)\" class=\"sign-in-button\" [nzType]=\"'primary'\">登录</button>\n              还没有账户？\n            <!-- 注册 -->\n            <a routerLink=\"/sign-up\">创建一个!</a>\n          </nz-form-control>\n        </nz-form-item>\n      </form>\n    </div>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/sign-in/sign-in.component.ts":
/*!**********************************************!*\
  !*** ./src/app/sign-in/sign-in.component.ts ***!
  \**********************************************/
/*! exports provided: SignInComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SignInComponent", function() { return SignInComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var ng_zorro_antd__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ng-zorro-antd */ "./node_modules/ng-zorro-antd/esm5/antd.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _services_user_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/user.service */ "./src/app/services/user.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var SignInComponent = /** @class */ (function () {
    function SignInComponent(fb, userService, message) {
        this.fb = fb;
        this.userService = userService;
        this.message = message;
    }
    SignInComponent.prototype.createMessage = function (type) {
        if (type === 'success') {
            this.message.create(type, "\u767B\u5F55\u6210\u529F\uFF01\u6B63\u5728\u8FDB\u5165\u60A8\u7684\u7528\u6237\u4E2D\u5FC3...");
        }
        else {
            this.message.create(type, "\u7528\u6237\u540D\u6216\u5BC6\u7801\u6709\u8BEF\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165");
        }
    };
    // “登录”按钮提交
    SignInComponent.prototype.submitForm = function () {
        // 循环表单的所有常规验证
        for (var i in this.validateForm.controls) {
            this.validateForm.controls[i].markAsDirty();
            this.validateForm.controls[i].updateValueAndValidity();
        }
        console.log();
    };
    SignInComponent.prototype.signIn = function (phoneNum, password) {
        var _this = this;
        this.userService.login({
            phoneNum: phoneNum,
            password: password
        }).subscribe(function (data) {
            if (data.code === 500) {
                _this.createMessage('error');
            }
            else if (data.code === 200) {
                _this.createMessage('success');
            }
        });
    };
    SignInComponent.prototype.ngOnInit = function () {
        this.validateForm = this.fb.group({
            phoneNum: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]],
            password: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]],
            remember: [true] // 是否记住
        });
    };
    SignInComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'sign-in-layout',
            template: __webpack_require__(/*! ./sign-in.component.html */ "./src/app/sign-in/sign-in.component.html"),
            styles: [__webpack_require__(/*! ./sign-in.component.css */ "./src/app/sign-in/sign-in.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormBuilder"], _services_user_service__WEBPACK_IMPORTED_MODULE_3__["UserService"], ng_zorro_antd__WEBPACK_IMPORTED_MODULE_1__["NzMessageService"]])
    ], SignInComponent);
    return SignInComponent;
}());



/***/ }),

/***/ "./src/app/sign-up/sign-up.component.css":
/*!***********************************************!*\
  !*** ./src/app/sign-up/sign-up.component.css ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "/* “注册” 二字 */\r\n.nf4-sign__sign-up-text{\r\n  text-align: center;\r\n  color: #404040;\r\n  font-size: 2rem;\r\n  font-weight: 600;\r\n  padding: 0;\r\n  margin-top: 20px;\r\n  margin-bottom: 12px;\r\n}\r\n/* 注册表单 */\r\n.nf4-sign__sign-up-form{\r\n  display: flex;\r\n  justify-content: center;\r\n}\r\n/* antd 样式 */\r\n[nz-form] {\r\n  max-width: 600px;\r\n}"

/***/ }),

/***/ "./src/app/sign-up/sign-up.component.html":
/*!************************************************!*\
  !*** ./src/app/sign-up/sign-up.component.html ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!-- 注册模块 -->\n<div class=\"sign-up\">\n  <!-- brand -->\n  <brand-layout></brand-layout>\n  <!-- 注册表单模块 -->\n  <div class=\"nf4-sign__sign-up\">\n    <!-- “注册” 标题 -->\n    <div class=\"nf4-sign__sign-up-text\">\n      <span>注册</span>\n    </div>\n    <!-- 注册 表单div -->\n    <div class=\"nf4-sign__sign-up-form\">\n      <!-- 注册表单 -->\n    <form nz-form [formGroup]=\"validateForm\" (ngSubmit)=\"submitForm()\">\n      <!-- 手机号 -->\n      <nz-form-item>\n          <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"phoneNumber\" nzRequired>手机号码</nz-form-label>\n          <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\" [nzValidateStatus]=\"validateForm.controls['phoneNumber']\">\n            <!-- 在input前+“+86”标签 -->\n            <nz-input-group nzAddOnBefore=\"+86\">\n              <input formControlName=\"phoneNumber\" #phoneNumber nz-input>\n            </nz-input-group>\n          <!-- 判断是否修改过&&提示错误 -->\n          <nz-form-explain *ngIf=\"validateForm.get('phoneNumber').dirty && validateForm.get('phoneNumber').errors\">请输入手机号!</nz-form-explain>\n        </nz-form-control>\n      </nz-form-item>\n      <!-- 密码 -->\n      <nz-form-item>\n        <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"password\" nzRequired>密码</nz-form-label>\n        <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">\n          <input nz-input type=\"password\" id=\"password\" formControlName=\"password\" (ngModelChange)=\"updateConfirmValidator()\">\n          <!-- 判断是否修改过&&提示错误 -->\n          <nz-form-explain *ngIf=\"validateForm.get('password').dirty && validateForm.get('password').errors\">请输入密码!</nz-form-explain>\n        </nz-form-control>\n      </nz-form-item>\n      <!-- 确认密码 -->\n      <nz-form-item>\n        <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"checkPassword\" nzRequired>确认密码</nz-form-label>\n        <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">\n          <input nz-input type=\"password\" formControlName=\"checkPassword\" id=\"checkPassword\">\n          <!-- 判断是否修改过&&提示错误 -->\n          <nz-form-explain *ngIf=\"validateForm.get('checkPassword').dirty && validateForm.get('checkPassword').errors\">\n            <ng-container *ngIf=\"validateForm.get('checkPassword').hasError('required')\">\n              请确认密码!\n            </ng-container>\n            <!-- 验证两次密码是否输入一致 -->\n            <ng-container *ngIf=\"validateForm.get('checkPassword').hasError('confirm')\">\n              您输入的两个密码不一致!\n            </ng-container>\n          </nz-form-explain>\n        </nz-form-control>\n      </nz-form-item>\n      <!-- 用户名 -->\n      <nz-form-item>\n        <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"nickname\" nzRequired>\n          <span>\n            用户名\n            <nz-tooltip nzTitle=\"What do you want other to call you\">\n              <i nz-tooltip class=\"anticon anticon-question-circle-o\"></i>\n            </nz-tooltip>\n          </span>\n        </nz-form-label>\n        <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">\n          <input nz-input id=\"nickname\" formControlName=\"nickname\">\n          <!-- 判断是否修改过&&提示错误 -->\n          <nz-form-explain *ngIf=\"validateForm.get('nickname').dirty && validateForm.get('nickname').errors\">!</nz-form-explain>\n        </nz-form-control>\n      </nz-form-item>\n      <!-- 验证码 -->\n      <nz-form-item>\n        <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"captcha\" nzRequired>验证码</nz-form-label>\n        <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">\n          <div nz-row [nzGutter]=\"8\">\n            <div nz-col [nzSpan]=\"12\">\n              <input nz-input formControlName=\"captcha\" id=\"captcha\">\n            </div>\n            <div nz-col [nzSpan]=\"12\">\n              <button nz-button (click)=\"getCaptcha(phoneNumber.value)\">获取验证码</button>\n            </div>\n          </div>\n          <!--<nz-form-explain *ngIf=\"validateForm.get('captcha').dirty && validateForm.get('captcha').errors\">Please input the captcha you got!</nz-form-explain>-->\n        </nz-form-control>\n      </nz-form-item>\n      <!-- 协议 -->\n      <nz-form-item nz-row style=\"margin-bottom:8px;\">\n        <nz-form-control [nzSpan]=\"18\" [nzOffset]=\"4\">\n          <label nz-checkbox formControlName=\"agree\">\n            <span>我已阅读<a>《nf4用户协议》</a></span>\n          </label>\n        </nz-form-control>\n      </nz-form-item>\n      <!-- 注册按钮 -->\n      <nz-form-item nz-row style=\"margin-bottom:8px;\">\n        <nz-form-control [nzSpan]=\"14\" [nzOffset]=\"6\">\n          <button nz-button nzType=\"primary\">注册</button>\n        </nz-form-control>\n      </nz-form-item>\n    </form>\n    </div>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/sign-up/sign-up.component.ts":
/*!**********************************************!*\
  !*** ./src/app/sign-up/sign-up.component.ts ***!
  \**********************************************/
/*! exports provided: SignUpComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SignUpComponent", function() { return SignUpComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _services_user_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/user.service */ "./src/app/services/user.service.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var SignUpComponent = /** @class */ (function () {
    function SignUpComponent(fb, userService) {
        var _this = this;
        this.fb = fb;
        this.userService = userService;
        this.confirmationValidator = function (control) {
            if (!control.value) {
                return { required: true };
            }
            else if (control.value !== _this.validateForm.controls.password.value) {
                return { confirm: true, error: true };
            }
        };
    }
    SignUpComponent.prototype.submitForm = function () {
        for (var i in this.validateForm.controls) {
            this.validateForm.controls[i].markAsDirty();
            this.validateForm.controls[i].updateValueAndValidity();
        }
    };
    SignUpComponent.prototype.updateConfirmValidator = function () {
        var _this = this;
        /** wait for refresh value */
        Promise.resolve().then(function () { return _this.validateForm.controls.checkPassword.updateValueAndValidity(); });
    };
    SignUpComponent.prototype.getCaptcha = function (phoneNumber) {
        this.userService.sendVerifyCode(phoneNumber).subscribe();
        alert(phoneNumber);
    };
    SignUpComponent.prototype.ngOnInit = function () {
        this.validateForm = this.fb.group({
            email: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].email]],
            password: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required]],
            checkPassword: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required, this.confirmationValidator]],
            nickname: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required]],
            phoneNumberPrefix: ['+86'],
            phoneNumber: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required]],
            website: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required]],
            captcha: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required]],
            agree: [false]
        });
    };
    SignUpComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'sign-up-layout',
            template: __webpack_require__(/*! ./sign-up.component.html */ "./src/app/sign-up/sign-up.component.html"),
            styles: [__webpack_require__(/*! ./sign-up.component.css */ "./src/app/sign-up/sign-up.component.css")]
        }),
        __metadata("design:paramtypes", [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormBuilder"], _services_user_service__WEBPACK_IMPORTED_MODULE_2__["UserService"]])
    ], SignUpComponent);
    return SignUpComponent;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\programme\AngularSpace\nf4-sign\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map