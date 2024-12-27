package com.example.capital.mappers;

public interface Mapper<A, B> {
	B mapTo(A a);
	A mapFrom(B a);
}
