package com.gogools.collection;

import lombok.Data;
import lombok.NonNull;

@Data
public class Pair<L, R> {

	private L left;
	private R right;
	
	public Pair(@NonNull L left, @NonNull R right) {
		
		this.left = left;
		this.right = right;
	}
}
