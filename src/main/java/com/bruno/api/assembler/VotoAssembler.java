package com.bruno.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bruno.api.model.VotoModel;
import com.bruno.api.model.input.VotoInput;
import com.bruno.domain.model.Voto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class VotoAssembler {
	
	private ModelMapper modelMapper;
	
	public VotoModel toModel(Voto voto) {
		return modelMapper.map(voto, VotoModel.class);
	}
	
	public Voto toEntity(VotoInput votoInput) {
		return modelMapper.map(votoInput, Voto.class);
	}
}
