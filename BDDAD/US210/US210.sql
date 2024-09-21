-- Função para verificar se a operação a ser marcada é ou não válida.
CREATE OR REPLACE FUNCTION fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida (
    parametroCodPlantacao operacaoAgricola.codPlantacao%TYPE,
    parametroCodFatorProducao operacaoAgricola.codFatorProducao%TYPE,
    parametroDataAtual restricao.dataInicio%TYPE
) RETURN BOOLEAN IS
    -- Cursor com todas as restrições que correspondem a um dado fator produção e a uma dada plantação, para verificar se é possível realizar uma dada operação agrícola
    CURSOR curRestricoes IS
        SELECT dataInicio, dataFim
        FROM restricao
        WHERE 
            codFatorProducao = parametroCodFatorProducao 
            AND codParcela = (
                SELECT codParcela
                FROM plantacao
                WHERE codPlantacao = parametroCodPlantacao
            );

    dataInicioRestricao restricao.dataInicio%TYPE;
    dataFimRestricao restricao.dataFim%TYPE;

    aplicacaoValida BOOLEAN := TRUE;
BEGIN

    OPEN curRestricoes;
    LOOP
        FETCH curRestricoes INTO dataInicioRestricao, dataFimRestricao;
        EXIT WHEN curRestricoes%NOTFOUND OR aplicacaoValida = FALSE;
        -- Se a data atual ainda estiver entre a data de início e fim da restrição, esta operação argícola não é válida
        IF parametroDataAtual >= dataInicioRestricao AND parametroDataAtual <= dataFimRestricao THEN
            aplicacaoValida := FALSE;
        END IF;
    END LOOP;
    CLOSE curRestricoes;

    RETURN aplicacaoValida;

END;

-- Testes à função fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida, em que existe uma restrição em curso
DECLARE
    resultado BOOLEAN;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Resultado expectável: FALSE');

    resultado := fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida(1, 1, TO_DATE('7-1-2023', 'dd-mm-yyyy'));

    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('Resultado atual: TRUE');
    ElSE
        DBMS_OUTPUT.PUT_LINE('Resultado atual: FALSE');
    END IF;

END;

-- Testes à função fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida, em que existe duas restrições em curso
DECLARE
    resultado BOOLEAN;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Resultado expectável: FALSE');

    resultado := fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida(1, 2, TO_DATE('7-1-2023', 'dd-mm-yyyy'));

    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('Resultado atual: TRUE');
    ElSE
        DBMS_OUTPUT.PUT_LINE('Resultado atual: FALSE');
    END IF;

END;

-- Testes à função fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida, em que não existe uma restrição
DECLARE
    resultado BOOLEAN;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Resultado expectável: TRUE');

    resultado := fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida(1, 3, TO_DATE('7-1-2023', 'dd-mm-yyyy'));

    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('Resultado atual: TRUE');
    ElSE
        DBMS_OUTPUT.PUT_LINE('Resultado atual: FALSE');
    END IF;
END;

-- Testes à função fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida, em que a restrição já passou
DECLARE
    resultado BOOLEAN;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Resultado expectável: TRUE');

    resultado := fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida(4, 1, TO_DATE('7-1-2023', 'dd-mm-yyyy'));

    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('Resultado atual: TRUE');
    ElSE
        DBMS_OUTPUT.PUT_LINE('Resultado atual: FALSE');
    END IF;
END;

-- Testes à função fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida, em que uma restrição já passou, mas existe uma agendada para o futuro
DECLARE
    resultado BOOLEAN;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Resultado expectável: TRUE');

    resultado := fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida(3, 3, TO_DATE('7-1-2023', 'dd-mm-yyyy'));

    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('Resultado atual: TRUE');
    ElSE
        DBMS_OUTPUT.PUT_LINE('Resultado atual: FALSE');
    END IF;
END;

