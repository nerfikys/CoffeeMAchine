<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>

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
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Прикрепить файл</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		 <script type="text/javascript">
							google.charts.load('current', {'packages':['corechart']});
							google.charts.setOnLoadCallback(drawChart);
							function drawChart() {
								var data = google.visualization.arrayToDataTable([
									['Дата', 'Пульс'],
									<#list pulses as pulse>
											 <#if pulse.authorName==name>
															['${pulse.data}',  ${pulse.value}],
											 </#if>
											</#list>
								]);
								var options = {
									title: 'Пульс',
									curveType: 'function',
									legend: { position: 'bottom' }
								};
								var chart = new google.visualization.LineChart(document.getElementById('pulse_chart'));
								chart.draw(data, options);
							}
						</script>

		 <script type="text/javascript">
							google.charts.load('current', {'packages':['corechart']});
							google.charts.setOnLoadCallback(drawChart);
							function drawChart() {
								var data = google.visualization.arrayToDataTable([
									['Дата', 'Шаги'],
									<#list steps as step>
											<#if step.authorName==name>
											['${step.data}',  ${step.value?string.computer}],
											</#if>
									</#list>]);
								var options = {
									title: 'Шаги',
									curveType: 'function',
									legend: { position: 'bottom' }
								};
								var chart = new google.visualization.LineChart(document.getElementById('step_chart'));
								chart.draw(data, options);
							}
		</script>

		 <script type="text/javascript">
							google.charts.load('current', {'packages':['corechart']});
							google.charts.setOnLoadCallback(drawChart);
							function drawChart() {
								var data = google.visualization.arrayToDataTable([
									['Дата', 'Дистанция'],
									<#list distances as distance>
											<#if distance.authorName==name>
											['${distance.data}',  ${distance.value?string.computer}],
											</#if>
									</#list>]);
								var options = {
									title: 'Дистанция',
									curveType: 'function',
									legend: { position: 'bottom' }
								};
								var chart = new google.visualization.LineChart(document.getElementById('distance_chart'));
								chart.draw(data, options);
							}
		</script>

		<script type="text/javascript">
						 google.charts.load('current', {'packages':['corechart']});
						 google.charts.setOnLoadCallback(drawChart);
						 function drawChart() {
							 var data = google.visualization.arrayToDataTable([
								 ['Дата', 'Вес'],
								 <#list weights as weight>
											<#if weight.authorName==name>
														 ['${weight.data}',  ${weight.value}],
											</#if>
										 </#list>
							 ]);
							 var options = {
								 title: 'Вес',
								 curveType: 'function',
								 legend: { position: 'bottom' }
							 };
							 var chart = new google.visualization.LineChart(document.getElementById('weight_chart'));
							 chart.draw(data, options);
						 }
					 </script>


<div class="table-wrap">
<table class="table-1">
<div id="pulse_chart"></div>
</table>
</div>

<div class="table-wrap">
<table class="table-1">
<div id="step_chart"></div>
</table>
</div>

<div class="table-wrap">
<table class="table-1">
<div id="distance_chart" ></div>
</table>
</div>

<div class="table-wrap">
<table class="table-1">
<div id="weight_chart" ></div>
</table>
</div>

<!--
<div class="table-wrap">
<table class="table-1">
    <tr class="tableHeader">
        <td>Дата</td>
        <td>Название</td>
        <td>Значение</td>
    </tr>
    <#list pulses as pulse>
     <#if pulse.authorName==name>
        <tr class="tableBody">
            <td>${pulse.data}</td>
            <td>${pulse.name}</td>
            <td>${pulse.value}</td>
        </tr>
     </#if>
    </#list>
    <#list steps as step>
    <#if step.authorName==name>
        <tr class="tableBody">
            <td>${step.data}</td>
            <td>${step.name}</td>
            <td>${step.value?string.computer}</td>
        </tr>
    </#if>
    </#list>
</table>
</div>
-->
</@c.page>
