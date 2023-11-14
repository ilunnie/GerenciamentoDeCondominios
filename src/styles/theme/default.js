import { StyleSheet } from "react-native";

var default_theme;
export default default_theme = StyleSheet.create({
    card : {
        display: "flex",
        flexDirection: "row",
    },

    cardLocal : {
        fontStyle: "italic",
        fontSize: "small",
        opacity: "0.6",
        marginBottom: "-5px"
    },

    cardTitle : {
        fontWeight: "bolder",
        fontSize: "larger"
    },

    cardImage : {
        borderRadius: "50%",
        borderWidth: "1",
        borderColor: "black"
    },

    cardNames : {
        marginBottom: "-5px"
    },
})