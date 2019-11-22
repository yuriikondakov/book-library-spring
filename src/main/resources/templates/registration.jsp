<%@ include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6 mt-5">
            <form action="front-controller" method="post">
                <input type="hidden" name="command" value="register"/>
                <div class="form-group">
                    <label for="InputName"><fmt:message key="UserName"/></label>
                    <input type="text" class="form-control" name="name" value="${name}"
                           id="InputName" placeholder="<fmt:message key="UserName"/>" required autofocus>
                </div>
                <div class="form-group">
                    <label for="InputEmail1">Email address</label>
                    <input type="email" class="form-control" name="email" id="InputEmail1"
                           aria-describedby="emailHelp1" placeholder="Enter email" value="${email}" required>
                    <small id="emailHelp1" class="form-text text-muted">We'll never share your email with anyone
                        else.</small>
                </div>
                <div class="form-group">
                    <label for="InputPassword1"><fmt:message key="Password"/></label>
                    <input type="password" class="form-control" name="password1"
                           id="InputPassword1" placeholder="<fmt:message key="Password"/>" required>
                    <small id="emailHelp" class="form-text text-muted"><fmt:message key="Atleast5symbols"/></small>
                </div>
                <div class="form-group">
                    <label for="RepeatPassword"><fmt:message key="RepeatPassword"/></label>
                    <input type="password" class="form-control" name="password2"
                           id="RepeatPassword" placeholder="<fmt:message key="Password"/>" required>
                </div>
                <c:if test="${not empty registrationWarningMessage}">
                    <div class="alert alert-danger" role="alert">
                            ${registrationWarningMessage}
                    </div>
                </c:if>
                <button type="submit" class="btn btn-primary"><fmt:message key="Signup"/></button>
            </form>
        </div>
        <div class="col-3"></div>
    </div>
</div>
<%@ include file="footer.jsp" %>

