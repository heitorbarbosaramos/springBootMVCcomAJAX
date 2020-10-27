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
	
	$.ajax({
		method:"GET",
		url:"/promocao/list/ajax",
		data:{page:pageNumber},
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