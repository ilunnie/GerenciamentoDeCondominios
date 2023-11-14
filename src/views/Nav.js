import Card from "../components/card";

export default function Nav(props) {
    const moradores = ["Jesus", "Jubileu", "Karen", "Joca", "Kowalsk", "Clovs"]
    const owner = {
        "name": "Jesus",
        "image": "https://thumbs.dreamstime.com/z/s%C3%ADmbolo-de-perfil-masculino-inteligente-retrato-estilo-desenho-animado-m%C3%ADnimo-166146967.jpg"
    }

    return (
        <Card
            condominio="sla das quantas"
            bloco="Teste kk"
            num="10"
            owner={owner}
            residents={moradores}
        />
    );
}