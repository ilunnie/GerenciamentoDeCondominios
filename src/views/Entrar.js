import { View, TextInput, Pressable } from "react-native";
import { styles } from "../styles/styles";
import { useState, useContext } from "react";
import { UtilsContext } from "../config/context";
import api from "../config/api";
import Text from "../components/Text";
import ThemeButton from "../components/ThemeButton";

export default function Entrar(props) {
    const [id, setId] = useState("");
    const {utils, setUtils} = useContext(UtilsContext)

    function submit() {
        var response = api.get('user/login', {"identity": id})
        console.log(response)
    }

    return (<>
        <ThemeButton />
        <View style={{height:" 100%", alignItems: "center", justifyContent: "center", gap: "1%", paddingBottom: "20%"}}>
            <Text style={{fontSize: "xxx-large", marginBottom: "2%"}}>Entrar</Text>
            <TextInput
                value={id}
                onChangeText={value => setId(value)}
                keyboardType="numeric"
                placeholder="digite seu cpf/cnpj"
                style={styles.textInput}
            />
            <Pressable style={styles.nextButton}
                onPress={() => submit()}
            ><Text>Entrar</Text></Pressable>
        </View>
    </>);
}