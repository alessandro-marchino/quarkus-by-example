package de.schulte.smartbar.backoffice.articles;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import de.schulte.smartbar.backoffice.api.model.ApiArticle;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface ArticlesMapper {

	@Mapping(source = "pictureBase64", target = "picture")
	ApiArticle mapFrom(Article article);
}
