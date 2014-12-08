<%@include file="header.jsp"%>

<div class="main_sign_up">
    <form method="post" action="sign_up">
    <h2>Sign Up</h2>
    <div><label class="prefix">Name: </label>
        <span class="field">
            <input type="text" size="30" name="su_name"
                value="<% if(session.getAttribute("su_name")!=null)
                           out.print(session.getAttribute("su_name"));
                       %>"><br>
            <label class="suffix">
                <% if(session.getAttribute("n_error")!=null)
                    out.print(session.getAttribute("n_error"));
                %>
            </label>
        </span>

    </div>
    <div><label class="prefix">Email: </label>
        <span class="field">
            <input type="text" size="30" name="su_email"
                   value="<% if(session.getAttribute("su_email")!=null)
                           out.print(session.getAttribute("su_email"));
                       %>"><br>
            <label class="suffix">
                <% if(session.getAttribute("e_error")!=null)
                    out.print(session.getAttribute("e_error"));
                %>
            </label>
        </span>
    </div>
    <div><label class="prefix">Password: </label>
        <span class="field">
            <input type="password" size="30" name="su_password"
                   value="<% if(session.getAttribute("su_password")!=null)
                           out.print(session.getAttribute("su_password"));
                       %>"><br>
            <label class="suffix">
                <% if(session.getAttribute("p_error")!=null)
                    out.print(session.getAttribute("p_error"));
                %>
            </label>
        </span>
    </div>
        <input type="hidden" value="sign_up" name="request">
    <div><label class="prefix"></label><input type="submit" size="30" value="Add User"></div>
    </form>
</div>

<%@include file="footer.jsp"%>