-- Testes à função fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida, em que apenas existe uma restrição agendada para o futuro
DECLARE
    resultado BOOLEAN;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Resultado expectável: TRUE');

    resultado := fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida(5, 4, TO_DATE('7-1-2023', 'dd-mm-yyyy'));

    IF resultado THEN
        DBMS_OUTPUT.PUT_LINE('Resultado atual: TRUE');
    ElSE
        DBMS_OUTPUT.PUT_LINE('Resultado atual: FALSE');
    END IF;
END;

-- --------------------------------------------------------------------------------------------------------------------------------------
-- Procedimento para registar uma operação numa determinada data para manter um calendário de operações.
CREATE OR REPLACE PROCEDURE prcUS210RegistarOperação(
    pCodPlantacao IN operacaoAgricola.codPlantacao%TYPE,
    pCodFatorProducao IN operacaoAgricola.codFatorProducao%TYPE,
    pCodFormaAplicacao IN operacaoAgricola.codFormaAplicacao%TYPE,
    pQuantidade IN operacaoAgricola.quantidade%TYPE,
    pDataOperacaoAgricola IN operacaoAgricola.dataOperacaoAgricola%TYPE,
    pDataAtual IN operacaoAgricola.dataOperacaoAgricola%TYPE
) IS

    proximoCodOperacaoAgricola operacaoAgricola.codOperacaoAgricola%TYPE;

    numCodPlantacao INTEGER;
    numCodFatorProducao INTEGER;
    numCodFormaAplicacao INTEGER;
    operacaoAgricolaValida BOOLEAN;

    codPlantacaoInexistente EXCEPTION;
    codFatorProducaoInexistente EXCEPTION;
    codFormaAplicacaoInexistente EXCEPTION;
    quantidadeInvalida EXCEPTION;
    dataInvalida EXCEPTION;
    operacaoAgricolaInvalida EXCEPTION;
BEGIN
    SELECT COUNT(*) INTO numCodPlantacao FROM plantacao WHERE codPlantacao = pCodPlantacao;

    IF (pCodPlantacao IS NULL OR numCodPlantacao = 0) THEN
        RAISE codPlantacaoInexistente;
    ElSE
        SELECT COUNT(*) INTO numCodFatorProducao FROM fatorProducao WHERE codFatorProducao = pCodFatorProducao;

        IF (pCodFatorProducao IS NULL OR numCodFatorProducao = 0) THEN
            RAISE codFatorProducaoInexistente;
        ELSE
            SELECT COUNT(*) INTO numCodFormaAplicacao FROM formaAplicacao WHERE codFormaAplicacao = pCodFormaAplicacao;

            IF (pCodFormaAplicacao IS NULL OR numCodFormaAplicacao = 0) THEN
                RAISE codFormaAplicacaoInexistente;
            ELSE IF (pQuantidade IS NULL OR pQuantidade <= 0) THEN
                    RAISE quantidadeInvalida;
                ELSE IF (pDataAtual > pDataOperacaoAgricola) THEN
                        RAISE dataInvalida;
                    ELSE
                        -- Valida se a operação agrícola a agendar encontra-se válida (se não há uma restrição para o fator produção que é pretendido aplicar, nesta plantação)
                        operacaoAgricolaValida := fncUS210VerificarRestricoesAplicacaoFatoresProducaoEValida(pCodPlantacao, pCodFatorProducao, SYSDATE);
                        IF NOT operacaoAgricolaValida THEN
                            RAISE operacaoAgricolaInvalida;
                        END IF;

                        SELECT MAX(codOperacaoAgricola) INTO proximoCodOperacaoAgricola FROM operacaoAgricola;
                        IF (proximoCodOperacaoAgricola IS NULL ) THEN
                            proximoCodOperacaoAgricola := 1;
                        ELSE
                            proximoCodOperacaoAgricola := proximoCodOperacaoAgricola + 1;
                        END IF;

                        INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola)
                        VALUES (proximoCodOperacaoAgricola, pCodPlantacao, pCodFatorProducao, pCodFormaAplicacao, pQuantidade, pDataOperacaoAgricola);

                        dbms_output.put_line('Operação agrícola inserida com sucesso.');
                    END IF;
                END IF;
            END IF;
        END IF;
    END IF;

    EXCEPTION
        WHEN codPlantacaoInexistente THEN
            dbms_output.put_line('Não existe nenhuma plantação com o código ' || pCodPlantacao || '.');
        WHEN codFatorProducaoInexistente THEN
            dbms_output.put_line('Não existe nenhum fator de produção com o código ' || pCodFatorProducao || '.');
        WHEN codFormaAplicacaoInexistente THEN
            dbms_output.put_line('Não existe nenhuma forma de aplicação com o código ' || pCodFormaAplicacao || '.');
        WHEN quantidadeInvalida THEN
            dbms_output.put_line('A quantidade de uma operação agrícola tem de ser maior que 0.');
        WHEN dataInvalida THEN
            dbms_output.put_line('A data da operação agrícola não pode ser inferior ao dia atual.');
        WHEN operacaoAgricolaInvalida THEN
            dbms_output.put_line('Não é possível registar a operação agrícola devido a existir uma restrição do fator de produção a aplicar nesta plantação.');
