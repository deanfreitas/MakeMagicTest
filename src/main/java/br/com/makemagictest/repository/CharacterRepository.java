package br.com.makemagictest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.makemagictest.repository.model.CharacterSchool;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterSchool, Long>, CharacterRepositoryCustom {
}
