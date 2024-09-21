-- Tabela estadoEncomenda
CREATE TABLE estadoEncomenda (
codEstadoEncomenda INTEGER CONSTRAINT pkEstadoEncomendaCodEstadoEncomenta PRIMARY KEY,
descricao VARCHAR2(50) 
    CONSTRAINT nnEstadoEncomendaDescricao NOT NULL
    CONSTRAINT ukEstadoEncomendaDescricao UNIQUE
);

-- Tabela unidade
CREATE TABLE unidade (
codUnidade INTEGER CONSTRAINT pkUnidadeCodUnidade PRIMARY KEY,
descricao VARCHAR2(50) 
    CONSTRAINT nnUnidadeDescricao NOT NULL
    CONSTRAINT ukUnidadeDescricao UNIQUE
);

-- Tabela fatorProducaoClassificacao
CREATE TABLE fatorProducaoClassificacao (
codFatorProducaoClassificacao INTEGER CONSTRAINT pkFatorProducaoClassificacaoCodClassificacao PRIMARY KEY,
descricao VARCHAR2(100) 
    CONSTRAINT nnFatorProducaoClassificacaoDescricao NOT NULL
    CONSTRAINT ukFatorProducaoClassificacaoDescricao UNIQUE
);

-- Tabela formulacaoProduto
CREATE TABLE formulacaoProduto (
codFormulacaoProduto INTEGER CONSTRAINT pkFormulacaoProdutoCodFormulacaoProduto PRIMARY KEY,
descricao VARCHAR2(100)
    CONSTRAINT nnFormulacaoProdutoDescricao NOT NULL
    CONSTRAINT ukFormulacaoProdutoDescricao UNIQUE
);

-- Tabela instalacaoAgricola
CREATE TABLE instalacaoAgricola (
codInstalacaoAgricola INTEGER CONSTRAINT pkInstalacaoAgricolaCodInstalacaoAgricola PRIMARY KEY,
nome VARCHAR2(100)
    CONSTRAINT nnInstalacaoAgricolaNome NOT NULL
    CONSTRAINT ukInstalacaoAgricolaNome UNIQUE
);

-- Tabela objetivoCultura
CREATE TABLE objetivoCultura (
codObjetivoCultura INTEGER CONSTRAINT pkObjetivoCulturaCodObjetivoCultura PRIMARY KEY,
descricao VARCHAR2(100) 
    CONSTRAINT nnObjetivoCulturaDescricao NOT NULL
    CONSTRAINT ukObjetivoCulturaDescricao UNIQUE
);

-- Tabela tipoCultura
CREATE TABLE tipoCultura (
codTipoCultura INTEGER CONSTRAINT pkTipoCulturaCodTipoCultura PRIMARY KEY,
descricao VARCHAR2(50) 
    CONSTRAINT nnTipoCulturaDescricao NOT NULL
    CONSTRAINT ukTipoCulturaDescricao UNIQUE
);

-- Tabela tipoEdificio
CREATE TABLE tipoEdificio (
codTipoEdificio INTEGER CONSTRAINT pkTipoEdificioCodTipoEdificio PRIMARY KEY,
nome VARCHAR2(100) 
    CONSTRAINT nnTipoEdificioNome NOT NULL
    CONSTRAINT ukTipoEdificioNome UNIQUE
);

-- Tabela Iva
CREATE TABLE iva (
codIva INTEGER CONSTRAINT pkIvaCodIva PRIMARY KEY,
percentagemIva INTEGER 
    CONSTRAINT nnIvaPercentagemIva NOT NULL
    CONSTRAINT ukIvaPercentagemIva UNIQUE,
CONSTRAINT ckIvaPercentagemIva CHECK (percentagemIva BETWEEN 0 AND 100)
);

-- Tabela produto
CREATE TABLE produto (
codProduto INTEGER CONSTRAINT pkProdutoCodProduto PRIMARY KEY,
codIva INTEGER 
    CONSTRAINT fkProdutoCodIva REFERENCES iva (codIva)
    CONSTRAINT nnProdutoCodIva NOT NULL,
nome VARCHAR2(100) 
    CONSTRAINT nnProdutoNome NOT NULL
    CONSTRAINT ukProdutoNome UNIQUE,
valorProduto NUMBER(7, 2) CONSTRAINT nnProdutoValorProduto NOT NULL,
CONSTRAINT ckProdutoValorProduto CHECK (valorProduto >= 0)
);