END;

-- Bloco anónimo para testar o procedimento para registar uma operação numa determinada data para manter um calendário de operações.
-- Teste com sucesso
DECLARE
    pCodPlantacao operacaoAgricola.codPlantacao%TYPE := 3;
    pCodFatorProducao operacaoAgricola.codFatorProducao%TYPE := 3;
    pCodFormaAplicacao operacaoAgricola.codFormaAplicacao%TYPE := 1;
    pQuantidade operacaoAgricola.quantidade%TYPE := 100;
    pDataOperacaoAgricola operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('25/01/2023', 'dd/mm/yy');
    pDataAtual operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('11/01/2023', 'dd/mm/yy');
BEGIN
    prcUS210RegistarOperação(pCodPlantacao, pCodFatorProducao, pCodFormaAplicacao, pQuantidade, pDataOperacaoAgricola, pDataAtual);
END;

-- Bloco anónimo para testar o procedimento para registar uma operação numa determinada data para manter um calendário de operações.
-- Teste com um código de plantação inválido.
DECLARE
    pCodPlantacao operacaoAgricola.codPlantacao%TYPE := 100;
    pCodFatorProducao operacaoAgricola.codFatorProducao%TYPE := 3;
    pCodFormaAplicacao operacaoAgricola.codFormaAplicacao%TYPE := 1;
    pQuantidade operacaoAgricola.quantidade%TYPE := 100;
    pDataOperacaoAgricola operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('25/01/2023', 'dd/mm/yy');
    pDataAtual operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('11/01/2023', 'dd/mm/yy');
BEGIN
    prcUS210RegistarOperação(pCodPlantacao, pCodFatorProducao, pCodFormaAplicacao, pQuantidade, pDataOperacaoAgricola, pDataAtual);
END;

-- Bloco anónimo para testar o procedimento para registar uma operação numa determinada data para manter um calendário de operações.
-- Teste com um código de fator de produção inválido.
DECLARE
    pCodPlantacao operacaoAgricola.codPlantacao%TYPE := 3;
    pCodFatorProducao operacaoAgricola.codFatorProducao%TYPE := 100;
    pCodFormaAplicacao operacaoAgricola.codFormaAplicacao%TYPE := 1;
    pQuantidade operacaoAgricola.quantidade%TYPE := 100;
    pDataOperacaoAgricola operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('25/01/2023', 'dd/mm/yy');
    pDataAtual operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('11/01/2023', 'dd/mm/yy');
BEGIN
    prcUS210RegistarOperação(pCodPlantacao, pCodFatorProducao, pCodFormaAplicacao, pQuantidade, pDataOperacaoAgricola, pDataAtual);
END;

-- Bloco anónimo para testar o procedimento para registar uma operação numa determinada data para manter um calendário de operações.
-- Teste com um código de forma de aplicação inválido.
DECLARE
    pCodPlantacao operacaoAgricola.codPlantacao%TYPE := 3;
    pCodFatorProducao operacaoAgricola.codFatorProducao%TYPE := 3;
    pCodFormaAplicacao operacaoAgricola.codFormaAplicacao%TYPE := 100;
    pQuantidade operacaoAgricola.quantidade%TYPE := 100;
    pDataOperacaoAgricola operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('25/01/2023', 'dd/mm/yy');
    pDataAtual operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('11/01/2023', 'dd/mm/yy');
