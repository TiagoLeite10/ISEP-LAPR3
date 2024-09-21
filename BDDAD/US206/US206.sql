-- Procedimento para criar setores numa exploração agrícola biológica especificando suas características.
CREATE OR REPLACE PROCEDURE prcUS206CriarSetor(
                                            codInstalacaoAgricolaParcela IN parcela.codInstalacaoAgricola%TYPE,
                                            designacaoParcela IN parcela.designacao%TYPE,
                                            areaParcela IN parcela.area%TYPE) IS

    cod parcela.codParcela%TYPE;

BEGIN
    SELECT MAX(codParcela)
    INTO cod
    FROM parcela;
    cod := cod + 1;

    INSERT INTO parcela(codParcela, codInstalacaoAgricola,  designacao, area)
    VALUES (cod, codInstalacaoAgricolaParcela, designacaoParcela, areaParcela);

END;

-- Bloco anónimo para testar o procedimento para criar setores numa exploração agrícola biológica especificando suas características.
DECLARE
    codInstalacaoAgricolaParcela parcela.codInstalacaoAgricola%TYPE;
    designacaoParcela  parcela.designacao%TYPE;
    areaParcela  parcela.area%TYPE;
BEGIN
    codInstalacaoAgricolaParcela := 1;
    designacaoParcela := 'Parcela da Framboesas';
    areaParcela := 6;

    prcUS206CriarSetor(codInstalacaoAgricolaParcela, designacaoParcela, areaParcela);
END;

------------------------------------------------------------------------------------------------------------------------
-- Função para definir um novo tipo de cultura.
CREATE OR REPLACE FUNCTION fncUS206DefinirTipoCultura(pDescricao IN tipoCultura.descricao%TYPE)
                                                                                RETURN tipoCultura.codTipoCultura%TYPE IS
    cod tipoCultura.codTipoCultura%TYPE;
BEGIN
    cod := 0;
    SELECT MAX(codTipoCultura)
    INTO cod
    FROM tipoCultura;
    cod := cod + 1;

    INSERT INTO tipoCultura(codTipoCultura, descricao)
    VALUES (cod, pDescricao);

    RETURN cod;
    EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
            RETURN NULL;
END;

-- Função para definir um novo objetivo da cultura.
CREATE OR REPLACE FUNCTION fncUS206DefinirObjetivoCultura(pDescricao IN objetivoCultura.descricao%TYPE)
                                                                                RETURN objetivoCultura.codObjetivoCultura%TYPE IS
    cod objetivoCultura.codObjetivoCultura%TYPE;
BEGIN
    cod := 0;
    SELECT MAX(codObjetivoCultura)
    INTO cod
    FROM objetivoCultura;
    cod := cod + 1;

    INSERT INTO objetivoCultura(codObjetivoCultura, descricao)
    VALUES (cod, pDescricao);

    RETURN cod;
    EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
            RETURN NULL;
END;

-- Procedimento para definir uma nova cultura.
CREATE OR REPLACE PROCEDURE fncUS206DefinirCultura(
                                                pCodTipoCultura IN cultura.codTipoCultura%TYPE,
                                                pCodObjetivoCultura IN cultura.codObjetivoCultura%TYPE,
                                                pCodProduto IN cultura.codProduto%TYPE,
                                                pNome IN cultura.nome%TYPE) AS

    codigoCultura cultura.codCultura%TYPE;
    numObjetivoCultura INTEGER;
    numCodTipoCultura INTEGER;
    numCodProduto INTEGER;
    objetivoCulturaInexistente EXCEPTION;
    tipoCulturaInexistente EXCEPTION;
    codProdutoInexistente EXCEPTION;

