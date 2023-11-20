import { View, TextInput } from "react-native";
import { styles } from "../styles/styles";
import Text from "../components/Text";
import ThemeButton from "../components/ThemeButton";

export default function Senha(props) {
    return (<>
        <ThemeButton />
        <View style={{height:" 100%", alignItems: "center", justifyContent: "center", gap: "3%", paddingBottom: "20%"}}>
            <Text style={{fontSize: "xxx-large"}}>Senha</Text>
            <TextInput placeholder="digite sua senha" style={styles.textInput} />
        </View>
    </>);
}