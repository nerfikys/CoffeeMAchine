<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<div class="container">
  <div class="form-row">
      <div class="form-group col-md-6">
      <form method="get" action="/main" class="form-inline">
      <input type="date" id="start" name="data-start">
      <input type="date" id="start" name="data-finish">
      <button type="submit" class="btn btn-primary ml-2">Найти</button>
      </form>
    </div>
  </div>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="name" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Найти по имени">
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>
</div>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Отправить Файл
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">

                <div class="mb-3">
                  <input class="form-control" type="file" id="formFile" name="file">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>
 <div class="table-wrap">
<table class="table-1">
  <tr class="tableBody">
    <td>
      <button type="button" class="btn btn-outline-primary" onclick="isPulse()">График пульса</button><br>
      <div id="pulse_chart"></div>
    </td>
  <tr class="tableBody">
  <tr class="tableBody">
    <td>
    <button type="button" class="btn btn-outline-primary" onclick="isStep()">График шагов</button>
      <div id="step_chart"></div>
    </td>
  <tr class="tableBody">
  <tr class="tableBody">
    <td>
      <button type="button" class="btn btn-outline-primary" onclick="isDistance()">График дистанции</button>
        <div id="distance_chart"></div>
    </td>
  <tr class="tableBody">
  <tr class="tableBody">
    <td>
        <button type="button" class="btn btn-outline-primary" onclick="isWeight()">График веса</button>
        <div id="weight_chart"></div>
    </td>
  <tr class="tableBody">
</table>
</div>
</div>
<script type="text/javascript">function isPulse(){google.charts.load('current',{'packages':['corechart']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата','Пульс'],<#list pulses as pulse><#if pulse.authorName==name>['${pulse.data}',${pulse.value}],</#if></#list>]);var options={title:'Пульс',curveType:'function',legend:{position:'bottom'}};var chart=new google.visualization.LineChart(document.getElementById('pulse_chart'));chart.draw(data,options)}}</script>
<script type="text/javascript">function isStep(){google.charts.load('current',{'packages':['corechart']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата','Шаги'],<#list steps as step><#if step.authorName==name>['${step.data}',${step.value?string.computer}],</#if></#list>]);var options={title:'Шаги',curveType:'function',legend:{position:'bottom'}};var chart=new google.visualization.LineChart(document.getElementById('step_chart'));chart.draw(data,options)}}</script>
<script type="text/javascript">function isDistance(){google.charts.load('current',{'packages':['corechart']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата','Дистанция'],<#list distances as distance><#if distance.authorName==name>['${distance.data}',${distance.value?string.computer}],</#if></#list>]);var options={title:'Дистанция',curveType:'function',legend:{position:'bottom'}};var chart=new google.visualization.LineChart(document.getElementById('distance_chart'));chart.draw(data,options)}}</script>
<script type="text/javascript">function isWeight(){google.charts.load('current',{'packages':['corechart']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата','Вес'],<#list weights as weight><#if weight.authorName==name>['${weight.data}',${weight.value}],</#if></#list>]);var options={title:'Вес',curveType:'function',legend:{position:'bottom'}};var chart=new google.visualization.LineChart(document.getElementById('weight_chart'));chart.draw(data,options)}}</script>
</@c.page>
