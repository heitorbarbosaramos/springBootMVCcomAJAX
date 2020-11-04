var pageNumber = 0;

$(document).ready(function(){
	$("#loader-img").hide();
	$("#fim-btn").hide();
});

//efeito scroll infinito
$(window).scroll(function(){
	
	var scrolltop = $(this).scrollTop();
	var conteudo = $(document).height() - $(window).height();
	
	console.log('scroll-top', scrolltop , ' | ', 'conteudo',conteudo);
	
	if(scrolltop >= conteudo){
		pageNumber++;
		setTimeout(function(){
			loadByScroolBar(pageNumber);
		},200);		
	}
});

function loadByScroolBar(pageNumber){
	var site = $("#autocomplete-input").val();
	$.ajax({
		method:"GET",
		url:"/promocao/list/ajax",
		data:{
			page:pageNumber,
			site: site},
		beforeSend: function(){
			$("#loader-img").show();
		},
		success:function(response){
			console.log("response.length > ",response.length);
			
			if(response.length > 150){
				$(".row").fadeIn(250, function(){
					$(this).append(response);
				});
			}else{
				$("#fim-btn").show();
				$("#loader-img").removeClass("loader");
			}
			
		},
		error:function(xhr){
			alert("ops, ocorreu um erro: " + xhr.status + ", " + xhr.statusText);
		},
		complete:function(){
			$("#loader-img").hide();
		}
	})
};

$(document).on("click","button[id*='likes-btn-']", function(){
	var id = $(this).attr("id").split("-")[2];
	console.log(">curtida id: ", id);
	
	$.ajax({
		method: "POST",
		url: "/promocao/like/"+id,
		success:function(response){
			console.log("ID " + id + " - LIKES " + response);
			$("#likes-count-" + id).text(response);			
		},
		error:function(xhr){
			console.log("LIKE NÃO CONTABILIZADO, ID: " + id + "> ERROR " + xhr.statusText);
			alert("Ops, seu like não foi contabilizado - " + xhr.status + " - " + xhr.statusText);
		}
	
	})
})

$("#autocomplete-input").autocomplete({
	source: function(request, response){
		$.ajax({
			method:"GET",
			url:"/promocao/site",
			data:{
				termo: request.term
			},
			success:function(result){
				console.log(result)
				response(result);
			}
		})
	}
});

$("#autocomplete-submit").on("click", function(){
	var site = $("#autocomplete-input").val();
	console.log(">busca por site: ",site);
	$.ajax({
		method:"GET",
		url:"/promocao/site/list",
		data:{site},
		beforeSend: function(){
			pageNumber = 0;
			$("#fim-btn").hide();
			$(".row").fadeOut(400, function(){
				$(this).empty();
			})
		},
		success:function(response){
			console.log(response);
			$(".row").fadeIn(250, function(){
				$(this).append(response);
			});
		},
		error:function(xhr){
			console.log("BUSCA DE SITE FALHOU: > ERROR " + xhr.statusText);
			alert("Ops, busca de site falhou - " + xhr.status + " - " + xhr.statusText);
		}

	})
})

// AJAX REVERSO

var totalOfertas = 0;

$(document).ready(function(){
	init();
})

function init(){
	console.log("DWR init...");
	
	dwr.engine.setActiveReverseAjax(true);
	dwr.engine.setErrorHandler(error);
	
	DWRAlertaPromocoes.init();
}

function error(exception){
	console.log("DWR error: ", exception);
}

function showButton(count){
	totalOfertas = totalOfertas + count;
	console.log("Total de novas ofertas: "+totalOfertas)
	$("#btn-alert").show(function(){
		$(this).attr("style", "display:block")
		.text("veja " + totalOfertas + " nova(s) oferta(s)!");
	})
}