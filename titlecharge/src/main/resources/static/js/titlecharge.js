$('#deleteConfirmation').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget);
	var codeTitle = button.data('code');
	var descriptionTitle = button.data('description');
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action+='/';
	}
	form.attr('action', action + codeTitle);

	modal.find('.modal-body span').html('Are you sure that you want to delete the title of <strong>'+descriptionTitle+'</strong>?');
});

$(function(){
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: false});
	
	$('.js-update-status').on('click',function(event){
		event.preventDefault();

		var buttonRecieve = $(event.currentTarget);
		var urlRecieve = buttonRecieve.attr('href');
		
		var response = $.ajax({
			url:urlRecieve,
			type: 'PUT'
		});
		
		response.done(function(e){
			var code = buttonRecieve.data('code');
			$('[data-role='+code+']').html('<span class="label label-success">'+e+'</span>');
			buttonRecieve.hide();
		});
		
		response.fail(function(e){
			console.log('fail');
			alert('Error recieving charge!');
		});
	});
});
