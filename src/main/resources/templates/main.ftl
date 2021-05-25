<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

  <@c.page>
<div class="container">
<div class="row justify-content-md-center">
<div class="container">
<div class="row align-items-center">

  <div class="row">
    <div class="col-sm">
      <button type="button" class="btn btn-primary btn-lg btn-block" onclick="printJS({ printable: 'printJS-form', type: 'html', header: 'Печать данных с носимых устройств ${dataFrom?ifExists} ${dataTo?ifExists}'})">Распечатать форму</button>
      <p> </p>
    </div>
      <div class="col-sm">
        <form method="get" action="/main" class="form-inline">
          <div class="container">
          <div class="d-flex justify-content-center">
            <div class="row align-items-center">            
              <div class="row row-cols-2">
                <div class="col">
                  <input type="date" id="start" name="dataFrom" value = "${dataFrom?ifExists}">
                </div>
                <div class="col">
                  <input type="date" id="end" name="dataTo" value="${dataTo?ifExists}">
                </div>
              </div>
            </div>  
          </div>
          </div>
          <button type="submit" class="btn btn-primary btn-lg btn-block">Найти</button>
          <div class="container">
            <div class="accordion" id="accordionExample">
              <div class="card">
                <div class="card-header" id="headingOne">
                  <h2 class="mb-0">
                    <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseTop" aria-expanded="false" aria-controls="collapseTop">Выбор источников</button>
                  </h2>
                </div>
                <div id="collapseTop" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                  <div class="card-body">
                  <p>Пульс</p>
                    <#list spisokP as sp>
                      <div class="container">
                        <p>
                          <div class="custom-control custom-switch">        
                            <input type="checkbox" name = "${sp} pulse" class="custom-control-input" id="customSwitch ${sp} pulse" ${spisok2P? seqContains(sp)?string ("checked","")}>
                            <label class="custom-control-label ${sp} pulse" for="customSwitch ${sp} pulse">${sp}</label>       
                          </div>
                        </p> 
                      </div>
                    </#list>
                  </div>
                  <div class="card-body">
                  <p>Шаги</p>
                    <#list spisokS as ss>
                      <div class="container">
                        <p>
                          <div class="custom-control custom-switch">        
                            <input type="checkbox" name = "${ss} step" class="custom-control-input" id="customSwitch ${ss} step" ${spisok2S? seqContains(ss)?string ("checked","")}>
                            <label class="custom-control-label ${ss} step" for="customSwitch ${ss} step">${ss}</label>       
                          </div>
                        </p> 
                      </div>
                    </#list>
                  </div>
                  <div class="card-body">
                  <p>Расстояние</p>
                    <#list spisokD as sd>
                      <div class="container">
                        <p>
                          <div class="custom-control custom-switch">        
                            <input type="checkbox" name = "${sd} distance" class="custom-control-input" id="customSwitch ${sd} distance" ${spisok2D? seqContains(sd)?string ("checked","")}>
                            <label class="custom-control-label ${sd} distance" for="customSwitch ${sd} distance">${sd}</label>       
                          </div>
                        </p> 
                      </div>
                    </#list>
                  </div>
                  <div class="card-body">
                  <p>Вес</p>
                    <#list spisokW as sw>
                      <div class="container">
                        <p>
                          <div class="custom-control custom-switch">        
                            <input type="checkbox" name = "${sw} weight" class="custom-control-input" id="customSwitch ${sw} weight" ${spisok2W? seqContains(sw)?string ("checked","")}>
                            <label class="custom-control-label ${sw} weight" for="customSwitch ${sw} weight">${sw}</label>       
                          </div>
                        </p> 
                      </div>
                    </#list>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </form>
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
                <#if !ZANAYTO>
                  <button type="submit" class="btn btn-primary btn-lg btn-block">Добавить</button>
                </#if>
                <#if ZANAYTO>
                <p>
                Кнопка добавить появится когда очередь освободиться, просто подождите и обновите страничку
                </p>
                </#if>
                </div>
              </form>
            </div>
         </div>
      </div>
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
        <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne" id="printPageButton">
          Данные пульса
        </button>
      </h2>
      
    </div>



    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
      <div class="card-body">
        <button type="button" class="btn btn-outline-primary" onclick="isPulse()">График пульса</button><br>
            <pre class="chroma"><div id="pulse_chart"></div></pre>
            <h3>Таблица значений</h3>
            <div class="table-wrap">
                <table class="table-1">
                <tr class="tableHeader">
                <td>Дата/Время </td>
                <td>Название устройства</td>
                <td>Значение, уд/мин</td>
                </tr>
                  <#list pulses?sortBy("data") as pulse>
                  <#if pulse.authorName==name>
                
                    <#if spisok2P?seqContains(pulse.name)>
                     <tr class="tableBody">
                      <td>${pulse.data}</td>
                      <td>${pulse.name}</td>
                      <td>${pulse.value}</td>
                      </tr>
                      </#if>

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
        <pre class="chroma"><div id="step_chart"></div></pre>
        <h3>Таблица значений</h3>
        <div class="table-wrap">
                <table class="table-1">
                <tr class="tableHeader">
                <td>Дата</td>
                <td>Название устройства</td>
                <td>Значение</td>
                </tr>
                  <#list steps?sortBy("data") as step>
                  <#if step.authorName==name>                     
                   <#if spisok2S?seqContains(step.name)>
                    <tr class="tableBody">
                      <td>${step.data}</td>
                      <td>${step.name}</td>
                      <td>${step.value}</td>
                      </tr>
                      </#if>                
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
        <pre class="chroma"><div id="distance_chart"></div></pre>
        <h3>Таблица значений</h3>
        <div class="table-wrap">
                <table class="table-1">
                <tr class="tableHeader">
                <td>Дата</td>
                <td>Название устройства</td>
                <td>Значение, км</td>
                </tr>
                  <#list distances?sortBy("data") as distance>
                  <#if distance.authorName==name>                     
                      <#if spisok2D?seqContains(distance.name)>
                       <tr class="tableBody">
                      <td>${distance.data}</td>
                      <td>${distance.name}</td>
                      <td>${distance.value}</td>
                      </tr>
                      </#if>                
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
        <pre class="chroma"><div id="weight_chart"></div></pre>
        <h3>Таблица значений</h3>
        <div class="table-wrap">
                <table class="table-1">
                <tr class="tableHeader">
                <td>Дата/Время</td>
                <td>Название устройства</td>
                <td>Значение, кг</td>
                </tr>
                  <#list weights?sortBy("data") as weight>
                  <#if weight.authorName==name>                      
                      <#if spisok2W?seqContains(weight.name)>
                      <tr class="tableBody">
                      <td>${weight.data}</td>
                      <td>${weight.name}</td>
                      <td>${weight.value}</td>
                      </tr>
                      </#if>                
                  </#if>
                  </#list>
      </table>
     </div>
      </div>
    </div>
  </div>