BEGIN
    prcUS210RegistarOperação(pCodPlantacao, pCodFatorProducao, pCodFormaAplicacao, pQuantidade, pDataOperacaoAgricola, pDataAtual);
END;

-- Bloco anónimo para testar o procedimento para registar uma operação numa determinada data para manter um calendário de operações.
-- Teste com uma quantidade negativa.
DECLARE
    pCodPlantacao operacaoAgricola.codPlantacao%TYPE := 3;
    pCodFatorProducao operacaoAgricola.codFatorProducao%TYPE := 3;
    pCodFormaAplicacao operacaoAgricola.codFormaAplicacao%TYPE := 1;
    pQuantidade operacaoAgricola.quantidade%TYPE := -100;
    pDataOperacaoAgricola operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('25/01/2023', 'dd/mm/yy');
    pDataAtual operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('11/01/2023', 'dd/mm/yy');
BEGIN
    prcUS210RegistarOperação(pCodPlantacao, pCodFatorProducao, pCodFormaAplicacao, pQuantidade, pDataOperacaoAgricola, pDataAtual);
END;

-- Bloco anónimo para testar o procedimento para registar uma operação numa determinada data para manter um calendário de operações.
-- Teste com uma data da operação agrícola inferior ao dia atual.
DECLARE
    pCodPlantacao operacaoAgricola.codPlantacao%TYPE := 3;
    pCodFatorProducao operacaoAgricola.codFatorProducao%TYPE := 3;
    pCodFormaAplicacao operacaoAgricola.codFormaAplicacao%TYPE := 1;
    pQuantidade operacaoAgricola.quantidade%TYPE := 100;
    pDataOperacaoAgricola operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('10/01/2023', 'dd/mm/yy');
    pDataAtual operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('11/01/2023', 'dd/mm/yy');
BEGIN
    prcUS210RegistarOperação(pCodPlantacao, pCodFatorProducao, pCodFormaAplicacao, pQuantidade, pDataOperacaoAgricola, pDataAtual);
END;

-- Bloco anónimo para testar o procedimento para registar uma operação numa determinada data para manter um calendário de operações.
-- Teste com uma operação agrícola que possui uma plantação com uma restrição do fator de produção a aplicar.
DECLARE
    pCodPlantacao operacaoAgricola.codPlantacao%TYPE := 1;
    pCodFatorProducao operacaoAgricola.codFatorProducao%TYPE := 2;
    pCodFormaAplicacao operacaoAgricola.codFormaAplicacao%TYPE := 1;
    pQuantidade operacaoAgricola.quantidade%TYPE := 100;
    pDataOperacaoAgricola operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('25/01/2023', 'dd/mm/yy');
    pDataAtual operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('11/01/2023', 'dd/mm/yy');
BEGIN
    prcUS210RegistarOperação(pCodPlantacao, pCodFatorProducao, pCodFormaAplicacao, pQuantidade, pDataOperacaoAgricola, pDataAtual);
END;

-- ---------------------------------------------------------------------------------------------------------------------
-- Procedimento para verificar as restrições à aplicação dos factores de produção uma semana antes da sua aplicação.
CREATE OR REPLACE PROCEDURE prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar (
    pDataAtual operacaoAgricola.dataOperacaoAgricola%TYPE
) IS

    CURSOR curAplicacoesUmaSemana IS
        SELECT oa.codOperacaoAgricola, oa.codPlantacao, oa.codFatorProducao, oa.dataOperacaoAgricola, oa.cancelada
        FROM operacaoAgricola oa
        INNER JOIN plantacao p ON p.codPlantacao = oa.codPlantacao
        WHERE 
            pDataAtual < dataOperacaoAgricola 
            AND pDataAtual + 7 >= dataOperacaoAgricola 
            --AND cancelada = 0 
            AND (
                SELECT COUNT(*) 
                FROM restricao r 
                WHERE r.codParcela = p.codParcela AND r.codFatorProducao = oa.codFatorProducao
                ) > 0;

    variavelCodOperacaoAgricola operacaoAgricola.codOperacaoAgricola%TYPE;
    variavelCodPlantacao operacaoAgricola.codPlantacao%TYPE;
    variavelCodFatorProducao operacaoAgricola.codFatorProducao%TYPE;
    variavelDataOperacaoAgricola operacaoAgricola.dataOperacaoAgricola%TYPE;
    variavelCancelada operacaoAgricola.cancelada%TYPE;
    existeOperacoesInvalidas BOOLEAN := FALSE;

