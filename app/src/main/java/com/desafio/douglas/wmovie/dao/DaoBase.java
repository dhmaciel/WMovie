package com.desafio.douglas.wmovie.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by worlo on 07/04/2017.
 * Classe responsável por implementar um DAO Genérico, que implementa os métodos básicos de
 * persistência utilizando o framework de Mapeamento Objeto Relacional "ORMLite".
 * <p/>
 */

public class DaoBase <T extends EntityBase> {

    protected Context ctx;

    protected DatabaseHelper getDatabaseHelper() {
        return DatabaseManager.getInstance().getDatabaseHelper();
    }

    protected Dao<T, Object> getConnection() {
        return getDatabaseHelper().getDAO(getEntityClass());
    }

    private Class getEntityClass() {
        ParameterizedType t = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class) t.getActualTypeArguments()[0];
    }

    public List<T> listar(String nomeColunaOrderBy, boolean ordenacaoAscendente) {
        try {
            return (List) getDatabaseHelper().getDAO(getEntityClass()).queryBuilder().orderBy(nomeColunaOrderBy, ordenacaoAscendente).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public T buscarPorId(Object id) {
        try {
            return (T) getDatabaseHelper().getDAO(getEntityClass()).queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean salvarOuAtualizar(T obj) {
        try {
            if (obj.getDataCadastro() == null) {
                obj.setDataCadastro(new Date());
            }
            obj.setDataAlteracao(new Date());

            return getDatabaseHelper().getDAO(getEntityClass()).createOrUpdate(obj).getNumLinesChanged() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public boolean deletar(T obj) {
        try {
            return getDatabaseHelper().getDAO(getEntityClass()).delete(obj) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public boolean deletarTodosRegistros(String nomeTabela) {
        Integer i = getDatabaseHelper().getReadableDatabase().delete(nomeTabela, null, null);

        if (i != null) {
            return true;
        } else {
            return false;
        }
    }

    public long count() {
        try {
            return getDatabaseHelper().getDAO(getEntityClass()).countOf();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

