--2. Os mecanismos apropriados para gravação de operações de escrita (INSERT, UPDATE, DELETE) estão implementados.

-- Trigger para registar uma pista de auditoria das operações agrícolas sempre que houver uma alteração.
CREATE OR REPLACE TRIGGER triggerUS213GravacaoOperacaosEscrita
    AFTER INSERT OR UPDATE OR DELETE ON operacaoAgricola
    FOR EACH ROW
DECLARE
    vProximoCodPistaAuditoriaOperacao pistaAuditoriaOperacao.codPistaAuditoriaOperacao%TYPE;
    vCodOperacaoAgricola pistaAuditoriaOperacao.codOperacaoAgricola%TYPE;
    vCodTipoAlteracao pistaAuditoriaOperacao.codTipoAlteracao%TYPE;
    vCodUtilizador pistaAuditoriaOperacao.codUtilizador%TYPE;

    vTipoAlteracao tipoAlteracao.descricao%TYPE;
BEGIN
    -- Para obter o próximo código de um registo da tabela pistaAuditoriaOperacao
    SELECT MAX(codPistaAuditoriaOperacao) INTO vProximoCodPistaAuditoriaOperacao FROM pistaAuditoriaOperacao;
    IF (vProximoCodPistaAuditoriaOperacao IS NULL ) THEN
        vProximoCodPistaAuditoriaOperacao := 1;
    ELSE
        vProximoCodPistaAuditoriaOperacao := vProximoCodPistaAuditoriaOperacao + 1;
    END IF;

    -- Para obter o tipo de alteração executada pelo trigger
    IF INSERTING THEN
        vTipoAlteracao := 'INSERT';
    ELSE IF UPDATING THEN
            vTipoAlteracao := 'UPDATE';
        ELSE
            vTipoAlteracao := 'DELETE';
        END IF;
    END IF;

    SELECT codTipoAlteracao INTO vCodTipoAlteracao FROM tipoAlteracao WHERE descricao LIKE vTipoAlteracao;

    -- Para obter o código da operação
    IF INSERTING OR UPDATING THEN
        vCodOperacaoAgricola := :NEW.codOperacaoAgricola;
    ELSE
        vCodOperacaoAgricola := :OLD.codOperacaoAgricola;
    END IF;

    -- Para obter o código do utilizador que executou a alteração;
    SELECT user_id INTO vCodUtilizador FROM user_users;

    -- Grava a operação de escrita
    INSERT INTO pistaAuditoriaOperacao(codPistaAuditoriaOperacao, codOperacaoAgricola, codTipoAlteracao, codUtilizador)
    VALUES(vProximoCodPistaAuditoriaOperacao, vCodOperacaoAgricola, vCodTipoAlteracao, vCodUtilizador);
END;

-- Bloco anónimo para realizar uma alteração à operação agrícola do tipo INSERT e testar o trigger que regista uma pista de auditoria da operação agrícola.
DECLARE
BEGIN
    INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada)
    VALUES (100, 1, 2, 1, 20.5, TO_DATE('26-02-2023 21:02:00', 'dd-mm-yyyy hh24:mi:ss'), 0);
END;

-- Bloco anónimo para realizar uma alteração à operação agrícola do tipo UPDATE e testar o trigger que regista uma pista de auditoria da operação agrícola.
DECLARE
BEGIN
    UPDATE operacaoAgricola
    SET codFatorProducao = 2, quantidade = 100
    WHERE codOperacaoAgricola = 3;
END;

-- Bloco anónimo para realizar uma alteração à operação agrícola do tipo DELETE e testar o trigger que regista uma pista de auditoria da operação agrícola.
DECLARE
BEGIN
    DELETE
    FROM operacaoAgricola
    WHERE codOperacaoAgricola = 3;
END;

------------------------------------------------------------------------------------------------------------------------
--3. É implementado um processo de consulta de pistas de auditoria simples e eficaz.

-- Procedimento para listar todas pistas de auditoria.
CREATE OR REPLACE PROCEDURE prcUS213ListarPistasAuditoria IS

    CURSOR curPistaAuditoriaOperacao IS
        SELECT p.codPistaAuditoriaOperacao, p.codOperacaoAgricola, t.descricao, p.codUtilizador, p.dataAlteracao
        FROM pistaAuditoriaOperacao p
        INNER JOIN tipoAlteracao t ON t.codTipoAlteracao = p.codTipoAlteracao
        ORDER BY p.dataAlteracao DESC;

    vCodPistaAuditoriaOperacao pistaAuditoriaOperacao.codPistaAuditoriaOperacao%TYPE;
    vCodOperacaoAgricola pistaAuditoriaOperacao.codOperacaoAgricola%TYPE;
    vDescricao tipoAlteracao.descricao%TYPE;
    vCodUtilizador pistaAuditoriaOperacao.codUtilizador%TYPE;
    vDataAlteracao pistaAuditoriaOperacao.dataAlteracao%TYPE;

BEGIN
    OPEN curPistaAuditoriaOperacao;
    LOOP
        FETCH curPistaAuditoriaOperacao INTO vCodPistaAuditoriaOperacao, vCodOperacaoAgricola, vDescricao, vCodUtilizador, vDataAlteracao;
        EXIT WHEN curPistaAuditoriaOperacao%notfound;
        dbms_output.put_line('Código da pista de auditoria da operação: ' || vCodPistaAuditoriaOperacao);
        dbms_output.put_line('Código da operação agrícola: ' || vCodOperacaoAgricola);
        dbms_output.put_line('Tipo de alteração: ' || vDescricao);
        dbms_output.put_line('Código de utilizador: ' || vCodUtilizador);
        dbms_output.put_line('Data de alteração: ' || TO_CHAR(vDataAlteracao, 'dd-mm-yyyy hh24:mi:ss'));
        dbms_output.put_line('------------------------------------------------');
    END LOOP;
    CLOSE curPistaAuditoriaOperacao;
END;

-- Bloco anónimo para testar o procedimento para listar todas pistas de auditoria.
CALL prcUS213ListarPistasAuditoria();