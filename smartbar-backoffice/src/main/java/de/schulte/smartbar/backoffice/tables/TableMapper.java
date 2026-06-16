package de.schulte.smartbar.backoffice.tables;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import de.schulte.smartbar.backoffice.api.model.ApiTable;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface TableMapper {

    @Mapping(target = "id", ignore = true)
    Table mapToTable(ApiTable apiTable, @MappingTarget Table table);

    ApiTable mapToApiTable(Table table);
}
