package br.edu.ifpr.foz.biblioteca.controllers;

import br.edu.ifpr.foz.biblioteca.models.Autor;
import br.edu.ifpr.foz.biblioteca.models.Book;
import br.edu.ifpr.foz.biblioteca.repositories.AutorRepository;
import br.edu.ifpr.foz.biblioteca.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/books/update")
public class BookUpdateController extends HttpServlet {

    BookRepository repository = new BookRepository();
    AutorRepository autorRepository = new AutorRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.valueOf(req.getParameter("id"));

        List<Autor> autores = autorRepository.getAll();
        req.setAttribute("autor", autores);

        Book book = repository.getById(id);
        req.setAttribute("book", book);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books-update.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("field_id"));
        String nome = req.getParameter("field_nome");
        LocalDate dataCriacao = LocalDate.parse(req.getParameter("field_data_criacao"));

        Autor autor = new Autor();
        autor.setId(id);

        Book book = new Book();

        book.setId(id);
        book.setNome(nome);
        book.setDataCriacao(dataCriacao);

        repository.update(book);

        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
