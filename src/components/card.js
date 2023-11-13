import { View } from "react-native";

export default function Card(props) {
    const maxResidents = 4
    var residents = props.residents.length <= maxResidents ? props.residents :
                    props.residents.slice(0, maxResidents)
    console.log(residents)
    return (
        <View>
            <View>
                
            </View>
            <View>

            </View>
        </View>
    );
}