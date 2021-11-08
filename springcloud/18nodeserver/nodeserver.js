var http = require('http');
var url = require("url");
var path = require('path');

// 创建server
var server = http.createServer(function(req, res) {
    // 获得请求的路径
    var pathname = url.parse(req.url).pathname;
    res.writeHead(200, { 'Content-Type' : 'application/json; charset=utf-8' });
    // 访问http://localhost:8060/，将会返回{"index":"欢迎来到首页"}
    if (pathname === '/') {
        res.end(JSON.stringify({ "index" : "欢迎来到首页" }));
    }
    // 访问http://localhost:8060/health，将会返回{"status":"UP"}
    else if (pathname === '/health.json') {
        res.end(JSON.stringify({ "status" : "UP" }));//此处的 up down 会影响到 eureka 的状态,UP 代表是 ok 的,此处营房根据实际业务判断,当然也可以直接返回 UP,当程序崩溃后无结果也被认为是DOWN
    }
    else if (pathname==='/userinfo.json'){ //如果访问的是/userinfo.json 返回的是下面的内容
        res.end(JSON.stringify({"name":"zhangsan","age":18,"address":"china"}))
    }
    // 其他情况返回404,内容是404
    else {
        res.end("404");
    }
});
// 创建监听，并打印日志,服务运行的端口是8060
server.listen(8060, function() {
    console.log('listening on localhost:8060');
});