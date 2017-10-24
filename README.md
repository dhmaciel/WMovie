# WMovie

Aplicativo android para informações de filmes baseado na api imdb: http://www.omdbapi.com/

## Começando

Estas instruções devem lhe guiar como instalar o aplicativo, qual versão da plataforma android necessária e como foi desenvolvido a aplicação.

### Pré-requisitos

Para instalação em um dispositivo este deve possuir no mínimo a versão 15 (Ice Cream Sandwich) do sistema android. Para navegar e alterar o código fonte é recomendado a IDE Android Studio com acesso a internet.

### Funcionamento

![Alt text](https://preview.ibb.co/htOz1Q/Screenshot_Git_Hub.jpg "Optional title")

Pesquisa de filmes

```
Pesquisa de filmes por nome.
```

Resultado da pesquisa

```
Visualizar todos filmes contendo a pesquisa inserida.
```

Listagem de filmes

```
Pode visualizar os filmes salvos e realizar uma pesquisa de filmes.
```

Dados do filme

```
Possui dados do filme selecionado, pode salvar ou exluir um registro.
```

## Executando os testes

Teste de interface se encontra na classe FluxoBasicoTest.java

* [Testes de Interface](https://github.com/worlock257/WMovie/tree/master/app/src/androidTest/java/com/desafio/douglas/wmovie/activity)


### O que cobrem os testes?

O teste de interface cobre todo o funcionamento básico do sistema.

```
Visualização dos filmes salvos

Pesquisa

Detalhes

Salvar 

Excluir
```

## Desenvolvimento

O aplicativo possui alguns recursos como: 

```
Paginação de resultados

Imagens armazenadas são tratadas como Cache

Banco de dados

```

## Construído com

* [Butter Knife](http://jakewharton.github.io/butterknife/) - Biblioteca de injeção de view
* [Retrofit](http://square.github.io/retrofit/) - Cliente HTTP
* [GSON](https://github.com/google/gson) - Converter objetos java para o formato JSON ou vice-versa.
* [Picasso](http://square.github.io/picasso/) - Download de imagens e cache.
* [OrmLite](http://ormlite.com/) - Mapeamento de objetos
* [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) - Testes de UI.

## Autor

* **Douglas Maciel** - *Trabalho inicial* - [worlock257](https://github.com/worlock257)

