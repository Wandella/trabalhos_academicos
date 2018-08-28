<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : vida.xsl
    Created on : 28 de Agosto de 2018, 19:28
    Author     : samue
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>vida.xsl</title>
            </head>
            <body>
                
                <table border="1">
                    <tr bgcolor="#add8e6">
                        <th>Empresa</th>
                        <th>CNPJ</th>
                        <th>UF</th>
                    </tr>
                    <xsl:for-each select="resource/_embedded/resource">
                        <xsl:if test="cnpj != ''">
                            <xsl:if test="contains(nome,'BA')">
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
                        </xsl:if>
                    </xsl:for-each>
                </table>
                
                <br/>
                
                <table border="1">
                    <tr bgcolor="#add8e6">
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
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
