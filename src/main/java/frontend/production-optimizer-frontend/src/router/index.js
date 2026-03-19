import { createRouter, createWebHistory } from "vue-router"

import ProductsView from "../views/ProductsView.vue"
import UnitsView from "../views/UnitsView.vue"
import RawMaterialsView from "../views/RawMaterialsView.vue"
import ProductCompositionView from "../views/ProductCompositionView.vue"
import ProductionView from "../views/ProductionView.vue"
import DashboardView from "../views/DashboardView.vue"
import LoginView from "../views/LoginView.vue"

const routes = [
    { path: "/", component: DashboardView },
    {
        path: "/units",
        name: "units",
        component: UnitsView
    },
    {
        path: "/raw-materials",
        component: RawMaterialsView
    },
    {
        path: "/product-compositions",
        component: ProductCompositionView
    },
    {
        path: "/production",
        component: ProductionView
    },
    { path: "/products", component: ProductsView },
    {
        path: "/login",
        component: LoginView,
        meta: { hideHeader: true }
    }

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to, from, next) => {
    const isAuth = localStorage.getItem("auth")

    if (to.path !== "/login" && !isAuth) {
        next("/login")
    } else {
        next()
    }
})

export default router