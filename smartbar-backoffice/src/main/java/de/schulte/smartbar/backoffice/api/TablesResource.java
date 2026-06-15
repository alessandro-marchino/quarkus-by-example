package de.schulte.smartbar.backoffice.api;

import java.net.URI;
import java.util.List;

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
        return Response.ok(List.of(tablesService.get())).build();
    }

    @Override
    public Response tablesPost(@Valid ApiTable apiTable) {
        final Table table = new Table();
        table.setName(apiTable.getName());
        table.setSeatCount(apiTable.getSeatCount());
        table.setActive(apiTable.getActive());
        final Table persistedTable = tablesService.persist(table);
        return Response.created(URI.create("/tables/" + persistedTable.getId())).build();
    }

    @Override
    public Response tablesTableIdDelete(String tableId) {
        return Response.ok().build();
    }

    @Override
    public Response tablesTableIdGet(String tableId) {
        return Response.ok(tablesService.get()).build();
    }

    @Override
    public Response tablesTableIdPut(String tableId, @Valid ApiTable table) {
        return Response.ok().build();
    }

}
