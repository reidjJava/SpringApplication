<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">Имя пользователя:</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control"/>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group">
                <label for="exampleInputEmail1">Почта:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Место жительства:</label>
                <div class="col-sm-6">
                    <input type="text" name="location" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Вакансия:</label>
                <div class="col-sm-6">
                    <input type="text" name="vacancy" class="form-control"/>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration">Зарегистрироваться</a></#if>
        <button type="submit"
                class="btn btn-primary ml-2"><#if isRegisterForm>Зарегистрироваться<#else>Войти</#if></button>
    </form>
</#macro>
<#macro logout>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Выйти</button>
        </form>
    </div>
</#macro>