-- Tabela tipoComposicao
CREATE TABLE tipoComposicao (
codTipoComposicao INTEGER CONSTRAINT pkTipoComposicaoCodTipoComposicao PRIMARY KEY,
codUnidade INTEGER 
    CONSTRAINT fkTipoComposicaoCodUnidade references unidade (codUnidade)
    CONSTRAINT nnTipoComposicaoCodUnidade NOT NULL,
nome VARCHAR2(100) 
    CONSTRAINT nnTipoComposicaoNome NOT NULL
    CONSTRAINT ukTipoComposicaoNome UNIQUE
);

-- Tabela tipoCliente
CREATE TABLE tipoCliente (
codTipoCliente INTEGER CONSTRAINT pkTipoClienteCodTipoCliente PRIMARY KEY,
descricao VARCHAR2(50) 
    CONSTRAINT nnTipoClienteDescricao NOT NULL
    CONSTRAINT ukTipoClienteDescricao UNIQUE
);

-- Tabela tipoHub
CREATE TABLE tipoHub (
codTipoHub INTEGER CONSTRAINT pkTipoHubCodTipoHub PRIMARY KEY,
acronimo CHAR(1) CONSTRAINT nnTipoHubAcronimo NOT NULL,
descricao VARCHAR2(50)
    CONSTRAINT nnTipoHubDescricao NOT NULL
    CONSTRAINT ukTipoHubDescricao UNIQUE,
CONSTRAINT ckTipoHubAcronimo CHECK (acronimo = 'E' OR acronimo = 'P')
);

-- Tabela hub
CREATE TABLE hub (
codHub VARCHAR2(5) CONSTRAINT pkHubCodHub PRIMARY KEY,
codTipoHub INTEGER
    CONSTRAINT fkHubCodTipoHub references tipoHub (codTipoHub)
    CONSTRAINT nnHubCodTipoHub NOT NULL,
latitude NUMBER(8, 6) CONSTRAINT nnHubLatitude NOT NULL,
longitude NUMBER(9, 6) CONSTRAINT nnHubLongitude NOT NULL,
CONSTRAINT ckHubCodHub CHECK (REGEXP_LIKE (codHub, 'CT[0-9][0-9]?[0-9]?'))
);

-- Tabela cliente
CREATE TABLE cliente (
codInternoCliente INTEGER CONSTRAINT pkClienteCodInternoCliente PRIMARY KEY,
nome VARCHAR2(100) CONSTRAINT nnClienteNome NOT NULL,
numeroFiscal INTEGER
    CONSTRAINT nnClienteNumeroFiscal NOT NULL
    CONSTRAINT ukClienteNumeroFiscal UNIQUE,
email VARCHAR2(100)
    CONSTRAINT nnClienteEmail NOT NULL
    CONSTRAINT ukClienteEmail UNIQUE,
plafond INTEGER CONSTRAINT nnClientePlafond NOT NULL,
codTipoCliente INTEGER
    CONSTRAINT fkClienteCodTipoCliente references tipoCliente (codTipoCliente)
    CONSTRAINT nnClienteCodTipoCliente NOT NULL,
numeroEncomendas INTEGER,
valorTotalEncomendas NUMBER,
codHub VARCHAR2(5)
    CONSTRAINT fkClienteCodHub references hub (codHub),
CONSTRAINT ckClienteNumeroFiscal CHECK (numeroFiscal BETWEEN 100000000 AND 999999999),
CONSTRAINT ckClienteEmail CHECK (email LIKE '%@%.%'),
CONSTRAINT ckClientePlafond CHECK (plafond > 0),
CONSTRAINT ckClienteNumeroEncomendas CHECK (numeroEncomendas >= 0),
CONSTRAINT ckClienteValorTotalEncomendas CHECK (valorTotalEncomendas >= 0)
);

