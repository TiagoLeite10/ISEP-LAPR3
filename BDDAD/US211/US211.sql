-- ***** Trigger para a realização da US211 *****
-- Este Trigger é executado antes de uma ação de update ou delete (DML), e é executado para todos os registos afetados.
CREATE OR REPLACE TRIGGER triggerUS211PrevenirCancelamentoOuAtualizacaoOperacaoRealizada
    BEFORE UPDATE OR DELETE ON operacaoAgricola
    FOR EACH ROW
BEGIN
    -- Se for uma ação de um comando de delete
    IF DELETING THEN
        raise_application_error(-20000, 'Não é permitido eliminar dados da tabela.');
    ELSE
        -- Se a data da operação agrícola for menor do que a data de agora
        IF :OLD.dataOperacaoAgricola <= SYSDATE THEN
            -- Se a operação agrícola já estiver cancelada e estiverem a tentar cancelar novamente
            IF :OLD.cancelada = 1 AND :NEW.cancelada = 1 THEN
                raise_application_error(-20001, 'Esta operação agrícola já foi cancelada antes de ser realizada. Esta operação estava agendada para a data ' || TO_CHAR(:OLD.dataOperacaoAgricola, 'dd-mm-yyyy') || ' no horário ' || TO_CHAR(:OLD.dataOperacaoAgricola, 'hh24:mi:ss') || '.');
            END IF;

            raise_application_error(-20002, 'Não é possível atualizar nem cancelar uma operação agrícola após esta ter sido realizada.');

        -- Se a data da operação agrícola for maior do que a data de agora
        ELSE
            -- Se a operação já estava cancelada e voltam a tentar cancelar
            IF :OLD.cancelada = 1 AND :NEW.cancelada = 1 THEN
                raise_application_error(-20003, 'A operação agrícola já se encontra cancelada, não é possível atualizá-la.');
            END IF;

            -- Se a operação estava cancelada e tentam descancelar
            IF :OLD.cancelada = 1 AND :NEW.cancelada = 0 THEN
                raise_application_error(-20004, 'Uma operação agrícola cancelada não pode ser descancelada. Por favor, crie outra operação agrícola se assim o desejar.');
            END IF;
        END IF;
    END IF;
END;

-- ***** Testes ao Trigger *****
-- Bloco anónimo para testar o trigger em que qualquer operação não pode ser apagada (operação agricola já realizada)
-- Exceção é lançada e tratada
DECLARE
    codOperacaoAgricolaVariable operacaoAgricola.codOperacaoAgricola%TYPE;
    erroEncontrado EXCEPTION;
    PRAGMA EXCEPTION_INIT (erroEncontrado, -20000);
BEGIN
    DBMS_OUTPUT.PUT_LINE('Mensagem expectável: Não é permitido eliminar dados da tabela.');

    codOperacaoAgricolaVariable := 2;

    -- Tenta eliminar a operação agrícola já passada, mas será sem sucesso
    DELETE FROM operacaoAgricola WHERE codOperacaoAgricola = codOperacaoAgricolaVariable;

    DBMS_OUTPUT.PUT_LINE('Mensagem atual: O trigger falhou!');

-- Expectável que seja lançada esta exceção neste bloco anónimo
EXCEPTION
    WHEN erroEncontrado THEN
        DBMS_OUTPUT.PUT_LINE('Mensagem atual: Não é permitido eliminar dados da tabela.');
END;

-- Bloco anónimo para testar o trigger em que qualquer operação não pode ser apagada (operação agricola ainda por realizar)
-- Exceção é lançada e tratada
DECLARE
    codOperacaoAgricolaVariable operacaoAgricola.codOperacaoAgricola%TYPE;
    erroEncontrado EXCEPTION;
    PRAGMA EXCEPTION_INIT (erroEncontrado, -20000);
BEGIN
    DBMS_OUTPUT.PUT_LINE('Mensagem expectável: Não é permitido eliminar dados da tabela.');

    codOperacaoAgricolaVariable := 99999999;

    -- Tenta eliminar a operação agrícola já passada, mas será sem sucesso
    DELETE FROM operacaoAgricola WHERE codOperacaoAgricola = codOperacaoAgricolaVariable;

    DBMS_OUTPUT.PUT_LINE('Mensagem atual: O trigger falhou!');

