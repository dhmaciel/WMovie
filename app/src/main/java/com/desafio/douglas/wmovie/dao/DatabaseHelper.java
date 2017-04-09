package com.desafio.douglas.wmovie.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.desafio.douglas.wmovie.model.DetailMovie;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by worlo on 07/04/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // Nome do Banco de Dados
    public static final String DATABASE_FILE_NAME = "WMovie.db";

    // Versão do Banco de Dados. Sempre que alterar o BD incrementar a versão.
    public static final int DATABASE_VERSION = 1;

    private Map<Class, Dao<EntityBase, Object>> daos = new HashMap<Class, Dao<EntityBase, Object>>();

    public  DatabaseHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), ">>>>>>>>>> Criando o BD...");

            Log.i(DatabaseHelper.class.getName(), "Criando a tabela DetailMovie");
            TableUtils.createTable(connectionSource, DetailMovie.class);

            Log.i(DatabaseHelper.class.getName(), ">>>>>>>>> BD criado com sucesso!");
        } catch (SQLException ex) {
            Log.e(DatabaseHelper.class.getName(), ">>>>>>>>> Erro ao criar o BD <<<<<<<<<", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public <T> Dao<T, Object> getDAO(Class<EntityBase> entidadeClass) {
        Dao<EntityBase, Object> dao = null;
        if (daos.get(entidadeClass) == null) {
            try {
                dao = getDao(entidadeClass);
            } catch (SQLException e) {
                Log.e(DatabaseHelper.class.getName(), "Erro durante getDAO", e);
                throw new RuntimeException(e);
            }
            daos.put(entidadeClass, dao);
        }

        return (Dao<T, Object>) daos.get(entidadeClass);
    }
}



