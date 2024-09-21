-- Procedimento para registar a entrega de uma encomenda numa determinada data.
CREATE OR REPLACE PROCEDURE prcUS209RegistarDataEntregaEncomenda(pCodEncomenda IN encomenda.codEncomenda%TYPE,
                                                          pDataEntrega IN encomenda.dataEntrega%TYPE) AS
    dataRegistadaEncomenda encomenda.dataEncomenda%TYPE;
    codEstado estadoEncomenda.codEstadoEncomenda%TYPE;
    numEncomendas INTEGER;
    encomendaInexistente EXCEPTION;
    estadoEncomendaInexistente EXCEPTION;
    dataEntregaInvalida EXCEPTION;
BEGIN
    SELECT COUNT(*) INTO numEncomendas FROM encomenda WHERE codEncomenda = pCodEncomenda;

    IF numEncomendas = 0 THEN
        RAISE encomendaInexistente;
    ELSE
        SELECT dataEncomenda INTO dataRegistadaEncomenda FROM encomenda WHERE codEncomenda = pCodEncomenda;

        IF dataRegistadaEncomenda > pDataEntrega THEN
            RAISE dataEntregaInvalida;
        ELSE
            SELECT codEstadoEncomenda INTO codEstado FROM estadoEncomenda WHERE descricao = 'Entregue';

            IF codEstado IS NULL THEN
                RAISE estadoEncomendaInexistente;
            ELSE
                UPDATE encomenda SET dataEntrega = pDataEntrega, codEstadoEncomenda = codEstado WHERE codEncomenda = pCodEncomenda;
                dbms_output.put_line('Registada com sucesso a data de entrega ' ||  pDataEntrega || ' na encomenda com o id ' || pCodEncomenda || '.');
            END IF;
        END IF;
    END IF;

    EXCEPTION
        WHEN encomendaInexistente THEN
            dbms_output.put_line('Não existe nenhuma encomenda com o id ' || pCodEncomenda || '.');
        WHEN dataEntregaInvalida THEN
            dbms_output.put_line('A data da encomenda não pode ser superior à data de entrega.');
        WHEN estadoEncomendaInexistente THEN
            dbms_output.put_line('Não foi possível atualizar o estado da encomenda com o id ' || pCodEncomenda || '.');
END;

-- Bloco anónimo para registar a entrega de uma encomenda existente numa determinada data.
DECLARE
    codEncomenda encomenda.codEncomenda%TYPE;
    dataEntrega encomenda.dataEntrega%TYPE;
BEGIN
    codEncomenda := 5;
    dataEntrega := TO_DATE('15/12/2022', 'dd/mm/yy');

    prcUS209RegistarDataEntregaEncomenda(codEncomenda, dataEntrega);
END;

-- Bloco anónimo para registar a entrega de uma encomenda inexistente numa determinada data.
DECLARE
    codEncomenda encomenda.codEncomenda%TYPE;
    dataEntrega encomenda.dataEntrega%TYPE;
BEGIN
    codEncomenda := 25;
    dataEntrega := TO_DATE('15/12/2022', 'dd/mm/yy');

    prcUS209RegistarDataEntregaEncomenda(codEncomenda, dataEntrega);
END;

-- Bloco anónimo para registar a entrega de uma encomenda existente numa data inferior à data da encomenda.
DECLARE
    codEncomenda encomenda.codEncomenda%TYPE;
    dataEntrega encomenda.dataEntrega%TYPE;
BEGIN
    codEncomenda := 5;
    dataEntrega := TO_DATE('15/2/2022', 'dd/mm/yy');

    prcUS209RegistarDataEntregaEncomenda(codEncomenda, dataEntrega);
END;

------------------------------------------------------------------------------------------------------------------------
-- Procedimento para registar o pagamento de uma encomenda numa determinada data.
CREATE OR REPLACE PROCEDURE prcUS209RegistarDataPagamentoEncomenda(pCodEncomenda IN encomenda.codEncomenda%TYPE,
                                                          pDataPagamento IN encomenda.dataPagamento%TYPE) AS
    dataRegistadaEncomenda encomenda.dataEncomenda%TYPE;
    codEstado estadoEncomenda.codEstadoEncomenda%TYPE;
    numEncomendas INTEGER;
    encomendaInexistente EXCEPTION;
    estadoEncomendaInexistente EXCEPTION;
    dataPagamentoInvalida EXCEPTION;