-- Expectável que seja lançada esta exceção neste bloco anónimo
EXCEPTION
    WHEN erroEncontrado THEN
        DBMS_OUTPUT.PUT_LINE('Mensagem atual: Não é permitido eliminar dados da tabela.');
END;


-- Bloco anónimo para testar o trigger em que se tenta cancelar uma operação que já tinha sido cancelada antes da sua data de realização
-- Exceção é lançada e tratada
DECLARE
    codOperacaoAgricolaVariable operacaoAgricola.codOperacaoAgricola%TYPE;
    erroEncontrado EXCEPTION;
    PRAGMA EXCEPTION_INIT (erroEncontrado, -20001);
BEGIN
    DBMS_OUTPUT.PUT_LINE('Mensagem expectável: Esta operação agrícola já foi cancelada antes de ser realizada. Esta operação estava agendada para a data 05-01-2023 no horário 15:00:00.');

    codOperacaoAgricolaVariable := 6;

    -- Tentar cancelar uma operação em que esta já foi cancelada e a sua suposta data de realização também já passou
    UPDATE operacaoAgricola
    SET cancelada = 1
    WHERE codOperacaoAgricola = codOperacaoAgricolaVariable;

    DBMS_OUTPUT.PUT_LINE('Mensagem atual: O trigger falhou!');

-- Expectável que seja lançada esta exceção neste bloco anónimo
EXCEPTION
    WHEN erroEncontrado THEN
        DBMS_OUTPUT.PUT_LINE('Mensagem atual: Esta operação agrícola já foi cancelada antes de ser realizada. Esta operação estava agendada para a data 05-01-2023 no horário 15:00:00.');
END;


-- Bloco anónimo para testar o trigger em que qualquer operação já realizada não pode ser atualizada
-- Exceção é lançada e tratada
DECLARE
    codOperacaoAgricolaVariable operacaoAgricola.codOperacaoAgricola%TYPE;
    erroEncontrado EXCEPTION;
    PRAGMA EXCEPTION_INIT (erroEncontrado, -20002);
BEGIN
    dbms_output.put_line('Mensagem expectável: Não é possível atualizar uma operação agrícola após esta ter sido realizada.');

    codOperacaoAgricolaVariable := 2;

    -- Tenta atualizar a operação agrícola, mas será sem sucesso
    UPDATE operacaoAgricola
    SET quantidade = 10, cancelada = 1
    WHERE codOperacaoAgricola = codOperacaoAgricolaVariable;

    DBMS_OUTPUT.PUT_LINE('Mensagem atual: O trigger falhou!');

-- Expectável que seja lançada esta exceção neste bloco anónimo
EXCEPTION
    WHEN erroEncontrado THEN
        DBMS_OUTPUT.PUT_LINE('Mensagem atual: Não é possível atualizar uma operação agrícola após esta ter sido realizada.');
END;


-- Bloco anónimo para testar o trigger em que uma operação já cancelada não pode voltar a ser cancelada
-- Exceção é lançada e tratada
DECLARE
    codOperacaoAgricolaVariable operacaoAgricola.codOperacaoAgricola%TYPE;
    erroEncontrado EXCEPTION;
    PRAGMA EXCEPTION_INIT (erroEncontrado, -20003);
BEGIN
    DBMS_OUTPUT.PUT_LINE('Mensagem expectável: A operação agrícola já se encontra cancelada.');

    codOperacaoAgricolaVariable := 99999998;

    -- Tenta atualizar a operação agrícola, mas será sem sucesso
    UPDATE operacaoAgricola
    SET cancelada = 1
    WHERE codOperacaoAgricola = codOperacaoAgricolaVariable;

    DBMS_OUTPUT.PUT_LINE('Mensagem atual: O trigger falhou!');

-- Expectável que seja lançada esta exceção neste bloco anónimo
EXCEPTION
    WHEN erroEncontrado THEN
        DBMS_OUTPUT.PUT_LINE('Mensagem atual: A operação agrícola já se encontra cancelada.');
END;


-- Bloco anónimo para testar o trigger em que uma operação já cancelada não pode ser descancelada
-- Exceção é lançada e tratada
DECLARE
    codOperacaoAgricolaVariable operacaoAgricola.codOperacaoAgricola%TYPE;
    erroEncontrado EXCEPTION;
    PRAGMA EXCEPTION_INIT (erroEncontrado, -20004);