-- Tabela moradaFiscal
CREATE TABLE moradaFiscal (
codMoradaFiscal INTEGER CONSTRAINT pkMoradaFiscalCodMorada PRIMARY KEY,
codInternoCliente INTEGER
    CONSTRAINT fkMoradaFiscalCodInternoCliente references cliente (codInternoCliente)
    CONSTRAINT nnMoradaFiscalCodInternoCliente NOT NULL,
morada VARCHAR2(100) CONSTRAINT nnMoradaMorada NOT NULL,
codPostal CHAR(8) CONSTRAINT nnMoradaFiscalCodPostal NOT NULL,
cidade VARCHAR2(100),
estado CHAR(1) CONSTRAINT nnMoradaFiscalEstado NOT NULL,
CONSTRAINT ckMoradaFiscalCodPostal CHECK (REGEXP_LIKE (codPostal, '[0-9]{4}-[0-9]{3}')),
CONSTRAINT ckMoradaFiscalEstado CHECK (estado LIKE 0 OR estado LIKE 1)
);

-- Tabela encomenda
CREATE TABLE encomenda (
codEncomenda INTEGER CONSTRAINT pkEncomendaCodEncomenda PRIMARY KEY,
codInternoCliente INTEGER
    CONSTRAINT fkEncomendaCodInternoCliente references cliente (codInternoCliente)
    CONSTRAINT nnEncomendaCodInternoCliente NOT NULL,
codEstadoEncomenda INTEGER 
    CONSTRAINT fkEncomendaCodEstadoEncomenta references estadoEncomenda (codEstadoEncomenda)
    CONSTRAINT nnEncomendaCodEstadoEncomenta NOT NULL,
codHub VARCHAR2(5)
    CONSTRAINT fkEncomendaCodHub references hub (codHub)
    CONSTRAINT nnEncomendaCodHub NOT NULL,
dataEncomenda DATE CONSTRAINT nnEncomendaDataEncomenda NOT NULL,
dataVencimento DATE,
dataPagamento DATE,
dataEntrega DATE,
CONSTRAINT ckEncomendaDataEncomendaDataVencimento CHECK (dataEncomenda <= dataVencimento),
CONSTRAINT ckEncomendaDataEncomendaDataPagamento CHECK (dataEncomenda <= dataPagamento),
CONSTRAINT ckEncomendaDataEncomendaDataEntrega CHECK (dataEncomenda <= dataEntrega)
);

-- Tabela edificio
CREATE TABLE edificio (
codEdificio INTEGER CONSTRAINT pkEdificioCodEdificio PRIMARY KEY,
nome VARCHAR2(100) 
    CONSTRAINT nnEdificioNome NOT NULL
    CONSTRAINT ukEdificioNome UNIQUE,
codTipoEdificio INTEGER 
    CONSTRAINT fkEdificioCodTipoEdificio REFERENCES tipoEdificio (codTipoEdificio)
    CONSTRAINT nnEdificioCodTipoEdificio NOT NULL,
codInstalacaoAgricola INTEGER 
    CONSTRAINT fkEdificioCodInstalacaoAgricola REFERENCES instalacaoAgricola (codInstalacaoAgricola)
    CONSTRAINT nnEdificioCodInstalacaoAgricola NOT NULL
);

-- Tabela cultura
CREATE TABLE cultura (
codCultura INTEGER CONSTRAINT pkCulturaCodCultura PRIMARY KEY,
codObjetivoCultura INTEGER 
    CONSTRAINT fkCulturaCodObjetivoCultura REFERENCES objetivoCultura (codObjetivoCultura)
    CONSTRAINT nnCulturaCodObjetivoCultura NOT NULL,
codTipoCultura INTEGER 
    CONSTRAINT fkCulturaCodTipoCultura REFERENCES tipoCultura (codTipoCultura)
    CONSTRAINT nnCulturaCodTipoCultura NOT NULL,
nome VARCHAR2(150)
    CONSTRAINT nnCulturaNome NOT NULL
    CONSTRAINT ukCulturaNome UNIQUE
);