BEGIN
    OPEN curAplicacoesUmaSemana;
    LOOP
        FETCH curAplicacoesUmaSemana INTO variavelCodOperacaoAgricola, variavelCodPlantacao, variavelCodFatorProducao, variavelDataOperacaoAgricola, variavelCancelada;
        EXIT WHEN curAplicacoesUmaSemana%NOTFOUND;
            existeOperacoesInvalidas := TRUE;
            IF variavelCancelada = 1 THEN
                dbms_output.put_line('(Esta operação encontra-se cancelada) Código operação agrícola: ' || variavelCodOperacaoAgricola || '. Código plantação agrícola: ' || variavelCodPlantacao || '. Código fator produção: ' || variavelCodFatorProducao || '. Data de realização: ' ||  TO_CHAR(variavelDataOperacaoAgricola, 'DD-MM-YYYY HH24:MI:SS' ));
            ELSE
                dbms_output.put_line('Código operação agrícola: ' || variavelCodOperacaoAgricola || '. Código plantação agrícola: ' || variavelCodPlantacao || '. Código fator produção: ' || variavelCodFatorProducao || '. Data de realização: ' ||  TO_CHAR(variavelDataOperacaoAgricola, 'DD-MM-YYYY HH24:MI:SS' ));
            END IF;
    END LOOP;
    CLOSE curAplicacoesUmaSemana;

    IF NOT existeOperacoesInvalidas THEN
        DBMS_OUTPUT.PUT_LINE('Boas notícias! Não foram encontradas operações agrícolas agendadas para esta semana com restrições violadas.');
    END IF;
END;

-- Testa o procedimento prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar, em que não existe operações agrícolas no espaço de uma semana que violem as restrições
DECLARE
    dataAtual DATE := TO_DATE('3-3-2018', 'dd-mm-yyyy');
BEGIN
    DBMS_OUTPUT.PUT_LINE('Mensagem expectável: Boas notícias! Não foram encontradas operações agrícolas agendadas para esta semana com restrições violadas.');
    DBMS_OUTPUT.PUT_LINE('Mensagem atual: ');
    prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar(dataAtual);
END;

-- Testa o procedimento prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar, em que existe uma restrição e que a data contém uma hora em que bate 7 dias certinhos com a realização da operação
-- Expectável que contenha restrições violadas
DECLARE
    dataAtual DATE := TO_DATE('10-2-2023 21:02:00', 'dd-mm-yyyy hh24:mi:ss');
BEGIN
    prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar(dataAtual);
END;

-- Testa o procedimento prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar, em que existiu uma restrição mas em que a data contém uma hora que por 1 segundo não bate os 7 dias certinhos com a realização da operação
DECLARE
    dataAtual DATE := TO_DATE('10-2-2023 21:01:59', 'dd-mm-yyyy hh24:mi:ss');
BEGIN
    DBMS_OUTPUT.PUT_LINE('Mensagem expectável: Boas notícias! Não foram encontradas operações agrícolas agendadas para esta semana com restrições violadas.');
    DBMS_OUTPUT.PUT_LINE('Mensagem atual: ');
    prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar(dataAtual);
END;

-- Testa o procedimento prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar, em que existe uma restrição, mas em que a data de realização foi há 1 segundo atrás
DECLARE
    dataAtual DATE := TO_DATE('17-2-2023 21:02:01', 'dd-mm-yyyy hh24:mi:ss');