BEGIN
    SELECT COUNT(*) INTO numEncomendas FROM encomenda WHERE codEncomenda = pCodEncomenda;

    IF numEncomendas = 0 THEN
        RAISE encomendaInexistente;
    ELSE
        SELECT dataEncomenda INTO dataRegistadaEncomenda FROM encomenda WHERE codEncomenda = pCodEncomenda;

        IF dataRegistadaEncomenda > pDataPagamento THEN
            RAISE dataPagamentoInvalida;
        ELSE
            SELECT codEstadoEncomenda INTO codEstado FROM estadoEncomenda WHERE descricao = 'Paga';

            IF codEstado IS NULL THEN
                RAISE estadoEncomendaInexistente;
            ELSE
                UPDATE encomenda SET dataPagamento = pDataPagamento, codEstadoEncomenda = codEstado WHERE codEncomenda = pCodEncomenda;
                dbms_output.put_line('Registada com sucesso a data de pagamento ' ||  pDataPagamento || ' na encomenda com o id ' || pCodEncomenda || '.');
            END IF;
        END IF;
    END IF;

    EXCEPTION
        WHEN encomendaInexistente THEN
            dbms_output.put_line('Não existe nenhuma encomenda com o id ' || pCodEncomenda || '.');
        WHEN dataPagamentoInvalida THEN
            dbms_output.put_line('A data da encomenda não pode ser superior à data de pagamento.');
        WHEN estadoEncomendaInexistente THEN
            dbms_output.put_line('Não foi possível atualizar o estado da encomenda com o id ' || pCodEncomenda || '.');
END;

-- Bloco anónimo para registar o pagamento de uma encomenda existente numa determinada data.
DECLARE
    codEncomenda encomenda.codEncomenda%TYPE;
    dataPagamento encomenda.dataPagamento%TYPE;
BEGIN
    codEncomenda := 5;
    dataPagamento := TO_DATE('12/12/2022', 'dd/mm/yy');

    prcUS209RegistarDataPagamentoEncomenda(codEncomenda, dataPagamento);
END;

-- Bloco anónimo para registar o pagamento de uma encomenda inexistente numa determinada data.
DECLARE
    codEncomenda encomenda.codEncomenda%TYPE;
    dataPagamento encomenda.dataPagamento%TYPE;
BEGIN
    codEncomenda := 25;
    dataPagamento := TO_DATE('12/12/2022', 'dd/mm/yy');

    prcUS209RegistarDataPagamentoEncomenda(codEncomenda, dataPagamento);
END;

-- Bloco anónimo para registar o pagamento de uma encomenda existente numa data inferior à data da encomenda.
DECLARE
    codEncomenda encomenda.codEncomenda%TYPE;
    dataPagamento encomenda.dataPagamento%TYPE;
BEGIN
    codEncomenda := 5;
    dataPagamento := TO_DATE('12/2/2022', 'dd/mm/yy');

    prcUS209RegistarDataPagamentoEncomenda(codEncomenda, dataPagamento);
END;

------------------------------------------------------------------------------------------------------------------------
-- Função para registar a encomenda de um determinado cliente.
CREATE OR REPLACE FUNCTION fncUS209CriacaoEncomenda(
                                                pCodInternoCliente IN encomenda.codInternoCliente%TYPE,
                                                pCodHub IN encomenda.codHub%TYPE,
                                                pDataEncomenda IN encomenda.dataEncomenda%TYPE,
                                                pDataVencimento IN encomenda.dataVencimento%TYPE)
                                                RETURN encomenda.codEncomenda%TYPE AS

    codEstado estadoEncomenda.codEstadoEncomenda%TYPE;
    cod INTEGER;
    numCliente INTEGER;
    numCodHub INTEGER;
    clienteNaoExistir EXCEPTION;
    estadoEncomendaInexistente EXCEPTION;
    codHubNaoExistir EXCEPTION;

BEGIN
    SELECT COUNT(*) INTO numCliente FROM cliente WHERE codInternoCliente = pCodInternoCliente;
    IF  numCliente = 0 THEN
            RAISE clienteNaoExistir;
    ElSE
        SELECT codEstadoEncomenda INTO codEstado FROM estadoEncomenda WHERE descricao = 'Registada';
        IF codEstado IS NULL THEN
            RAISE estadoEncomendaInexistente;
        ELSE
            SELECT COUNT(*) INTO numCodHub FROM hub WHERE codHub = pCodHub;
            IF  numCodHub = 0 THEN
                        RAISE codHubNaoExistir;
            ELSE
                cod := 0;
                SELECT MAX(codEncomenda)
                INTO cod
                FROM encomenda;
                cod := cod + 1;

                INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda,  codHub, dataEncomenda, dataVencimento)
                VALUES (cod, pCodInternoCliente, codEstado, pCodHub, pDataEncomenda, pDataVencimento);

                RETURN cod;
            END IF;
        END IF;
    END IF;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            dbms_output.put_line('Não foi possível criar a encomenda!');
            RETURN NULL;
        WHEN clienteNaoExistir THEN
            dbms_output.put_line('Não existe nenhum cliente com o código interno!');
            RETURN NULL;
        WHEN estadoEncomendaInexistente THEN
            dbms_output.put_line('Não foi possível criar a encomenda devido ao estado da encomenda inexistente!');
            RETURN NULL;
        WHEN codHubNaoExistir THEN
            dbms_output.put_line('O hub indicado não existe!');
            RETURN NULL;
