var app=angular.module("MyAppHome",[]);
app.controller('HomeController' , function($scope,$http) {
	$scope.pageProduits=null;
	$scope.pageCourante=0;
	$scope.size=10;
	$scope.pages=[];
	$http.get("etudiant")
	.then(function(response){
	$scope.etudiant=response.data;
	
	
		
	})
	.catch(function(response){
		console.log(response.err);
	});
	$scope.getAnnonces=function(){

	}

	$scope.gotoPage=function(p){
		$scope.pageCourante=p;
		$scope.chercherProduits();
		
	}
	$scope.getByMotCle=function(){
		$http.get("produitsMc/"+$scope.motCle)
		.then(function(response){
		$scope.pageProduits=response.data;
		$scope.pages=new Array(response.data.totalPages);
		
			
		})
		.catch(function(response){
			console.log(response.err);
		});
	}
});

var app=angular.module("MyAppMesAnnonces",[]);
app.controller('MesAnnoncesController' , function($scope,$http) {
	$scope.pageProduits=null;
	$scope.pageCourante=0;
	$scope.size=10;
	$scope.pages=[];
	
	$scope.getProduits=function(){
		$http.get("produitsUser")
		.then(function(response){
		$scope.pageProduits=response.data;
		$scope.pages=new Array(response.data.totalPages);
		
			
		})
		.catch(function(response){
			console.log(response.err);
		});
	}
	$scope.getProduits();
	$scope.gotoPage=function(p){
		$scope.pageCourante=p;
		$scope.getProduits();
		
	}
	$scope.deleteProduit=function(id){
		$http.delete("deleteProduit/"+id)
		.then(function(response){
		$scope.pageProduits=response.data;
		$scope.getProduits();
		
		})
		.catch(function(response){
			console.log(response.err);
		});
	}
});

var app=angular.module("MyAppMessage",[]);
app.controller('MessageController' , function($scope,$http) {
	$scope.pageMessages=null;
	$scope.pageCourante=0;
	$scope.size=10;
	$scope.pages=[];
	$http.get("MessageUser")
	.then(function(response){
	$scope.pageMessages=response.data;
	$scope.pages=new Array(response.data.totalPages);
	
		
	})
	.catch(function(response){
		console.log(response.err);
	});
	$scope.getMessages=function(){
		$http.get("MessageUser")
		.then(function(response){
		$scope.pageMessages=response.data;
		$scope.pages=new Array(response.data.totalPages);
		
			
		})
		.catch(function(response){
			console.log(response.err);
		});
		
	}
	$scope.gotoPage=function(p){
		$scope.pageCourante=p;
		$scope.getMessages();
	}
	$scope.lire=function(a){
		$scope.luse=a;
		$("#exampleModalCenter").modal('show');
	}
	$scope.deleteMessage=function(id){
		$http.delete("deleteMessage/"+id)
		.then(function(response){
		$scope.pageMessages=response.data;
		$scope.getMessages();
		
		})
		.catch(function(response){
			console.log(response.err);
		});
	}
});

var app=angular.module("MyAppOffres",[]);
app.controller('OffresController' , function($scope,$http) {
	$scope.pageProduits=null;
	$scope.pageCourante=0;
	$scope.size=10;
	$scope.pages=[];
	$scope.getAnnonces=function(){

	}
	$http.get("produits")
	.then(function(response){
	$scope.pageProduits=response.data;
	$scope.pages=new Array(response.data.totalPages);
	
		
	})
	.catch(function(response){
		console.log(response.err);
	});
	$http.get("types")
	.then(function(response){
	$scope.types=response.data;		
	})
	.catch(function(response){
		console.log(response.err);
	});

	
	$scope.recherchertout=function(URL){
		$http.get(URL)
		.then(function(response){
		$scope.pageProduits=response.data;
		$scope.pages=new Array(response.data.totalPages);
		
			
		})
		.catch(function(response){
			console.log(response.err);
		});
	}
		$scope.rechercher=function(){
		
				var url;
			if($scope.motCle!= undefined && $scope.ville!= undefined && $scope.type == undefined){
				url="produitsMcV/"+$scope.motCle+"/"+$scope.ville;
		}
			else if($scope.motCle!= undefined && $scope.ville!= undefined && $scope.type != undefined){
				url="produitsMcVT/"+$scope.motCle+"/"+$scope.ville+"/"+$scope.type;
			}
			else if($scope.motCle == undefined && $scope.ville == undefined && $scope.type != undefined){
				url="produitsT/"+$scope.type;
			}
			else if($scope.motCle == undefined && $scope.ville != undefined && $scope.type == undefined){
				url="produitsV/"+$scope.ville;
			}
			else if($scope.motCle != undefined && $scope.ville == undefined && $scope.type == undefined){
				url="produitsMc/"+$scope.motCle;
			}
			else if($scope.motCle!= undefined && $scope.ville == undefined && $scope.type != undefined){
				url="produitsMcT/"+$scope.motCle+"/"+$scope.type;
			}
			else if($scope.motCle == undefined && $scope.ville!= undefined && $scope.type != undefined){
				url="produitsVT/"+$scope.ville+"/"+$scope.type;
			}
			else{
				url="produits";
			}
			$scope.recherchertout(url);
			$scope.motCle=undefined;
			$scope.ville=undefined;
			$scope.type=undefined;
		}
	
});
