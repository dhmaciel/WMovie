package com.desafio.douglas.wmovie.dao;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by worlo on 07/04/2017.
 */

public class EntityBase implements Serializable {

    @DatabaseField(columnName = "data_cadastro", canBeNull = false)
    private Date dataCadastro;

    @DatabaseField(columnName = "data_alteracao")
    private Date dataAlteracao;

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
