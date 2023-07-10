package com.example.demo.converters;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E,D> {
	
	public abstract E fromDto(D dto);
	public abstract D fromEntity(E entity);
	
	public List<E> fromDto(List<D> dtos){
		return dtos.stream().map(d -> fromDto(d)).collect(Collectors.toList());
	}
	
	public List<D> fromEntity(List<E> entities){
		return entities.stream().map(e->fromEntity(e)).collect(Collectors.toList());
	}

}
