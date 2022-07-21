import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./components/users/Home";
import Shop from "./components/users/Shop";
import ProductDetail from "./components/users/ProductDetail";
import Login from "./components/users/Login";
import AdminHome from "./components/admin/pages/home/AdminHome";
import AdminLogin from "./components/admin/pages/Login/AdminLogin";
import UserDetail from "./components/admin/pages/userdetail/UserDetail";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route exact path="/" element={<Home/>}/>
                <Route path="/shop" element={<Shop/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/product-detail/:id" element={<ProductDetail/>}/>
                <Route exact path="/admin" element={<AdminHome/>}/>
                <Route exact path="/admin/login" element={<AdminLogin/>}/>
                <Route path="/admin/users/:id" element={<UserDetail/>}/>
                <Route path="/admin/users" element={<UserDetail/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
