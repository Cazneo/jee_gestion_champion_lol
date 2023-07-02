package com.gestionnaire.champion_lol.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.gestionnaire.champion_lol.model.LoginRequest;
import com.gestionnaire.champion_lol.services.AuthenticationService;

import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private AuthenticationService authenticationService = new AuthenticationService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Créez un objet LoginRequest avec les valeurs fournies
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Utilisez l'objet LoginRequest pour appeler la méthode d'authentification
        String token = authenticationService.authenticate(loginRequest);

        if (token != null) {
            // Stockez le JWT dans la session HTTP
            request.getSession().setAttribute("jwt", token);
            response.sendRedirect("/champion_lol/champions.jsp");
        } else {
            response.sendRedirect("/champion_lol/login.jsp?error=true");
        }
    }
}
