package com.desafio.douglas.wmovie.service_impl;

import android.content.Context;

import com.desafio.douglas.wmovie.dao.DatabaseManager;
import com.desafio.douglas.wmovie.dao.DetailMovieDao;
import com.desafio.douglas.wmovie.exception.RegraNegocioException;
import com.desafio.douglas.wmovie.model.DetailMovie;
import com.desafio.douglas.wmovie.service.DetailMovieService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by worlo on 07/04/2017.
 */

public class DetailMovieServiceImpl implements DetailMovieService, Serializable {

    private DetailMovieDao dao;

    public DetailMovieServiceImpl(Context ctx) {
        dao = DetailMovieDao.getInstance(ctx);
        DatabaseManager.init(ctx);
    }

    @Override
    public boolean deletar(DetailMovie detailMovie) throws RegraNegocioException {
        return dao.deletar(detailMovie);
    }

    @Override
    public boolean deletarTudo() {
        return dao.deletarTodosRegistros("tb_datail_movie");
    }

    @Override
    public boolean salvarOuAtualizar(DetailMovie detailMovie) throws RegraNegocioException {
        return dao.salvarOuAtualizar(detailMovie);
    }

    @Override
    public DetailMovie buscarPorId(String id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<DetailMovie> listar(String nomeColunaOrderBy, boolean ordenacaoAscendente) {
        return dao.listar(nomeColunaOrderBy, ordenacaoAscendente);
    }
}