-- Tabela fichaTecnicaTipoComposicao
CREATE TABLE fichaTecnicaTipoComposicao (
codFichaTecnicaTipoComposicao INTEGER CONSTRAINT pkFichaTecnicaTipoComposicaoCodFichaTecnicaTipoComposicao PRIMARY KEY,
codUnidade INTEGER 
    CONSTRAINT fkFichaTecnicaTipoComposicaoCodUnidade REFERENCES unidade (codUnidade)
    CONSTRAINT nnFichaTecnicaTipoComposicaoCodUnidade NOT NULL,
nome VARCHAR2(150) 
    CONSTRAINT nnFichaTecnicaTipoComposicaoNome NOT NULL
    CONSTRAINT ukFichaTecnicaTipoComposicaoNome UNIQUE
);

-- Tabela fornecedor
CREATE TABLE fornecedor (
codFornecedor INTEGER CONSTRAINT pkFornecedorCodFornecedor PRIMARY KEY,
nome VARCHAR2(150) 
    CONSTRAINT nnFornecedorNome NOT NULL
);

-- Tabela fatorProducao
CREATE TABLE fatorProducao (
codFatorProducao INTEGER CONSTRAINT pkFatorProducaoCodFatorProducao PRIMARY KEY,
codFatorProducaoClassificacao INTEGER 
    CONSTRAINT fkFatorProducaoCodFatorProducaoClassificacao REFERENCES fatorProducaoClassificacao (codFatorProducaoClassificacao)
    CONSTRAINT nnFatorProducaoCodFatorProducaoClassificacao NOT NULL,
codFormulacaoProduto INTEGER 
    CONSTRAINT fkFatorProducaoCodFormulacaoProduto REFERENCES formulacaoProduto (codFormulacaoProduto)
    CONSTRAINT nnFatorProducaoCodFormulacaoProduto NOT NULL,
codFornecedor INTEGER 
    CONSTRAINT fkFatorProducaoCodFornecedor REFERENCES fornecedor (codFornecedor)
    CONSTRAINT nnFatorProducaoCodFornecedor NOT NULL,
codUnidade INTEGER
    CONSTRAINT fkFatorProducaoCodUnidade REFERENCES unidade (codUnidade)
    CONSTRAINT nnFatorProducaoCodUnidade NOT NULL,
nomeComercial VARCHAR2(100) 
    CONSTRAINT nnFatorProducaoNomeComercial NOT NULL
    CONSTRAINT ukFatorProducaoNomeComercial UNIQUE
);

-- Tabela fichaTecnica
CREATE TABLE linhaFichaTecnica (
codFatorProducao INTEGER 
    CONSTRAINT fkLinhaFichaTecnicaCodFatorProducao references fatorProducao (codFatorProducao)
    CONSTRAINT nnLinhaFichaTecnicaCodFatorProducao NOT NULL,
codFichaTecnicaTipoComposicao INTEGER 
    CONSTRAINT fkLinhaFichaTecnicaCodFichaTecnicaTipoComposicao references fichaTecnicaTipoComposicao (codFichaTecnicaTipoComposicao)
    CONSTRAINT nnLinhaFichaTecnicaCodFichaTecnicaTipoComposicao NOT NULL,
quantidade INTEGER CONSTRAINT nnLinhaFichaTecnicaQuantidade NOT NULL,
CONSTRAINT pkLinhaFichaTecnicaCodFatorProducaoCodFichaTecnicaTipoComposicao PRIMARY KEY (codFatorProducao, codFichaTecnicaTipoComposicao),
CONSTRAINT ckLinhaFichaTecnicaQuantidade CHECK (quantidade >= 0)
);

-- Tabela registoEncomenda
CREATE TABLE registoEncomenda (
codProduto INTEGER 
    CONSTRAINT fkRegistoEncomendaCodProduto references produto (codProduto)
    CONSTRAINT nnRegistoEncomendaCodProduto NOT NULL,
codEncomenda INTEGER 
    CONSTRAINT fkRegistoEncomendaCodEncomenda references encomenda (codEncomenda)
    CONSTRAINT nnRegistoEncomendaCodEncomenda NOT NULL,
quantidade INTEGER CONSTRAINT nnRegistoEncomendaQuantidade NOT NULL,
valorProduto NUMBER(7, 2) CONSTRAINT nnRegistoEncomendaValorProduto NOT NULL,
percentagemIva INTEGER CONSTRAINT nnRegistoEncomendaPercentagemIva NOT NULL,
CONSTRAINT pkRegistoEncomendaCodProdutoCodEncomenda PRIMARY KEY (codProduto, codEncomenda),
CONSTRAINT ckRegistoEncomendaQuantidade CHECK (quantidade > 0),
CONSTRAINT ckRegistoEncomendaValorProduto CHECK (valorProduto >= 0),
CONSTRAINT ckRegistoEncomendaPercentagemIva CHECK (percentagemIva BETWEEN 0 AND 100)
);

