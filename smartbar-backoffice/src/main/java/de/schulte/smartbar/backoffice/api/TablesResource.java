package de.schulte.smartbar.backoffice.api;

import java.net.URI;
import java.util.List;

import de.schulte.smartbar.backoffice.api.model.Table;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public class TablesResource implements TablesApi {

    @Override
    public Response tablesGet() {
        return Response.ok(List.of(new Table().name("chile"))).build();
    }

    @Override
    public Response tablesPost(@Valid Table table) {
        return Response.created(URI.create("todo")).build();
    }

    @Override
    public Response tablesTableIdDelete(String tableId) {
        return Response.ok().build();
    }

    @Override
    public Response tablesTableIdGet(String tableId) {
        return Response.ok(new Table().name("chile")).build();
    }

    @Override
    public Response tablesTableIdPut(String tableId, @Valid Table table) {
        return Response.ok().build();
    }

}
