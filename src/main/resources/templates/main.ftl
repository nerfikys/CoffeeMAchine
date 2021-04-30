<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

  <@c.page>
<div class="container">
<div class="row justify-content-md-center">
<div class="row align-items-center">

  <div class="row">
    <div class="col-sm">
      <button type="button" class="btn btn-primary btn-lg btn-block" onclick="printJS({ printable: 'printJS-form', type: 'html', header: 'Печать данных с носимых устройств ${dataFrom?ifExists} ${dataTo?ifExists}'})">Распечатать форму</button>
      <p> </p>
    </div>
    <div class="col-sm">
        <form method="get" action="/main" class="form-inline">
        <center>
            <input type="date" id="start" name="dataFrom" value = "${dataFrom?ifExists}">
            <input type="date" id="end" name="dataTo" value="${dataTo?ifExists}">
        </center>
          <button type="submit" class="btn btn-primary btn-lg btn-block">Найти</button>
        </form>
        <p> </p>
    </div>
    
    <div class="col-sm">
      <a class="btn btn-primary btn-lg btn-block" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">Отправить Файл</a>
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
                  <button type="submit" class="btn btn-primary btn-lg btn-block">Добавить</button>
                </div>
              </form>
            </div>
         </div>
         <p> </p>
      </div>
      
   </div>
</div>
</div>
</div>

<form method="post" action="#" id="printJS-form">
<div class="accordion" id="accordionExample">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h2 class="mb-0">
        <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
          Данные пульса
        </button>
      </h2>
    </div>

    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
      <div class="card-body">

        <button type="button" class="btn btn-outline-primary" onclick="isPulse()">График пульса</button><br>
            <div id="pulse_chart"></div>
            <h3>Таблица значений</h3>
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
      </table>
     </div>

      </div>
    </div>
  </div>

  <div class="card">
    <div class="card-header" id="headingTwo">
      <h2 class="mb-0">
        <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          Данные шагов
        </button>
      </h2>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
      <div class="card-body">
        
        <button type="button" class="btn btn-outline-primary" onclick="isStep()">График шагов</button>
        <div id="step_chart"></div>
        <h3>Таблица значений</h3>
        <div class="table-wrap">
                <table class="table-1">
                <tr class="tableHeader">
                <td>Дата</td>
                <td>Название</td>
                <td>Значение</td>
                </tr>
                  <#list steps as step>
                  <#if step.authorName==name>
                      <tr class="tableBody">
                      <td>${step.data}</td>
                      <td>${step.name}</td>
                      <td>${step.value}</td>
                </tr>
                  </#if>
                  </#list>
      </table>
     </div>

      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingThree">
      <h2 class="mb-0">
        <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          Данные дистанции
        </button>
      </h2>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
      <div class="card-body">
      
        <button type="button" class="btn btn-outline-primary" onclick="isDistance()">График дистанции</button>
        <div id="distance_chart"></div>
        <h3>Таблица значений</h3>
        <div class="table-wrap">
                <table class="table-1">
                <tr class="tableHeader">
                <td>Дата</td>
                <td>Название</td>
                <td>Значение</td>
                </tr>
                  <#list distances as distance>
                  <#if distance.authorName==name>
                      <tr class="tableBody">
                      <td>${distance.data}</td>
                      <td>${distance.name}</td>
                      <td>${distance.value}</td>
                </tr>
                  </#if>
                  </#list>
      </table>
     </div>

      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingFor">
      <h2 class="mb-0">
        <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseFor" aria-expanded="false" aria-controls="collapseFor">
          Данные веса
        </button>
      </h2>
    </div>
    <div id="collapseFor" class="collapse" aria-labelledby="headingFor" data-parent="#accordionExample">
      <div class="card-body">
        
        <button type="button" class="btn btn-outline-primary" onclick="isWeight()">График веса</button>
        <div id="weight_chart"></div>
        <h3>Таблица значений</h3>
        <div class="table-wrap">
                <table class="table-1">
                <tr class="tableHeader">
                <td>Дата</td>
                <td>Название</td>
                <td>Значение</td>
                </tr>
                  <#list weights as weight>
                  <#if weight.authorName==name>
                      <tr class="tableBody">
                      <td>${weight.data}</td>
                      <td>${weight.name}</td>
                      <td>${weight.value}</td>
                </tr>
                  </#if>
                  </#list>
      </table>
     </div>


      </div>
    </div>
  </div>
</div>
</form>

<script type="text/javascript">function isPulse(){google.charts.load('current', {'packages':['line']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата','Пульс'],<#list pulses as pulse><#if pulse.authorName==name>['${pulse.data}',${pulse.value}],</#if></#list>]);var options={curveType:'function',legend:{position:'bottom'}};var chart=new google.charts.Line(document.getElementById('pulse_chart'));chart.draw(data, google.charts.Line.convertOptions(options))}}</script>
<script type="text/javascript">function isStep(){google.charts.load('current', {'packages':['line']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата','Шаги'],<#list steps as step><#if step.authorName==name>['${step.data}',${step.value?string.computer}],</#if></#list>]);var options={curveType:'function',legend:{position:'bottom'}};var chart=new google.charts.Line(document.getElementById('step_chart'));chart.draw(data,google.charts.Line.convertOptions(options))}}</script>
<script type="text/javascript">function isDistance(){google.charts.load('current', {'packages':['line']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата','Дистанция'],<#list distances as distance><#if distance.authorName==name>['${distance.data}',${distance.value?string.computer}],</#if></#list>]);var options={curveType:'function',legend:{position:'bottom'}};var chart=new google.charts.Line(document.getElementById('distance_chart'));chart.draw(data,google.charts.Line.convertOptions(options))}}</script>
<script type="text/javascript">function isWeight(){google.charts.load('current', {'packages':['line']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата','Вес'],<#list weights as weight><#if weight.authorName==name>['${weight.data}',${weight.value}],</#if></#list>]);var options={curveType:'function',legend:{position:'bottom'}};var chart=new google.charts.Line(document.getElementById('weight_chart'));chart.draw(data,google.charts.Line.convertOptions(options))}}</script>
</@c.page>