-- Tabela liquidoRega
CREATE TABLE liquidoRega (
codLiquidoRega INTEGER CONSTRAINT pkLiquidoRegaCodLiquidoRega PRIMARY KEY, 
descricao VARCHAR2(100) 
    CONSTRAINT nnLiquidoRegaDescricao NOT NULL
    CONSTRAINT ukLiquidoRegaDescricao UNIQUE
);

-- Tabela metodoRega
CREATE TABLE metodoRega (
codMetodoRega INTEGER CONSTRAINT pkMetodoRegaCodMetodoRega PRIMARY KEY, 
descricao VARCHAR2(100) 
    CONSTRAINT nnMetodoRegaDescricao NOT NULL
    CONSTRAINT ukMetodoRegaDescricao UNIQUE
);

-- Tabela tipoSensor
CREATE TABLE tipoSensor (
codTipoSensor INTEGER CONSTRAINT pkTipoSensorCodTipoSensor PRIMARY KEY,
acronimo CHAR(2)
    CONSTRAINT nnTipoSensorAcronimo NOT NULL
    CONSTRAINT ukTipoSensorAcronimo UNIQUE,
descricao VARCHAR2(100) 
    CONSTRAINT nnTipoSensorDescricao NOT NULL
    CONSTRAINT ukTipoSensorDescricao UNIQUE,
CONSTRAINT ckTipoSensorAcronimo CHECK (REGEXP_LIKE(acronimo, '[A-Z]{2}'))
);

-- Tabela sensor
CREATE TABLE sensor (
codSensor CHAR(5) CONSTRAINT pkSensorCodSensor PRIMARY KEY,
codTipoSensor INTEGER 
    CONSTRAINT fkSensorCodTipoSensor references tipoSensor (codTipoSensor)
    CONSTRAINT nnSensorCodTipoSensor NOT NULL,
valorReferencia NUMBER
    CONSTRAINT nnSensorValorReferencia NOT NULL
CONSTRAINT ckSensorValorReferencia CHECK (valorReferencia BETWEEN 0 AND 100)
);

-- Tabela estacaoMeteorologica
CREATE TABLE estacaoMeteorologica (
codEstacaoMeteorologica INTEGER CONSTRAINT pkEstacaoMeteorologicaCodEstacaoMeteorologica PRIMARY KEY, 
nome VARCHAR2(100) 
    CONSTRAINT nnEstacaoMeteorologicaNome NOT NULL
    CONSTRAINT ukEstacaoMeteorologicaNome UNIQUE
);

-- Tabela leituraSensor
CREATE TABLE leituraSensor (
codLeituraSensor INTEGER CONSTRAINT pkLeituraSensorCodLeituraSensor PRIMARY KEY, 
codEstacaoMeteorologica INTEGER 
    CONSTRAINT fkLeituraSensorCodEstacaoMeteorologica references estacaoMeteorologica (codEstacaoMeteorologica),
codSensor CHAR(5)
    CONSTRAINT fkLeituraSensorCodSensor references sensor (codSensor)
    CONSTRAINT nnLeituraSensorCodSensor NOT NULL,
resultado NUMBER CONSTRAINT nnLeituraSensorResultado NOT NULL,
dataLeitura DATE DEFAULT SYSDATE
    CONSTRAINT nnLeituraSensorDataLeitura NOT NULL,
CONSTRAINT ckLeituraSensorResultado CHECK (resultado BETWEEN 0 AND 100)
);

