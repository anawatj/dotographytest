package com.anawat.dotography.exam.repository;

import java.io.Serializable;
import java.util.List;

import com.anawat.dotography.exam.domain.IDomain;

public interface Repository<E extends IDomain<K> ,K extends Serializable> {

	
	
			List<E> findAll();
			E findByKey(K id);
			E save(E entity);
			void remove(K id);
			
}
