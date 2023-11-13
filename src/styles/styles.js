import default_theme from './theme/default'
import dark_theme from './theme/dark'

var dark_theme_on = localStorage.getItem('dark_theme_on') == "true" ? true : false;

export function styleConcat(baseStyle, newStyle) {
    var concatenatedStyles = {};
    for (const key in baseStyle) {
        concatenatedStyles[key] = baseStyle[key];
    }
    for (const key in newStyle) {
        concatenatedStyles[key] = newStyle[key];
    }
    return concatenatedStyles;
}

function stylesConcat(baseStyle, newStyle) {
    var concatenatedStyles = {};

    for (const key in baseStyle) {
        concatenatedStyles[key] = baseStyle[key];
    }
    
    for (const keybase in baseStyle) {
        for (const keynew in newStyle) {
            if(baseStyle[keynew] == undefined)
                concatenatedStyles[keynew] = newStyle[keynew]
            else
                concatenatedStyles[keynew] = styleConcat(baseStyle[keynew], newStyle[keynew])
        }
    }

    return concatenatedStyles;
}

export var styles = dark_theme_on ? stylesConcat(default_theme, dark_theme) : default_theme
export function setTheme() {
    dark_theme_on = !dark_theme_on
    localStorage.setItem('dark_theme_on', dark_theme_on)
    window.location.reload()
}