END;


-- Procedimento para registar produtos em uma encomenda
CREATE OR REPLACE PROCEDURE prcUS209RegistarProdutoNaEncomenda(
                                                                pCodEncomenda IN encomenda.codEncomenda%TYPE,
                                                                pCodProduto IN registoEncomenda.codProduto%TYPE,
                                                                pQuantidade IN registoEncomenda.quantidade%TYPE) AS

    custoProduto registoEncomenda.valorProduto%TYPE;
    ivaProduto iva.percentagemIva%TYPE;
    plafondExistente cliente.plafond%TYPE;
    valorEncomenda registoEncomenda.valorProduto%TYPE;
    codCliente encomenda.codInternoCliente%TYPE;
    numEncomendas INTEGER;
    numCodProduto INTEGER;
    codProdutoNaoExistir EXCEPTION;
    encomendaInexistente EXCEPTION;
    limitePlafond EXCEPTION;

BEGIN

    SELECT COUNT(*) INTO numEncomendas FROM encomenda WHERE codEncomenda = pCodEncomenda;

    IF  numEncomendas = 0 THEN
            RAISE encomendaInexistente;
    ELSE
        SELECT COUNT(*) INTO numCodProduto FROM produto WHERE codProduto = pCodProduto;
         IF numCodProduto = 0 THEN
            RAISE codProdutoNaoExistir;
         ELSE
            SELECT p.valorProduto, i.percentagemIva
            INTO custoProduto, ivaProduto
            FROM produto p INNER JOIN iva i ON i.codIva = p.codIva
            WHERE codProduto = pCodProduto;

            SELECT codInternoCliente
            INTO codCliente
            FROM encomenda
            WHERE codEncomenda = pCodEncomenda;

            SELECT plafond
            INTO plafondExistente
            FROM cliente
            WHERE codInternoCliente = codCliente;

            SELECT SUM(r.valorProduto * r.quantidade)
            INTO valorEncomenda
            FROM registoEncomenda r INNER JOIN encomenda e ON e.codEncomenda = r.codEncomenda
            WHERE e.codInternoCliente = codCliente AND dataPagamento IS NULL;
            IF valorEncomenda IS NULL THEN
              valorEncomenda := 0;
            END IF;

            valorEncomenda := valorEncomenda + (custoProduto * pQuantidade);

            IF plafondExistente > valorEncomenda THEN
                    INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade,  valorProduto, percentagemIva)
                    VALUES (pCodProduto, pCodEncomenda, pQuantidade, custoProduto, ivaProduto);
                    dbms_output.put_line('O produto com o id - ' || pcodProduto || ' foi adicionado com sucesso a encomenda - '|| pCodEncomenda || '.');

                    plafondExistente := plafondExistente - valorEncomenda;
                    dbms_output.put_line('Ainda resta '|| plafondExistente || '€ ao plafond do cliente.');
            ELSE
                    RAISE limitePlafond;
            END IF;
        END IF;
    END IF;

        EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
                dbms_output.put_line('Não foi possivel adicionar o produto na encomenda com o id ' || pCodEncomenda || '.');
            WHEN encomendaInexistente THEN
                dbms_output.put_line('Não existe nenhuma encomenda com o id ' || pCodEncomenda || '.');
            WHEN limitePlafond THEN
                dbms_output.put_line('O cliente excedeu o plafond.');
            WHEN codProdutoNaoExistir THEN
                dbms_output.put_line('Não existe o produto.');

END;

