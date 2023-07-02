<!DOCTYPE html>
<html>
<head>
    <title>Champions</title>
</head>
<body>
    <h1>Champions</h1>
    <ul>
        <% for (Champion champion : champions) { %>
            <li>
                <h2><%= champion.getName() %></h2>
                <p><%= champion.getRole() %></p>
            </li>
        <% } %>
    </ul>
</body>
</html>
