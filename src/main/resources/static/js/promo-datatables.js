$(document).ready(function(){
	moment.locale("pt-br");
	var table = $("#table-server").DataTable({
		processing: true,
		serverSide: true,
		responsive: true,
		lengthMenu: [5, 10, 15, 20, 25],
		
		ajax:{
			url:"/promocao/datatables/server",
			data: "data"
		},
		columns: [
			{data: 'id'},
			{data: 'titulo'},
			{data: 'site'},
			{data: 'linkPromocao'},
			{data: 'descricao'},
			{data: 'linkImagem'},
			{data: 'preco', render: $.fn.dataTable.render.number(',', '.', 2, 'R$ ')},
			{data: 'likes'},
			{data: 'dataCadastro', render:
				function(dataCadastro){
					return moment(dataCadastro).format('LLL'); 
				}
			},
			{data: 'categoria.titulo'}
		],
		dom: 'Bfrtip',
		buttons: [
			{text : 'Editar', attr: {
									id: 'btn-editar', type:'button'
								},
					enabled: false
			},
			{text : 'Excluir', attr: {
									id: 'btn-excluir', type:'button'
								},
					enabled: false
			},
		]
	});
	
	$("#table-server tbody").on('click', 'tr', function(){
		if($(this).hasClass('selected')){
			$(this).removeClass('selected');
			table.buttons().disable();
		} else {
			$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
			table.buttons().enable();
		}
	});
	
	$("#table-server thead").on('click', 'tr', function(){
			table.buttons().disable();
		});
	
	$("#btn-editar").on('click', function(){
				
		if( isSelectedRow() ){
			var id = getPromoId();
			$.ajax({
				method:"GET",
				url:"/promocao/edit/" + id,
				beforeSend:function(){
					$("span").closest('.error-span').remove();				
					$(".is-invalid").removeClass("is-invalid");
					$("#modal-form").modal('show');
				},
				success:function(data){
					console.log(">data: ",data);
					$("#edt_id").val(data.id);
					$("#edt_site").text(data.site);
					$("#edt_titulo").val(data.titulo);
					$("#edt_descricao").val(data.descricao);
					$("#edt_preco").val(data.preco.toLocaleString('pt-br',{
						minimumFractionDigits: 2,
						maximumFractionDigits: 2
					}));
					$("#edt_categoria").val(data.categoria.id);
					$("#edt_linkImagem").val(data.linkImagem);
					$("#edt_imagem").attr('src',data.linkImagem);
				},
				error:function(xhr){
					console.log("ops..., algum erro ocorreu, tente novamente");
				}
			});
			
			console.log("Batão Editar clicado id: " + id);
		} else {
			console.log('> nenhuma linha selecionada');
		}
		
	});
	
	$("#btn-edit-modal").on('click', function(){
		var promo = {};
		promo.descricao = $("#edt_descricao").val();
		promo.preco = $("#edt_preco").val();
		promo.titulo = $("#edt_titulo").val();
		promo.id = $("#edt_id").val();
		promo.categoria = $("#edt_categoria").val();
		promo.linkImagem = $("#edt_linkImagem").val();
		
		$.ajax({
			method:"GET",
			url: "/promocao/edit",
			data: promo,
			beforeSend: function(){				
				$("span").closest('.error-span').remove();				
				$(".is-invalid").removeClass("is-invalid");
			},
			success:function(){
				$("#modal-form").modal('hide');
				table.ajax.reload();
			},
			statusCode:{
				422:function(xhr){
					console.log('status errors: ' ,xhr.status);
					var error = $.parseJSON(xhr.responseText);
					$.each(error, function(key, value){
						$("#edt_" + key).addClass("is-invalid");
						$("#error-" + key).addClass("invalid-feedback").append("<span class='error-span'>" + value + "</span>");
					});
					
				}
			}
		})
		
	});
	
	$("#edt_linkImagem").on('change', function(){
		var link = $(this).val();
		$("#edt_imagem").attr('src',link);
	})
	
	$("#btn-excluir").on('click', function(){
		if( isSelectedRow() ){
			var id = getPromoId();
			$("#modal-delete").modal('show');
			console.log("Batão Excluir clicado id: " + id);
		} else {
			console.log('> nenhuma linha selecionada');
		}
	});
	
	function getPromoId(){
		return table.row(table.$('tr.selected')).data().id;
	};
	
	function isSelectedRow(){
		var tRow =  table.row(table.$('tr.selected')).data() !== undefined;
		return tRow;
	}
	
	$("#btn-del-modal").on('click', function(){
		var id =  getPromoId();
		$.ajax({
			method:"GET",
			url:"/promocao/delete/" + id,
			success:function(){
				$("#modal-delete").modal('hide');
				table.ajax.reload();
				console.log(">exclusao realizada id: "+id)
			},
			error:function(){
				alert('Ops, ocorreu um erro tente mais tarde');
			}
		});
	})
})