-- Tabela planoRega
CREATE TABLE planoRega (
codPlanoRega INTEGER CONSTRAINT pkPlanoRegaCodPlanoRega PRIMARY KEY,
codEstacaoMeteorologica INTEGER 
    CONSTRAINT fkPlanoRegaCodEstacaoMeteorologica references estacaoMeteorologica (codEstacaoMeteorologica)
    CONSTRAINT nnPlanoRegaCodEstacaoMeteorologica NOT NULL,
periodicidade INTEGER CONSTRAINT nnPlanoRegaPeriodicidade NOT NULL, -- minutos --
tempoRega INTEGER CONSTRAINT nnPlanoRegaTempoRega NOT NULL, -- minutos --
CONSTRAINT ckPlanoRegaPeriodicidade CHECK (periodicidade > 0),
CONSTRAINT ckPlanoRegaTempoRega CHECK (tempoRega > 0),
dataInicio DATE DEFAULT SYSDATE
    CONSTRAINT nnPlanoRegaDataInicio NOT NULL,
dataFim DATE DEFAULT ADD_MONTHS(SYSDATE, 12)
    CONSTRAINT nnPlanoRegaDataFim NOT NULL,
CONSTRAINT ckPlanoRegaDataInicioDataFim CHECK (dataInicio <= dataFim)
);

-- Tabela parcela
CREATE TABLE parcela (
codParcela INTEGER CONSTRAINT pkParcelaCodParcela PRIMARY KEY,
codInstalacaoAgricola INTEGER
    CONSTRAINT fkParcelaCodInstalacaoAgricola references instalacaoAgricola (codInstalacaoAgricola)
    CONSTRAINT nnParcelaCodInstalacaoAgricola NOT NULL,
designacao VARCHAR2(255) CONSTRAINT nnParcelaDesignacao NOT NULL,
area NUMBER CONSTRAINT nnParcelaArea NOT NULL,
CONSTRAINT ckParcelaArea CHECK (area > 0)
);

-- Tabela tipoRega
CREATE TABLE tipoRega (
codTipoRega INTEGER CONSTRAINT pkTipoRegaCodTipoRega PRIMARY KEY,
codLiquidoRega INTEGER
  CONSTRAINT fkTipoRegaCodLiquidoRega REFERENCES liquidoRega (codLiquidoRega)
  CONSTRAINT nnTipoRegaCodLiquidoRega NOT NULL,
codMetodoRega INTEGER
  CONSTRAINT fkTipoRegaCodMetodoRega REFERENCES metodoRega (codMetodoRega)
  CONSTRAINT nnTipoRegaCodMetodoRega NOT NULL,
descricao VARCHAR2(100) CONSTRAINT nnTipoRegaDescricao NOT NULL
);

-- Tabela rega
CREATE TABLE rega (
codRega INTEGER CONSTRAINT pkRegaCodRega PRIMARY KEY,
codTipoRega INTEGER
  CONSTRAINT fkRegaCodTipoRega REFERENCES tipoRega (codTipoRega)
  CONSTRAINT nnRegaCodTipoRega NOT NULL,
codPlanoRega INTEGER
  CONSTRAINT fkRegaCodPlanoRega REFERENCES planoRega (codPlanoRega)
  CONSTRAINT nnRegaCodPlanoRega NOT NULL,
codParcela INTEGER
  CONSTRAINT fkRegaCodParcela REFERENCES parcela (codParcela)
  CONSTRAINT nnRegaCodParcela NOT NULL,
quantidade NUMBER,
dataRega DATE DEFAULT SYSDATE,
CONSTRAINT ckRegaQuantidade CHECK (quantidade > 0)
);

-- Tabela plantacao
CREATE TABLE plantacao (
codPlantacao INTEGER CONSTRAINT pkPlantacaoCodPlantacao PRIMARY KEY, 
codParcela INTEGER 
    CONSTRAINT fkPlantacaoCodParcela references parcela (codParcela)
    CONSTRAINT nnPlantacaoCodParcela NOT NULL,
codCultura INTEGER 
    CONSTRAINT fkPlantacaoCodCultura references cultura (codCultura)
    CONSTRAINT nnPlantacaoCodCultura NOT NULL,
dataInicio DATE CONSTRAINT nnPlantacaoDataInicio NOT NULL,
dataFim DATE CONSTRAINT nnPlantacaoDataFim NOT NULL,
area NUMBER CONSTRAINT nnPlantacaoArea NOT NULL,
CONSTRAINT ckPlantacaoArea CHECK (area > 0),
CONSTRAINT ckPlantacaoDataInicioDataFim CHECK (dataInicio <= dataFim)
);

