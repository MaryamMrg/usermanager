package com.example.usermannager.controller;

public class CreateServlet {

private UserDAO userDAO ;
public void init() {
    userDAO = new UserDAO();
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("CreateUserForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String post = request.getParameter("post");
        double salaire = Double.parseDouble(request.getParameter("salaire"));
        User user = new User(name, email, phone, post, salaire);
        userDAO.insertUser(user);
        response.sendRedirect("read");
    }

}
