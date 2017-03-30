package com.exxeta.iss.sonar.esql.tree.impl.expression;

import java.util.Iterator;

import com.exxeta.iss.sonar.esql.api.symbols.Type;
import com.exxeta.iss.sonar.esql.api.symbols.TypeSet;
import com.exxeta.iss.sonar.esql.api.tree.FieldReferenceTree;
import com.exxeta.iss.sonar.esql.api.tree.PathElementTree;
import com.exxeta.iss.sonar.esql.api.tree.Tree;
import com.exxeta.iss.sonar.esql.api.tree.Tree.Kind;
import com.exxeta.iss.sonar.esql.api.tree.expression.ExpressionTree;
import com.exxeta.iss.sonar.esql.api.tree.expression.InExpressionTree;
import com.exxeta.iss.sonar.esql.api.tree.symbols.type.TypableTree;
import com.exxeta.iss.sonar.esql.api.visitors.DoubleDispatchVisitor;
import com.exxeta.iss.sonar.esql.tree.impl.EsqlTree;
import com.exxeta.iss.sonar.esql.tree.impl.SeparatedList;
import com.exxeta.iss.sonar.esql.tree.impl.declaration.FieldReferenceTreeImpl;
import com.exxeta.iss.sonar.esql.tree.impl.lexical.InternalSyntaxToken;
import com.google.common.base.Functions;
import com.google.common.collect.Iterators;

public class InExpressionTreeImpl extends EsqlTree implements InExpressionTree, TypableTree {

	private FieldReferenceTreeImpl fieldReference;
	private InternalSyntaxToken inKeyword;
	private SeparatedList<Tree> argumentList;

	private TypeSet types = TypeSet.emptyTypeSet();

	public InExpressionTreeImpl(FieldReferenceTreeImpl fieldReference, InternalSyntaxToken inKeyword,
			SeparatedList<Tree> argumentList) {
		super();
		this.fieldReference = fieldReference;
		this.inKeyword = inKeyword;
		this.argumentList = argumentList;
	}

	@Override
	public FieldReferenceTree fieldReference() {
		return fieldReference;
	}

	@Override
	public InternalSyntaxToken inKeyword() {
		return inKeyword;
	}

	@Override
	public SeparatedList<Tree> argumentList() {
		return argumentList;
	}

	@Override
	public TypeSet types() {
		return types;
	}

	@Override
	public void accept(DoubleDispatchVisitor visitor) {
		visitor.visitInExpression(this);

	}

	@Override
	public Kind getKind() {
		return Kind.IN_EXPRESSION;
	}

	@Override
	public Iterator<Tree> childrenIterator() {
		return Iterators.concat(Iterators.forArray(fieldReference, inKeyword), argumentList.elementsAndSeparators(Functions.<Tree>identity()));
	}

	@Override
	public void add(Type type) {
		types.add(type);
	}

}