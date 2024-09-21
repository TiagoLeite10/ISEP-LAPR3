-- ** Procedimento para inserir uma nova unidade **
CREATE OR REPLACE PROCEDURE prcUS208CriarNovaUnidade(
    novoCodUnidade IN unidade.codUnidade%TYPE,
    novaDescricaoUnidade IN unidade.descricao%TYPE
) IS
BEGIN
    INSERT INTO unidade (codUnidade, descricao) 
    VALUES (novoCodUnidade, novaDescricaoUnidade);
END;

-- Bloco anónimo para testar o procedimento insere uma nova unidade
DECLARE
    novoCodUnidade unidade.codUnidade%TYPE;
    novaDescricaoUnidade unidade.descricao%TYPE;
BEGIN
    novoCodUnidade := 1000;
    novaDescricaoUnidade := 'Teste nova unidade';
    prcUS208CriarNovaUnidade(novoCodUnidade, novaDescricaoUnidade);
END;


-- ** Procedimento para inserir um novo tipo de composição de uma ficha técnica  **
CREATE OR REPLACE PROCEDURE prcUS208CriarNovaFichaTecnicaTipoComposicao(
    novoCodFichaTecnicaTipoComposicao IN fichaTecnicaTipoComposicao.codFichaTecnicaTipoComposicao%TYPE,
    codUnidade IN unidade.codUnidade%TYPE,
    novoNome IN fichaTecnicaTipoComposicao.nome%TYPE
) IS
BEGIN
    INSERT INTO fichaTecnicaTipoComposicao (codFichaTecnicaTipoComposicao, codUnidade, nome) 
    VALUES (novoCodFichaTecnicaTipoComposicao, codUnidade, novoNome);
END;

-- Bloco anónimo para testar o procedimento que insere um novo tipo de composição de uma ficha técnica
DECLARE
    novoCodFichaTecnicaTipoComposicao fichaTecnicaTipoComposicao.codFichaTecnicaTipoComposicao%TYPE;
    codUnidade unidade.codUnidade%TYPE;
    novoNome fichaTecnicaTipoComposicao.nome%TYPE;
BEGIN
    novoCodFichaTecnicaTipoComposicao := 1000;
    codUnidade := 1;
    novoNome := 'Teste insert nova ficha tecnica tipo composicao';
    prcUS208CriarNovaFichaTecnicaTipoComposicao(novoCodFichaTecnicaTipoComposicao, codUnidade, novoNome);
END;

-- ** Procedimento para inserir uma nova aplicação de fator de producao  **
/*CREATE OR REPLACE PROCEDURE prcUS208CriarNovaAplicacaoFatorProducao(
    novoCodFatorProducaoAplicacao IN fatorProducaoAplicacao.codFatorProducaoAplicacao%TYPE,
    novaDescricao IN fatorProducaoAplicacao.descricao%TYPE
) IS
BEGIN
    INSERT INTO fatorProducaoAplicacao (codFatorProducaoAplicacao, descricao) 
    VALUES (novoCodFatorProducaoAplicacao, novaDescricao);
END;

-- Bloco anónimo para testar o procedimento que insere uma nova aplicação de fator de produção
DECLARE
    novoCodFatorProducaoAplicacao fatorProducaoAplicacao.codFatorProducaoAplicacao%TYPE;
    novaDescricao fatorProducaoAplicacao.descricao%TYPE;
BEGIN
    novoCodFatorProducaoAplicacao := 1000;
    novaDescricao := 'Novo fator de producao aplicação';
    prcUS208CriarNovaAplicacaoFatorProducao(novoCodFatorProducaoAplicacao, novaDescricao);
END;*/


-- ** Procedimento para inserir uma nova classificação do fator de produção **
CREATE OR REPLACE PROCEDURE prcUS208CriarNovaClassificacaoFatorProducao(
    novoCodFatorProducaoClassificacao IN fatorProducaoClassificacao.codFatorProducaoClassificacao%TYPE,
    novaDescricao IN fatorProducaoClassificacao.descricao%TYPE
) IS
BEGIN
    INSERT INTO fatorProducaoClassificacao (codFatorProducaoClassificacao, descricao) 
    VALUES (novoCodFatorProducaoClassificacao, novaDescricao);
END;

-- Bloco anónimo para testar o procedimento que insere uma nova classificação de fator de produção
DECLARE
    novoCodFatorProducaoClassificacao fatorProducaoClassificacao.codFatorProducaoClassificacao%TYPE;
    novaDescricao fatorProducaoClassificacao.descricao%TYPE;
