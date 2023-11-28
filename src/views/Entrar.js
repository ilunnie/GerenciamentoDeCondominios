import { View, TextInput, Pressable } from "react-native";
import { styles } from "../styles/styles";
import { useState, useContext } from "react";
import { UtilsContext } from "../config/context";
import { verifyToken } from "../config/auth";
import api from "../config/api";
import Text from "../components/Text";
import ThemeButton from "../components/ThemeButton";

export default function Entrar(props) {
    verifyToken().then((tokenValido) => {
        if (tokenValido) {
            props.navigation.navigate("Nav");
        }
    });
    const [id, setId] = useState("");
    const { utils, setUtils } = useContext(UtilsContext)

    async function submit() {
        const body = {
            identity: id
        };

        try {
            const response = await api.post("user/login", body, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            localStorage.setItem("token", response.data)
            location.reload()
        } catch (error) {
            if (error.response.data.status == 500) {
                props.navigation.navigate("Nome");
            } else if (error.response.data.status == 401) {
                props.navigation.navigate("Senha");
            }
        }
    }

    function setValue(value) {
        setId(value)
        setUtils({...utils, identity: value})
    }

    return (<>
        <ThemeButton />
        <View style={{ height: " 100%", alignItems: "center", justifyContent: "center", gap: "1%", paddingBottom: "20%" }}>
            <Text style={{ fontSize: "xxx-large", marginBottom: "2%" }}>Entrar</Text>
            <TextInput
                value={id}
                onChangeText={value => setValue(value)}
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