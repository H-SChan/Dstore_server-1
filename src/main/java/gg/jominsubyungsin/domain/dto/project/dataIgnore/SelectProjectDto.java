package gg.jominsubyungsin.domain.dto.project.dataIgnore;

import gg.jominsubyungsin.domain.dto.user.dataIgnore.SelectUserDto;
import gg.jominsubyungsin.domain.entity.FileEntity;
import gg.jominsubyungsin.domain.entity.ProjectEntity;
import gg.jominsubyungsin.domain.entity.TagEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SelectProjectDto {
	private Long id;
	private String title;
	private FileEntity mainPhoto;
	private Date createAt;
	private List<SelectUserDto> users;
	private List<String> tags = new ArrayList<>();

	public SelectProjectDto(ProjectEntity projectEntity, List<SelectUserDto> selectUserDto) {
		this.id = projectEntity.getId();
		this.title = projectEntity.getTitle();
		this.createAt = projectEntity.getCreateAt();
		this.mainPhoto = projectEntity.getFiles().get(0);
		for(TagEntity tagEntity: projectEntity.getTags()){
			tags.add(tagEntity.getTag());
		}
		this.users = selectUserDto;
	}
}