BEGIN
    SELECT COUNT(*) INTO numObjetivoCultura FROM objetivoCultura WHERE codObjetivoCultura = pCodObjetivoCultura;
    IF  numObjetivoCultura = 0 THEN
            RAISE objetivoCulturaInexistente;
    ElSE
        SELECT COUNT(*) INTO numCodTipoCultura FROM tipoCultura WHERE codTipoCultura = pCodTipoCultura;
        IF  numCodTipoCultura = 0 THEN
            RAISE tipoCulturaInexistente;
        ELSE
            SELECT COUNT(*) INTO numCodProduto FROM produto WHERE codProduto = pCodProduto;
            IF  numCodProduto = 0 THEN
                        RAISE codProdutoInexistente;
            ELSE
                codigoCultura := 0;
                SELECT MAX(codCultura)
                INTO codigoCultura
                FROM cultura;
                codigoCultura := codigoCultura + 1;

                INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, codProduto, nome)
                VALUES (codigoCultura, pCodObjetivoCultura, pCodTipoCultura, pCodProduto, pNome);

                dbms_output.put_line('Cultura inserida com sucesso. O código da cultura é ' || codigoCultura || '!');

            END IF;
        END IF;
    END IF;

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            dbms_output.put_line('Não foi possível definir a nova cultura!');
        WHEN objetivoCulturaInexistente THEN
            dbms_output.put_line('O código do objetivo de cultura não existe!');
        WHEN tipoCulturaInexistente THEN
            dbms_output.put_line('O código do tipo de cultura não existe!');
        WHEN codProdutoInexistente THEN
            dbms_output.put_line('O código do produto não existe!');
END;

-- Bloco anónimo para definir um novo tipo de cultura, um novo objetivo e uma cultura.
DECLARE
    descricaoTipoCultura tipoCultura.descricao%TYPE;
    descricaoObjetivoCultura objetivoCultura.descricao%TYPE;
    codProduto cultura.codProduto%TYPE;
    nome cultura.nome%TYPE;
    resultadoTipoCultura tipoCultura.codTipoCultura%TYPE;
    resultadoObjetivoCultura objetivoCultura.codObjetivoCultura%TYPE;
BEGIN
    descricaoTipoCultura := 'Em preparação';

    resultadoTipoCultura := fncUS206DefinirTipoCultura(descricaoTipoCultura);

    IF resultadoTipoCultura IS NOT NULL THEN
            dbms_output.put_line('Tipo de cultura inserida com sucesso. O código do tipo da cultura é ' || resultadoTipoCultura || '!');

            descricaoObjetivoCultura := 'Para estrume';
            resultadoObjetivoCultura := fncUS206DefinirObjetivoCultura(descricaoObjetivoCultura);

        IF resultadoObjetivoCultura IS NOT NULL THEN
            dbms_output.put_line('Objetivo da cultura inserida com sucesso. O código do objetivo da cultura é ' || resultadoObjetivoCultura || '!');

            codProduto := 1;
            nome := 'Cultura AZ';

            fncUS206DefinirCultura(resultadoTipoCultura, resultadoObjetivoCultura, codProduto, nome);

        ELSE
                dbms_output.put_line('Não foi possível definir o novo objetivo da cultura!');
        END IF;
    ELSE
        dbms_output.put_line('Não foi possível definir o novo tipo de cultura!');
    END IF;
END;

------------------------------------------------------------------------------------------------------------------------

-- Procedimento para listar os setores da exploração agrícola ordenados por ordem alfabética.
CREATE OR REPLACE PROCEDURE prcUS206ListarPorOrdemAlfabetica(pCodInstalacaoAgricola IN parcela.codInstalacaoAgricola%type) IS
    CURSOR curParcela IS
        SELECT * FROM parcela
        WHERE codInstalacaoAgricola = pCodInstalacaoAgricola
        ORDER BY designacao;

    recParcela parcela%ROWTYPE;

BEGIN
    OPEN curParcela;
    LOOP
        FETCH curParcela INTO recParcela;
        EXIT WHEN curParcela%notfound;
        dbms_output.put_line('Designação: ' || recParcela.designacao || '. Area: ' || recParcela.area || '.');
    END LOOP;
    CLOSE curParcela;
END;

-- Bloco anónimo para testar o procedimento que lista os setores da exploração agrícola ordenados por ordem alfabética.
DECLARE
    pCodInstalacaoAgricola parcela.codInstalacaoAgricola%type;
BEGIN
    pCodInstalacaoAgricola := 1;
    prcUS206ListarPorOrdemAlfabetica(pCodInstalacaoAgricola);