BEGIN
    DBMS_OUTPUT.PUT_LINE('Mensagem expectável: Boas notícias! Não foram encontradas operações agrícolas agendadas para esta semana com restrições violadas.');
    DBMS_OUTPUT.PUT_LINE('Mensagem atual: ');
    prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar(dataAtual);
END;

-- Testa o procedimento prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar, em que existe mais do que uma restrição em que uma data da operação é mesmo no limite da data de 7 semana e outra é no limite da data atual para a frente 1 segundo
-- Expectável que contenha restrições violadas
DECLARE
    dataAtual DATE := TO_DATE('10-1-2019 17:55:00', 'dd-mm-yyyy hh24:mi:ss');
BEGIN
    prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar(dataAtual);
END;

-- Testa o procedimento prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar, em que existe mais do que uma restrição em que uma data da operação é mesmo no limite da data de 7 semana e outra é no limite da data atual para a frente 1 segundo
-- Expectável que contenha restrições violadas
DECLARE
    dataAtual DATE := TO_DATE('10-1-2019 17:55:00', 'dd-mm-yyyy hh24:mi:ss');
BEGIN
    prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar(dataAtual);
END;

-- Testa o procedimento prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar, em que existe restrições para duas operações agrícolas na semana indicada, mas em que numa destas a operação já foi cancelada e noutra não
-- Expectável que contenha restrições violadas
DECLARE
    dataAtual DATE := TO_DATE('31-12-2999', 'dd-mm-yyyy');
BEGIN
    prcUS210VerificarRestricoesAplicacaoFatoresProducaoUmaSemanaAntesDeAplicar(dataAtual);
END;

------------------------------------------------------------------------------------------------------------------------
-- Procedimento para listar todas as restrições de fatores de produção que se aplicam a um determinado Setor da minha exploração agrícola numa determinada data.
CREATE OR REPLACE PROCEDURE prcUS210ListarRestriçõesDeFatores(
    pCodParcela IN parcela.codParcela%TYPE,
    pData IN restricao.dataInicio%TYPE
) IS

    CURSOR curRestricao IS
        SELECT r.codRestricao, p.designacao, f.nomeComercial
        FROM restricao r
        INNER JOIN parcela p ON p.codParcela = r.codParcela
        INNER JOIN fatorProducao f ON f.codFatorProducao = r.codFatorProducao
        WHERE p.codParcela = pCodParcela AND pData BETWEEN r.dataInicio AND r.dataFim;

    vCodRestricao restricao.codRestricao%TYPE;
    vDesignacao parcela.designacao%TYPE;
    vNomeComercial fatorProducao.nomeComercial%TYPE;
BEGIN
    OPEN curRestricao;
    LOOP
        FETCH curRestricao INTO vCodRestricao, vDesignacao, vNomeComercial;
        EXIT WHEN curRestricao%notfound;
        dbms_output.put_line('Código da restrição: ' || vCodRestricao || '. Designação da parcela: ' || vDesignacao || '. Nome do fator de produção: ' || vNomeComercial);
    END LOOP;
    CLOSE curRestricao;
END;

-- Bloco anónimo para testar o procedimento para listar todas as restrições de fatores de produção que se aplicam a um determinado Setor da minha exploração agrícola numa determinada data.
DECLARE
    pCodParcela parcela.codParcela%TYPE := 1;
    pData restricao.dataInicio%TYPE := TO_DATE('05-01-2023', 'dd-mm-yyyy');
BEGIN
    prcUS210ListarRestriçõesDeFatores(pCodParcela, pData);
END;

-- Bloco anónimo para testar o procedimento para listar todas as restrições de fatores de produção que se aplicam a um determinado Setor da minha exploração agrícola numa determinada data.
DECLARE
    pCodParcela parcela.codParcela%TYPE := 2;
    pData restricao.dataInicio%TYPE := TO_DATE('05-01-2023', 'dd-mm-yyyy');
BEGIN
    prcUS210ListarRestriçõesDeFatores(pCodParcela, pData);
END;

