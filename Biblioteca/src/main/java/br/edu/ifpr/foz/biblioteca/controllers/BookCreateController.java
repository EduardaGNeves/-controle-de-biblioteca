package br.edu.ifpr.foz.biblioteca.controllers;

import br.edu.ifpr.foz.biblioteca.models.Autor;
import br.edu.ifpr.foz.biblioteca.models.Book;
import br.edu.ifpr.foz.biblioteca.repositories.AutorRepository;
import br.edu.ifpr.foz.biblioteca.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@WebServlet("/books/create")
public class BookCreateController extends HttpServlet {

    BookRepository repository = new BookRepository();
    AutorRepository autorRepository = new AutorRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Autor> autores = autorRepository.getAll();
        req.setAttribute("autores", autores);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books/create.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nome = req.getParameter("field_nome");
        LocalDate dataCriacao = LocalDate.parse(req.getParameter("field_data_criacao"));
        Integer autorId = Integer.valueOf(req.getParameter("field_autor_id"));

        Autor autor = new Autor();
        autor.setId(autorId);

        Book book = new Book();
        book.setNome(nome);
        book.setDataCriacao(dataCriacao);

        repository.insert(book);

        resp.sendRedirect( req.getContextPath() + "/books");
    }
}
