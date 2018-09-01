<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Fornecedores</title>
                <link rel="stylesheet" type="text/css" href="estilo.css"/>
                <script type="text/javascript" src="funcoes.js"/> 
            </head>

            <body>
                <div>
                    
                    <h1>Fornecedores do Governo Federal do Brasil <img src="imagens/acesso-a-informacao.png" /></h1>
                </div>
                
                <div class="menu">
                    <div class="btn_menu">
                        <button onclick="mostraEsconde('empresas', 1); mostraEsconde('pf', 0)">Pessoa Juridica</button>
                    </div>
                    <div class="btn_menu">
                        <button onclick="mostraEsconde('empresas', 0); mostraEsconde('pf', 1)">Pessoa Física</button>
                    </div>
                    
                </div>
                
                <!-- empresas -->
                <div id="empresas">
                    <table border="1">
                        <tr>
                            <th>Empresa</th>
                            <th>CNPJ</th>
                            <th>UF</th>
                        </tr>
                        <xsl:for-each select="resource/_embedded/resource">
                            <xsl:if test="cnpj != ''">
                                
                                <tr>
                                    <td>
                                        <xsl:value-of select="nome" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="cnpj" />
                                    </td>
                            
                                    <td>
                                        <xsl:value-of select="uf" />
                                    </td>
                                </tr>
                                
                            </xsl:if>
                        </xsl:for-each>
                    </table>
                </div>
                
                <br/>
                
                <!-- pessoas fisicas -->
                <div id="pf">
                    <table border="1">
                        <tr>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>UF</th>
                        </tr>
                        <xsl:for-each select="resource/_embedded/resource">
                            <xsl:if test="cpf != ''">
                                <tr>
                                    <td>
                                        <xsl:value-of select="nome" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="cpf" />
                                    </td>
                            
                                    <td>
                                        <xsl:value-of select="uf" />
                                    </td>
                                </tr>
                            </xsl:if>
                        </xsl:for-each>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
