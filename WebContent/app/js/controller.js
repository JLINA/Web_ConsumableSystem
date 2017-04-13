;
(function() {
	var app = angular.module('data.controller', []);
	//111111111111111111111111111111111111111111111111主页面的控制器

	app.controller('mainpageCtrl', function($scope, $http, $window, $rootScope,$state) {
		$scope.mainpages2 = [{
			name: '我的',
			id: '1',
			url: '#/mainpage/login',
			src:'img/wode.png'
		}, {
			name: '数据',
			id: '2',
			url: '#/mainpage/data',
			src:'img/data.png'
		}, {
			name: '历史',
			id: '3',
			url: '#/mainpage/detail',
			src:'img/jieshao.png'
		}]
		$rootScope.id = 1;
		$scope.toggle = function(id, url) {
			$rootScope.id = id;
			$window.location.href = url;
//			window.location.reload();
			$state.reload();
		}

	});
//	22222222222222222222222222222222222222222222超级管理员
	app.controller('datacjCtrl', function($scope, $rootScope, $http, $state, cookie) {
		var password = cookie.getCookie('password');
		var username = cookie.getCookie('username');
		var role = cookie.getCookie('role');
		var id = cookie.getCookie('id');
		var idw;
		if (password == undefined || username == undefined || role == undefined) {
			window.location.href = "#/mainpage/login"
		} else if (role == "ptyh"||role == "gly") {
			//普通用户则显示普通用户界面
			window.location.href = "#/mainpage/data";
		} else if (role == "cjgly") {
			window.location.href = "#/mainpage/datacj"
		}
		
		//--------------超级管理员
		//获取数据
		$http.post("/Web_ConsumableSystem/selectusers.do", {
			str: name,
			count:12,
			pageNum:1
		}).success(function(data) {
			var arry = data.split("|");
			$("#xx2").html(1);
			$scope.glydatas = JSON.parse(arry[1]);
			$rootScope.allPage = arry[0];
//         $scope.glydatas=data;
		});
		//把已经选中的id放到数组中
		
		//点击删除
		
		
		$scope.glysc = function() {
			var idw = [];
			var q="0";
			$('input:checkbox[name=check]:checked').each(function(i) {
				idw[i] = $(this).val();
				q=q+","+idw[i];
			});
			 
			     $http.post("/Web_ConsumableSystem/deleteuser.do", {
			    	 ids: q
				}).success(function(data) {
					for (var i = 0; i < idw.length; i++) {
						
						$("#superishide"+idw[i]).hide();
						$state.reload();
					}

				})
		//		console.log($scope.glydatas);
			}
		//鼠标经过背景变换
		$(".tablebj1 tr td").hover(function(){
	    	//鼠标划入每一行时样式背景变
	    	   $(this).toggleClass("bhbj1");
	     });
	     //分页部分鼠标经过时的样式变化
	     $(".turnPage span").hover(function(){
	     	//鼠标划入每一行时样式背景变
	    	   $(this).toggleClass("bhbj2");   
	     })
		//--------------------分页
		
		//上一页
	$scope.previous = function() {
		var pageNum = $("#xx2").html();
		var name = $("#input").val();
		pageNum = parseInt(pageNum) - 1;
		if (pageNum < 1) {
			alert('这是第一页');
		} else {
			$http.post("/Web_ConsumableSystem/selectusers.do", {
				count: 12,
				pageNum: pageNum,
				str: name
			}).success(function(data) {
				var arr = data.split("|");
				$("#xx2").html(pageNum);
				$scope.glydatas = JSON.parse(arr[1]);
				$rootScope.allPage = arr[0];

			})

		}
	};
	//下一页
	$scope.next = function() {
		var name = $("#input").val();
		var pageNum = $("#xx2").html();
		var allPage = $scope.allPage;

		pageNum = parseInt(pageNum) + 1;

		if (pageNum > allPage) {
			alert('这是最后一页');
		} else {
			$http.post("/Web_ConsumableSystem/selectusers.do", {
				count: 12,
				pageNum: pageNum,
				str: name
			}).success(function(data) {
				var arr = data.split("|");
				$("#xx2").html(pageNum);
				$scope.glydatas = JSON.parse(arr[1]);
				$rootScope.allPage = arr[0];

			})
		}
	};

	//共多少页
	$scope.goToPage = function() {
		//从input输入框绑定的currentPage变量中获取用户输入的值
		var pageNum = $scope.currentPages;
		//alert($scope.currentPages);
		var name = $("#input").val();
		//为了程序的严密和可用性，需要先判断输入是否为空
		if (pageNum == null || pageNum == undefined || pageNum == "") {
			alert("请输入页数");
			//如果不是空，再判断用户输入的页码是否超出了页码的范围
			//这里出现了$rootScope这个根域及其属性allPage，该属性的值是页码的总数
		} else if (!(pageNum >= 1 && pageNum <= $rootScope.allPage)) {
			alert("您输入的页数已经超出了范围");
		} else {
			//如果都没问题了，则修改URL，此时angularJS会捕捉地址栏的变化，然后跳转，细节将在下面讲解。
			$http.post("/Web_ConsumableSystem/selectusers.do", {
				count: 12,
				pageNum: pageNum,
				str: name
			}).success(function(data) {
				var arr = data.split("|");
				$("#xx2").html(pageNum);
				$scope.glydatas = JSON.parse(arr[1]);
				$rootScope.allPage = arr[0];
			})

		}
	};


			//重置密码
		$scope.rspassword = function() {
			var idw = [];
			var q="0";
			$('input:checkbox[name=check]:checked').each(function(i) {
				idw[i] = $(this).val();
				q=q+","+idw[i];
			});
				$http.post("/Web_ConsumableSystem/updateuser.do", {
					ids: q,
					password:123456
				}).success(function(data) {
					$("[name='check']").removeAttr("checked");   //取消选中
					$state.reload();
				})
			}
			//赋予权限
		$scope.fquanx = function() {
			var idw = [];
			var q="0";
			$('input:checkbox[name=check]:checked').each(function(i) {
				idw[i] = $(this).val();
				q=q+","+idw[i];
			});
				$http.post("/Web_ConsumableSystem/updateuser.do", {
					ids: q,
					role:1
				}).success(function(data) {
					$("[name='check']").removeAttr("checked");
					
					
				})
			}
			//收回权限
		$scope.squanx = function() {
			var idw = [];
			var q="0";
			$('input:checkbox[name=check]:checked').each(function(i) {
				idw[i] = $(this).val();
				q=q+","+idw[i];
			});
				$http.post("/Web_ConsumableSystem/updateuser.do", {
					ids: q,
					role:-1
				}).success(function(data) {
					$("[name='check']").removeAttr("checked");
					
				})
			}
			//冻结
		$scope.dongj = function() {
			var idw = [];
			var q="0";
			$('input:checkbox[name=check]:checked').each(function(i) {
				idw[i] = $(this).val();
				q=q+","+idw[i];
			});
				$http.post("/Web_ConsumableSystem/updateuser.do", {
					ids: q,
					state:-1
				}).success(function(data) {
					$("[name='check']").removeAttr("checked");
					$state.reload();
				})
			}
			//解冻
		$scope.jdong = function() {
			var idw = [];
			var q="0";
			$('input:checkbox[name=check]:checked').each(function(i) {
				idw[i] = $(this).val();
				q=q+","+idw[i];
			});
				$http.post("/Web_ConsumableSystem/updateuser.do", {
					ids: q,
					state:1
				}).success(function(data) {
					$("[name='check']").removeAttr("checked");
					$state.reload();
				})
			}
		//搜索按钮
		//默认搜索内容为空
		//  $scope.searchName='';
	$scope.searcher = function() {
		var inp = $("#input5").val();
		
		$http.post("/Web_ConsumableSystem/selectusers.do", {
			pageNum: 1,
			count: 12,
			str: inp
		}).success(function(data) {
			var arr = data.split("|");
			$("#xx2").html(1);
			$scope.glydatas = JSON.parse(arr[1]);
			$rootScope.allPage = arr[0];
		})
	}
	})
	
	//	22222222222222222222222222222222222222222222超级管理员详情页
	app.controller('superxqCtrl', function($scope, $rootScope, $http, $state, cookie) {
		var id5 = $state.params.id;
		var role = cookie.getCookie('role');
		if (role !='cjgly') {
			window.location.href = "#/mainpage/login"
		}
		//--------------超级管理员
		//获取数据
		$http.post("/Web_ConsumableSystem/selectuser.do", {
			id:id5
		}).success(function(data) {
		//	console.log(data);
			$scope.superxqys =data;
		});

	})
	//22222222222222222222222222222222222222222222222222数据页面
	app.controller('dataCtrl', function($scope, $rootScope, $http, $state, cookie) {
		$scope.administer = false;
		$scope.ptyh=false;
		var password = cookie.getCookie('password');
		var username = cookie.getCookie('username');
		var role = cookie.getCookie('role');
		var id = cookie.getCookie('id');
		if (password == undefined || username == undefined || role == undefined) {
			window.location.href = "#/mainpage/login"
		} else if (role == "ptyh") {
			//普通用户则显示普通用户界面
			$scope.administer = false;
			$scope.ptyh=true;
		} else if (role == "gly") {
			$scope.administer = true;
			$scope.ptyh=false;
		} else if (role == "cjgly") {
			window.location.href = "#/mainpage/datacj"
		}
		//搜索下拉框
        $http.post("/Web_ConsumableSystem/gettypes.do",{
        	typeids:''
        }).success(function(res){
        	var ret=[];
        	var hash={};
        	for(var i=0;i<res.length;i++){
        		var item=res[i].typeid;
        		var key=typeof(item)+item;
        		if(hash[key]!=1){
        			ret.push(item);
        			hash[key]=1;
        		}
        	}
        	
        	  $scope.seachers=ret;
        })
       
        //分页部分鼠标经过时的样式变化
	     $(".turnPage span").hover(function(){
	     	//鼠标划入每一行时样式背景变
	    	   $(this).toggleClass("bhbj2");   
	     })
		//--------------------分页
			//上一页
		$scope.previous = function() {
			var pageNum = $("#x").html();
			var name = $("#input").val();
			var typeid=$scope.typeid1;
			console.log(typeid);
			pageNum = parseInt(pageNum) - 1;
			if (pageNum < 1) {
				alert('这是第一页');
			} else {
				$http.post("/Web_ConsumableSystem/findall.do", {
					count: 15,
					pageNum: pageNum,
					name: name,
					typeid:typeid,
					typeids:"1",
					stprice:"0",
					endprice:"999999",
					price:""
				}).success(function(data) {
					//  $location.path("mainpage/data/"+pageNum);

					var arr = data.split("|");

					$("#x").html(pageNum);
					$scope.datacontents = JSON.parse(arr[1]);

				})

			}
		};
		//下一页
		$scope.next = function() {
			var name = $("#input").val();
			var pageNum = $("#x").html();
			var allPage = $scope.allPage;
			var typeid=$scope.typeid1;
			console.log(typeid);
			pageNum = parseInt(pageNum) + 1;

			if (pageNum > allPage) {
				alert('这是最后一页');
			} else {
				$http.post("/Web_ConsumableSystem/findall.do", {
					count: 15,
					pageNum: pageNum,
					name: name,
					typeid:typeid,
					typeids:"1",
					stprice:"0",
					endprice:"999999",
					price:""
				}).success(function(data) {
					//  $location.path("mainpage/data/"+pageNum);

					var arr = data.split("|");
					$("#x").html(pageNum);
					$scope.datacontents = JSON.parse(arr[1]);

				})
			}
		};

		//共多少页
		$scope.goToPage = function() {
			//从input输入框绑定的currentPage变量中获取用户输入的值
			var pageNum = $scope.currentPages;
			var name = $("#input").val();
			var typeid=$scope.typeid1;
			console.log(typeid);
			//为了程序的严密和可用性，需要先判断输入是否为空
			if (pageNum == null || pageNum == undefined || pageNum == "") {
				alert("请输入页数");
				//如果不是空，再判断用户输入的页码是否超出了页码的范围
				//这里出现了$rootScope这个根域及其属性allPage，该属性的值是页码的总数
			} else if (!(pageNum >= 1 && pageNum <= $rootScope.allPage)) {
				alert("您输入的页数已经超出了范围");
			} else {
				//如果都没问题了，则修改URL，此时angularJS会捕捉地址栏的变化，然后跳转，细节将在下面讲解。
				$http.post("/Web_ConsumableSystem/findall.do", {
					count: 15,
					pageNum: pageNum,
					name: name,
					typeid:typeid,
					typeids:"1",
					stprice:"0",
					endprice:"999999",
					price:""
				}).success(function(data) {

					var arr = data.split("|");
					$("#x").html(pageNum);
					$scope.datacontents = JSON.parse(arr[1]);
					
				})

			}
		};

		//  $scope.datacontents=[];
		$http.post("/Web_ConsumableSystem/findall.do", {
			pageNum: 1,
			count: 15,
     		name: name,
			stprice:"0",
			endprice:"999999",
		}).success(function(data) {
			var arr = data.split("|");
			$("#x").html(1);
			$scope.datacontents = JSON.parse(arr[1]);
			$rootScope.allPage = arr[0];
			// $scope.datacontents=data;
		})

		
//			//生成二维码
//		$scope.ewms = [];
//		$scope.ewm = function(id) {
//				alert(id);
//				//显示二维码表格
//				$scope.isewm = true;
//				$http.post('/Web_ConsumableSystem/findone.do', {
//					id: id
//				}).success(function(data) {
//					alert("2");
//
//					$scope.ewms = $scope.ewms.concat(data);
//					console.log($scope.ewms);
//				})
//			}
//			//删除二维码
//		$scope.ewmsc = function(index) {
//				//     	    	  $scope.post("",{
//				//     	    	  	   id:index
//				//     	    	  }).success(function(data){
//				$scope.ewms.splice(index, 1);
//				console.log($scope.ewms);
//				//     	    	  })
//
//			}
//			//打印按钮
//		$scope.printadd = function() {
//				console.log("打印");
//			}
			//搜索按钮
			//默认搜索内容为空
			//  $scope.searchName='';
		$scope.searcher = function() {
			var inp = $("#input").val();
			var typeid=$scope.typeid1;
			
			$http.post("/Web_ConsumableSystem/findall.do", {
				pageNum: 1,
				count: 15,
				name: inp,
				typeid:typeid,
				typeids:"1",
				stprice:"0",
				endprice:"999999",
				price:""
			}).success(function(data) {
				var arr = data.split("|");
				$("#x").html(1);
				$scope.datacontents = JSON.parse(arr[1]);
				$rootScope.allPage = arr[0];
			//	alert($scope.datacontents);

			})
		}
		$scope.databj = function() {
				console.log("编辑");

			}
			//删除数据
	
		$scope.datadelet = function(index) {
		//	     	console.log("删除记录");	   
		//	     	console.log(index);	 
			$http.post("/Web_ConsumableSystem/delete.do", {
				ids: index
			}).success(function(data) {
				if(data=="1"){
			//		console.log($scope.datacontents);
				//	$scope.datacontents.splice(index-1,1);
					$("#ishide"+index).hide();
				//	console.log("ishide"+index);
					console.log("删除记录成功");
					
				}
			})
		}
	});
	
		//编辑页面的控制器
	app.controller('editorCtrl', function($scope, $http, $state,cookie) {
		$scope.numbers=false;
		$scope.typeis=true;
		$scope.isgly = false;
		var str = window.location.search;
	    var arr=str.split("=");
		var id1 = $state.params.id;
		var materialid;
		if(id1!=null){
			materialid=id1;
		}else if(arr[1]!=null){
			materialid=arr[1];
		}
		$http.post('/Web_ConsumableSystem/findone.do', {
			id: materialid
		}).success(function(data) {
			var d1 = new Date(data[0].leavefactorydate);
			var d2 = new Date(data[0].indate);
			var d3 = new Date(data[0].buydate);
			
		    $scope.d1=d1;
		    $scope.d2=d2;  
		    $scope.d3=d3;
			$scope.contents = data;
			console.log(data);
		});
		
		var role = cookie.getCookie('role');
		var id = cookie.getCookie('id');
		if (role == "ptyh"|| role ==null) {
			//判断是否是普通用户还是管理员，超级管理员
			$scope.isgly = false;
			$scope.isglyp=true;
			
		} else if (role == "gly") {
			//	   		   	    //判断是否是普通用户，管理员还是超级管理员
			$scope.isgly = true;
			$scope.isglyp=true;
		
		}
		//点击修改信息
		
		$scope.updata = function() {
			$scope.numbers=true;
			$scope.isshow=true;
			$scope.typeis=false;
			$(".in").removeAttr("disabled","disabled");
			//搜索下拉框
	        $http.post("/Web_ConsumableSystem/gettypes.do",{
	        	typeids:''
	        }).success(function(res){
	        	var ret=[];
	        	var hash={};
	        	for(var i=0;i<res.length;i++){
	        		var item=res[i].typeids;
	        		var key=typeof(item)+item;
	        		if(hash[key]!=1){
	        			ret.push(item);
	        			hash[key]=1;
	        		}
	        	}
	        	
	        	  $scope.seachers=ret;
	        });
	        $scope.xgsj = function() {
	        	//搜索下拉框
	            $http.post("/Web_ConsumableSystem/gettypes.do",{
	            	typeids:$("#type2").val()
	            }).success(function(res){
	            	var ret=[];
	            	var hash={};
	            	for(var i=0;i<res.length;i++){
	            		var item=res[i].typeid;
	            		var key=typeof(item)+item;
	            		if(hash[key]!=1){
	            			ret.push(item);
	            			hash[key]=1;
	            		}
	            	}
	            	
	            	  $scope.seachers1=ret;
	            })
	        }
		}
		$scope.savedata = function() {
			
			var d11 = new Date($scope.contents[0].leavefactorydate);
			var d22 = new Date($scope.contents[0].buydate);
			var d33 = new Date($scope.contents[0].indate);
		    var d1=d11.getFullYear() + '-' + (d11.getMonth() + 1) + '-' + d11.getDate() ;
		    var d2=d22.getFullYear() + '-' + (d22.getMonth() + 1) + '-' + d22.getDate() ;
		    var d3=d33.getFullYear() + '-' + (d33.getMonth() + 1) + '-' + d33.getDate() ; 
			$http.post('/Web_ConsumableSystem/updateone.do', {
				id:$scope.contents[0].id,
			    numbers:$scope.contents[0].numbers,
			    name:$scope.contents[0].name,
			    type:$scope.contents[0].type,
			    typeid:$("#type3").val(),
			    standstandard:$scope.contents[0].standstandard,
			    price:$scope.contents[0].price,
			    factory:$scope.contents[0].factory,
			    leavefactorydate:$scope.d1,
			    buydate:$scope.d2,
			    indate:$scope.d3,
			    place:$scope.contents[0].place,
			    usesituation:$scope.contents[0].usesituation,
			    count:$scope.contents[0].count,
			    surplus:$scope.contents[0].surplus, 
			    remarks:$scope.contents[0].remarks, 
			}).success(function(data) {
				
				if (data == 1) {
					alert("保存成功");
					$scope.typeis=true;
					$scope.isshow=false;
					$scope.numbers=false;
					$(".in").attr("disabled","true");
				} else {
					alert("保存失败");
				}

			})
			

		}
		
		//器材出库入库 
		

		$scope.inku=function(){
			   
			var inkunum=$("#num1").val();
			  var id= materialid;
			  $http.post('/Web_ConsumableSystem/updatesurplus.do', {
					id:id,
					surplu:inkunum
				}).success(function(data) {
					if (data == "1") {
						alert("保存成功");
						window.location.reload();
					} else {
						alert("保存失败");
					}

				});
		}
	});
	
	//444444444444444444444444444444444444444444444444登录注册的控制器
	app.controller('loginCtrl', function($scope, $http, cookie) {
		var password = cookie.getCookie('password');
		var username = cookie.getCookie('username');
		var lasttime = cookie.getCookie('lasttime');
		var phone = cookie.getCookie('phone');
		var id = cookie.getCookie('id');
		var role = cookie.getCookie('role');
		if(username==undefined||password==undefined||lasttime==undefined||phone==undefined||id==undefined||role==undefined){
			   
		}else{
			window.location.href = "#/mainpage/login2"	 
		}
		//刷新验证码
		var newcodes = function() {
			$http.post("/Web_ConsumableSystem/codeController/code.do", {

			}).success(function(data) {
				$("#imgId").attr('src', "/Web_ConsumableSystem/codeController/code.do");
			})
		}
		newcodes();
		$scope.newcode = function() {
			newcodes();
		}

		//刷新$scope.$apply()
		$scope.page1 = true;
		$scope.page2 = false;
		$scope.boolname1 = false;
		$scope.boolpassword1 = false;
		$scope.code1 = false;
		$scope.login = function() {
			$http.post('/Web_ConsumableSystem/login.do', {
				username: $scope.username,
				password: $scope.password,
				code: $scope.code

			}).success(function(data) {
				var arr = data.split("|");
				if (arr[0] == "1") {
					$scope.datacontents = JSON.parse(arr[1]);
					cookie.setCookie('id', JSON.parse(arr[1])[0].id);
					cookie.setCookie('password', JSON.parse(arr[1])[0].password);
					cookie.setCookie('username', JSON.parse(arr[1])[0].username);
					cookie.setCookie('lasttime', JSON.parse(arr[1])[0].lasttime);
					cookie.setCookie('phone', JSON.parse(arr[1])[0].phone);
					if (JSON.parse(arr[1])[0].role == "普通用户") {
						cookie.setCookie('role', "ptyh");
					} else if (JSON.parse(arr[1])[0].role == "管理员") {
						cookie.setCookie('role', "gly");
					} else if (JSON.parse(arr[1])[0].role == "超级管理员") {
						cookie.setCookie('role', "cjgly");
					}
					window.location.href = "#/mainpage/login2"
				} else if (arr[0] == "3") {
					alert("账户已被冻结");
					window.location.reload();
					$scope.boolname1 = true;
					$scope.boolpassword1 = true;
					$scope.code1 = true;
					//刷新验证码
					newcodes();
				} else if (arr[0] == "4") {
					alert("用户不存在或密码错误");
					window.location.reload();
				} else {
					alert("验证码错误");
					//刷新验证码
					newcodes();
				}

			})
		}
	});
	//5555555555555555555555555555555555555登录2页面
	app.controller("login2Ctrl", function($scope, $http, cookie) {

		var password = cookie.getCookie('password');
		var username = cookie.getCookie('username');
		var lasttime = cookie.getCookie('lasttime');
		var phone = cookie.getCookie('phone');
		var id = cookie.getCookie('id');
		var role = cookie.getCookie('role');
		if(role==""||role==undefined){
			window.location.href = "#/mainpage/login"	
		}
		//在欢迎页面渲染用户名
		$scope.loginusername = username;
		$scope.logintimer = lasttime;
		$scope.phone = phone;
		
		
		//点击修改密码显示输入框
		$scope.xiugaipass=function(){
			 $scope.isxgpass=true;
		}
		//退出登录
		$scope.tuchulogin=function(){
			
			  $http.post("/Web_ConsumableSystem/exit.do", {
				}).success(function(data) {
					if(data=="1"){
						cookie.delCookie('id',id);
						cookie.delCookie('lasttime',lasttime);
						cookie.delCookie('password',password);
						cookie.delCookie('phone',phone);
						cookie.delCookie('role',role);
						cookie.delCookie('username',username);
						window.location.href = "#/mainpage/login"
					}
					
				})

		}
		//新密码
		$scope.repassw = function() {
			
			var newpass=$scope.passwords;
			$http.post("/Web_ConsumableSystem/updateuser.do", {
				ids: id,
				password:newpass
					//    phone:phone
			}).success(function(data) {
				if (data == "1") {
					alert("修改成功");
					//存新密码入cookie
					cookie.setCookie('password', newpass);
//					成功后退出登录
					 $http.post("/Web_ConsumableSystem/exit.do", {
						}).success(function(data) {
							if(data=="1"){
								cookie.delCookie('id',id);
								cookie.delCookie('lasttime',lasttime);
								cookie.delCookie('password',password);
								cookie.delCookie('phone',phone);
								cookie.delCookie('role',role);
								cookie.delCookie('username',username);
								window.location.href = "#/mainpage/login"
							}	
						})
				}
			})

			//修改密码成功然后隐藏输入框
			$scope.isxgpass=false;
		}

	});
	//666666666666666666666666666666666666666666注册页面的控制器
	app.controller("registerCtrl", function($scope, $http) {
		//刷新验证码         
		var newcodes = function() {
			$http.post("/Web_ConsumableSystem/codeController/code.do", {

			}).success(function(data) {
				$("#imgId").attr('src', "/Web_ConsumableSystem/codeController/code.do");
			})
		}
		newcodes();
		$scope.newcode = function() {
			newcodes();
		}

		$scope.page1 = false;
		$scope.page2 = true;
		$scope.register = function() {
			//正则表达式http://www.jb51.net/article/102542.htm
			//验证密码
			//验证用户名4到10个字母
			$scope.boolname = false;
			$scope.namecheck = /^[a-zA-Z]{4,10}$/.test($scope.username);
			if ($scope.namecheck) {} else {
				$scope.boolname = true;
			}
			//字符或字母6-12位，可以是数据和字母的组合，纯数字，纯字母。
			$scope.boolpassword1 = false;
			$scope.pa1check = /^[0-9a-zA-Z]{6,12}$/.test($scope.password1);
			if ($scope.pa1check) {

			} else {
				$scope.boolpassword1 = true;
			}
			//确认密码
			$scope.boolpassword2 = false;
			if ($scope.password2 == $scope.password1) {
				//				    	     console.log("密码一致");
			} else {
				$scope.boolpassword2 = true;
			}
			//验证手机号
			//只能以一开头的11位数字
			$scope.boolnumber = false;
			$scope.numcheck = /^1\d{10}$/.test($scope.phone);
			if ($scope.numcheck) {

			} else {
				$scope.boolnumber = true;
			}

			if ($scope.boolname || $scope.boolpassword || $scope.boolcheckps || $scope.boolnumber) {
				alert("填写的信息格式错误");
				//刷新验证码
				newcodes();
			} else {
			
				$http.post('/Web_ConsumableSystem/register.do', {
					//								   params: {
					username: $scope.username,
					password: $scope.password1,
					phone: $scope.phone,
					code: $scope.code
						//								   }
				}).success(function(data) {
					//后台接受到数据后验证成功返回data==1给前台进行跳转
					if (data == "1") {
						alert("注册成功");
						//跳转到登录页面
						$scope.page1 = true;
						$scope.page2 = false;
						window.location.href = '#/mainpage/login';
					} else if (data == "3") {
						alert("用户已存在");
						//刷新验证码
						newcodes();
					} else {
						alert("验证码错误");
						//刷新验证码
						newcodes();
					}
				})
			}

		}

	});
	//777777777777777777777777777777777777777777777777777介绍页面的控制器
	app.controller('detailCtrl', function($scope, $http, cookie,$rootScope) {
		var password = cookie.getCookie('password');
		var username = cookie.getCookie('username');
		var role = cookie.getCookie('role');
		var id = cookie.getCookie('id');
		var ide;
		if (password == undefined || username == undefined || role == undefined) {
			window.location.href = "#/mainpage/login"
		} else if (role == "ptyh") {
			//判断是否是普通用户还是管理员，超级管理员
			$scope.isptgl= true;
		} else if (role == "gly") {
			//判断是否是普通用户，管理员还是超级管理员
			$scope.isptgl= false;
			//判断是管理员还是超级管理员
			ide=id;
			$scope.cjgly=false;
		} else if (role == "cjgly") {
			//判断是否是普通用户，管理员还是超级管理员
			$scope.isptgl= false;
			//判断是管理员还是超级管理员
			$scope.cjgly=true;
		}
		//加载数据
          $http.post("/Web_ConsumableSystem/selecthistory.do",{
        	  count:15,
        	  pageNum:1,
        	    str:ide
          }).success(function(data) {
				var arry = data.split("|");
				$("#zz").html(1);
				$scope.allPages=arry[0];
	//			console.log(JSON.parse(arry[1]));
				$scope.detaildatas = JSON.parse(arry[1]);
			})
			//分页
			//分页部分鼠标经过时的样式变化
	     $(".turnPage span").hover(function(){
	     	//鼠标划入每一行时样式背景变
	    	   $(this).toggleClass("bhbj2");   
	     })
			//--------------------分页
			//上一页
		$scope.previous = function() {
			var pageNum = $("#zz").html();
	//		var str = $("#input").val();
			pageNum = parseInt(pageNum) - 1;
			if (pageNum < 1) {
				alert('这是第一页');
			} else {
				
					$http.post("/Web_ConsumableSystem/selecthistory.do", {
						count:15,
						pageNum: pageNum,
						str: ide
					}).success(function(data) {
						var arry = data.split("|");
						$("#zz").html(pageNum);
						$scope.allPages=arry[0];
			//			console.log(JSON.parse(arry[1]));
						$scope.detaildatas = JSON.parse(arry[1]);

					})

				
				
			}
		};
		//下一页
		$scope.next = function() {
	//		var str = $("#input").val();
			var pageNum = $("#zz").html();
			var allPage = $scope.allPages;
			pageNum = parseInt(pageNum) + 1;

			if (pageNum > allPage) {
				alert('这是最后一页');
			} else {
				   alert
					$http.post("/Web_ConsumableSystem/selecthistory.do", {
						count: 15,
						pageNum: pageNum,
						str: ide
					}).success(function(data) {
						var arry = data.split("|");
						$("#zz").html(pageNum);
						$scope.allPages=arry[0];
			//			console.log(JSON.parse(arry[1]));
						$scope.detaildatas = JSON.parse(arry[1]);

					})
				
				
			}
		};

		//共多少页
		$scope.goToPage = function() {
			//从input输入框绑定的currentPage变量中获取用户输入的值
			var pageNum = $scope.currentPage;
			pageNum = parseInt(pageNum);
			console.log($scope.currentPage);
			console.log($scope.allPages);
		//	var str = $("#input").val();
			//为了程序的严密和可用性，需要先判断输入是否为空
			if (pageNum == null || pageNum == undefined || pageNum == "") {
				alert("请输入页数");
				//如果不是空，再判断用户输入的页码是否超出了页码的范围
				//这里出现了$rootScope这个根域及其属性allPage，该属性的值是页码的总数
			} else if ((pageNum < 1) ||( pageNum > $scope.allPages)) {
				alert("您输入的页数已经超出了范围");
			} else {
				//如果都没问题了，则修改URL，此时angularJS会捕捉地址栏的变化，然后跳转，细节将在下面讲解。
				$http.post("/Web_ConsumableSystem/selecthistory.do", {
					count: 15,
					pageNum: pageNum,
					str: ide
				}).success(function(data) {

					var arry = data.split("|");
					$("#zz").html(pageNum);
					$scope.allPages=arry[0];
		//			console.log(JSON.parse(arry[1]));
					$scope.detaildatas = JSON.parse(arry[1]);
				})

			}
		};

			//搜索
			//搜索按钮
			//默认搜索内容为空
			//  $scope.searchName='';
		$scope.searcher = function() {
			var inp = $("#input").val();
			alert(inp)
			$http.post("/Web_ConsumableSystem/findall.do", {
				pageNum: 1,
				count: 7,
				name: inp
			}).success(function(data) {
				var arr = data.split("|");
				$("#x").html(1);
				$scope.datacontents = JSON.parse(arr[1]);
				$rootScope.allPage = arr[0];
				alert($scope.datacontents);

			})
		}

	});
	//添加数据
	app.controller("adddataCtrl",function($scope,$http,cookie){
		var role = cookie.getCookie('role');
		var id = cookie.getCookie('id');
		if(role!= "gly"){
			   window.location.href = "#/mainpage/login"
		}
		//搜索下拉框
        $http.post("/Web_ConsumableSystem/gettypes.do",{
        	typeids:''
        }).success(function(res){
        	var ret=[];
        	var hash={};
        	for(var i=0;i<res.length;i++){
        		var item=res[i].typeids;
        		var key=typeof(item)+item;
        		if(hash[key]!=1){
        			ret.push(item);
        			hash[key]=1;
        		}
        	}
        	
        	  $scope.seachers=ret;
        });
        $scope.typeids1=function(){
        	//搜索下拉框
            $http.post("/Web_ConsumableSystem/gettypes.do",{
            	typeids:$scope.typeia1
            }).success(function(res){
            	var ret=[];
            	var hash={};
            	for(var i=0;i<res.length;i++){
            		var item=res[i].typeid;
            		var key=typeof(item)+item;
            		if(hash[key]!=1){
            			ret.push(item);
            			hash[key]=1;
            		}
            	}
            	
            	  $scope.seachers2=ret;
            });	
        };
		$scope.savedata=function(){
			console.log($scope.typeia);
			  $http.post("/Web_ConsumableSystem/addmaterial.do",{
				    numbers:$scope.numbers,
			  	    name:$scope.name,
			  	    type:$scope.type,
			  	    typeid:$scope.typeia,
			  	    standstandard:$scope.standstandard,
			  	    price:$scope.price,
			  	    factory:$scope.factory,
			  	    leavefactorydate:$scope.leavefactorydate,
			  	    buydate:$scope.buydate,
			  	    place:$scope.place,
			  	    usesituation:$scope.usesituation,
			  	    remarks:$scope.remarks,
			  	    surplus:$scope.surplus

			  }).success(function(data){
			  	   if(data=="1"){
			  	   	    console.log("保存成功");
			  	   	    window.location.href = '#/mainpage/data';
			  	   }else{
			  	   	    console.log("保存失败");
			  	   }
			  })
		}
	})

})();