BEGIN
    DBMS_OUTPUT.PUT_LINE('Mensagem expectável: Uma operação agrícola cancelada não pode ser descancelada. Por favor, crie outra operação agrícola se assim o desejar.');

    codOperacaoAgricolaVariable := 99999998;

    -- Tenta atualizar a operação agrícola, mas será sem sucesso
    UPDATE operacaoAgricola
    SET cancelada = 0
    WHERE codOperacaoAgricola = codOperacaoAgricolaVariable;

    DBMS_OUTPUT.PUT_LINE('Mensagem atual: O trigger falhou!');

-- Expectável que seja lançada esta exceção neste bloco anónimo
EXCEPTION
    WHEN erroEncontrado THEN
        DBMS_OUTPUT.PUT_LINE('Mensagem atual: Uma operação agrícola cancelada não pode ser descancelada. Por favor, crie outra operação agrícola se assim o desejar.');
END;


-- Bloco anónimo para testar o trigger em que agora é dada uma data para a operação em que é possível alterar os dados
-- Teste de sucesso
DECLARE
    varivavelCodOperacaoAgricola operacaoAgricola.codOperacaoAgricola%TYPE;
    linhaOperacaoAgricolaUpdate operacaoAgricola%ROWTYPE;
BEGIN
    varivavelCodOperacaoAgricola := 99999999;

    -- Coloca os dados atuais da operação agrícola na variável linhaOperacaoAgricolaUpdate
    SELECT * INTO linhaOperacaoAgricolaUpdate
    FROM operacaoAgricola
    WHERE codOperacaoAgricola = varivavelCodOperacaoAgricola;

    DBMS_OUTPUT.PUT_LINE('***** Antes do update *****');
    DBMS_OUTPUT.PUT_LINE('codOperacaoAgricola: ' || linhaOperacaoAgricolaUpdate.codOperacaoAgricola);
    DBMS_OUTPUT.PUT_LINE('codPlantacao: ' || linhaOperacaoAgricolaUpdate.codPlantacao);
    DBMS_OUTPUT.PUT_LINE('codFatorProducao: ' || linhaOperacaoAgricolaUpdate.codFatorProducao);
    DBMS_OUTPUT.PUT_LINE('codFormaAplicacao: ' || linhaOperacaoAgricolaUpdate.codFormaAplicacao);
    DBMS_OUTPUT.PUT_LINE('quantidade: ' || linhaOperacaoAgricolaUpdate.quantidade);
    DBMS_OUTPUT.PUT_LINE('dataOperacaoAgricola: ' || TO_CHAR(linhaOperacaoAgricolaUpdate.dataOperacaoAgricola, 'DD-MM-YYYY HH24:MI:SS' ));
    DBMS_OUTPUT.PUT_LINE('cancelada: ' || linhaOperacaoAgricolaUpdate.cancelada);

    -- Faz o update dessa operação agrícola com sucesso
    UPDATE operacaoAgricola
    SET codPlantacao = 2, codFatorProducao = 2, codFormaAplicacao = 2, quantidade = 10, dataOperacaoAgricola = TO_DATE('31-1-2025 15:00:00', 'dd-mm-yyyy hh24:mi:ss')
    WHERE codOperacaoAgricola = varivavelCodOperacaoAgricola;
    DBMS_OUTPUT.PUT_LINE('A operação agrícola foi atualizada com sucesso!');

    -- Coloca os dados da operação agrícola na variável linhaOperacaoAgricolaUpdate depois do update
    SELECT * INTO linhaOperacaoAgricolaUpdate
    FROM operacaoAgricola
    WHERE codOperacaoAgricola = varivavelCodOperacaoAgricola;

    DBMS_OUTPUT.PUT_LINE('***** Depois do update *****');
    DBMS_OUTPUT.PUT_LINE('codOperacaoAgricola: ' || linhaOperacaoAgricolaUpdate.codOperacaoAgricola);
    DBMS_OUTPUT.PUT_LINE('codPlantacao: ' || linhaOperacaoAgricolaUpdate.codPlantacao);
    DBMS_OUTPUT.PUT_LINE('codFatorProducao: ' || linhaOperacaoAgricolaUpdate.codFatorProducao);
    DBMS_OUTPUT.PUT_LINE('codFormaAplicacao: ' || linhaOperacaoAgricolaUpdate.codFormaAplicacao);
    DBMS_OUTPUT.PUT_LINE('quantidade: ' || linhaOperacaoAgricolaUpdate.quantidade);
    DBMS_OUTPUT.PUT_LINE('dataOperacaoAgricola: ' || TO_CHAR(linhaOperacaoAgricolaUpdate.dataOperacaoAgricola, 'DD-MM-YYYY HH24:MI:SS' ));
    DBMS_OUTPUT.PUT_LINE('cancelada: ' || linhaOperacaoAgricolaUpdate.cancelada);
