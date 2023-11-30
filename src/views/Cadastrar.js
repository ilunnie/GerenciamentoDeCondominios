import { View, TextInput, Pressable } from "react-native";
import { styles } from "../styles/styles";
import { useState, useContext } from "react";
import { UtilsContext } from "../config/context";
import api from "../config/api";
import Text from "../components/Text";
import ThemeButton from "../components/ThemeButton";

export default function Cadastrar(props) {
    const [error, setError] = useState(false)
    const [password, setPassword] = useState("");
    const [confirm, setConfirm] = useState("");
    const { utils, setUtils } = useContext(UtilsContext)

    async function submit(sendPassword) {
        if((sendPassword && (password == confirm)) || !sendPassword) {
            const body = {
                identity: utils.identity,
                password: sendPassword ? password : null,
                name: utils.name
            };

            await api.post("user", body, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            const response = await api.post("user/login", body, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            localStorage.setItem("token", response.data)
            location.reload()
        } else if (sendPassword) {
            setError(true)
        }
    }

    return (<>
        <ThemeButton />
        <View style={{ height: " 100%", alignItems: "center", justifyContent: "center", gap: "1vh", paddingBottom: "20%" }}>
            <Text style={{ fontSize: "xxx-large", marginBottom: "4%" }}>Por segurança</Text>
            <TextInput
                value={password}
                secureTextEntry={true}
                onChangeText={value => setPassword(value)}
                placeholder="digite sua senha"
                style={styles.textInput}
            />
            <TextInput
                value={confirm}
                secureTextEntry={true}
                onChangeText={value => setConfirm(value)}
                placeholder="confirme sua senha"
                style={error ? { ...styles.textInput, borderColor: "red", color: "red" } : styles.textInput}
            />
            <View style={{marginTop:"3%", gap: "1.5vh", alignItems: "center"}}>
                <Pressable style={styles.nextButton}
                    onPress={() => submit(true)}
                ><Text>Cadastrar</Text></Pressable>
                <Pressable
                    onPress={() => submit(false)}
                ><Text>Pular por enquanto</Text></Pressable>
            </View>
        </View>
    </>);
}