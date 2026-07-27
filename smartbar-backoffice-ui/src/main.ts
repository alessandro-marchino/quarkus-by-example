import './assets/main.css'

import App from './App.vue'
import KeycloackService from './KeycloackService'
import { createApp } from 'vue'

const keyclock = new KeycloackService()
keyclock.authenticate(() => {
    const app = createApp(App)
    app.provide('keyclock', keyclock)
    app.mount('#app')
})

