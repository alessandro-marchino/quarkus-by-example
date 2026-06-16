package de.schulte.smartbar.backoffice.categories;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import de.schulte.smartbar.backoffice.api.model.ApiCategory;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category mapToCategory(ApiCategory apiCategory, @MappingTarget Category category);

    ApiCategory mapToApiCategory(Category category);
}
