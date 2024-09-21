# Convenções de nomenclatura #

**1. Geral**

Os nomes das tabelas e atributos devem ser iniciados com minúsculas e unidos sem espaços, seguindo o padrão camelCase.
Todos os termos devem estar em português, exceto alguns em que não há tradução apropriada para o português, estes devem ser curtos, descritivos, evitando ao máximo contradições.

**2. Tabelas e Atributos**

Os nomes das tabelas e atributos devem estar no singular. 

**Exemplos:**

**• Bons nomes**: tipoProduto, fichaTecnica, produto.

**• Maus nomes**: TiposDeProdutos, FICHA_TECNICA, PrOdUcTo.

**3. Restrições**

Ao definir um tipo de restrição, esta deve conter uma constraint que dependendo do tipo deve seguir o seguinte padrão:

• **NOT NULL:** nn[Nome da tabela][Atributo]

• **UNIQUE:** un[Nome da tabela][Atributo]

• **CHECK:** ck[Nome da tabela][Atributo]

Por exemplo, se for pretendido que o atributo “nome” da tabela “produto” seja NOT NULL, a constraint a criar deve ser chamada “nnProdutoNome”.

**4. Chave Primária**

Todas as constraint das primary keys devem seguir o padrão **pk[Nome da tabela][Atributo]**

Por exemplo, se for pretendido que o atributo “codIdentificacao” seja a chave primária da tabela
“produto”, a constraint a criar deve ser chamada “pkProdutoCodIdentificao”.

**5. Chave Primária Composta**

No caso de ser uma chave primária composta, o padrão a seguir é exatamente o mesmo que numa chave primária normal.

Por exemplo, se for pretendido que o atributo “codIdentificacao” e o atributo “idTipoProduto” que é uma chave estrangeira da tabela “tipoProduto” sejam a chave primária da tabela “produto”, a constraint a criar deve ser chamada “pkProdutoCodIdentificaoIdTipoProduto”.

**6. Chave Estrangeira**

Todas as constraint das foreign keys devem seguir o padrão **fk[Nome da tabela][Atributo]**

A título de exemplo, no caso da tabela “produto” ter um relacionamento com a tabela “tipoProduto”
através do atributo “codTipoProduto”, a constraint a criar deve ser “fkProdutoCodTipoProduto”.

**7. Código PL/SQL**

• **Funções**:
Todos os nomes das funções criadas devem seguir o padrão **fnc[USXXX][Descricao]**

• **Procedimentos**:
Todos os nomes dos procedimentos criados devem seguir o padrão **prc[USXXX][Descricao]**

• **Views**:
Todos os nomes das views criadas devem seguir o padrão **view[USXXX][Descricao]**