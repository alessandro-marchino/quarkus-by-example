package de.schulte.smartbar.backoffice.tables;

import java.net.URI;
import java.util.Optional;

import de.schulte.smartbar.backoffice.api.TablesApi;
import de.schulte.smartbar.backoffice.api.model.ApiTable;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public class TablesResource implements TablesApi {

    private final TablesService tablesService;
    private final TableMapper tableMapper;

    @Inject
    public TablesResource(TablesService tablesService, TableMapper tableMapper) {
        this.tablesService = tablesService;
        this.tableMapper = tableMapper;
    }

    @Override
    public Response tablesGet() {
        return Response.ok(tablesService.listAll().stream().map(tableMapper::mapToApiTable).toList()).build();
    }

    @Override
    public Response tablesPost(@Valid ApiTable apiTable) {
        final Table table = new Table();
        tableMapper.mapToTable(apiTable, table);
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
        return Response.ok(tableMapper.mapToApiTable(table.get())).build();
    }

    @Override
    public Response tablesTableIdPut(Long tableId, @Valid ApiTable apiTable) {
        final Optional<Table> existingTable = tablesService.getById(tableId);
        if (existingTable.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Table table = existingTable.get();
        tableMapper.mapToTable(apiTable, table);
        tablesService.update(table);
        return Response.ok().build();
    }

}
