package de.schulte.smartbar.backoffice.categories;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import de.schulte.smartbar.backoffice.api.model.ApiCategory;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface CategoriesMapper {

	@Mapping(target = "articles", ignore = true)
	@Mapping(target = "removeArticlesItem", ignore = true)
	ApiCategory mapToApiCategory(Category category);
}
