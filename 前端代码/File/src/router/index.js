import VueRouter from "vue-router"
import FileUpload from '../components/FileUpload'
import Login from "../components/Login"
import Signup from "../components/Signup"
import Layout from "../components/Layout"

export default new VueRouter({
    routes: [
        {
            path: "/login",
            component: Login
        },
        {
            path: "/login/signup",
            component: Signup
        },
        {
            path: "/layout",
            component: Layout
        },
        {
            path: "/layout/fileup",
            component: FileUpload
        },
        { 
            path: '*', 
            redirect: '/login' 
        }
    ]
})