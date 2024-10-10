use loja

INSERT INTO Usuario (login, senha)
VALUES
('op1','op1'),
('op2','op2'),
('op3','op3'),
('op4','op4');

SELECT * FROM Usuario;

INSERT INTO Produto (nome, quantidade, precoVenda)
VALUES
('banana', 100, 5.0),
('laranja', 500, 2.0),
('Manga', 800, 4.0),
('Pera', 300, 6.0),
('Abacaxi', 300, 3.0);

SELECT * FROM Produto;

INSERT INTO Pessoa (nome, rua, cidade, estado, telefone, email)
VALUES
('Joao', 'Rua 12,casa3,Quintanda', 'Riacho do Sul', 'PA','11111111', 'joao@riacho.com'),
('JJC', 'Rua 11, Centro', 'Riacho do Norte', 'PA', '12121212', 'jjc@riacho.com');

SELECT * FROM Pessoa;

INSERT INTO PessoaFisica (idPessoa, cpf)
VALUES
(1, '11111111111');

SELECT * FROM PessoaFisica;

INSERT INTO PessoaJuridica (idPessoa, cnpj)
VALUES
(2, '22222222222');

SELECT * FROM PessoaJuridica;

INSERT INTO Movimento (idUsuario, idPessoa, idProduto, quantidade, tipo, valorUnitario)
VALUES
(1, 1, 1, 10, 'E', 3.0),
(1, 1, 2, 10, 'E', 1.0),
(1, 1, 3, 10, 'E', 3.0),
(2, 2, 1, 3, 'S', 5.0),
(2, 2, 2, 1, 'S', 2.0),
(2, 2, 3, 8, 'S', 4.0),
(3, 2, 1, 5, 'E', 4.75),
(3, 2, 2, 7, 'E', 1.32),
(3, 1, 1, 9, 'S', 5.0),
(3, 1, 2, 3, 'S', 2.0),
(4,2, 1, 8, 'S', 5.0),
(4, 2, 2, 6, 'S', 2.0);

SELECT * FROM Movimento;
