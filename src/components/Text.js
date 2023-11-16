import { StyleSheet } from "react-native";
import { Text as TextOrigin } from "react-native";
import { styles } from "../styles/styles";

export default function Text(props) {
    return (
        <TextOrigin
            style={StyleSheet.flatten([styles.text, props.style])}
        >
            {props.children}
        </TextOrigin>
    );
}