</div>
</form>

<script type="text/javascript">function isPulse(){google.charts.load('current', {'packages':['line']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата, Устройство','Пульс, уд/мин'],<#list pulses?sortBy("data") as pulse><#if pulse.authorName==name><#if spisok2P?seqContains(pulse.name)>['${pulse.data},${pulse.name}',${pulse.value},],</#if></#if></#list>]);var options={curveType:'function',legend:{position:'bottom'}, width: 1000, height: 500};var chart=new google.charts.Line(document.getElementById('pulse_chart'));chart.draw(data, google.charts.Line.convertOptions(options))}}</script>
<script type="text/javascript">function isStep(){google.charts.load('current', {'packages':['line']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата, Устройство','Шаги'],<#list steps?sortBy("data") as step><#if step.authorName==name><#if spisok2S?seqContains(step.name)>['${step.data}, ${step.name}',${step.value?string.computer}],</#if></#if></#list>]);var options={curveType:'function',legend:{position:'bottom'}, width: 1000, height: 500};var chart=new google.charts.Line(document.getElementById('step_chart'));chart.draw(data,google.charts.Line.convertOptions(options))}}</script>
<script type="text/javascript">function isDistance(){google.charts.load('current', {'packages':['line']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата, Устройство','Дистанция, км'],<#list distances?sortBy("data") as distance><#if distance.authorName==name><#if spisok2D?seqContains(distance.name)>['${distance.data}, ${distance.name}',${distance.value?string.computer}],</#if></#if></#list>]);var options={curveType:'function',legend:{position:'bottom'}, width: 1000, height: 500};var chart=new google.charts.Line(document.getElementById('distance_chart'));chart.draw(data,google.charts.Line.convertOptions(options))}}</script>
<script type="text/javascript">function isWeight(){google.charts.load('current', {'packages':['line']});google.charts.setOnLoadCallback(drawChart);function drawChart(){var data=google.visualization.arrayToDataTable([['Дата, Устройство','Вес, кг'],<#list weights?sortBy("data") as weight><#if weight.authorName==name><#if spisok2W?seqContains(weight.name)>['${weight.data}, ${weight.name}',${weight.value}],</#if></#if></#list>]);var options={curveType:'function',legend:{position:'bottom'}, width: 1000, height: 500};var chart=new google.charts.Line(document.getElementById('weight_chart'));chart.draw(data,google.charts.Line.convertOptions(options))}}</script>
</@c.page>