BEGIN
    novoCodFatorProducaoClassificacao := 1000;
    novaDescricao := 'Nova classificação do fator de producao';
    prcUS208CriarNovaClassificacaoFatorProducao(novoCodFatorProducaoClassificacao, novaDescricao);
END;


-- ** Procedimento para inserir uma nova formulação do produto para o fator de produção **
CREATE OR REPLACE PROCEDURE prcUS208CriarNovaFormulacaoProduto(
    novoCodFormulacaoProduto IN formulacaoProduto.codFormulacaoProduto%TYPE,
    novaDescricao IN formulacaoProduto.descricao%TYPE
) IS
BEGIN
    INSERT INTO formulacaoProduto (codFormulacaoProduto, descricao) 
    VALUES (novoCodFormulacaoProduto, novaDescricao);
END;

-- Bloco anónimo para testar o procedimento que insere uma nova formulação do produto para o fator de produção
DECLARE
    novoCodFormulacaoProduto formulacaoProduto.codFormulacaoProduto%TYPE;
    novaDescricao formulacaoProduto.descricao%TYPE;
BEGIN
    novoCodFormulacaoProduto := 1000;
    novaDescricao := 'Nova formulação de produto para o fator de producao';
    prcUS208CriarNovaFormulacaoProduto(novoCodFormulacaoProduto, novaDescricao);
END;

-- ** Procedimento para inserir um novo fator de produção **
CREATE OR REPLACE PROCEDURE prcUS208CriarNovoFatorProducao(
    novoCodFatorProducao IN fatorProducao.codFatorProducao%TYPE,
    codFatorProducaoAplicacao IN fatorProducao.codFatorProducaoAplicacao%TYPE,
    codFatorProducaoClassificacao IN fatorProducao.codFatorProducaoClassificacao%TYPE,
    codFormulacaoProduto IN fatorProducao.codFormulacaoProduto%TYPE,
    codFornecedor IN fatorProducao.codFornecedor%TYPE,
    novoNomeComercial IN fatorProducao.nomeComercial%TYPE
) IS
BEGIN
    INSERT INTO fatorProducao (codFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, nomeComercial) 
    VALUES (novoCodFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, novoNomeComercial);
END;

-- Bloco anónimo para testar o procedimento que insere um novo fator de produção
DECLARE
    novoCodFatorProducao fatorProducao.codFatorProducao%TYPE;
    codFatorProducaoAplicacao fatorProducao.codFatorProducaoAplicacao%TYPE;
    codFatorProducaoClassificacao fatorProducao.codFatorProducaoClassificacao%TYPE;
    codFormulacaoProduto fatorProducao.codFormulacaoProduto%TYPE;
    codFornecedor fatorProducao.codFornecedor%TYPE;
    novoNomeComercial fatorProducao.nomeComercial%TYPE;
BEGIN
    novoCodFatorProducao := 1000;
    codFatorProducaoAplicacao := 1;
    codFatorProducaoClassificacao := 1;
    codFormulacaoProduto := 1;
    codFornecedor := 1;
    novoNomeComercial := 'Novo fator de producao teste';
    prcUS208CriarNovoFatorProducao(novoCodFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, novoNomeComercial);
END;


-- ** Procedimento para inserir uma nova linha na ficha tecnica **
CREATE OR REPLACE PROCEDURE prcUS208CriarNovaLinhaFichaTecnica(
    codFatorProducao IN linhaFichaTecnica.codFatorProducao%TYPE,
    codFichaTecnicaTipoComposicao IN linhaFichaTecnica.codFichaTecnicaTipoComposicao%TYPE,
    novaQuantidade IN linhaFichaTecnica.quantidade%TYPE
) IS
BEGIN
    INSERT INTO linhaFichaTecnica (codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) 
    VALUES (codFatorProducao, codFichaTecnicaTipoComposicao, novaQuantidade);
END;

-- Bloco anónimo para testar o procedimento que insere uma nova linha na ficha tecnica
DECLARE
    codFatorProducao linhaFichaTecnica.codFatorProducao%TYPE;
    codFichaTecnicaTipoComposicao linhaFichaTecnica.codFichaTecnicaTipoComposicao%TYPE;
    novaQuantidade linhaFichaTecnica.quantidade%TYPE;
BEGIN
    codFatorProducao := 1;
    codFichaTecnicaTipoComposicao := 4;
    novaQuantidade := 20;
    prcUS208CriarNovaLinhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, novaQuantidade);
END;