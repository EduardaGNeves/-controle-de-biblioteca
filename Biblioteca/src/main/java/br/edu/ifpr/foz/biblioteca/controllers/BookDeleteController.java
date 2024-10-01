package br.edu.ifpr.foz.biblioteca.controllers;

import br.edu.ifpr.foz.biblioteca.repositories.BookRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/books/delete")
public class BookDeleteController extends HttpServlet {

    BookRepository repository = new BookRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));

        repository.delete(id);

        resp.sendRedirect("http://localhost:8080/biblioteca/books");
    }

}
