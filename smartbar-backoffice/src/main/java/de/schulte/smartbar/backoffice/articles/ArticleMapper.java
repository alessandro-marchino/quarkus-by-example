package de.schulte.smartbar.backoffice.articles;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import de.schulte.smartbar.backoffice.api.model.ApiArticle;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pictureBase64", source = "picture")
    Article mapToArticle(ApiArticle apiArticle, @MappingTarget Article article);

    ApiArticle mapToApiArticle(Article article);
}
