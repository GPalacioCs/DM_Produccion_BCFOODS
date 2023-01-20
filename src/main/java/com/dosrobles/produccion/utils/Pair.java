package com.dosrobles.produccion.utils;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Pair<A, B> {
    private A first;
    private B second;
    
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return first.equals(pair.first) &&
                second.equals(pair.second);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
