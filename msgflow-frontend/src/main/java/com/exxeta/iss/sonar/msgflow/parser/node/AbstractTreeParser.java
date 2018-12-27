package com.exxeta.iss.sonar.msgflow.parser.node;

import static com.exxeta.iss.sonar.msgflow.parser.ParserUtils.getXPathString;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Element;

import com.exxeta.iss.sonar.msgflow.api.tree.Tree;

public class AbstractTreeParser<T extends Tree> {
	protected String getId(final Element nodeElement) throws XPathExpressionException {
		return nodeElement.getAttribute("xmi:id");
	}

	protected int getLocationX(final Element nodeElement) throws XPathExpressionException {
		return Integer.parseInt(nodeElement.getAttribute("location").split(",")[0]);
	}

	protected int getLocationY(final Element nodeElement) throws XPathExpressionException {
		return Integer.parseInt(nodeElement.getAttribute("location").split(",")[1]);
	}

	protected String getName(final Element nodeElement) throws XPathExpressionException {
		return getXPathString(nodeElement, "./translation/@string");
	}
}