END;


-- ---------------------------------------------------------------------------------------------------------------------
-- Descartado
-- 1. Qualquer operação que ainda não tenha sido realizada pode ser cancelada.
-- Procedimento para cancelar uma operação 
CREATE OR REPLACE PROCEDURE prcUS211CancelarOperacao(
    pCodOperacaoAgricola IN operacaoAgricola.codOperacaoAgricola%TYPE,
    pDataAtual IN operacaoAgricola.dataOperacaoAgricola%TYPE
    
) AS

    dataOperacao operacaoAgricola.dataOperacaoAgricola%TYPE;
    canceladaEstado operacaoAgricola.cancelada%TYPE;
    numOperacao INTEGER;
    operacaoJaRealizada EXCEPTION;
    operacaoJaCancelada EXCEPTION;
    operacaoInexistente EXCEPTION;

BEGIN

    SELECT COUNT(*) INTO numOperacao 
    FROM operacaoAgricola 
    WHERE codOperacaoAgricola = pCodOperacaoAgricola;
    
    IF numOperacao = 0 OR pCodOperacaoAgricola IS NULL THEN
        RAISE operacaoInexistente;
    ELSE
 
        SELECT dataOperacaoAgricola, cancelada 
        INTO dataOperacao, canceladaEstado 
        FROM operacaoAgricola 
        WHERE codOperacaoAgricola = pCodOperacaoAgricola;
                
        IF dataOperacao > pDataAtual THEN     
            IF canceladaEstado = 0 THEN
                canceladaEstado := 1;

                UPDATE operacaoAgricola 
                SET cancelada = canceladaEstado 
                WHERE codOperacaoAgricola = pCodOperacaoAgricola;

                DBMS_OUTPUT.PUT_LINE('A operação com o id ' || pCodOperacaoAgricola || ' foi cancelada com sucesso.');
            ELSE
                RAISE operacaoJaCancelada;
            END IF;

        ELSE
            RAISE operacaoJaRealizada;
        END IF;
    END IF;

EXCEPTION
    WHEN operacaoInexistente THEN
        DBMS_OUTPUT.PUT_LINE('Não existe nenhuma operação com o id ' || pCodOperacaoAgricola || '.');
    WHEN operacaoJaCancelada THEN
        DBMS_OUTPUT.PUT_LINE('A operação com o id ' || pCodOperacaoAgricola || ' já foi cancelada.');
    WHEN operacaoJaRealizada THEN
        DBMS_OUTPUT.PUT_LINE('A operação ja foi realizada, por isso não pode ser cancelada.');
END;

-- Bloco anónimo para testar o procedimento para cancelar uma operação caso a data da operacao ja tenha passado 
DECLARE
    codOperacaoAgricola operacaoAgricola.codOperacaoAgricola%TYPE;
    dataAtual operacaoAgricola.dataOperacaoAgricola%TYPE;
BEGIN
    codOperacaoAgricola := 2;
    dataAtual := SYSDATE;
    prcUS211CancelarOperacao(codOperacaoAgricola, dataAtual ) ;
END;

-- Bloco anónimo para testar o procedimento para cancelar uma operação caso a data da operacao ainda nao tenha passado
DECLARE
    codOperacaoAgricola operacaoAgricola.codOperacaoAgricola%TYPE;
    dataAtual operacaoAgricola.dataOperacaoAgricola%TYPE;
BEGIN
    codOperacaoAgricola := 1;
    dataAtual := SYSDATE;
    prcUS211CancelarOperacao(codOperacaoAgricola, dataAtual ) ;
END; 

-- Bloco anónimo para testar o procedimento para cancelar uma operação caso a mesma ja tenha sido cancelada
DECLARE
    codOperacaoAgricola operacaoAgricola.codOperacaoAgricola%TYPE;
    dataAtual operacaoAgricola.dataOperacaoAgricola%TYPE;
BEGIN
    codOperacaoAgricola := 3;
    dataAtual := SYSDATE;
    prcUS211CancelarOperacao(codOperacaoAgricola, dataAtual ) ;

END;

