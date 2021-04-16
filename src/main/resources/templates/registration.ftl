<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="container">
  <div class="row">
    <div class="col-sm"></div>
    <div class="col-sm"><center>Добавить нового пользователя</center></div>
    <div class="col-sm"></div>
  </div>
  <div class="row">
    <div class="col-sm"></div>
    <div class="col-sm"><center>${message?ifExists}</center></div>
    <div class="col-sm"></div>
  </div>
</div>
<@l.login "/registration" true />
</@c.page>
