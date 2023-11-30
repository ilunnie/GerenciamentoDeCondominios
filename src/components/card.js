import { View, Image } from "react-native";
import { styles } from "../styles/styles";
import Text from "./Text";

export default function Card(props) {
    const maxResidents = 3
    try {
        props.residents.sort()
        var residents = props.residents.length <= maxResidents ? props.residents :
            [...props.residents.slice(0, maxResidents), "+" + (props.residents.length - maxResidents)]
    } catch (error) {
        var residents = []
    }
    console.log(props.owner)
    return (
        <View style={props.style ? [...styles.card, ...props.style] : styles.card}>
            <View
                style={{ width: "50%" }}>
                <Text style={styles.cardLocal}>{props.condominio.name}</Text>
                <Text style={styles.cardTitle}>Bloco {props.bloco.name} - N°{props.num}</Text>
                {props.owner ? (
                    <div style={{ display: "flex", gap: "5px" }}>
                        <Image source={{ uri: props.owner.image }} style={styles.cardImage} />
                        <Text>{props.owner.name}</Text>
                    </div>
                ) : (
                    <Text>Desocupado</Text>
                )}
            </View>
            <View
                style={{ width: "50%", display: "flex", alignItems: "flex-start" }}>
                {residents.map((resident, index) => (
                    <Text
                        key={index}
                        style={styles.cardNames}
                    >{resident.name}</Text>
                ))}
            </View>
        </View>
    );
}