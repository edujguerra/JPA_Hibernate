package br.com.alura.loja.testes;


import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {
    public static void cadastrar(){
        Categoria categoria1 = new Categoria();
        categoria1.setNome("CELULARES  ");

        Categoria categoria2 = new Categoria();
        categoria2.setNome("IPAD");

        Categoria categoria3 = new Categoria();
        categoria3.setNome("NOTEBOOK");

        Produto celular1 = new Produto();
        celular1.setNome("Xiomi");
        celular1.setDescricao("muito legal");
        celular1.setCategoria(categoria1);
        celular1.setPreco(new BigDecimal("800"));

        Produto celular2 = new Produto();
        celular2.setNome("Sansung");
        celular2.setDescricao("muito legal");
        celular2.setCategoria(categoria2);
        celular2.setPreco(new BigDecimal("1800"));

        Produto celular3 = new Produto();
        celular3.setNome("IPhone");
        celular3.setDescricao("muito legal");
        celular3.setCategoria(categoria3);
        celular3.setPreco(new BigDecimal("2800"));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(categoria1);
        categoriaDao.cadastrar(categoria2);
        categoriaDao.cadastrar(categoria3);

        produtoDao.cadastrar(celular1);
        produtoDao.cadastrar(celular2);
        produtoDao.cadastrar(celular3);

        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args){
        cadastrar();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        Produto p = produtoDao.buscarPorId(1L);
        System.out.println("Preço : " + p.getPreco());

        List<Categoria> categorias = categoriaDao.buscarTodos();
        for (Categoria cat : categorias) {
            System.out.println("Categorias : " + cat.getNome());
        }

        List<Produto> produtos = produtoDao.buscarTodos();
        for (Produto prod : produtos) {
            System.out.println("Produto : " + prod.getNome() + " " + prod.getCategoria().getNome());
        }

        List<Produto> produtos1 = produtoDao.buscarPorNomeCategoria("IPAD");
        for (Produto prod : produtos1) {
            System.out.println("Produto por categoria : "
                    + prod.getNome() + " "
                    + prod.getCategoria().getNome());
        }
    }

}
