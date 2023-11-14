import { View, Text, Image } from "react-native";
import { styles } from "../styles/styles";

export default function Card(props) {
    const maxResidents = 3
    props.residents.sort()
    var residents = props.residents.length <= maxResidents ? props.residents :
                    [...props.residents.slice(0, maxResidents), "+" + (props.residents.length - maxResidents)]
        
    return (
        <View style={styles.card}>
            <View
                style={{width: "50%"}}>
                <Text style={styles.cardLocal}>{props.condominio}</Text>
                <Text style={styles.cardTitle}>{props.bloco} - {props.num}</Text>
                <div>
                    <Image
                        source={{uri: props.owner.image}}
                        style={styles.cardImage}
                    />
                    <Text>{props.owner.name}</Text>
                </div>
            </View>
            <View
                style={{width: "50%", display: "flex", alignItems: "flex-start"}}>
                {residents.map((resident, index) => (
                    <Text
                     key={index}
                     style={styles.cardNames}
                    >{resident}</Text>
                ))}
            </View>
        </View>
    );
}