----------------------------------------------------------------------------------------------------------------------------------------------
-- Procedimento para listar todas as operações planeadas na minha exploração agrícola durante um determinado período de tempo ordem cronológica.
CREATE OR REPLACE PROCEDURE prcUS210ListarTodasOperações(
    pCodInstalacaoAgricola IN parcela.codInstalacaoAgricola%TYPE,
    pDataInicio IN operacaoAgricola.dataOperacaoAgricola%TYPE,
    pDataFinal IN operacaoAgricola.dataOperacaoAgricola%TYPE
) IS

    CURSOR curOperacaoAgricola IS
        SELECT p.codParcela, p.designacao, f.nomeComercial, fa.descricao, o.quantidade, o.dataOperacaoAgricola
        FROM operacaoAgricola o
        INNER JOIN plantacao pt ON o.codPlantacao = pt.codPlantacao
        INNER JOIN parcela p ON pt.codParcela = p.codParcela
        INNER JOIN fatorProducao f ON o.codFatorProducao = f.codFatorProducao
        INNER JOIN formaAplicacao fa ON o.codFormaAplicacao = fa.codFormaAplicacao
        WHERE p.codInstalacaoAgricola = pCodInstalacaoAgricola AND o.dataOperacaoAgricola BETWEEN pDataInicio AND pDataFinal
        GROUP BY p.codParcela, p.designacao, f.nomeComercial, fa.descricao, o.quantidade, o.dataOperacaoAgricola
        ORDER BY o.dataOperacaoAgricola;


    vCodParcela restricao.codParcela%TYPE;
    vDesignacao parcela.designacao%TYPE;
    vNomeComercial fatorProducao.nomeComercial%TYPE;
    vDescricao  formaAplicacao.descricao%TYPE;
    vQuantidade OperacaoAgricola.quantidade%TYPE;
    vDataOperacaoAgricola operacaoAgricola.dataOperacaoAgricola%TYPE;

BEGIN
    OPEN curOperacaoAgricola;
    LOOP
        FETCH curOperacaoAgricola INTO vCodParcela, vDesignacao, vNomeComercial, vDescricao, vQuantidade, vDataOperacaoAgricola;
        EXIT WHEN curOperacaoAgricola%notfound;
        dbms_output.put_line('Código da parcela: ' || vCodParcela);
        dbms_output.put_line('Designação da parcela: ' || vDesignacao);
        dbms_output.put_line('Nome do fator de produção: ' || vNomeComercial);
        dbms_output.put_line('Nome do forma de aplicação: ' || vDescricao);
        dbms_output.put_line('Quantidade: ' || vQuantidade);
        dbms_output.put_line('Data da operação agrícola: ' || TO_CHAR (vDataOperacaoAgricola, 'dd-mm-yyyy'));
        dbms_output.put_line('        ');
    END LOOP;
    CLOSE curOperacaoAgricola;
END;

-- Bloco anónimo para testar o procedimento para listar todas as operações planeadas na minha exploração agrícola durante um determinado período de tempo ordem cronológica.
DECLARE
    pCodInstalacaoAgricola parcela.codInstalacaoAgricola%TYPE := 1;
    pDataInicio operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('05-01-2023', 'dd-mm-yyyy');
    pDataFinal operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('30-01-2023', 'dd-mm-yyyy');
BEGIN
    prcUS210ListarTodasOperações(pCodInstalacaoAgricola, pDataInicio, pDataFinal);
END;

-- Bloco anónimo para testar o procedimento para listar todas as operações planeadas na minha exploração agrícola durante um determinado período de tempo ordem cronológica.
DECLARE
    pCodInstalacaoAgricola parcela.codInstalacaoAgricola%TYPE := 1;
    pDataInicio operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('01-02-2023', 'dd-mm-yyyy');
    pDataFinal operacaoAgricola.dataOperacaoAgricola%TYPE := TO_DATE('20-02-2023', 'dd-mm-yyyy');
BEGIN
    prcUS210ListarTodasOperações(pCodInstalacaoAgricola, pDataInicio, pDataFinal);
END;