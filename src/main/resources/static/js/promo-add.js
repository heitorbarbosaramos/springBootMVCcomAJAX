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
				console.log(data);
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