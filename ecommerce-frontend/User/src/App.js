import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Navbar from "./components/users/Navbar";
import Header from "./components/users/Header";
import Section from "./components/users/Section";
import Footer from "./components/users/Footer";

function App() {
    return (
        <div>
            <Navbar/>
            <Header/>
            <Section>
            </Section>
            <Footer/>
        </div>

    );
}

export default App;
