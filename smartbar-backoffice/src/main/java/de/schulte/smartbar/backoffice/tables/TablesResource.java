package de.schulte.smartbar.backoffice.tables;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.schulte.smartbar.backoffice.api.TablesApi;
import de.schulte.smartbar.backoffice.api.model.ApiTable;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

@Transactional
public class TablesResource implements TablesApi {

    private final TablesRepository tablesRepository;
    private final TableMapper tableMapper;

    @Inject
    public TablesResource(TablesRepository tablesRepository, TableMapper tableMapper) {
        this.tablesRepository = tablesRepository;
        this.tableMapper = tableMapper;
    }

    @Override
    public Response tablesGet() {
        final List<Table> tables = tablesRepository.listAll();
        return Response.ok(tables.stream().map(tableMapper::mapToApiTable).toList()).build();
    }

    @Override
    public Response tablesPost(@Valid ApiTable apiTable) {
        final Table table = new Table();
        tableMapper.mapToTable(apiTable, table);
        tablesRepository.persist(table);
        return Response.created(URI.create("/tables/" + table.getId())).build();
    }

    @Override
    public Response tablesTableIdDelete(Long tableId) {
        final Optional<Table> table = tablesRepository.findByIdOptional(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        tablesRepository.delete(table.get());
        return Response.ok().build();
    }

    @Override
    public Response tablesTableIdGet(Long tableId) {
        final Optional<Table> table = tablesRepository.findByIdOptional(tableId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(tableMapper.mapToApiTable(table.get())).build();
    }

    @Override
    public Response tablesTableIdPut(Long tableId, @Valid ApiTable apiTable) {
        final Optional<Table> existingTable = tablesRepository.findByIdOptional(tableId);
        if (existingTable.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Table table = existingTable.get();
        tableMapper.mapToTable(apiTable, table);
        return Response.ok().build();
    }

}
