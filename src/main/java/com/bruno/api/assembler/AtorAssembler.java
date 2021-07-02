package com.bruno.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bruno.api.model.AtorModel;
import com.bruno.domain.model.Ator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AtorAssembler {

	private ModelMapper modelMapper;
	
	public AtorModel toModel(Ator ator) {
		return modelMapper.map(ator, AtorModel.class);
	}
	
	public List<AtorModel> toCollectionModel(List<Ator> ocorrencias) {
		return ocorrencias.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
}
