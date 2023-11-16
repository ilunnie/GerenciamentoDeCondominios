import { StyleSheet } from "react-native";
import default_theme from './theme/default'
import dark_theme from './theme/dark'

var dark_theme_on = Boolean(localStorage.getItem('dark_theme_on'));

function Compose(style1, style2) {
    var styles = style1
    for(const key in style2) {
        styles[key] = StyleSheet.flatten(styles[key], style2[key])
    }
    return styles
}

export const styles = dark_theme_on ? Compose(default_theme, dark_theme) : default_theme
export function setTheme() {
    dark_theme_on = !dark_theme_on
    localStorage.setItem('dark_theme_on', dark_theme_on)
    window.location.reload()
}