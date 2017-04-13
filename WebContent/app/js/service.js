;
(function() {
	var app = angular.module('data.service', []);
	app.service("cookie", ['$document', function($document) {
		return {
			setCookie: function(name, value) {
				var days = 10;
				var ex = new Date();
				ex.setTime(ex.getTime() + days * 24 * 60 * 60 * 1000);
				$document[0].cookie = name + "=" + value + ";expires=" + ex;
			},
			getCookie: function(name) {
				var a;
				var reg = new RegExp("(^|)" + name + "=([^;]*)(;|$)");
				if(a = $document[0].cookie.match(reg)) {
					return a[2];
				}
			},
			delCookie:function(name,value){
			    var exp = new Date();
				exp.setTime(exp.getTime() - 1);
//				var cval=getCookie(name);
				if(value!=null)
//				$document[0].cookie= name + "="+cval+";expires="+exp.toGMTString();
				$document[0].cookie= name + "="+value+";expires="+exp.toGMTString();
			}
		}
	}]);
	
	//选区几张照片显示在轮播图上，默认三张
	app.service("swiperImg", [function(){
		return {
			get: function(data, num){
				if(num){
					
				}else{
					//如果没传参数进来则设置取图数量为2
					num = 2;
				}
				var imgs = [];
				angular.forEach(data,function(data, index){
					if(data.havePic&&imgs.length<=num){
						//记录有图片是出现在第几条
						data.id = index
						imgs.push(data)
					}
				})
				return imgs;
			}
		}
	}]);
	//刷新验证码
//	app.service("newcode",[function($http){
//		return{
//			newcodes:function(isnewcode){
//				if(isnewcode){
//					console.log("刷新验证码");
//					$http.post("",{
//     	      	    	  
//     	      	     }).success(function(data){
//     	      	    	  $("#imgId").attr('src',"/Web_ConsumableSystem/codeController/code.do");  
//     	      	     })
//				}else{
//					 console.log("验证码无法刷新");
//				}
//			}
//		}
//	}]);
	//刷新验证码
//	app.service("newcode",[function(){
//		return{
//			newcodes:function(isnewcode,codeapi,imgsrc){
//				if(isnewcode){
//					console.log("刷新验证码");
//					$http.post(codeapi,{
//     	      	    	  
//     	      	     }).success(function(data){
//     	      	    	  $("#imgId").attr('src',imgsrc);  
//     	      	     })
//				}else{
//					 console.log("验证码无法刷新");
//				}
//			}
//		}
//	}]);
})();