-- Tabela colheita
CREATE TABLE colheita (
codColheita INTEGER CONSTRAINT pkColheitaCodColheita PRIMARY KEY,
codPlantacao INTEGER 
    CONSTRAINT fkColheitaCodPlantacao references plantacao (codPlantacao)
    CONSTRAINT nnColheitaCodPlantacao NOT NULL,
codProduto INTEGER
    CONSTRAINT fkColheitaCodProduto REFERENCES produto (codProduto)
    CONSTRAINT nnColheitaCodProduto NOT NULL,
dataColheita DATE CONSTRAINT nnColheitaDataColheita NOT NULL,
quantidade INTEGER CONSTRAINT nnColheitaQuantidade NOT NULL,
CONSTRAINT ckColheitaQuantidade CHECK (quantidade > 0)
);

-- Tabela formaAplicacao
CREATE TABLE formaAplicacao (
codFormaAplicacao INTEGER CONSTRAINT pkFormaAplicacaoCodFormaAplicacao PRIMARY KEY,
descricao VARCHAR2(100)
    CONSTRAINT nnFormaAplicacaoDescricao NOT NULL
    CONSTRAINT ukFormaAplicacaoDescricao UNIQUE
);

-- Tabela operacaoAgricola
CREATE TABLE operacaoAgricola (
codOperacaoAgricola INTEGER CONSTRAINT pkOperacaoAgricolaCodOperacaoAgricola PRIMARY KEY,
codPlantacao INTEGER 
    CONSTRAINT fkOperacaoAgricolaCodPlantacao references plantacao (codPlantacao)
    CONSTRAINT nnOperacaoAgricolaCodPlantacao NOT NULL,
codFatorProducao INTEGER 
    CONSTRAINT fkOperacaoAgricolaCodFatorProducao references fatorProducao (codFatorProducao)
    CONSTRAINT nnOperacaoAgricolaCodFatorProducao NOT NULL,
codFormaAplicacao INTEGER
    CONSTRAINT fkOperacaoAgricolaCodFormaAplicacao references formaAplicacao (codFormaAplicacao)
    CONSTRAINT nnOperacaoAgricolaCodFormaAplicacao NOT NULL,
quantidade NUMBER
    CONSTRAINT nnOperacaoAgricolaQuantidade NOT NULL,
dataOperacaoAgricola DATE CONSTRAINT nnOperacaoAgricolaData NOT NULL,
cancelada CHAR(1) DEFAULT 0
    CONSTRAINT nnOperacaoAgricolaCancelada NOT NULL,
CONSTRAINT ckOperacaoAgricolaQuantidade CHECK (quantidade > 0),
CONSTRAINT ckOperacaoAgricolaCancelada CHECK (cancelada = 0 OR cancelada = 1)
);

-- Tabela processoLeituraSensor
CREATE TABLE processoLeituraSensor (
codProcessoLeituraSensor INTEGER CONSTRAINT pkProcessoLeituraSensorCodProcessoLeituraSensor PRIMARY KEY,
dataExecuçãoProcesso DATE DEFAULT SYSDATE
    CONSTRAINT nnProcessoLeituraSensorDataExecuçãoProcesso NOT NULL,
numTotalRegistosLidos INTEGER DEFAULT 0
    CONSTRAINT nnProcessoLeituraSensorNumTotalRegistosLidos NOT NULL,
numRegistosTransferidos INTEGER DEFAULT 0
    CONSTRAINT nnProcessoLeituraSensorNumRegistosTransferidos NOT NULL,
numRegistosNãoTransferidos INTEGER DEFAULT 0
    CONSTRAINT nnProcessoLeituraSensorNumRegistosNãoTransferidos NOT NULL,
CONSTRAINT ckProcessoLeituraSensorNumTotalRegistosLidos CHECK (numTotalRegistosLidos >= 0),
CONSTRAINT ckProcessoLeituraSensorNumRegistosTransferidos CHECK (numRegistosTransferidos >= 0),
CONSTRAINT ckProcessoLeituraSensorNumRegistosNãoTransferidos CHECK (numRegistosNãoTransferidos >= 0),
CONSTRAINT ckProcessoLeituraSensorNumTotalRegistosLidosNumRegistosTransferidosNumRegistosNãoTransferidos CHECK (numRegistosTransferidos + numRegistosNãoTransferidos = numTotalRegistosLidos)
);

