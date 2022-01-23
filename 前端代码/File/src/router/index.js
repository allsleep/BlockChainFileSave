import VueRouter from "vue-router"
import FileUpload from '../components/body/FileUpload'
import Login from "../components/Login"
import Signup from "../components/Signup"
import Layout from "../components/Layout"
import UserInfo from "../components/body/UserInfo"
import PutFileResult from "../components/body/PutFileResult"

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
            component: Layout,
            children: [
                {
                    path: "",
                    component: UserInfo
                },
                {
                    path: "/fileup",
                    component: FileUpload,
                },
                {
                    path: "/userinfo",
                    component: UserInfo
                },
                {
                    path: "/putfileresult",
                    component: PutFileResult
                }
            ]
        },
        { 
            path: '*', 
            redirect: '/login' 
        }
    ]
})