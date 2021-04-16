<#macro login path isRegisterForm>
<form class="form-signin" action="${path}" method="post">
<div class="container">
    <div class="row justify-content-md-center">
      <div class="col-sm">
      </div>
      <div class="col-sm">
        <div class="form-group">
          <input type="text" name="username" class="form-control" placeholder="Логин" required=""/>
        </div>
        <div class="form-group">
          <input type="password" name="password" class="form-control" placeholder="Пароль" required=""/>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary btn-lg btn-block" type="submit"><#if isRegisterForm>Регистрация<#else>Войти</#if></button>
          <div class="row justify-content-md-center">
              <div class="mx-auto">
                <#if !isRegisterForm><a href="/registration">Зарегистрироваться</a></#if>
              </div>
          </div>
        </div>
      <div class="col-sm">
      </div>
    </div>
</div>
</form>
</#macro>
<#macro logout>
<form action="/logout" method="post">
<input type="hidden" name="_csrf" value="${_csrf.token}" />
<button class="btn btn-primary btn-lg btn-block" type="submit">Выход</button>
</form>
</#macro>