-- Tabela registoProcessoLeituraSensor
CREATE TABLE registoProcessoLeituraSensor (
codRegistoProcessoLeituraSensor INTEGER CONSTRAINT pkRegistoProcessoLeituraSensorRegistoProcessoLeituraSensor PRIMARY KEY,
codProcessoLeituraSensor INTEGER
    CONSTRAINT fkRegistoProcessoLeituraSensorCodProcessoLeituraSensor REFERENCES processoLeituraSensor (codProcessoLeituraSensor)
    CONSTRAINT nnRegistoProcessoLeituraSensorCodProcessoLeituraSensor NOT NULL,
codSensor CHAR(5)
    CONSTRAINT fkRegistoProcessoLeituraSensorCodSensor references sensor (codSensor)
    CONSTRAINT nnRegistoProcessoLeituraSensorCodSensor NOT NULL,
numErrosIdentificados INTEGER DEFAULT 0
    CONSTRAINT nnRegistoProcessoLeituraSensorNumErrosIdentificados NOT NULL,
CONSTRAINT ckRegistoProcessoLeituraSensorNumeroErrosIdentificados CHECK (numErrosIdentificados >= 0)
);

-- Tabela restricao
CREATE TABLE restricao (
codRestricao INTEGER CONSTRAINT pkRestricaoCodRestricao PRIMARY KEY,
codParcela INTEGER
    CONSTRAINT fkRestricaoCodParcela REFERENCES parcela (codParcela)
    CONSTRAINT nnRestricaoCodParcela NOT NULL,
codFatorProducao INTEGER
    CONSTRAINT fkRestricaoCodFatorProducao REFERENCES fatorProducao (codFatorProducao)
    CONSTRAINT nnRestricaoCodFatorProducao NOT NULL,
dataInicio DATE
    CONSTRAINT nnRestricaoDataInicio NOT NULL,
dataFim DATE
    CONSTRAINT nnRestricaoDataFim NOT NULL,
CONSTRAINT ckRestricaoDataInicioDataFim CHECK (dataInicio <= dataFim)
);

-- Tabela tipoAlteracao
CREATE TABLE tipoAlteracao (
codTipoAlteracao INTEGER CONSTRAINT pkTipoAlteracaoCodTipoAlteracao PRIMARY KEY,
descricao VARCHAR2(50)
    CONSTRAINT nnTipoAlteracaoDescricao NOT NULL
    CONSTRAINT ukTipoAlteracaoDescricao UNIQUE
);

-- Tabela pistaAuditoriaOperacao
CREATE TABLE pistaAuditoriaOperacao (
codPistaAuditoriaOperacao INTEGER CONSTRAINT pkPistaAuditoriaOperacaoCodPistaAuditoriaOperacao PRIMARY KEY,
codOperacaoAgricola INTEGER
    CONSTRAINT fkPistaAuditoriaOperacaoCodOperacaoAgricola REFERENCES operacaoAgricola (codOperacaoAgricola)
    CONSTRAINT nnPistaAuditoriaOperacaoCodOperacaoAgricola NOT NULL,
codTipoAlteracao INTEGER
    CONSTRAINT fkPistaAuditoriaOperacaoCodTipoAlteracao REFERENCES tipoAlteracao (codTipoAlteracao)
    CONSTRAINT nnPistaAuditoriaOperacaoCodTipoAlteracao NOT NULL,
codUtilizador NUMBER CONSTRAINT nnPistaAuditoriaOperacaoCodUtilizador NOT NULL,
dataAlteracao DATE DEFAULT SYSDATE CONSTRAINT nnPistaAuditoriaOperacaoDataAlteracao NOT NULL
);


-- Tabela intermédia necessária para a US212
CREATE TABLE inputSensor (
inputString VARCHAR(25)
);

-- Tabela intermédia necessária para a US215
CREATE TABLE inputHub (
inputString VARCHAR(25)
);