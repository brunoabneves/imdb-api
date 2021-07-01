package com.bruno.api.assembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bruno.api.model.FilmeModel;
import com.bruno.api.model.input.FilmeInput;
import com.bruno.domain.model.Filme;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class FilmeAssembler {
	
	private ModelMapper modelMapper;
	
	public FilmeModel toModel(Filme filme) {
		return modelMapper.map(filme, FilmeModel.class);
	}
	
	public List<FilmeModel> toCollectionModel(List<Filme> filmes) {
		return filmes.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Filme toEntity(FilmeInput filmeInput) {
		return modelMapper.map(filmeInput, Filme.class);
	}

	public Filme toEntity(Optional<Filme> filme) {

		return modelMapper.map(filme, Filme.class);
	}
}
