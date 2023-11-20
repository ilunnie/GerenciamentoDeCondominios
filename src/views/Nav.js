import { View, TextInput, TouchableOpacity } from "react-native";
import { useState } from "react";
import { styles } from "../styles/styles";
import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import ThemeButton from "../components/ThemeButton";
import Card from "../components/Card";

export default function Nav(props) {
    const [search, setSeatch] = useState(localStorage.getItem('search') ? String(localStorage.getItem('search')) : "")
    const cards = [
        {
            local: {
                condominio: "sla das quantas",
                bloco: "A",
                num: 10
            },
            owner: {
                name: "Jesus",
                image: "https://thumbs.dreamstime.com/z/s%C3%ADmbolo-de-perfil-masculino-inteligente-retrato-estilo-desenho-animado-m%C3%ADnimo-166146967.jpg"
            },
            moradores: ["Jesus", "Jubileu", "Karen", "Joca", "Kowalsk", "Clovs"]
        },
        {
            local: {
                condominio: "condominio do eduardo",
                bloco: "B",
                num: 5
            },
            owner: {
                name: "Eduardo",
                image: "https://thumbs.dreamstime.com/z/s%C3%ADmbolo-de-perfil-masculino-inteligente-retrato-estilo-desenho-animado-m%C3%ADnimo-166146967.jpg"
            },
            moradores: ["Eduardo", "Mariana", "Lucas", "João", "Maria"]
        },
        {
            local: {
                condominio: "condominio do pedro",
                bloco: "C",
                num: 7
            },
            owner: {
                name: "Pedro",
                image: "https://thumbs.dreamstime.com/z/s%C3%ADmbolo-de-perfil-masculino-inteligente-retrato-estilo-desenho-animado-m%C3%ADnimo-166146967.jpg"
            },
            moradores: ["Pedro", "Paula", "Luciana", "Luis", "Larissa"]
        },
        {
            local: {
                condominio: "condominio do joão",
                bloco: "D",
                num: 3
            },
            owner: {
                name: "João",
                image: "https://thumbs.dreamstime.com/z/s%C3%ADmbolo-de-perfil-masculino-inteligente-retrato-estilo-desenho-animado-m%C3%ADnimo-166146967.jpg"
            },
            moradores: ["João", "Julia", "Lucas", "Larissa", "Lais"]
        }
    ]

    return (<>
        <ThemeButton />
        <View style={{ display: "flex", flexDirection: "row", gap: "5px", paddingInline: "50px" }}>
            <TextInput
                value={search}
                onChangeText={(value) => { setSeatch(value); localStorage.setItem('search', value); }}
                style={{...styles.textInput, marginTop: "10%"}} />
            <TouchableOpacity style={{ display: "flex", justifyContent: "flex-end" }}>
                <FontAwesomeIcon icon={faMagnifyingGlass} style={styles.icon} size={20} />
            </TouchableOpacity>
        </View>
        <View style={{ padding: "50px", gap: "15px" }}>
            {cards.map((card, index) => {
                return (
                    <Card key={index}
                        condominio={card.local.condominio}
                        bloco={card.local.bloco}
                        num={card.local.num}
                        owner={card.owner}
                        residents={card.moradores} />
                );
            })}
        </View>
    </>);
}