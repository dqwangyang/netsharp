var app = angular.module('app', []);
var ResultType =
{
    info : "info",
    warn : "warn",
    error: "error",
    loginTimeout:"loginTimeout"
};

app.factory('ajax', function ($http) {	
    var invoke = function (service,methodName,pars,callback) {   	
        var data = {
        		service : service,
        		methodName : methodName,
        		vid : null,
        		parameters:[]
        };
        if (pars) {
        	
            for (var i = 0; i < pars.length; i++) {

            	var j = JSON.stringify(pars[i]);
                var jpa = {value : encodeURIComponent(j)};
                data.parameters.push(jpa);
            }
        }
        $http.post('/panda/rest/service',data)
		.success(function (result, status, headers, config) {

			if(result.type == ResultType.info){
            	
            	if (callback == undefined || callback == null) {
                    return;
                }
                callback(result.data);
            }
            else if(result.type == ResultType.warn){
            	
//		            ionicToast.show(result.message, 'bottom',false, 2000);
//		            $ionicLoading.hide();
            }
            else if(result.type == ResultType.error){
            	
//            	ionicToast.show(result.message, 'bottom',false, 2000);
//            	$ionicLoading.hide();
            }
            else if(result.type == ResultType.loginTimeout){
            	
//            	var alertPopup = $ionicPopup.alert({ title: '错误',template: result.message});
//            	alertPopup.then(function(){
//            		
//            		//window.location.href = "/nav/zgc/weixin/login";
//            	});
//            	$ionicLoading.hide();
            }
			
         }).error(function(data,header,config,status){
        	
//        	 ionicToast.show("系统错误！", 'bottom',false, 2000);
//        	 $ionicLoading.hide();
         });
    }

    return {
    	invoke: function (service,methodName,pars,callback) {
    		
            invoke(service,methodName,pars,callback);
        }
    }
}).factory('UrlParameter', [function () {
	
    var get = function (key) {
    	
		var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			var x = unescape(r[2]);
			return decodeURI(x);
		} else {
			return null;
		}
    }

    return {
    	get: function (key) {
            return get(key);
        }
    }
}]);