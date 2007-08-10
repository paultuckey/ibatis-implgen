<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">

package abc;

import abc;

public interface <xsl:value-of select="sqlMap/@namespace"/> {

    <xsl:for-each select="sqlMap/*">

	<xsl:if test="name(current()) = 'cacheModel'">
    @cacheModel(id="<xsl:value-of select="@id"/>", hours="<xsl:value-of select="flushInterval/@hours"/>" type="<xsl:value-of select="@type"/>")

    </xsl:if><xsl:if test="name(current()) = 'resultMap'">
    @resultMap(id="<xsl:value-of select="@id"/>")

	</xsl:if><xsl:if test="name(current()) = 'parameterMap'">

    @parameterMap(id="<xsl:value-of select="@id"/>")

    </xsl:if><xsl:if test="name(current()) = 'procedure'">

    @Procedure
    public void <xsl:value-of select="@id"/>() /*sql{
		<xsl:value-of select="current()"/>
    }*/;

    </xsl:if>
	</xsl:for-each>

}    

    </xsl:template>
</xsl:stylesheet>
