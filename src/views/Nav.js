import Card from "../components/card";

export default function Nav(props) {
    const moradores = ["Jesus", "Jubileu", "Karen", "Joca", "Kowalsk", "Clovs"]

    return (
        <Card
            title="Teste kk"
            owner="Jesus"
            residents={moradores}
        />
    );
}