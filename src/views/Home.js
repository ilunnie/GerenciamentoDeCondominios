import api from "../config/api";

export default function Home(props) {
    const token = localStorage.getItem("token");

    api.post("jwt", { token: token }, {
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response) => {
        if(response.data?.isAdm == true) {
            //! É um adm (⌐■_■)
            props.navigation.navigate("Nav")
        } else {
            api.get("home/" + response.data?.identity)
                .then((homes) => {
                    if (homes.data.length > 0) {
                        //ToDo Fazer tela de morador
                        console.log("é um morador")
                    } else {
                        //Todo Fazer tela de visitante
                        console.log("é um visitante")
                    }
                }).catch((error) => {
                    console.error("User error", error)
                })
        }
    }).catch((error) => {
        console.error("Erro na verificação de usuário:", error);
        localStorage.removeItem("token");
        location.reload()
    });
}