-- Bloco anónimo para o testar a função de registar uma encomenda e o procedimento de registar produtos
DECLARE
    pCodInternoCliente encomenda.codInternoCliente%TYPE;
    pCodHub encomenda.codHub%TYPE;
    pDataEncomenda  encomenda.dataEncomenda%TYPE;
    pDataVencimento encomenda.dataVencimento%TYPE;
    resultado encomenda.codEncomenda%TYPE;
    pCodProduto registoEncomenda.codProduto%TYPE;
    pQuantidade registoEncomenda.quantidade%TYPE;
        BEGIN
        pCodInternoCliente := 1;
        pCodHub := 'CT100';
        pDataEncomenda:= TO_DATE('15/12/2022', 'dd/mm/yy');
        pDataVencimento:= TO_DATE('15/12/2022', 'dd/mm/yy');

        resultado := fncUS209CriacaoEncomenda(pCodInternoCliente, pCodHub, pDataEncomenda, pDataVencimento);
        IF resultado IS NOT NULL THEN

            pCodProduto := 1;
            pQuantidade := 5;

            prcUS209RegistarProdutoNaEncomenda(resultado, pCodProduto, pQuantidade);

            pCodProduto := 2;
            pQuantidade := 1;

            prcUS209RegistarProdutoNaEncomenda(resultado, pCodProduto, pQuantidade);
    END IF;
END;

------------------------------------------------------------------------------------------------------------------------
-- Procedimento para listar encomendas por estado (registada, entregue, paga).
CREATE OR REPLACE PROCEDURE prcUS209ListarEncomendaPorEstado(pDescricaoEstadoEncomenda IN estadoEncomenda.descricao%TYPE) IS
    CURSOR curEncomenda IS
        SELECT e.codEncomenda, e.dataEncomenda, e.codInternoCliente, SUM(r.valorProduto * r.quantidade), ee.descricao
        FROM encomenda e
        INNER JOIN registoEncomenda r ON r.codEncomenda = e.codEncomenda
        INNER JOIN estadoEncomenda ee ON ee.codEstadoEncomenda = e.codEstadoEncomenda
        WHERE UPPER(ee.descricao) = UPPER(pDescricaoEstadoEncomenda)
        GROUP BY e.codEncomenda, e.dataEncomenda, e.codInternoCliente, ee.descricao
        ORDER BY e.codEncomenda;

    dataRegistoEncomenda encomenda.dataEncomenda%TYPE;
    cliente encomenda.codInternoCliente%TYPE;
    numeroEncomenda encomenda.codEncomenda%TYPE;
    valorTotal NUMBER;
    estado estadoEncomenda.descricao%TYPE;
    encomendasInexistente EXCEPTION;
BEGIN
    OPEN curEncomenda;
    FETCH curEncomenda INTO numeroEncomenda, dataRegistoEncomenda, cliente, valorTotal, estado;
    IF curEncomenda%notfound THEN
        RAISE encomendasInexistente;
    ELSE
        LOOP
            EXIT WHEN curEncomenda%notfound;

            dbms_output.put_line('*** Código da Encomenda ' || numeroEncomenda || ' ***');
            dbms_output.put_line('Data de registo da encomenda: ' || dataRegistoEncomenda);
            dbms_output.put_line('Código interno do cliente: ' || cliente);
            dbms_output.put_line('Valor total: ' || ROUND(valorTotal, 2) || '€');
            dbms_output.put_line('Estado: ' || estado);
            dbms_output.put_line('                  ');
            FETCH curEncomenda INTO numeroEncomenda, dataRegistoEncomenda, cliente, valorTotal, estado;
        END LOOP;
    END IF;
    CLOSE curEncomenda;

    EXCEPTION
        WHEN encomendasInexistente THEN
            dbms_output.put_line('Não existem encomenda com o estado pretendido!');
END;

-- Bloco anónimo para testar o procedimento para listar encomendas com o estado 'Registada'.
DECLARE
    descricaoEstadoEncomenda estadoEncomenda.descricao%TYPE;
BEGIN
    descricaoEstadoEncomenda := 'Registada';
    prcUS209ListarEncomendaPorEstado(descricaoEstadoEncomenda);
END;

-- Bloco anónimo para testar o procedimento para listar encomendas com o estado 'Paga'.
DECLARE
    descricaoEstadoEncomenda estadoEncomenda.descricao%TYPE;
BEGIN
    descricaoEstadoEncomenda := 'Paga';
    prcUS209ListarEncomendaPorEstado(descricaoEstadoEncomenda);
END;

-- Bloco anónimo para testar o procedimento para listar encomendas com o estado 'Entregue'.
DECLARE
    descricaoEstadoEncomenda estadoEncomenda.descricao%TYPE;
BEGIN
    descricaoEstadoEncomenda := 'Entregue';
    prcUS209ListarEncomendaPorEstado(descricaoEstadoEncomenda);
END;

-- Bloco anónimo para testar o procedimento para listar encomendas com um estado inexistente.
DECLARE
    descricaoEstadoEncomenda estadoEncomenda.descricao%TYPE;
BEGIN
    descricaoEstadoEncomenda := 'Indefinida';
    prcUS209ListarEncomendaPorEstado(descricaoEstadoEncomenda);
END;