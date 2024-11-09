/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFc"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    ProdutoFacadeLocal facade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listar";  
        }

        String destino = "ProdutoLista.jsp";  

        switch (acao) {
            case "formIncluir":
                destino = "ProdutoDados.jsp";  
                break;

            case "excluir":
                int idDel = Integer.parseInt(request.getParameter("id"));
                Produto produtoDel = facade.find(idDel);
                if (produtoDel != null) {
                    facade.remove(produtoDel);  
                }
                List<Produto> produtosDel = facade.findAll();
                request.setAttribute("produtos", produtosDel);
                break;

            case "formAlterar":
                int id = Integer.parseInt(request.getParameter("id"));
                Produto produto = facade.find(id);
                request.setAttribute("produto", produto);  
                destino = "ProdutoDados.jsp";  
                break;

            default:
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);  
                break;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        if (acao == null || acao.isEmpty()) {
            acao = "listar"; 
        }

        String destino = "ProdutoLista.jsp"; 

        switch (acao) {
            case "incluir":
                Produto newProduto = new Produto();
                newProduto.setNome(request.getParameter("nome"));
                newProduto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                
                Float precoVendaFloat = Float.valueOf(request.getParameter("precoVenda"));
                BigDecimal precoVenda = BigDecimal.valueOf(precoVendaFloat);  
                newProduto.setPrecoVenda(precoVenda);  

                facade.create(newProduto); 

                List<Produto> produtosInclusao = facade.findAll();
                request.setAttribute("produtos", produtosInclusao);  
                break;

            case "alterar":
                Produto produtoAlterar = facade.find(Integer.valueOf(request.getParameter("id")));
                produtoAlterar.setNome(request.getParameter("nome"));
                produtoAlterar.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                
                Float precoVendaAlterarFloat = Float.valueOf(request.getParameter("precoVenda"));
                BigDecimal precoVendaAlterar = BigDecimal.valueOf(precoVendaAlterarFloat);  

                produtoAlterar.setPrecoVenda(precoVendaAlterar); 
                facade.edit(produtoAlterar);  

                List<Produto> produtosAlteracao = facade.findAll();
                request.setAttribute("produtos", produtosAlteracao);  
                break;

            default:
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);  
                break;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }
}