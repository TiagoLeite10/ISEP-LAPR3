-- Tabela produto
CREATE TABLE produto (
codProduto INTEGER CONSTRAINT pkProdutoCodProduto PRIMARY KEY,
nome VARCHAR2(50) 
    CONSTRAINT nnProdutoNome NOT NULL,
tipoCultura VARCHAR2(50) 
    CONSTRAINT nnProdutoTipoCultura NOT NULL,
cultura VARCHAR2(50) 
    CONSTRAINT nnProdutoCultura NOT NULL
);

-- Tabela cliente
CREATE TABLE cliente (
codCliente INTEGER CONSTRAINT pkClienteCodCLiente PRIMARY KEY,
numeroFiscal INTEGER
    CONSTRAINT nnClienteNumeroFiscal NOT NULL
    CONSTRAINT ukClienteNumeroFiscal UNIQUE,
CONSTRAINT ckClienteNumeroFiscal CHECK (numeroFiscal BETWEEN 100000000 AND 999999999)
);

-- Tabela tempo
CREATE TABLE tempo (
codTempo INTEGER CONSTRAINT pkTempoCodTempo PRIMARY KEY,
ano INTEGER CONSTRAINT nnTempoAno NOT NULL,
mes INTEGER CONSTRAINT nnTempoMes NOT NULL,
CONSTRAINT ckClienteAno CHECK (ano > 0),
CONSTRAINT ckClienteMes CHECK (mes BETWEEN 1 AND 12)
);

-- Tabela parcela
CREATE TABLE parcela (
codParcela INTEGER CONSTRAINT pkParcelaCodParcela PRIMARY KEY,
designacao VARCHAR2(255) CONSTRAINT nnParcelaDesignacao NOT NULL,
area NUMBER CONSTRAINT nnParcelaArea NOT NULL,
CONSTRAINT ckParcelaArea CHECK (area > 0)
);

-- Tabela venda
CREATE TABLE venda (
codCliente INTEGER
    CONSTRAINT fkVendaCodCliente REFERENCES cliente (codCliente)
    CONSTRAINT nnVendaCodCliente NOT NULL,
codProduto INTEGER
    CONSTRAINT fkVendaCodProduto REFERENCES produto (codProduto)
    CONSTRAINT nnVendaCodProduto NOT NULL,
codTempo INTEGER
    CONSTRAINT fkVendaCodTempo REFERENCES tempo (codTempo)
    CONSTRAINT nnVendaCodTempo NOT NULL,
vendasLiquidas NUMBER,
quantidade INTEGER,
CONSTRAINT ckVendaVendasLiquidas CHECK (vendasLiquidas >= 0),
CONSTRAINT ckVendaQuantidade CHECK (quantidade > 0)
);

-- Tabela colheita
CREATE TABLE colheita (
codParcela INTEGER
    CONSTRAINT fkColheitaCodParcela REFERENCES parcela (codParcela)
    CONSTRAINT nnColheitaCodParcela NOT NULL,
codProduto INTEGER
    CONSTRAINT fkColheitaCodProduto REFERENCES produto (codProduto)
    CONSTRAINT nnColheitaCodProduto NOT NULL,
codTempo INTEGER
    CONSTRAINT fkColheitaCodTempo REFERENCES tempo (codTempo)
    CONSTRAINT nnColheitaCodTempo NOT NULL,
quantidade INTEGER,
CONSTRAINT ckColheitaQuantidade CHECK (quantidade > 0)
);