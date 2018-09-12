/* (C) Copyright 2017-2018 Laran Evans */
package com.laranevans.algorithms.greedy;

import com.laranevans.algorithms.BaseAlgorithm;

public class FisherYatesShuffle<V extends Comparable> extends BaseAlgorithm {

	public void shuffle(V[] a) {
		if (a.length <= 1) {
			return;
		}

		for (int i = 0; i < a.length - 1; i++) {
			int r = getRandom(i, a.length - 1);
			if (r != i) {
				swap(a, i, r);
			}
		}
	}

}
