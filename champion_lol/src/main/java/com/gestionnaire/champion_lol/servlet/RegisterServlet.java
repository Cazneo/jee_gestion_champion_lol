package com.gestionnaire.champion_lol.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.gestionnaire.champion_lol.dao.UserDao;
import com.gestionnaire.champion_lol.model.User;

import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserDao userDao;
    private EntityManager entityManager;

    @Override
    public void init() throws ServletException {
        super.init();
        entityManager = Persistence.createEntityManagerFactory("my-persistence-unit").createEntityManager();
        userDao = new UserDao(entityManager);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Vérifiez si l'utilisateur existe déjà dans la base de données
        User existingUser = userDao.getUser(username);
        if (existingUser != null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write("L'utilisateur existe déjà");
            return;
        }

        // Créez un nouvel utilisateur avec les informations fournies
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // Enregistrez le nouvel utilisateur dans la base de données
        userDao.createUser(user);

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write("Compte créé avec succès");
    }
}
