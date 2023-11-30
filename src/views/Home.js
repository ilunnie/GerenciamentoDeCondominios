import api from "../config/api";

export default function Home(props) {
    const token = localStorage.getItem("token");

    api.post("jwt", { token: token }, {
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response) => {
        if(response.data?.isAdm == true) {
            props.navigation.navigate("Nav")
        }
    }).catch((error) => {
        console.error("Erro na verificação de usuário:", error);
        localStorage.removeItem("token");
        location.reload()
    });
}