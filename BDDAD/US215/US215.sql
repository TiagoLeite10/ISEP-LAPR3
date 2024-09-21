-- Procedimento para atualizar a tabela hub a partir da tabela inputHub.
CREATE OR REPLACE PROCEDURE prcUS215AtualizarTabelaHub IS
    CURSOR curInputHub IS
        SELECT REGEXP_SUBSTR(inputString, '[^;]+', 1, 1),
               TO_NUMBER(REGEXP_SUBSTR(inputString, '[^;]+', 1, 2)),
               TO_NUMBER(REGEXP_SUBSTR(inputString, '[^;]+', 1, 3)),
               SUBSTR(REGEXP_SUBSTR(inputString, '[^;]+', 1, 4), 1, 1)
        FROM inputHub;

    vCodHub hub.codHub%TYPE;
    vCodTipoHub hub.codTipoHub%TYPE;
    vLatitude hub.latitude%TYPE;
    vLongitude hub.longitude%TYPE;
    vTipoHub CHAR(1);
    vNumHubs INTEGER;
BEGIN
    OPEN curInputHub;
    LOOP
        FETCH curInputHub INTO vCodHub, vLatitude, vLongitude, vTipoHub;
        EXIT WHEN curInputHub%NOTFOUND;

        -- Ignorar dados que não correspondem a hubs
        IF (vTipoHub NOT LIKE 'C') THEN
            SELECT COUNT(*) INTO vNumHubs FROM hub WHERE codHub LIKE vCodHub;

            -- Verifica se o hub é inexistente
            IF (vNumHubs = 0) THEN
                -- Procurar código do tipo de hub
                SELECT codTipoHub INTO vCodTipoHub FROM tipoHub WHERE acronimo LIKE UPPER(vTipoHub);

                -- Inserir dados na tabela hub
                INSERT INTO hub(codHub, codTipoHub, latitude, longitude)
                VALUES (vCodHub, vCodTipoHub, vLatitude, vLongitude);

                dbms_output.put_line('Hub com o código ' || vCodHub || ', latitude ' || vLatitude || ' e longitude ' || vLongitude || ' inserido com sucesso!');

                -- Eliminar registo da tabela inputHub
                DELETE FROM inputHub WHERE inputString LIKE vCodHub || '%';
            END IF;
        END IF;
    END LOOP;
    CLOSE curInputHub;

    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line('Não foi possível atualizar a tabela hub a partir da tabela inputHub, devido a esta possuir registos inválidos!');
END;

-- Chamada para testar o procedimento que atualiza a tabela hub a partir da tabela inputHub.
CALL prcUS215AtualizarTabelaHub();

------------------------------------------------------------------------------------------------------------------------
-- Procedimento para atribuir e alterar o hub por defeito de um cliente.
CREATE OR REPLACE PROCEDURE prcUS215AtribuirHubCliente (pCodInternoCliente IN cliente.codInternoCliente%TYPE,
                                                        pCodHub IN hub.codHub%TYPE) IS
    numClientes INTEGER;
    numHubs INTEGER;
    clienteInexistente EXCEPTION;
    hubInexistente EXCEPTION;
BEGIN
    SELECT COUNT (*) INTO numClientes FROM cliente WHERE codInternoCliente = pCodInternoCliente;

    IF numClientes = 0 THEN
             RAISE clienteInexistente;
    ELSE
        SELECT COUNT (*) INTO numHubs FROM hub WHERE codHub = pCodHub;

        IF numHubs = 0 THEN
            RAISE hubInexistente;
        ELSE
            UPDATE cliente SET codHub = pCodHub WHERE codInternoCliente = pCodInternoCliente;
            dbms_output.put_line('O hub por defeito do cliente: ' || pCodInternoCliente || ' foi atribuído com sucesso.');
        END IF;
    END IF;

    EXCEPTION
        WHEN clienteInexistente THEN
            dbms_output.put_line('O cliente indicado com o código: ' || pCodInternoCliente || ' não existe.');
        WHEN hubInexistente THEN
            dbms_output.put_line('O hub indicado com o código: ' || pCodHub || ' não existe.');
END;

-- Bloco anónimo para testar o procedimento que vai atribuir e alterar o hub por defeito de um cliente.
DECLARE
    pCodInternoCliente cliente.codInternoCliente%TYPE;
    pCodHub  hub.codHub%TYPE;
BEGIN
    pCodInternoCliente := 4;
    pCodHub := 'CT4';

    prcUS215AtribuirHubCliente(pCodInternoCliente, pCodHub);
END;

------------------------------------------------------------------------------------------------------------------------
-- Função para registar a encomenda de um determinado cliente.
CREATE OR REPLACE FUNCTION fncUS215CriacaoEncomenda(pCodInternoCliente IN encomenda.codInternoCliente%TYPE,
                                                    pCodHub IN encomenda.codHub%TYPE,
                                                    pDataEncomenda IN encomenda.dataEncomenda%TYPE,
                                                    pDataVencimento IN encomenda.dataVencimento%TYPE)
                                                    RETURN encomenda.codEncomenda%TYPE IS

    codEstado estadoEncomenda.codEstadoEncomenda%TYPE;
    cod INTEGER;
    numCliente INTEGER;
    numCodHub INTEGER;
    clienteNaoExistir EXCEPTION;
    estadoEncomendaInexistente EXCEPTION;
    codHubNaoExistir EXCEPTION;
BEGIN
    SELECT COUNT(*) INTO numCliente FROM cliente WHERE codInternoCliente = pCodInternoCliente;

    IF numCliente = 0 THEN
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

-- Procedimento para registar produtos em uma encomenda.
CREATE OR REPLACE PROCEDURE prcUS215RegistarProdutoNaEncomenda(pCodEncomenda IN encomenda.codEncomenda%TYPE,
                                                                pCodProduto IN registoEncomenda.codProduto%TYPE,
                                                                pQuantidade IN registoEncomenda.quantidade%TYPE) IS

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

-- Bloco anónimo para testar a função de registar uma encomenda e o procedimento de registar produtos.
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
    pCodHub := 'CT1';
    pDataEncomenda:= TO_DATE('15/01/2023', 'dd/mm/yy');
    pDataVencimento:= TO_DATE('17/01/2023', 'dd/mm/yy');

    resultado := fncUS215CriacaoEncomenda(pCodInternoCliente, pCodHub, pDataEncomenda, pDataVencimento);
    IF resultado IS NOT NULL THEN

        pCodProduto := 1;
        pQuantidade := 5;

        prcUS215RegistarProdutoNaEncomenda(resultado, pCodProduto, pQuantidade);

        pCodProduto := 2;
        pQuantidade := 1;

        prcUS215RegistarProdutoNaEncomenda(resultado, pCodProduto, pQuantidade);
    END IF;
END;