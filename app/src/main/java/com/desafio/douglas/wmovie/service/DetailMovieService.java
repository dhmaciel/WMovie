package com.desafio.douglas.wmovie.service;

import com.desafio.douglas.wmovie.exception.RegraNegocioException;
import com.desafio.douglas.wmovie.model.DetailMovie;

import java.util.List;

/**
 * Created by worlo on 07/04/2017.
 */

public interface DetailMovieService extends InterfaceGeneric {

    boolean salvarOuAtualizar(DetailMovie detailMovie) throws RegraNegocioException;

    DetailMovie buscarPorId(String id);

    boolean deletar(DetailMovie detailMovie) throws RegraNegocioException;

    List<DetailMovie> listar(String nomeColunaOrderBy, boolean ordenacaoAscendente);
}
