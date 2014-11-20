<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Покупки</title>
<link rel="stylesheet" type="text/css" href="web/style/style.css">
<link rel="stylesheet" type="text/css" href="web/js/jquery-ui/jquery-ui.css">
<script type="text/javascript" src="web/js/json/json2.js"></script>
<script type="text/javascript" src="web/js/jquery-min.js"></script>
<script type="text/javascript" src="web/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="web/js/javascript.js" charset="utf-8"></script>
<!--ParamQuery Grid-->
 <link rel="stylesheet" href="web/js/grid/pqgrid.min.css" />
 <script type="text/javascript" src="web/js/grid/pqgrid.min.js" ></script>
</head>
<body>
	<div id="head">
		<div class="hdCont">
			<div class="hdCont2" align="center">
				<form class="form">
					<input type="button" name="butStorage" value="Создать склад"><br><br>
					<label>Выбрать склад и поставку</label><br>
					<input id="tagStorage" size="30">
					<input id="spinStorage" name="valStorage">
				</form>
			</div>
		</div>
		<div class="hdCont">
			<div class="hdCont2" align="center">
				<p align="center" id="who" style='color:#2e6e9e'>PURCH</p>
				<p align="center" id="name">&nbsp;</p>
				
				<input id="infoButton" type="button" name="butInfo" value="Показать информацию" style='font-size: 17px'>
			</div>
		</div>
		<div class="hdCont">
			<div class="hdCont2" align="center">
				<form class="form">
					<input id='createClient' type="button" name="butClient" value="Создать заказчика"><br><br>
					<label>Выбрать заказчика и заказ</label><br>
					<input id="tagClient" size="30">
					<input id="spinClient" name="valClient">
				</form>
			</div>
		</div>
	</div>
	<div id="info">
		<div id='infoCont'>
			<div class='infoCont2' align='center'><br>
				<label>Телефоны</label><br><br>
				<textarea id='telText'></textarea>
			</div>
			<div class='infoCont2' align='center'><br>
				<label>Другая информация</label><br><br>
				<textarea id='infoText'></textarea>
			</div>
			<div class='infoCont2' align='center'><br>
				<label>Имя</label><br><br>
				<input id='textName' type="text" name="textName" size='27'>
				<input class='butDelSave' type="button" name="butClientSave" value="Сохранить">
				<input type="button" name="butClientDelete" value="Удалить">
			</div>
			<div class='infoCont2' align='center'><br>
				<label id='labNewSite'>Адрес новой почты</label><br><br>
				<textarea id='newPostText'></textarea>
			</div>
			<div class='infoCont2' align='center'><br>
				<label id='labUkrBank'>Адрес укр почты</label><br><br>
				<textarea id='ukrPostText'></textarea>
			</div>
		</div>
	</div>
	<div id="table">
		<p class="pTable" align="center">Список товаров</p>
		<div id="grid_array" style="margin:auto;"></div>
	</div>
	<div id='bottom'><br>
		<div id='botContLeft'>
			<input type="button" name="butActive" value="Активен" style='font-size: 12px'>
			<input type="button" name="butNot" value="Нет в наличии" style='font-size: 12px'>
			<input type="button" name="butDelete" value="Удалить" style='font-size: 12px'>
		</div>
		<div id='botContCenter'>
			<input type="button" name="butItemOrder" value="Товар заказан" style='font-size: 17px'>
		</div>
		<div id='botContRight'>Общая цена  <strong>564</strong> грн</div>
	</div>
</body>
</html>