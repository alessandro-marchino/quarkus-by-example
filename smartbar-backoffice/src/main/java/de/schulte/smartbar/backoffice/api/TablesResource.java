package de.schulte.smartbar.backoffice.api;

import java.net.URI;
import java.util.Optional;

import de.schulte.smartbar.backoffice.api.model.ApiTable;
import de.schulte.smartbar.backoffice.entity.Table;
import de.schulte.smartbar.backoffice.service.TablesService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public class TablesResource implements TablesApi {

    private final TablesService tablesService;

    @Inject
    public TablesResource(TablesService tablesService) {
        this.tablesService = tablesService;
    }

    @Override
    public Response tablesGet() {
        return Response.ok(tablesService.listAll().stream().map(this::mapTableToApiTable).toList()).build();
    }

    @Override
    public Response tablesPost(@Valid ApiTable apiTable) {
        final Table table = new Table();
        mapApiTableToTable(apiTable, table);
        final Table persistedTable = tablesService.persist(table);
        return Response.created(URI.create("/tables/" + persistedTable.getId())).build();
    }

    @Override
    public Response tablesTableIdDelete(Long tableId) {
        final Optional<Table> table = tablesService.deleteById(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }

    @Override
    public Response tablesTableIdGet(Long tableId) {
        final Optional<Table> table = tablesService.getById(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapTableToApiTable(table.get())).build();
    }

    @Override
    public Response tablesTableIdPut(Long tableId, @Valid ApiTable apiTable) {
        final Optional<Table> existingTable = tablesService.getById(tableId);
        if (existingTable.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Table table = existingTable.get();
        mapApiTableToTable(apiTable, table);
        tablesService.update(table);
        return Response.ok().build();
    }

    private ApiTable mapTableToApiTable(Table table) {
        return new ApiTable()
            .id(table.getId())
            .active(table.getActive())
            .name(table.getName())
            .seatCount(table.getSeatCount());
    }
    private void mapApiTableToTable(ApiTable apiTable, Table table) {
        table.setActive(apiTable.getActive());
        table.setName(apiTable.getName());
        table.setSeatCount(apiTable.getSeatCount());
    }

}
