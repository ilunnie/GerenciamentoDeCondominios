import { View, TextInput, Pressable } from "react-native";
import { styles } from "../styles/styles";
import { useState, useContext } from "react";
import { UtilsContext } from "../config/context";
import api from "../config/api";
import Text from "../components/Text";
import ThemeButton from "../components/ThemeButton";

export default function Senha(props) {
    const [error, setError] = useState(false)
    const [password, setPassword] = useState("")
    const { utils, setUtils } = useContext(UtilsContext)

    async function submit() {
        const body = {
            identity: utils.identity,
            password: password
        };

        try {
            const response = await api.post("user/login", body, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            console.log(response.data)
            localStorage.setItem("token", response.data)
            props.navigation.navigate("Home");
        } catch (error) {
            if (error.response?.data.status == 401) {
                console.error("Senha incorreta")
                setError(true)
            } else {
                props.navigation.navigate("Entrar");
                console.error(error)
            }
        }
    }

    return (<>
        <ThemeButton />
        <View style={{ height: " 100%", alignItems: "center", justifyContent: "center", gap: "3%", paddingBottom: "20%" }}>
            <Text style={{ fontSize: "xxx-large" }}>Senha</Text>
            <TextInput
                value={password}
                secureTextEntry={true}
                onChangeText={value => setPassword(value)}
                placeholder="digite sua senha"
                style={error? {...styles.textInput, borderColor: "red", color: "red"} : styles.textInput}
            />
            <Pressable style={styles.nextButton}
                onPress={() => submit()}
            ><Text>Entrar</Text></Pressable>
        </View>
    </>);
}