# Login 

Módulo de login baseado em Java e postgres para projetos Java.

![](https://img.shields.io/github/license/ozebe/login.svg)
![](https://img.shields.io/github/issues/ozebe/login.svg)
![](https://img.shields.io/github/commit-activity/m/ozebe/login.svg)
![](https://img.shields.io/github/repo-size/ozebe/login.svg)

## Começando

Para utilizar é fácil, nos passos abaixo você verá como configura-lo e já sair usando.

### Pré requisitos

O principal pré requisito é o Postgres, o qual pode ser baixado pelo link abaixo:
* [PostgreSQL](https://www.postgresql.org/download/)

Também precisaremos baixar o DDL e o DML que se encontram na pasta do projeto, mas caso queira pode configurar com os links abaixo:
* [Script DDL para criar as tabelas necessárias](https://github.com/ozebe/login/blob/master/DDL.sql)
* [Script DML para adicionar os primeiros registros](https://github.com/ozebe/login/blob/master/DML.sql)

### Instalando

Primeiramente é necessário rodar o DDL no banco de dados escolhido no postgres e logo após rodar o DML.

No DML possuímos níveis criados previamente, mas você mesmo pode configurar os níveis que achar necessário.

Repare também que é utilizado MD5 para "criptografar" as senhas.

**Conectando com o banco**
Para o projeto entender qual banco de dados, servidor, porta, usuário e senha utilizaremos, é necessária a configuração prévia do arquivo **config.ini** que existe na raiz do projeto.

**Configurando o config.ini**

    [db-config]
    local=//servidor:porta/banco_de_dados
	user=usuarioPg
	password=senhaPg

Utilização comum:

    [db-config]
    local=//localhost:5432/usuarios
    user=postgres
    password=postgres
    
Logo após esses passos o sistema conseguirá conectar-se normalmente ao banco de dados.

## Versionamento

Utilizamos [SemVer](http://semver.org/) para versionamento.

## Autores

* **Wesley Ozebe** - *Projeto e versões iniciais* - [Ozebe](https://github.com/ozebe)

## Licença

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
