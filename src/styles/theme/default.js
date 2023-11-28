import { faBlackTie } from "@fortawesome/free-brands-svg-icons";
import { StyleSheet } from "react-native";

var default_theme;
export default default_theme = StyleSheet.create({
    text: {
        color: "black",
        whiteSpace: "nowrap",
        overflow: "hidden",
        textOverflow: "ellipsis"
    },

    card : {
        display: "flex",
        flexDirection: "row",
        padding: "10px",
        borderWidth: "1px",
        borderRadius: "10px",
        backgroundColor: "#FAFAFA"
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
        width: "20px",
        height: "20px",
        borderRadius: "50%",
        borderWidth: "1px"
    },

    cardNames : {
        marginBottom: "-5px"
    },

    textInput : {
        color: "black",
        backgroundColor: "#FAFAFA",
        borderWidth: "1px",
        borderColor: "black",
        padding: "1%"
    },

    icon: {
        color: "black"
    },

    nextButton: {
        padding: "2%",
        paddingHorizontal: "3%",
        borderWidth: "1px",
        borderColor: "black",
    },
})