$(function(){  
	
	var who = true;
	var create = false;
	
	var divInfo = $('div#info');
	var labelWho = $('#who');
	
	var labelName = $('#name');
	var textName = $('input[name="textName"]');
	
	var autoClient = $("#tagClient");
	var autoStorage = $("#tagStorage");
	
	var labNewSite = $('#labNewSite');
	var labUkrBank = $('#labUkrBank');
	
	var telText = $('#telText');
	var infoText = $('#infoText');
	var newPostText = $('#newPostText');
	var ukrPostText = $('#ukrPostText');
	
	telText.val("");
	infoText.val("");
	newPostText.val("");
	ukrPostText.val("");
	textName.val("");
	autoClient.val("");
	autoStorage.val("");
	
	/*button*/
	 $( "input[type=button]" )
		.button()
		.click(function( event ) {
			event.preventDefault();
	});
	$('input[name="butInfo"]').on('click', function(){
		
		if(divInfo.is(':hidden'))
		{
			divInfo.slideDown();
			$(this).attr('value', 'Скрыть информацию');
		}
		else
		{
			divInfo.slideUp();
			$(this).attr('value', 'Показать информацию');
		}
	});
	
	/*spinner*/
	var spinStorage = $("#spinStorage").spinner();
	spinStorage.spinner({ min: 0 });
	
	var spinClient = $("#spinClient").spinner();
	spinClient.spinner({ min: 0 });
	
	/*  load Clients and Storages  */
	var arrClient = null;
	var arrStorage = null;
	
	function loadClients(){
		$.ajax({
			type: 'GET',
			url: 'getclients',		
			success: function(data){
				var obj = JSON.parse(data);
				arrClient = obj.Clients;
				arrStorage = obj.Storages;
				autoClient.autocomplete({source: arrClient});
				autoStorage.autocomplete({source: arrStorage});
			 },
			error:function( jqXHR, textStatus, errorThrown){
				alert("Ошибка");  
		}});
	}
	$(window).load(function () {
		loadClients();
	});
	
	/*  select Client or Storage */
		
	autoClient.autocomplete({
		select: function( event, ui ) {
			who = true;
			create = false;
			labelWho.text("Заказчик");
			
			labNewSite.text('Адрес новой почты');
			labUkrBank.text('Адрес укр почты');
			labelName.text(ui.item.value);
			$.ajax({
				type: 'POST',
				url: 'getinfoclient',
				data: 'who=' + who + '&name=' + ui.item.value,
				success: function(data){
					var obj = JSON.parse(data);
					textName.val(ui.item.value);
					telText.val(obj.telClient);
					infoText.val(obj.infoClient);
					newPostText.val(obj.newPost);
					ukrPostText.val(obj.ukrPost);
					autoClient.val("");
					autoStorage.val("");
				 },
				error:function( jqXHR, textStatus, errorThrown){
					alert("Ошибка");  
			}});
		}
	});
	autoStorage.autocomplete({
		select: function( event, ui ) {
			who = false;
			create = false;
			labelWho.text("Склад");
			
			labNewSite.text('Сайт');
			labUkrBank.text('Реквизиты банка');
			labelName.text(ui.item.value);
			$.ajax({
				type: 'POST',
				url: 'getinfoclient',
				data: 'who=' + who + '&name=' + ui.item.value,
				success: function(data){
					var obj = JSON.parse(data);
					textName.val(ui.item.value);
					telText.val(obj.telStorage);
					infoText.val(obj.infoStorage);
					newPostText.val(obj.siteStorage);
					ukrPostText.val(obj.bankStorage);
					autoClient.val("");
					autoStorage.val("");
				 },
				error:function( jqXHR, textStatus, errorThrown){
					alert("Ошибка");  
			}});
		}
	});
	
	/* button create client and storage */
	
	$('input[name="butClient"]').on('click', function(){
		divInfo.slideDown();
		who = true;
		create = true;
		labelWho.text("Заказчик");
		textName.val("");
	
		labNewSite.text('Адрес новой почты');
		labUkrBank.text('Адрес укр почты');
		telText.val("");
		infoText.val("");
		newPostText.val("");
		ukrPostText.val("");
	});
	
	$('input[name="butStorage"]').on('click', function(){
		divInfo.slideDown();
		who = false;
		create = true;
		labelWho.text("Склад");
		labNewSite.text('Сайт');
		labUkrBank.text('Реквизиты банка');
		telText.val("");
		infoText.val("");
		newPostText.val("");
		ukrPostText.val("");
		textName.val("");
	});
	
	/* button save */
	
	$('input[name="butClientSave"]').on('click', function(){
				
		var yes = confirm("Сохранить?");
		
		if(yes){
			$.ajax({
				type: 'POST',
				url: 'saveinfoclient',
				data: 'telSave=' + telText.val() + 
					'&infoSave=' + infoText.val() + 
					'&newPostSave=' + newPostText.val() + 
					'&ukrPostSave=' +  ukrPostText.val() +
					'&name=' + textName.val() +
					'&oldname=' + labelName.text() +
					'&who=' + who +
					'&create=' + create,
				success: function(data){
					var obj = JSON.parse(data);
					labelName.text(textName.val());
					
					telText.text(obj.telStorage);
					infoText.text(obj.infoStorage);
					newPostText.text(obj.siteStorage);
					ukrPostText.text(obj.bankStorage);
					loadClients();
					create = false;
				 },
				error:function( jqXHR, textStatus, errorThrown){
					alert("Уже есть");  
			}});
			autoClient.val("");
			autoStorage.val("");
		}
	});
	
	/* button delete */
	
	$('input[name="butClientDelete"]').on('click', function(){	
		var yes = confirm("Удалить?");
		if(yes){
			$.ajax({
				type: 'POST',
				url: 'deleteclient',
				data: '&name=' + labelName.text() + '&who=' + who,
				success: function(data){
					loadClients();
					telText.val("");
					infoText.val("");
					newPostText.val("");
					ukrPostText.val("");
					textName.val("");
					labelName.html("&nbsp;");
				 },
				error:function( jqXHR, textStatus, errorThrown){
					alert("Ошибка");  
			}});
			}		
	});
	
	/* ParamQuery Grid */
var data = [
		['Шапки', 'Шапка кевин кляйн', 'белая', 'а14-с6', 43.5, 64, "12.05.2014", 'маломерки'],
        ['Шапки', 'Шапка кевин кляйн', 'белая', 'а14-с6', 43.5, 64, "12.05.2014", 'маломерки'],
        ['Шапки', 'Шапка кевин кляйн', 'белая', 'а14-с6', 43.5, 64, "12.05.2014", 'маломерки'],
        ['Шапки', 'Шапка кевин кляйн', 'белая', 'а14-с6', 43.5, 64, "12.05.2014", 'маломерки'],
        ['Шапки', 'Шапка кевин кляйн', 'белая', 'а14-с6', 43.5, 64, "12.05.2014", 'маломерки'],
		['Шапки', 'Шапка кевин кляйн', 'белая', 'а14-с6', 43.5, 64, "12.05.2014", 'маломерки'],
		['Шапки', 'Шапка кевин кляйн', 'белая', 'а14-с6', 43.5, 64, "12.05.2014", 'маломерки'],
		['Шапки', 'Шапка кевин кляйн', 'белая', 'а14-с6', 43.5, 64, "12.05.2014", 'маломерки'],
		['Шапки', 'Шапка кевин кляйн', 'белая', 'а14-с6', 43.5, 64, "12.05.2014", 'маломерки'],
		[]];
 
 
    var obj = { width:1000, height:100 };
    obj.colModel = [
	{ title: "Заказчик", width: 200, dataType: "string" },
    { title: "Товар", width: 200, dataType: "string" },
    { title: "Комментарий", width: 120, dataType: "string" },
    { title: "Артикул", width: 100, dataType: "string"},
	{ title: "Цена опт", width: 50, dataType: "float", align: 'right'},
	{ title: "Цена роз", width: 50, dataType: "float", align: 'right'},
	{ title: "Дата", width: 70, dataType: "string"},
	{ title: "Свои заметки", width: 150, dataType: "string"}];
    obj.dataModel = { data: data };
	
    var $grid = $("#grid_array").pqGrid(obj);
	
	$grid.pqGrid("option", "topVisible", false)
		.pqGrid("option", "bottomVisible", false)
		.pqGrid("option", "flexHeight", true)
		 $grid.pqGrid("option", "flexWidth", true)
		 .pqGrid("option", "scrollModel", {horizontal: false});
});


