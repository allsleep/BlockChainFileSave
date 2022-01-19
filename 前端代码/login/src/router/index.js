import VueRouter from "vue-router"
import Login from "../components/Login"
import Signup from "../components/Signup"

export default new VueRouter({
    routes: [
        {
            path: "/login",
            component: Login
        },
        {
            path: "/signup",
            component: Signup
        },
        { 
            path: '*', 
            redirect: '/login' 
        }
    ]
})