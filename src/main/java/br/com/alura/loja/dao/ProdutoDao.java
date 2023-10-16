package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String jpql = "Select p from Produto p";

        return em.createQuery(jpql,Produto.class).getResultList();
    }

    public List<Produto> buscarPorNomeCategoria(String nome) {
        String jpql = "Select p from Produto p " +
                "where p.categoria.nome = :nome";
        return em.createQuery(jpql,Produto.class)
                .setParameter("nome",nome)
                .getResultList();
    }
}
