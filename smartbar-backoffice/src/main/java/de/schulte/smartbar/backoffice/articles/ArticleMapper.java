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
    @Mapping(target = "category", ignore = true)
    Article mapToArticle(ApiArticle apiArticle, @MappingTarget Article article);

    @Mapping(target = "picture", source = "pictureBase64")
    ApiArticle mapToApiArticle(Article article);
}
