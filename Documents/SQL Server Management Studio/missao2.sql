USE loja;

CREATE TABLE Pessoa (
idPessoa INTEGER NOT NULL IDENTITY(1,1)PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
rua VARCHAR(255) NOT NULL,
cidade VARCHAR(255) NOT NULL,
estado CHAR(2) NOT NULL,
telefone VARCHAR(11) NOT NULL,
email VARCHAR(255) NOT NULL
);

CREATE TABLE PessoaJuridica (
idPessoa INTEGER NOT NULL PRIMARY KEY,
CNPJ VARCHAR(14) NOT NULL,
CONSTRAINT fk_PessoaJuridica_Pessoa 
	FOREIGN KEY (idPessoa)
	REFERENCES dbo.Pessoa(idPessoa)
);

CREATE TABLE PessoaFisica (
idPessoa INTEGER NOT NULL PRIMARY KEY,
CPF VARCHAR(11) NOT NULL,
CONSTRAINT fk_PessoaFisica_Pessoa
	FOREIGN KEY (idPessoa)
	REFERENCES Pessoa(idPessoa)
);

CREATE TABLE PRODUTO (
idProduto INTEGER NOT NULL IDENTITY(1,1) PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
quantidade INTEGER NOT NULL,
precoVenda NUMERIC(10, 2) NOT NULL
);

CREATE TABLE Usuario (
idUsuario INTEGER NOT NULL IDENTITY (1,1) PRIMARY KEY,
login VARCHAR(50) NOT NULL,
senha VARCHAR(50) NOT NULL
);

CREATE TABLE Movimento (
idMovimento INTEGER NOT NULL IDENTITY (1,1) PRIMARY KEY,
idUsuario INTEGER NOT NULL,
idPessoa INTEGER NOT NULL,
idProduto INTEGER NOT NULL,
quantidade INTEGER NOT NULL,
tipo CHAR(1) NOT NULL,
valorUnitario FLOAT NOT NULL,
CONSTRAINT fk_Movimento_Produto FOREIGN KEY (idProduto) REFERENCES dbo.Produto(idProduto),
CONSTRAINT fk_Movimento_Usuario FOREIGN KEY (idUsuario) REFERENCES dbo.Usuario(idUsuario),
CONSTRAINT fk_Movimento_Pessoa FOREIGN KEY (idPessoa) REFERENCES dbo.Pessoa(idPessoa)
);

CREATE SEQUENCE dbo.CodigoPessoa
START WITH 1
INCREMENT BY 1;

