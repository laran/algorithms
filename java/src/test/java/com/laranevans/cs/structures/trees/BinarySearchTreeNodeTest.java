/* (C) Copyright 2017-2018 Laran Evans */
package com.laranevans.cs.structures.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@DisplayName("Binary Search Tree")
public class BinarySearchTreeNodeTest {

	BinarySearchTreeNode<Integer> rightRotated;
	BinarySearchTreeNode<Integer> leftRotated;

	@BeforeEach
	public void setup() {
		rightRotated = new BinarySearchTreeNode<>(10);
		rightRotated.insertAll(5, 15, 13, 18);

		leftRotated = new BinarySearchTreeNode<>(15);
		leftRotated.insertAll(10, 18, 5, 13);
	}

	private void validateRightRotated(BinarySearchTreeNode root) {
		assertThat(root.getValue(), is(equalTo(10)));
		assertThat(root.getLeft().getValue(), is(equalTo(5)));
		assertThat(root.getRight().getValue(), is(equalTo(15)));
		assertThat(root.getRight().getLeft().getValue(), is(equalTo(13)));
		assertThat(root.getRight().getRight().getValue(), is(equalTo(18)));
	}

	private void validateLeftRotated(BinarySearchTreeNode root) {
		assertThat(root.getValue(), is(equalTo(15)));
		assertThat(root.getLeft().getValue(), is(equalTo(10)));
		assertThat(root.getRight().getValue(), is(equalTo(18)));
		assertThat(root.getLeft().getLeft().getValue(), is(equalTo(5)));
		assertThat(root.getLeft().getRight().getValue(), is(equalTo(13)));
	}

	@Test
	public void shouldRotateLeftProperly() {
		validateRightRotated(rightRotated);
		validateLeftRotated((BinarySearchTreeNode) rightRotated.rotateLeft());
	}

	@Test
	public void shouldRotateRightProperly() {
		validateLeftRotated(leftRotated);
		validateRightRotated((BinarySearchTreeNode) leftRotated.rotateRight());
	}

	@Test
	public void shouldFindValueAtRoot() {
		BinarySearchTreeNode ten = rightRotated.searchDepthFirst(10);
		assertThat(ten.getLeft().getValue(), is(equalTo(5)));
		assertThat(ten.getRight().getValue(), is(equalTo(15)));
	}

	@Test
	public void shouldFindValueInLeftSubtree() {
		BinarySearchTreeNode ten = leftRotated.searchDepthFirst(5);
		assertThat(ten.getValue(), is(equalTo(5)));
		assertThat(ten.getLeft(), is(nullValue()));
		assertThat(ten.getRight(), is(nullValue()));
	}

	@Test
	public void shouldFindValueInRightSubtree() {
		BinarySearchTreeNode thirteen = rightRotated.searchDepthFirst(13);
		assertThat(thirteen.getValue(), is(equalTo(13)));
		assertThat(thirteen.getLeft(), is(nullValue()));
		assertThat(thirteen.getRight(), is(nullValue()));
	}

	@Test
	public void shouldNotFindAValueNotInTheTree() {
		assertThat(rightRotated.searchDepthFirst(45), is(nullValue()));
	}

	@Test
	public void shouldHandleTryingToFindANullValue() {
		assertThat(rightRotated.searchDepthFirst(null), is(nullValue()));
	}
}
