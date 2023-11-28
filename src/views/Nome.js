import { View, TextInput, Pressable } from "react-native";
import { styles } from "../styles/styles";
import { useState, useContext } from "react";
import { UtilsContext } from "../config/context";
import Text from "../components/Text";
import ThemeButton from "../components/ThemeButton";

export default function Nome(props) {
    const [error, setError] = useState(false)
    const [name, setName] = useState("");
    const { utils, setUtils } = useContext(UtilsContext)

    function submit() {
        if(name == "") {
            setError(true)
            return
        }
        props.navigation.navigate("Cadastrar");
    }

    function setValue(value) {
        setName(value)
        setUtils({...utils, name: value})
    }

    return (<>
        <ThemeButton />
        <View style={{height:" 100%", alignItems: "center", justifyContent: "center", gap: "3%", paddingBottom: "20%"}}>
            <Text style={{fontSize: "xxx-large"}}>Novo por aqui?</Text>
            <TextInput
                value={name}
                onChangeText={value => setValue(value)}
                placeholder="digite seu nome"
                style={error? {...styles.textInput, borderColor: "red", color: "red"} : styles.textInput}
            />
            <Pressable style={styles.nextButton}
                onPress={() => submit()}
            ><Text>Continuar</Text></Pressable>
        </View>
    </>);
}