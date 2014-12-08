<%@include file="header.jsp"%>
<div class="main_sign_in">
    <form method="post" action="sign_in">
        <h2>Sign In</h2>
        <div>
            <label>
                <% if(session.getAttribute("si_error")!=null)
                      out.print(session.getAttribute("si_error"));
                %>
            </label>
        </div>
        <div>
            <input type="text" size="30" name="si_email" placeholder="email"
                   value="<% if(session.getAttribute("si_email")!=null)
                           out.print(session.getAttribute("si_email"));
                       %>">

        </div>

        <div>
            <input type="password" size="30" name="si_password" placeholder="password"
                   value="<% if(session.getAttribute("si_password")!=null)
                           out.print(session.getAttribute("si_password"));
                       %>">

        </div>
        <input type="hidden" value="sign_in" name="request">
        <div><input type="submit" size="30" value="Sign in"></div>
    </form>

</div>
<%@include file="footer.jsp"%>