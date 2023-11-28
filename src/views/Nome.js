import { View, TextInput } from "react-native";
import { styles } from "../styles/styles";
import Text from "../components/Text";
import ThemeButton from "../components/ThemeButton";

export default function Senha(props) {
    return (<>
        <ThemeButton />
        <View style={{height:" 100%", alignItems: "center", justifyContent: "center", gap: "3%", paddingBottom: "20%"}}>
            <Text style={{fontSize: "xxx-large"}}>Novo por aqui?</Text>
            <TextInput placeholder="digite seu nome" style={styles.textInput} />
        </View>
    </>);
}