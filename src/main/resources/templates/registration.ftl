<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="row justify-content-md-center">Добавить нового пользователя</div>
<br>
${message?ifExists}
<@l.login "/registration" true />
</@c.page>
