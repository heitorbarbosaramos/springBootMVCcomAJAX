$("#form-add-promo").submit(function(evt){
	evt.preventDefault();
	var promo = {};
	promo.linkPromocao = $("#linkPromocao").val();
	promo.descricao = $("#descricao").val();
	promo.preco = $("#preco").val();
	promo.titulo = $("#titulo").val();
	promo.site = $("#site").text();
	promo.categoria = $("#categoria").val();
	promo.linkImagem = $("#linkImagem").attr("src");
	
	console.log('promo>',promo);
	
	$.ajax({
		method:"POST",
		url:"/promocao/save",
		data:promo,
		beforeSend: function(){
			$("#form-add-promo").hide();
			$("#loader-form").addClass("loader").show();
			$("#alert").removeClass("alert alert-danger");
		   
			$("span").closest('.error-span').remove();
			
			$("#categoria").removeClass("is-invalid");
			$("#preco").removeClass("is-invalid");
			$("#linkPromocao").removeClass("is-invalid");
			$("#titulo").removeClass("is-invalid");
		},
		success:function(response){
			$("#form-add-promo").each(function(){
				this.reset();
			});
			$("#linkImagem").attr("src","/images/promo-dark.png");
			$("#site").text("");
			
			$("#alert").addClass("alert alert-success").text("Promoção cadastrada com sucesso.");
		},
		statusCode:{
			422:function(xhr){
				console.log('status errors: ' ,xhr.status);
				var error = $.parseJSON(xhr.responseText);
				$.each(error, function(key, value){
					$("#" + key).addClass("is-invalid");
					$("#error-" + key).addClass("invalid-feedback").append("<span class='error-span'>" + value + "</span>");
				});
				
			}
		},
		error:function(xhr){
			console.log("> error: ", xhr.responseText);
			$("#alert").addClass("alert alert-danger").text("Promoção não foi cadastrada com sucesso.");
		},
		complete:function(){
			$("#loader-form").fadeOut(800, function(){
				$("#form-add-promo").fadeIn(250);
				$("#loader-form").removeClass("loader")
			})
		}
	})
})

$("#linkPromocao").on('change', function(){
	var url = $(this).val();
	if(url.length > 7){
		$.ajax({
			method:"POST",
			url:"/meta/info?url=" + url,
			cahce:false,
			beforeSend: function(){
				
				$("#alert").removeClass("alert alert-danger alert-success").text("");
				$("#titulo").removeClass("alert alert-danger").val("");
				$("#site").removeClass("alert alert-danger").text("");
				$("#linkImagem").attr("src", "");
				$("#loader-img").removeClass("loadError");
				$("#loader-img").addClass("loader");
				
			},
			success:function(data){
				console.log('linkPromocao> ',data);
				$("#titulo").val(data.title);
				$("#site").text(data.site.replace("@",""));
				$("#linkImagem").attr("src",data.imagem);
				
				$("#loader-img").removeClass("loader");
			},
			statusCode:{
				404:function(){
					$("#alert").addClass("alert alert-danger").text("Nenhuma informação foi encontrada");
					$("#linkImagem").attr("src", "/images/promo-dark.png");
					$("#linkPromocao").val('');
					
					$("#loader-img").removeClass("loader");
					$('#form-add-promo').each(function () {
					      this.reset();
					});
				}
			},
			error: function(){					
				$("#alert").addClass("alert alert-danger").text("ops ..., pagina URL não encontrada");
				$("#linkImagem").attr("src", "/images/promo-dark.png");
				$("#linkPromocao").val('');
				
				$("#loader-img").removeClass("loader");
				$('#form-add-promo').each(function () {
				      this.reset();
				});
			}
		})
	}
})