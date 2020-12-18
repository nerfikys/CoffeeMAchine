<#macro login path isRegisterForm>
<form action="${path}" method="post">

<div class="container">
  <div class="row justify-content-md-center">
    <div class="col col-lg-2">
    </div>
    <div class="col-md-auto">
      <input type="text" name="username" class="form-control" placeholder="Логин" required=""/>
    </div>
    <div class="col col-lg-2">
    </div>
  </div>
<br>
  <div class="container">
    <div class="row justify-content-md-center">
      <div class="col col-lg-2">
      </div>
      <div class="col-md-auto">
        <input type="password" name="password" class="form-control" placeholder="Пароль" required=""/>
      </div>
      <div class="col col-lg-2">
      </div>
    </div>
<br>
<div class="row justify-content-md-center">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Войти</#if></button>
</div>
<div class="row justify-content-md-center">
     <#if !isRegisterForm><a href="/registration">Зарегистрироваться</a></#if>
</div>

</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Выход</button>
</form>
</#macro>
