<script setup>
import {inject, onMounted, ref} from "vue";
import axios from "axios";

const tables = ref([]);

onMounted(() => {
  const keyclock = inject('keyclock')
  const token = keyclock.keycloak.token
  axios.get("/api/tables", {
    headers: {
      Authorization: `Bearer ${token}`
    }
  }).then(response => {
    response.data.forEach(element => tables.value.push(element))
  })
})
</script>

<template>
  <h1>Smartbar - Tables overview</h1>

  <table border="1">
    <thead>
      <tr>
        <th>Name</th>
        <th>Anzahl Plätze</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="table in tables">
        <td>{{ table.name }}</td>
        <td style="text-align:right;padding:0 5px">{{ table.seatCount }}</td>
      </tr>
    </tbody>
  </table>
</template>
