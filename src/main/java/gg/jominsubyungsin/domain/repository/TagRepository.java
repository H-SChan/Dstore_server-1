package gg.jominsubyungsin.domain.repository;

import gg.jominsubyungsin.domain.entity.TagEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
	Optional<TagEntity> findByTag(String tag);
}