END;

------------------------------------------------------------------------------------------------------------------------

-- Procedimento para listar os setores da exploração agrícola ordenados por tamanho, em ordem crescente ou decrescente.
CREATE OR REPLACE PROCEDURE prcUS206ListarPorOrdemDecrescenteOuCrescente(pCodInstalacaoAgricola IN parcela.codInstalacaoAgricola%type, sentido NUMBER) IS
    CURSOR curParcelaAsc IS
    SELECT * FROM parcela
    WHERE codInstalacaoAgricola = pCodInstalacaoAgricola
    ORDER BY area;

    CURSOR curParcelaDesc IS
    SELECT * FROM parcela
    WHERE codInstalacaoAgricola = pCodInstalacaoAgricola
    ORDER BY area DESC;

    recParcela parcela%ROWTYPE;

BEGIN
    IF sentido > 0 THEN
        OPEN curParcelaDesc;
        LOOP
            FETCH curParcelaDesc INTO recParcela;
            EXIT WHEN curParcelaDesc%notfound;
            dbms_output.put_line('Designação: ' || recParcela.designacao || '. Area: ' || recParcela.area || '.');
        END LOOP;
        CLOSE curParcelaDesc;
    ELSE
        OPEN curParcelaAsc;
        LOOP
            FETCH curParcelaAsc INTO recParcela;
            EXIT WHEN curParcelaAsc%notfound;
            dbms_output.put_line('Designação: ' || recParcela.designacao || '. Area: ' || recParcela.area || '.');
        END LOOP;
        CLOSE curParcelaAsc;
    END IF;
END;

-- Bloco anónimo para testar o procedimento que lista os setores da exploração agrícola ordenados por tamanho em ordem decrescente.
DECLARE
    pCodInstalacaoAgricola parcela.codInstalacaoAgricola%type;
    sentido NUMBER;
BEGIN
    pCodInstalacaoAgricola := 1;
    sentido := -1;
    prcUS206ListarPorOrdemDecrescenteOuCrescente(pCodInstalacaoAgricola, sentido);
END;

-- Bloco anónimo para testar o procedimento que lista os setores da exploração agrícola ordenados por tamanho em ordem decrescente.
DECLARE
    pCodInstalacaoAgricola parcela.codInstalacaoAgricola%type;
    sentido NUMBER;
BEGIN
    pCodInstalacaoAgricola := 1;
    sentido := 1;
    prcUS206ListarPorOrdemDecrescenteOuCrescente(pCodInstalacaoAgricola, sentido);
END;

------------------------------------------------------------------------------------------------------------------------

-- Procedimento para listar os setores da exploração agrícola ordenados por tipo de cultura e cultura.
CREATE OR REPLACE PROCEDURE prcUS206ListarPorTipoDeCulturaECultura(pCodInstalacaoAgricola IN parcela.codInstalacaoAgricola%type) IS
    CURSOR curCultura IS
        SELECT tc.descricao, c.nome
        FROM plantacao pt
        INNER JOIN cultura c ON pt.codCultura = c.codCultura
        INNER JOIN parcela p ON pt.codParcela = p.codParcela
        INNER JOIN tipoCultura tc ON tc.codTipoCultura = c.codTipoCultura
        WHERE p.codInstalacaoAgricola = pCodInstalacaoAgricola
        GROUP BY  tc.descricao, c.nome
        ORDER BY tc.descricao, c.nome;

    recCultura cultura%ROWTYPE;

BEGIN
    FOR recCultura IN curCultura LOOP
        dbms_output.put_line('Cultura: ' || recCultura.nome || '. Tipo cultura: ' || recCultura.descricao || '.');
    END LOOP;
END;

-- Bloco anónimo para testar o procedimento que lista os setores da exploração agrícola ordenados por tipo de cultura e cultura.
DECLARE
    pCodInstalacaoAgricola parcela.codInstalacaoAgricola%type;
BEGIN
    pCodInstalacaoAgricola := 1;
    prcUS206ListarPorTipoDeCulturaECultura(pCodInstalacaoAgricola);
END;